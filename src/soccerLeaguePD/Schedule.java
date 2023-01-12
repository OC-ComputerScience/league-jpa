package soccerLeaguePD;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import soccerLeagueDAO.GameDAO;
import soccerLeagueDAO.emDAO;

/**
 * A Schedule is a list of the Games played in a Session for a League.
 */
@Entity(name = "schedule")
public class Schedule {
	@Id // signifies the primary key
	@Column(name = "schedule_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long scheduleID;

	/**
	 * The League that organizes the Games for the Schedule
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "league_id", referencedColumnName = "league_id")
	private League league;

	/**
	 * The a collection of Games to be played
	 */
//	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "schedule", targetEntity = Game.class, fetch = FetchType.EAGER)
	private Collection<Game> games;

	/**
	 * A season is a set period of time in which the games are played.
	 */
	@Column(name = "season", nullable = false, length = 10)
	private String season;

	/**
	 * The date the Season starts.
	 */

	@Column(name = "start_date")
	private LocalDate startDate;

	public Schedule() {
//		games = new ArrayList<Game>();
	}

	public League getLeague() {
		return this.league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public String getSeason() {
		return this.season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public LocalDate getStartDate() {
		return this.startDate;
	}

	public String getStartDateString() {
		if (startDate != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			String dateString = getStartDate().format(dtf);
			return dateString;
		} else
			return "";

	}

	public void setStartDate(LocalDate date) {
		this.startDate = date;
	}

	public void setStartDate(String startDateString) {
		DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("M/d/yy");

		startDate = LocalDate.parse(startDateString, dateFormater);

	}

	public Collection<Game> getGames() {
		return games;
	}

	public void addGame(Game game) {
		GameDAO.addGame(game);
	}

	public void removeGame(Game game) {
		GameDAO.removeGame(game);
	}

	public Boolean isOkToRemove() {
		if (getGames().size() == 0)
			return true;
		else
			return false;
	}

	public void generateSchedule() {
		final Collection<Team> teamCol = getLeague().getTeams();
		Team[] teamArray = teamCol.toArray(new Team[teamCol.size()]);
		LocalDate[] gameDate = new LocalDate[(teamArray.length - 1) * 2 + 4];

		for (int d = 0; d < (teamArray.length - 1) * 2 + 4; d += 2) {

			LocalDate date = startDate.plusDays(d * 7);
			gameDate[d] = date;
			System.out.println(gameDate[d]);

			gameDate[d + 1] = gameDate[d].plusDays(4);
			System.out.println(gameDate[d + 1]);
		}
		for (int i = 0; i < teamArray.length; i++)
			for (int j = 0; j < teamArray.length; j++) {
				if (i != j) {
					LocalDate availibleGameDate = null;
					for (int d = 0; d < gameDate.length; d++) {
						if (!teamHasGame(teamArray[i], gameDate[d]) && !teamHasGame(teamArray[j], gameDate[d])) {
							availibleGameDate = gameDate[d];
							break;
						}

					}
					Game game = new Game(this, teamArray[i], teamArray[j], availibleGameDate);
					System.out.println("Add:" + game);
					emDAO.getEM().flush();
					emDAO.getEM().refresh(this);
				}
			}
	}

	public boolean teamHasGame(Team team, LocalDate gameDate) {
		if (getGames() == null || getGames().isEmpty())
			return false;
		boolean hasGame = false;
		for (Game g : getGames()) {
			if (team == g.getHomeTeam() || team == g.getVisitingTeam())
				if (g.getDate().compareTo((gameDate)) == 0) {
					hasGame = true;
					break;
				}
		}
		return hasGame;
	}

	@Override
	public String toString() {
		return getSeason();
	}
}

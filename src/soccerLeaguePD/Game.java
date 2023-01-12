package soccerLeaguePD;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "game")
public class Game implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id // signifies the primary key
	@Column(name = "game_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long gameId;

	/**
	 * The Schedule that the game is a part of
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "schedule_id", referencedColumnName = "schedule_id")
	private Schedule schedule;

	/**
	 * The Team that is the home Team for the Game,
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "hometeam_id", referencedColumnName = "team_id")
	private Team homeTeam;

	/**
	 * The Team that is the visiting team for the Game.
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "visitingteam_id", referencedColumnName = "team_id")
	private Team visitingTeam;

	/**
	 * The Location where the game is played.
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "location_id", referencedColumnName = "location_id")
	private Location location;

	/**
	 * The date the Game is played.
	 */

	@Column(name = "date")
	private LocalDate date;

	/**
	 * The number of goals Scored by the home Team in regulation.
	 */
	@Column(name = "home_team_score")
	private int homeTeamScore;

	/**
	 * The number of goals scored by the visiting Team in regulation.
	 */
	@Column(name = "visting_team_score")
	private int visitingTeamScore;

	/**
	 * The number of PK's scored by the home Team after a tie.
	 */
	@Column(name = "home_team_pk")
	private int homeTeamPK;

	/**
	 * The number of PK's scored by the visiting Team in a tie.
	 */
	@Column(name = "visiting_team_pk")
	private int visitingTeamPK;

	public Game() {

	}

	public Game(Schedule schedule, Team homeTeam, Team visitingTeam, LocalDate date) {
		this.homeTeam = homeTeam;
		this.visitingTeam = visitingTeam;
		this.date = date;
		this.location = homeTeam.getHomeLocation();
		this.schedule = schedule;
		schedule.addGame(this);
	}

	public Schedule getSchedule() {
		return this.schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Team getHomeTeam() {
		return this.homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Team getVisitingTeam() {
		return this.visitingTeam;
	}

	public void setVisitingTeam(Team visitingTeam) {
		this.visitingTeam = visitingTeam;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public LocalDate getDate() {
		return this.date;
	}

	public String getDateString() {
		if (date != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			String dateString = getDate().format(dtf);
			return dateString;
		} else
			return "";

	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setDate(String dateString) {
		DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("M/d/yyyy");

		this.date = LocalDate.parse(dateString, dateFormater);
	}

	public int getHomeTeamScore() {
		return this.homeTeamScore;
	}

	public void setHomeTeamScore(int homeTeamScore) {
		this.homeTeamScore = homeTeamScore;
	}

	public int getVisitingTeamScore() {
		return this.visitingTeamScore;
	}

	public void setVisitingTeamScore(int visitingTeamScore) {
		this.visitingTeamScore = visitingTeamScore;
	}

	public int getHomeTeamPK() {
		return this.homeTeamPK;
	}

	public void setHomeTeamPK(int homeTeamPK) {
		this.homeTeamPK = homeTeamPK;
	}

	public int getVisitingTeamPK() {
		return this.visitingTeamPK;
	}

	public void setVisitingTeamPK(int visitingTeamPK) {
		this.visitingTeamPK = visitingTeamPK;
	}

	public Boolean isOkToRemove() {
		if (getDate().isBefore(LocalDate.now()))
			return true;
		else
			return false;
	}

	public Team getWinner() {
		if (getHomeTeamScore() > getVisitingTeamScore())
			return getHomeTeam();
		if (getVisitingTeamScore() > getHomeTeamScore())
			return getVisitingTeam();
		if (getHomeTeamPK() > getVisitingTeamPK())
			return getHomeTeam();
		if (getVisitingTeamPK() > getHomeTeamPK())
			return getVisitingTeam();
		return null;
	}

	@Override
	public String toString() {
		return getDateString() + ":" + getHomeTeam().getName() + " vs. " + getVisitingTeam().getName();
	}
}

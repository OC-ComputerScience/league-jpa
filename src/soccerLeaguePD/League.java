package soccerLeaguePD;

import java.io.Serializable;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import soccerLeagueDAO.LocationDAO;
import soccerLeagueDAO.ScheduleDAO;
import soccerLeagueDAO.TeamDAO;
import soccerLeagueDM.LeagueDM;

/**
 * A League is an organization the a group of teams competes in.
 */
@Entity(name = "league")
public class League implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id // signifies the primary key
	@Column(name = "league_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long leagueID;
	/**
	 * A collection of the Teams that complete in the League
	 */
	@OneToMany(mappedBy = "league", targetEntity = Team.class, fetch = FetchType.EAGER)
	private Collection<Team> teams;

	/**
	 * A collection of Schedule of Games that the League manages
	 */
//	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "league", targetEntity = Schedule.class, fetch = FetchType.EAGER)
	private Collection<Schedule> schedules;

	/**
	 * The collection of Locations where Games are played.
	 */
//	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "league", targetEntity = Location.class, fetch = FetchType.EAGER)
	private Collection<Location> locations;

	/**
	 * The name of the League.
	 */
	@Column(name = "name", nullable = false, length = 50)
	private String name;

	/**
	 * The name of the commissioner who manages the league.
	 */
	@Column(name = "commissioner", nullable = false, length = 50)
	private String commissioner;

	public League() {
		// JPA does this for us whoop whoop
//		teams = new ArrayList<Team>();
//		schedules = new ArrayList<Schedule>();
//		locations = new ArrayList<Location>();
	}

	public Collection<Team> getTeams() {
		return this.teams;
	}

	public void addTeam(Team team) {
		TeamDAO.addTeam(team);

	}

	public void removeTeam(Team team) {
		TeamDAO.removeTeam(team);

	}

	public Collection<Schedule> getSchedules() {
		return this.schedules;
	}

	public void addSchedule(Schedule schedule) {
		ScheduleDAO.addSchedule(schedule);

	}

	public void removeSchedule(Schedule schedule) {
		ScheduleDAO.removeSchedule(schedule);

	}

	public Collection<Location> getLocations() {
		return this.locations;
	}

	public void addLocation(Location location) {
		LocationDAO.addLocation(location);

	}

	public void removeLocation(Location location) {
		LocationDAO.removeLocation(location);

	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCommissioner() {
		return commissioner;
	}

	public void setCommissioner(String commissioner) {
		this.commissioner = commissioner;
	}

	public Team findTeam(String teamName) {
		for (Team team : getTeams()) {
			if (team.getName().equals(teamName))
				return team;
		}
		return null;
	}

	public void init() {
		LeagueDM.loadLeague(this);
	}

	@Override
	public String toString() {
		return getName() + ": " + getCommissioner();
	}
}
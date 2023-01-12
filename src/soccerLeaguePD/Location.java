package soccerLeaguePD;

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
import jakarta.persistence.OneToOne;

/**
 * The Location of the field where a game can be played.
 */
@Entity(name = "location")
public class Location {
	@Id // signifies the primary key
	@Column(name = "location_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long locationId;

	/**
	 * The League that uses the location for games
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "league_id", referencedColumnName = "league_id")
	private League league;

	/**
	 * A collection of Games played at this Location.
	 */
//	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "location", targetEntity = Game.class, fetch = FetchType.EAGER)
	private Collection<Game> games;

	/**
	 * The team that plays its home Games at this Location.
	 */
	@OneToOne(optional = false)
	@JoinColumn(name = "team_id")
	private Team homeTeam;

	/**
	 * The name of the Location.
	 */
	@Column(name = "name", nullable = false, length = 50)
	private String name;

	/**
	 * The physical address of the Location.
	 */
	@Column(name = "address", nullable = false, length = 50)
	private String address;

	public Location() {
		// this.games = new ArrayList<Game>();
	}

	public Location(League league, String name, String address, Team homeTeam) {
		this();
		this.name = name;
		this.address = address;
		this.homeTeam = homeTeam;
		this.league = league;
		homeTeam.setHomeLocation(this);
		league.addLocation(this);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public Collection<Game> getGames() {
		return games;
	}

	public void addGames(Game game) {
		// Not sure what to do
	}

	public void setGames(Collection<Game> games) {
		this.games = games;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public boolean isOkToRemove() {
		return true;
	}

	@Override
	public String toString() {
		return getName();
	}
}

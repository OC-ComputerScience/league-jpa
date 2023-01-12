package soccerLeaguePD;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "player")
public class Player {
	@Id // signifies the primary key
	@Column(name = "player_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long playerId;

	/**
	 * The Team the Player plays on.
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "team_id", referencedColumnName = "team_id")
	private Team team;

	/**
	 * The name of the Player
	 */
	@Column(name = "name", nullable = false, length = 50)
	private String name;

	/**
	 * The position the Player plays on the Team.
	 */
	@Column(name = "position", nullable = false, length = 15)
	private String position;

	/**
	 * The number on the Player's jersey.
	 */
	@Column(name = "number", nullable = false, length = 3)
	private String number;

	public Player() {

	}

	public Player(Team team, String name, String position, String number) {
		this();
		this.name = name;
		this.position = position;
		this.number = number;
		this.team = team;
		team.addPlayer(this);
	}

	public Team getTeam() {
		return this.team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return getNumber() + ":" + getName() + ":" + getPosition();
	}
}

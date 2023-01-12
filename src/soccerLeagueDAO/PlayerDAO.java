package soccerLeagueDAO;

import java.util.ArrayList;

import jakarta.persistence.Query;
import soccerLeaguePD.Player;

public class PlayerDAO {

	public static void savePlayer(Player player) {
		emDAO.getEM().persist(player);
	}

	public static void addPlayer(Player player) {
		emDAO.getEM().persist(player);
	}

	public static ArrayList<Player> listPlayer() {
		Query query = emDAO.getEM().createQuery("SELECT player FROM player player");
		@SuppressWarnings({ "unchecked", "rawtypes" })
		ArrayList<Player> list = (ArrayList) query.getResultList();

		return list;
	}

	public static Player findPlayerById(int id) {
		Player player = emDAO.getEM().find(Player.class, Integer.toString(id));
		return player;
	}

	public static void removePlayer(Player player) {
		emDAO.getEM().remove(player);

	}

}

package soccerLeagueDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

// entity manger 
public class emDAO {

	static EntityManagerFactory entityManagerFactory;
	static EntityManager em;

	public static void initEM() {
		entityManagerFactory = Persistence.createEntityManagerFactory("League");
		em = entityManagerFactory.createEntityManager();
	}

	public static EntityManager getEM() {
		if (em == null) {
			initEM();

		}
		return em;
	}

	public static void close() {
		em.close();
		entityManagerFactory.close();
	}

}

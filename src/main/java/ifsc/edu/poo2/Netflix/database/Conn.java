package ifsc.edu.poo2.Netflix.database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conn {

	private static EntityManagerFactory entityManagerFactory;
	private static List<EntityManager> entityManagers = new ArrayList<>();

	public static EntityManager getEntityManager() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		entityManagers.add(em);
		return em;
	}

	private static EntityManagerFactory getEntityManagerFactory() {
		if (entityManagerFactory == null)
			entityManagerFactory = Persistence.createEntityManagerFactory("ifsc.edu.poo2.Netflix");
		return entityManagerFactory;
	}

	public static void closeConn() {
		if (entityManagerFactory != null)
			entityManagerFactory.close();
		for (EntityManager em : entityManagers)
			if (em.isOpen())
				em.close();
	}
}

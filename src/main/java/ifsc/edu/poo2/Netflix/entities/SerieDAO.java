package ifsc.edu.poo2.Netflix.entities;

import javax.persistence.EntityManager;

import ifsc.edu.poo2.Netflix.controllers.EnterController;
import ifsc.edu.poo2.Netflix.database.Conn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SerieDAO {
	private static ObservableList<Serie> series;

	public static ObservableList<Serie> getSerie() {
		if (series == null) {
			EntityManager em = Conn.getEntityManager();
			series = FXCollections.observableArrayList(
					em.createQuery("select serie from Serie as serie", Serie.class).getResultList());
			em.close();
		}
		return series;
	}

	public static void addSerie(Serie serie) {
		EntityManager em = Conn.getEntityManager();
		User logado = em.find(User.class, EnterController.loginName);
		em.getTransaction().begin();
		logado.shareSerie(serie);
		em.persist(logado);
		em.persist(serie);
		em.getTransaction().commit();
		em.close();
		getSerie().add(serie);
	}

	public static void update(Serie serie) {
		EntityManager em = Conn.getEntityManager();
		em.getTransaction().begin();
		Serie serieDB = em.find(Serie.class, serie.getTitulo());
		serieDB.setNumTemporada(serie.getNumTemporada());
		serieDB.setAno(serie.getAno());
		em.getTransaction().commit();
		em.close();
	}

	public static void delete(Serie serie) {
		EntityManager em = Conn.getEntityManager();
		User logado = em.find(User.class, EnterController.loginName);
		em.getTransaction().begin();
		Serie serieDB = em.find(Serie.class, serie.getTitulo());
		logado.removeShareSerie(serie);
		em.remove(serieDB);
		em.getTransaction().commit();
		em.close();
		getSerie().remove(serie);
	}
}

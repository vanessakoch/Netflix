package ifsc.edu.poo2.server.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import ifsc.edu.poo2.server.entities.Serie;
import ifsc.edu.poo2.server.entities.User;

public class SerieDAO implements InterfaceDAO<Serie> {

	public Serie get(String id) {
		EntityManager entityMng = Conn.getEntityManager();
		Serie serie = entityMng.find(Serie.class, id);
		entityMng.close();
		return serie;
	}

	public List<Serie> getAll() {
		List<Serie> serie = new ArrayList<Serie>();
		EntityManager entityMng = Conn.getEntityManager();
		serie = entityMng.createQuery("select serie from Serie as serie", Serie.class).getResultList();
		entityMng.close();
		return serie;
	}

	public void add(Serie serie) {
		EntityManager em = Conn.getEntityManager();
		User logado = em.find(User.class, "Admin");
		em.getTransaction().begin();
		logado.shareSerie(serie);
		em.persist(logado);
		em.persist(serie);
		em.getTransaction().commit();
		em.close();
	}

	public void update(Serie serie) {
		EntityManager em = Conn.getEntityManager();
		em.getTransaction().begin();
		Serie serieDB = em.find(Serie.class, serie.getTitulo());
		serieDB.setNumTemporada(serie.getNumTemporada());
		serieDB.setAno(serie.getAno());
		em.getTransaction().commit();
		em.close();
	}

	public void delete(Serie serie) {
		EntityManager em = Conn.getEntityManager();
		User logado = em.find(User.class, "Admin");
		em.getTransaction().begin();
		Serie serieDB = em.find(Serie.class, serie.getTitulo());
		logado.removeShareSerie(serie);
		em.remove(serieDB);
		em.getTransaction().commit();
		em.close();
	}
}

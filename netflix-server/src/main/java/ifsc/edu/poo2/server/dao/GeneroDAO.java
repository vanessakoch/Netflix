package ifsc.edu.poo2.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import ifsc.edu.poo2.server.entities.Genero;

public class GeneroDAO implements InterfaceDAO<Genero> {

	public Genero get(String id) {
		String[] divide = id.split(" ");

		EntityManager entityMng = Conn.getEntityManager();
		Genero genero = entityMng.find(Genero.class, Integer.parseInt(divide[0]));
		entityMng.close();
		return genero;
	}

	public List<Genero> getAll() {
		List<Genero> genero = new ArrayList<Genero>();
		EntityManager entityMng = Conn.getEntityManager();
		genero = entityMng.createQuery("select genero from Genero as genero", Genero.class).getResultList();
		entityMng.close();
		return genero;
	}

	public void add(Genero genero) {
		EntityManager entityMng = Conn.getEntityManager();
		entityMng.getTransaction().begin();
		entityMng.persist(genero);
		entityMng.getTransaction().commit();
		entityMng.close();
	}

	public void delete(Genero genero) {
		EntityManager entityMng = Conn.getEntityManager();
		entityMng.getTransaction().begin();
		Genero generoDB = entityMng.find(Genero.class, genero.getId());
		entityMng.remove(generoDB);
		entityMng.getTransaction().commit();
		entityMng.close();
	}

	public void update(Genero genero) {
		EntityManager em = Conn.getEntityManager();
		em.getTransaction().begin();
		Genero generoDB = em.find(Genero.class, genero.getId());
		generoDB.setNome(genero.getNome());
		em.getTransaction().commit();
		em.close();
	}

}

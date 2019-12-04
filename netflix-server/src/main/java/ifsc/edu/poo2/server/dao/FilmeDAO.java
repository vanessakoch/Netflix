package ifsc.edu.poo2.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import ifsc.edu.poo2.server.entities.Filme;
import ifsc.edu.poo2.server.entities.User;

public class FilmeDAO implements InterfaceDAO<Filme> {

	public List<Filme> getAll() {
		List<Filme> films = new ArrayList<Filme>();
		EntityManager entityMng = Conn.getEntityManager();
		films = entityMng.createQuery("select filme from Filme as filme", Filme.class).getResultList();
		entityMng.close();
		return films;
	}

	public Filme get(String id) {
		EntityManager entityMng = Conn.getEntityManager();
		Filme filme = entityMng.find(Filme.class, id);
		entityMng.close();
		return filme;
	}

	public void add(Filme filme) {
		EntityManager em = Conn.getEntityManager();
		User logado = em.find(User.class, "Admin");
		em.getTransaction().begin();
		logado.shareFilme(filme);
		em.persist(logado);
		em.persist(filme);
		em.getTransaction().commit();
		em.close();
	}

	public void update(Filme filme) {
		EntityManager em = Conn.getEntityManager();
		em.getTransaction().begin();
		Filme filmeDB = em.find(Filme.class, filme.getTitulo());
		filmeDB.setAno(filme.getAno());
		filmeDB.setDiretor(filme.getDiretor());
		filmeDB.setTitulo(filme.getTitulo());
		em.getTransaction().commit();
		em.close();
	}

	public void delete(Filme filme) {
		EntityManager em = Conn.getEntityManager();
		User logado = em.find(User.class, "Admin");
		em.getTransaction().begin();
		logado.removeShareFilme(filme);
		Filme filmeDB = em.find(Filme.class, filme.getTitulo());
		em.remove(filmeDB);
		em.getTransaction().commit();
		em.close();
	}
}

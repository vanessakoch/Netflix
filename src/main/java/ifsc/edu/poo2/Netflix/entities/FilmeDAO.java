package ifsc.edu.poo2.Netflix.entities;

import javax.persistence.EntityManager;

import ifsc.edu.poo2.Netflix.controllers.EnterController;
import ifsc.edu.poo2.Netflix.database.Conn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FilmeDAO {
	private static ObservableList<Filme> films;

	public static ObservableList<Filme> getFilm() {
		if (films == null) {
			EntityManager em = Conn.getEntityManager();
			films = FXCollections
					.observableArrayList(em.createQuery("select film from Filme as film", Filme.class).getResultList());
			em.close();
		}
		return films;
	}

	public static void addFilm(Filme filme) {
		EntityManager em = Conn.getEntityManager();
		User logado = em.find(User.class, EnterController.loginName);
		em.getTransaction().begin();
		logado.shareFilme(filme);
		em.persist(logado);
		em.persist(filme);
		em.getTransaction().commit();
		em.close();
		getFilm().add(filme);
	}
	
	public static void update(Filme filme) {
		EntityManager em = Conn.getEntityManager();
		em.getTransaction().begin();
		Filme filmeDB = em.find(Filme.class, filme.getTitulo());
		filmeDB.setAno(filme.getAno());
		filmeDB.setDiretor(filme.getDiretor());
		filmeDB.setTitulo(filme.getTitulo());	
		em.getTransaction().commit();
		em.close();
	}

	public static void delete(Filme filme) {		
		EntityManager em = Conn.getEntityManager();
		User logado = em.find(User.class, EnterController.loginName);
		em.getTransaction().begin();
		logado.removeShareFilme(filme);
		Filme filmeDB = em.find(Filme.class, filme.getTitulo());
		em.remove(filmeDB);
		em.getTransaction().commit();
		em.close();
		getFilm().remove(filme);
		logado.getListaFilme().remove(filme);
		
	}
}

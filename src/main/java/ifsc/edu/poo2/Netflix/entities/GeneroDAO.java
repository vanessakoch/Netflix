package ifsc.edu.poo2.Netflix.entities;

import javax.persistence.EntityManager;

import ifsc.edu.poo2.Netflix.database.Conn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GeneroDAO {
	private static ObservableList<Genero> genero;

	public static ObservableList<Genero> getGenero() {
		if (genero == null) {
			EntityManager em = Conn.getEntityManager();
			genero = FXCollections.observableArrayList(
					em.createQuery("select genero from Genero as genero", Genero.class).getResultList());
			em.close();
		}
		return genero;
	}

	public static void addGenero(Genero genero) {
		EntityManager em = Conn.getEntityManager();
		em.getTransaction().begin();
		em.persist(genero);
		em.getTransaction().commit();
		em.close();
		getGenero().add(genero);
	}

	public static void delete(Genero genero) {
		EntityManager em = Conn.getEntityManager();
		em.getTransaction().begin();
		Genero generoDB = em.createQuery("select genero from Genero as genero where genero.nome = ?1", Genero.class)
				.setParameter(1, genero.getNome()).getResultList().get(0);
		em.remove(generoDB);
		em.getTransaction().commit();
		em.close();
		getGenero().remove(genero);
	}
}

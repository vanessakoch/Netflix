package ifsc.edu.poo2.Netflix.entities;

import javax.persistence.EntityManager;

import ifsc.edu.poo2.Netflix.database.Conn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PerfilDAO {
	private static ObservableList<Perfil> perfis;

	public static ObservableList<Perfil> getPerfis() {
		if (perfis == null) {
			EntityManager em = Conn.getEntityManager();
			perfis = FXCollections.observableArrayList(
					em.createQuery("select perfil from Perfil as perfil", Perfil.class).getResultList());
			em.close();
		}
		return perfis;
	}

	public static void addPerfil(Perfil perfil) {
		EntityManager em = Conn.getEntityManager();
		User admin = em.find(User.class, "Admin");
		em.getTransaction().begin();
		admin.sharePerfil(perfil);
		em.persist(admin);
		em.persist(perfil);
		em.getTransaction().commit();
		em.close();
		getPerfis().add(perfil);
	}
	
	public static void update(Perfil perfil) {
		EntityManager em = Conn.getEntityManager();
		em.getTransaction().begin();
		Perfil perfilDB = em.find(Perfil.class, perfil.getId());
		perfilDB.setNome(perfil.getNome());
		perfilDB.setMenorIdade(perfil.isMenorIdade());
		perfilDB.setIdioma(perfil.getIdioma());	
		perfilDB.setPermissao(perfil.getPermissao());	
		em.getTransaction().commit();
		em.close();
	}


}

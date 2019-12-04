package ifsc.edu.poo2.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import ifsc.edu.poo2.server.dao.Conn;
import ifsc.edu.poo2.server.entities.Perfil;
import ifsc.edu.poo2.server.entities.User;

public class PerfilDAO implements InterfaceDAO<Perfil> {

	public Perfil get(String id) {
		EntityManager entityMng = Conn.getEntityManager();
		Perfil perfil = entityMng.find(Perfil.class, id);
		entityMng.close();
		return perfil;
	}

	public List<Perfil> getAll() {
		List<Perfil> users = new ArrayList<Perfil>();
		EntityManager entityMng = Conn.getEntityManager();
		users = entityMng.createQuery("select perfil from Perfil as perfil", Perfil.class).getResultList();
		entityMng.close();
		return users;
	}

	public void add(Perfil perfil) {
		EntityManager em = Conn.getEntityManager();
		User admin = em.find(User.class, "Admin");
		em.getTransaction().begin();
		admin.sharePerfil(perfil);
		em.persist(admin);
		em.persist(perfil);
		em.getTransaction().commit();
		em.close();
	}

	public void update(Perfil perfil) {
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

	public void delete(Perfil perfil) {
		EntityManager em = Conn.getEntityManager();
		User logado = em.find(User.class, "Admin");
		em.getTransaction().begin();
		logado.removeSharePerfil(perfil);
		Perfil perfilDB = em.find(Perfil.class, perfil.getId());
		em.remove(perfilDB);
		em.getTransaction().commit();
		em.close();
		// logado.getListaPerfis().remove(perfil);

	}

}

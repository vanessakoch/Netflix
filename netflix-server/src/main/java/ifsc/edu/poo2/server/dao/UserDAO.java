package ifsc.edu.poo2.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import ifsc.edu.poo2.server.entities.User;

public class UserDAO implements InterfaceDAO<User> {

	public User get(String id) {
		EntityManager entityMng = Conn.getEntityManager();
		User user = entityMng.find(User.class, id);
		entityMng.close();
		return user;
	}

	public List<User> getAll() {
		List<User> users = new ArrayList<User>();
		EntityManager entityMng = Conn.getEntityManager();
		users = entityMng.createQuery("select user from User as user", User.class).getResultList();
		entityMng.close();
		return users;
	}

	public void add(User user) {
		EntityManager em = Conn.getEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}

	public void delete(User obj) {
		EntityManager entityMng = Conn.getEntityManager();
		entityMng.getTransaction().begin();
		User userDB = entityMng.find(User.class, obj.getName());
		entityMng.remove(userDB);
		entityMng.getTransaction().commit();
		entityMng.close();
	}

	public void update(User user) {
		EntityManager em = Conn.getEntityManager();
		em.getTransaction().begin();
		User userDB = em.find(User.class, user.getName());
		userDB.setEmail(user.getEmail());
		userDB.setSenha(user.getSenha());
		userDB.setPlano(user.getPlano());
		userDB.setValorMensal(user.getValorMensal());
		em.getTransaction().commit();
		em.close();
	}

}

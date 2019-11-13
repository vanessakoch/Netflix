package ifsc.edu.poo2.Netflix.entities;

import javax.persistence.EntityManager;

import ifsc.edu.poo2.Netflix.database.Conn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserDAO {
	private static ObservableList<User> users;

	public static ObservableList<User> getUsers() {
		if (users == null) {
			EntityManager em = Conn.getEntityManager();
			users = FXCollections.observableArrayList(em.createQuery("select user from User as user", User.class).getResultList());
			em.close();
		}
		return users;
	}

	public static void addUser(User user) {
		EntityManager em = Conn.getEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
		getUsers().add(user);
	}
	public static void update(User user) {
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
	public static void delete(User user) {
		EntityManager em = Conn.getEntityManager();
		em.getTransaction().begin();
		User userDB = em.find(User.class, user.getName());
		em.remove(userDB);
		em.getTransaction().commit();
		em.close();
		getUsers().remove(user);
	}
}

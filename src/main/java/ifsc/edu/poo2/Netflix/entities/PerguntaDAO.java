package ifsc.edu.poo2.Netflix.entities;

import javax.persistence.EntityManager;

import ifsc.edu.poo2.Netflix.database.Conn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PerguntaDAO {
	private static ObservableList<Pergunta> question;

	public static ObservableList<Pergunta> getPergunta() {
		if (question == null) {
			EntityManager em = Conn.getEntityManager();
			question = FXCollections.observableArrayList(
					em.createQuery("select question from Pergunta as question", Pergunta.class).getResultList());
			em.close();
		}
		return question;
	}

	public static void addPergunta(Pergunta question) {
		EntityManager em = Conn.getEntityManager();
		User logado = em.find(User.class, "Admin");
		em.getTransaction().begin();
		logado.sharePergunta(question);
		em.persist(logado);
		em.persist(question);
		em.getTransaction().commit();
		em.close();
		getPergunta().add(question);
	}

	public static void delete(Pergunta question) {
		EntityManager em = Conn.getEntityManager();
		em.getTransaction().begin();
		Pergunta perguntaDB = em
				.createQuery("select question from Filme as question where question.titulo = ?1", Pergunta.class)
				.setParameter(1, question.getTitulo()).getResultList().get(0);
		em.remove(perguntaDB);
		em.getTransaction().commit();
		em.close();
		getPergunta().remove(question);
	}
}

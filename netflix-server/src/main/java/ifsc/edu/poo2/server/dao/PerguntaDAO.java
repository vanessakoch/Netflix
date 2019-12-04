package ifsc.edu.poo2.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import ifsc.edu.poo2.server.dao.Conn;
import ifsc.edu.poo2.server.entities.Pergunta;
import ifsc.edu.poo2.server.entities.User;

public class PerguntaDAO implements InterfaceDAO<Pergunta> {

	public Pergunta get(String id) {
		EntityManager entityMng = Conn.getEntityManager();
		Pergunta pergunta = entityMng.find(Pergunta.class, id);
		entityMng.close();
		return pergunta;
	}

	public List<Pergunta> getAll() {
		List<Pergunta> pergunta = new ArrayList<Pergunta>();
		EntityManager entityMng = Conn.getEntityManager();
		pergunta = entityMng.createQuery("select pergunta from Pergunta as pergunta", Pergunta.class).getResultList();
		entityMng.close();
		return pergunta;
	}

	public void add(Pergunta question) {
		EntityManager em = Conn.getEntityManager();
		User logado = em.find(User.class, "Admin");
		em.getTransaction().begin();
		logado.sharePergunta(question);
		em.persist(logado);
		em.persist(question);
		em.getTransaction().commit();
		em.close();
	}

	public void delete(Pergunta obj) {
		EntityManager entityMng = Conn.getEntityManager();
		entityMng.getTransaction().begin();
		Pergunta perguntaDB = entityMng.find(Pergunta.class, obj.getId());
		entityMng.remove(perguntaDB);
		entityMng.getTransaction().commit();
		entityMng.close();
	}

	public void update(Pergunta obj) {
		EntityManager entityMng = Conn.getEntityManager();
		entityMng.getTransaction().begin();
		Pergunta perguntaDB = entityMng.find(Pergunta.class, obj.getId());
		perguntaDB.setTitulo(obj.getTitulo());
		perguntaDB.setDescricao(obj.getDescricao());
		entityMng.getTransaction().commit();
		entityMng.close();
	}

}

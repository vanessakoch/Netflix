package ifsc.edu.poo2.Netflix.database;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import ifsc.edu.poo2.Netflix.entities.Pergunta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PerguntaDAO implements InterfaceDAO<Pergunta> {

	private static ObservableList<Pergunta> perguntas;
	private String ipServer = "localhost";
	private int portServer = 1024;

	@Override
	public Pergunta get(String id) {
		Pergunta pergunta = null;
		try {
			Socket server = new Socket(ipServer, portServer);

			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("pergunta;get;" + id);
			out.flush();

			ObjectInputStream in = new ObjectInputStream(server.getInputStream());
			String msg = in.readUTF();

			if (!msg.contains("404")) {
				String[] splitResult = msg.split(";");
				pergunta = new Pergunta(Integer.parseInt(splitResult[0]), splitResult[1], splitResult[2]);
			}

			in.close();
			out.close();
			server.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return pergunta;
	}

	@Override
	public List<Pergunta> getAll() throws UnknownHostException, IOException {
		perguntas = FXCollections.observableArrayList();
		Socket server = new Socket(ipServer, portServer);

		ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
		out.writeUTF("pergunta;getAll");
		out.flush();

		ObjectInputStream in = new ObjectInputStream(server.getInputStream());
		String msg = in.readUTF();

		if (!msg.contains("404") && msg.length() > 0) {
			String[] splitResult = msg.split(";");
			int perguntaIndex = 0;
			while (perguntaIndex < splitResult.length) {
				Pergunta pergunta = new Pergunta(Integer.parseInt(splitResult[perguntaIndex]),
						splitResult[perguntaIndex + 1], splitResult[perguntaIndex + 2]);
				perguntas.add(pergunta);
				perguntaIndex += 3;
			}
		}
		in.close();
		out.close();
		server.close();

		return perguntas;
	}

	public void change(Pergunta pergunta, String operation) {
		try {
			Socket server = new Socket(ipServer, portServer);
			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("pergunta;" + operation + ";" + pergunta.getId() + ";" + pergunta.getTitulo() + ";"
					+ pergunta.getDescricao() + ";");
			out.flush();
			out.close();
			server.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	@Override
	public void add(Pergunta pergunta) {
		change(pergunta, "add");
	}

	@Override
	public void delete(Pergunta pergunta) {
		change(pergunta, "delete");
	}

	@Override
	public void update(Pergunta pergunta) {
		change(pergunta, "update");
	}
}

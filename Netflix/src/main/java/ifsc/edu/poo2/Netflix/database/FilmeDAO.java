package ifsc.edu.poo2.Netflix.database;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import ifsc.edu.poo2.Netflix.entities.Filme;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FilmeDAO implements InterfaceDAO<Filme> {

	private static ObservableList<Filme> films;
	private String ipServer = "localhost";
	private int portServer = 1024;

	@Override
	public Filme get(String id) {
		Filme filme = null;
		try {
			Socket server = new Socket(ipServer, portServer);

			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("filme;get;" + id);
			out.flush();

			ObjectInputStream in = new ObjectInputStream(server.getInputStream());
			String msg = in.readUTF();

			if (!msg.contains("404")) {
				String[] splitResult = msg.split(";");
				filme = new Filme(splitResult[0], Integer.parseInt(splitResult[1]), splitResult[2],
						new GeneroDAO().get(splitResult[3]));
			}

			in.close();
			out.close();
			server.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return filme;
	}

	@Override
	public List<Filme> getAll() throws UnknownHostException, IOException {
		films = FXCollections.observableArrayList();
		Socket server = new Socket(ipServer, portServer);

		ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
		out.writeUTF("filme;getAll");
		out.flush();

		ObjectInputStream in = new ObjectInputStream(server.getInputStream());
		String msg = in.readUTF();

		if (!msg.contains("404") && msg.length() > 0) {
			String[] splitResult = msg.split(";");
			int filmIndex = 0;
			while (filmIndex < splitResult.length) {
				Filme filme = new Filme(splitResult[filmIndex], Integer.valueOf(splitResult[filmIndex + 1]),
						splitResult[filmIndex + 2], new GeneroDAO().get(splitResult[filmIndex + 3]));
				films.add(filme);
				filmIndex += 4;
			}
		}
		in.close();
		out.close();
		server.close();

		return films;
	}

	public void change(Filme filme, String operation) {
		try {
			Socket server = new Socket(ipServer, portServer);
			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("filme;" + operation + ";" + filme.getTitulo() + ";" + filme.getAno() + ";"
					+ filme.getDiretor() + ";" + filme.getGenero() + ";");
			out.flush();
			out.close();
			server.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	@Override
	public void add(Filme filme) {
		change(filme, "add");
	}

	@Override
	public void delete(Filme filme) {
		change(filme, "delete");
	}

	@Override
	public void update(Filme filme) {
		change(filme, "update");
	}
}
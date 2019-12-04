package ifsc.edu.poo2.Netflix.database;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import ifsc.edu.poo2.Netflix.entities.Genero;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GeneroDAO implements InterfaceDAO<Genero> {
	private static ObservableList<Genero> generos;

	private String ipServer = "localhost";
	private int portServer = 1024;

	@Override
	public Genero get(String id) {
		Genero genero = null;
		try {
			Socket server = new Socket(ipServer, portServer);

			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("genero;get;" + id);
			out.flush();

			ObjectInputStream in = new ObjectInputStream(server.getInputStream());
			String msg = in.readUTF();

			if (!msg.contains("404")) {
				String[] splitResult = msg.split(";");
				genero = new Genero(Integer.parseInt(splitResult[0]), splitResult[1]);
			}

			in.close();
			out.close();
			server.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return genero;
	}

	@Override
	public List<Genero> getAll() throws UnknownHostException, IOException {
		generos = FXCollections.observableArrayList();
		Socket server = new Socket(ipServer, portServer);

		ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
		out.writeUTF("genero;getAll");
		out.flush();

		ObjectInputStream in = new ObjectInputStream(server.getInputStream());
		String msg = in.readUTF();

		if (!msg.contains("404") && msg.length() > 0) {
			String[] splitResult = msg.split(";");
			int genIndex = 0;
			while (genIndex < splitResult.length) {
				Genero genero = new Genero(Integer.parseInt(splitResult[genIndex]), splitResult[genIndex + 1]);
				generos.add(genero);
				genIndex += 2;
			}
		}
		in.close();
		out.close();
		server.close();

		return generos;
	}

	public void change(Genero genero, String operation) {
		try {
			Socket server = new Socket(ipServer, portServer);
			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("genero;" + operation + ";" + genero.getId() + ";" + genero.getNome() + ";");
			out.flush();
			out.close();
			server.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	@Override
	public void add(Genero genero) {
		change(genero, "add");
	}

	@Override
	public void delete(Genero genero) {
		change(genero, "delete");
	}

	@Override
	public void update(Genero genero) {
		change(genero, "update");
	}
}
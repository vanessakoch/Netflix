package ifsc.edu.poo2.Netflix.database;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import ifsc.edu.poo2.Netflix.entities.Perfil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PerfilDAO implements InterfaceDAO<Perfil> {

	private static ObservableList<Perfil> perfis;
	private String ipServer = "localhost";
	private int portServer = 1024;

	@Override
	public Perfil get(String id) {
		Perfil perfil = null;
		try {
			Socket server = new Socket(ipServer, portServer);

			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("perfil;get;" + id);
			out.flush();

			ObjectInputStream in = new ObjectInputStream(server.getInputStream());
			String msg = in.readUTF();

			if (!msg.contains("404")) {
				String[] splitResult = msg.split(";");
				perfil = new Perfil(Integer.parseInt(splitResult[0]), splitResult[1], splitResult[2], splitResult[3],
						Boolean.parseBoolean(splitResult[4]));
			}

			in.close();
			out.close();
			server.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return perfil;
	}

	@Override
	public List<Perfil> getAll() throws UnknownHostException, IOException {
		perfis = FXCollections.observableArrayList();
		Socket server = new Socket(ipServer, portServer);

		ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
		out.writeUTF("perfil;getAll");
		out.flush();

		ObjectInputStream in = new ObjectInputStream(server.getInputStream());
		String msg = in.readUTF();

		if (!msg.contains("404") && msg.length() > 0) {
			String[] splitResult = msg.split(";");
			int perfilIndex = 0;
			while (perfilIndex < splitResult.length) {
				Perfil perfil = new Perfil(Integer.parseInt(splitResult[perfilIndex]), splitResult[perfilIndex + 1],
						splitResult[perfilIndex + 2], splitResult[perfilIndex + 3],
						Boolean.valueOf(splitResult[perfilIndex + 4]));
				perfis.add(perfil);
				perfilIndex += 5;
			}
		}
		in.close();
		out.close();
		server.close();

		return perfis;
	}

	public void change(Perfil perfil, String operation) {
		try {
			Socket server = new Socket(ipServer, portServer);
			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("perfil;" + operation + ";" + perfil.getId() + ";" + perfil.getNome() + ";"
					+ perfil.getIdioma() + ";" + perfil.getPermissao() + ";" + perfil.isMenorIdade() + ";");
			out.flush();
			out.close();
			server.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	@Override
	public void add(Perfil perfil) {
		change(perfil, "add");
	}

	@Override
	public void delete(Perfil perfil) {
		change(perfil, "delete");
	}

	@Override
	public void update(Perfil perfil) {
		change(perfil, "update");
	}
}

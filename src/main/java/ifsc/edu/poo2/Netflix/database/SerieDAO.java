package ifsc.edu.poo2.Netflix.database;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import ifsc.edu.poo2.Netflix.entities.Serie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SerieDAO implements InterfaceDAO<Serie> {

	private static ObservableList<Serie> series;
	private String ipServer = "localhost";
	private int portServer = 1024;

	@Override
	public Serie get(String id) {
		Serie serie = null;
		try {
			Socket server = new Socket(ipServer, portServer);

			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("serie;get;" + id);
			out.flush();

			ObjectInputStream in = new ObjectInputStream(server.getInputStream());
			String msg = in.readUTF();

			if (!msg.contains("404")) {
				String[] splitResult = msg.split(";");
				serie = new Serie(splitResult[0], Integer.parseInt(splitResult[1]), Integer.parseInt(splitResult[2]));
			}

			in.close();
			out.close();
			server.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return serie;
	}

	@Override
	public List<Serie> getAll() throws UnknownHostException, IOException {
		series = FXCollections.observableArrayList();
		Socket server = new Socket(ipServer, portServer);

		ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
		out.writeUTF("serie;getAll");
		out.flush();

		ObjectInputStream in = new ObjectInputStream(server.getInputStream());
		String msg = in.readUTF();

		if (!msg.contains("404") && msg.length() > 0) {
			String[] splitResult = msg.split(";");
			int serieIndex = 0;
			while (serieIndex < splitResult.length) {
				Serie serie = new Serie(splitResult[serieIndex], Integer.parseInt(splitResult[serieIndex + 1]),
						Integer.parseInt(splitResult[serieIndex + 2]));
				series.add(serie);
				serieIndex += 3;
			}
		}
		in.close();
		out.close();
		server.close();

		return series;
	}

	public void change(Serie serie, String operation) {
		try {
			Socket server = new Socket(ipServer, portServer);
			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("serie;" + operation + ";" + serie.getTitulo() + ";" + serie.getNumTemporada() + ";"
					+ serie.getAno() + ";");
			out.flush();
			out.close();
			server.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	@Override
	public void add(Serie serie) {
		change(serie, "add");
	}

	@Override
	public void delete(Serie serie) {
		change(serie, "delete");
	}

	@Override
	public void update(Serie serie) {
		change(serie, "update");
	}
}

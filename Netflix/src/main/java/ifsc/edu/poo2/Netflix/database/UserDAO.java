package ifsc.edu.poo2.Netflix.database;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import ifsc.edu.poo2.Netflix.entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserDAO implements InterfaceDAO<User> {

	private static ObservableList<User> users;
	private String ipServer = "localhost";
	private int portServer = 1024;

	@Override
	public User get(String id) {
		User user = null;
		try {
			Socket server = new Socket(ipServer, portServer);

			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("user;get;" + id);
			out.flush();

			ObjectInputStream in = new ObjectInputStream(server.getInputStream());
			String msg = in.readUTF();

			if (!msg.contains("404")) {
				String[] splitResult = msg.split(";");
				user = new User(splitResult[0], splitResult[1], splitResult[2], splitResult[3],
						Float.parseFloat(splitResult[4]));
			}

			in.close();
			out.close();
			server.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return user;
	}

	@Override
	public List<User> getAll() throws UnknownHostException, IOException {
		users = FXCollections.observableArrayList();
		Socket server = new Socket(ipServer, portServer);

		ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
		out.writeUTF("user;getAll");
		out.flush();

		ObjectInputStream in = new ObjectInputStream(server.getInputStream());
		String msg = in.readUTF();

		if (!msg.contains("404") && msg.length() >= 0) {
			String[] splitResult = msg.split(";");
			int userIndex = 0;
			while (userIndex < splitResult.length) {
				User user = new User(splitResult[userIndex], splitResult[userIndex + 1], splitResult[userIndex + 2],
						splitResult[userIndex + 3], Float.parseFloat(splitResult[userIndex + 4]));
				users.add(user);
				userIndex += 5;
			}
		}
		in.close();
		out.close();
		server.close();

		return users;
	}

	public void change(User user, String operation) {
		try {
			Socket server = new Socket(ipServer, portServer);
			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeUTF("user;" + operation + ";" + user.getName() + ";" + user.getEmail() + ";" + user.getSenha()
					+ ";" + user.getPlano() + ";" + user.getValorMensal() + ";");
			out.flush();
			out.close();
			server.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	@Override
	public void add(User user) {
		change(user, "add");
	}

	@Override
	public void delete(User user) {
		change(user, "delete");
	}

	@Override
	public void update(User user) {
		change(user, "update");
	}
}

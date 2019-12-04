package ifsc.edu.poo2.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;

import ifsc.edu.poo2.server.dao.Conn;
import ifsc.edu.poo2.server.dao.FilmeDAO;
import ifsc.edu.poo2.server.dao.GeneroDAO;
import ifsc.edu.poo2.server.dao.PerfilDAO;
import ifsc.edu.poo2.server.dao.PerguntaDAO;
import ifsc.edu.poo2.server.dao.SerieDAO;
import ifsc.edu.poo2.server.dao.Strings;
import ifsc.edu.poo2.server.dao.UserDAO;
import ifsc.edu.poo2.server.entities.Filme;
import ifsc.edu.poo2.server.entities.Genero;
import ifsc.edu.poo2.server.entities.Perfil;
import ifsc.edu.poo2.server.entities.Pergunta;
import ifsc.edu.poo2.server.entities.Serie;
import ifsc.edu.poo2.server.entities.User;
import ifsc.edu.poo2.server.exceptions.CommException;
import ifsc.edu.poo2.server.exceptions.NetDeviceException;
import ifsc.edu.poo2.server.exceptions.PortException;

public class Main {

	public static void main(String[] args) {

		UserDAO user = new UserDAO();
		GeneroDAO genero = new GeneroDAO();
		PerguntaDAO pergunta = new PerguntaDAO();

		if (user.getAll().isEmpty()) {
			user.add(new User("Admin", "netflix@netflix.com", "admin", "Premium", 0));
		}

		if (genero.getAll().isEmpty() || genero.getAll().size() < 5) {
			genero.add(new Genero("Terror"));
			genero.add(new Genero("Ação"));
			genero.add(new Genero("Comédia"));
			genero.add(new Genero("Comédia Romântica"));
			genero.add(new Genero("Suspense"));
		}

		if (pergunta.getAll().isEmpty()) {
			pergunta.add(new Pergunta("O que é a Netflix?", Strings.oqueNetflix));
			pergunta.add(new Pergunta("Quanto custa a Netflix?", Strings.custoNetflix));
			pergunta.add(new Pergunta("Onde posso assistir?", Strings.ondeNetflix));
			pergunta.add(new Pergunta("Como faço para cancelar?", Strings.cancelarNetflix));
			pergunta.add(new Pergunta("Onde eu posso assistir a Netflix?", Strings.assistirNetflix));
			pergunta.add(new Pergunta("Como funciona o período de utilização gratuíta?", Strings.utilizarNetflix));
		}

		ServerSocket server = null;
		try {
			Conn.getEntityManager().close();
			printServerInfo();
			server = openSocket();
			System.out.println("O servidor esta aberto na porta " + server.getLocalPort());
			while (true) {
				listen(server);
			}
		} catch (PortException ex) {
			System.err.println("Nenhuma porta disponível no servidor.");
		} catch (NetDeviceException ex) {
			System.err.println("A placa de rede esta com algum problema.");
		} catch (CommException ex) {
			System.err.println("Ocorreu algum problema em uma comunicação com um cliente.");
		} finally {
			if (server != null) {
				try {
					server.close();
				} catch (IOException e) {
				}
			}
			Conn.closeConn();
		}
	}

	private static void listen(ServerSocket server) throws CommException {
		try {
			Socket client = server.accept();
			process(client);
			client.close();
		} catch (IOException e) {
			throw new CommException();
		}
	}

	private static void process(Socket client) throws IOException {
		System.out.println("Cliente conectado: " + client.getInetAddress().getHostAddress());

		ObjectInputStream in = new ObjectInputStream(client.getInputStream());
		String msg = in.readUTF();
		System.out.println("Cliente enviou: " + msg);

		ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());

		String recebido[] = msg.split(";");
		if (recebido[0].contentEquals("user")) {
			if (recebido[1].contentEquals("get"))
				getUser(out, recebido);
			else if (recebido[1].contentEquals("getAll"))
				getAllUser(out);
			else if (recebido[1].contentEquals("add"))
				addUser(out, recebido);
			else if (recebido[1].contentEquals("delete"))
				deleteUser(out, recebido);
			else if (recebido[1].contentEquals("update"))
				updateUser(out, recebido);
		} else if (recebido[0].contentEquals("serie")) {
			if (recebido[1].contentEquals("get"))
				getSerie(out, recebido);
			else if (recebido[1].contentEquals("getAll"))
				getAllSerie(out);
			else if (recebido[1].contentEquals("add"))
				addSerie(out, recebido);
			else if (recebido[1].contentEquals("delete"))
				deleteSerie(out, recebido);
			else if (recebido[1].contentEquals("update"))
				updateSerie(out, recebido);
		} else if (recebido[0].contentEquals("filme")) {
			if (recebido[1].contentEquals("get"))
				getFilme(out, recebido);
			else if (recebido[1].contentEquals("getAll"))
				getAllFilme(out);
			else if (recebido[1].contentEquals("add"))
				addFilme(out, recebido);
			else if (recebido[1].contentEquals("delete"))
				deleteFilme(out, recebido);
			else if (recebido[1].contentEquals("update"))
				updateFilme(out, recebido);
		} else if (recebido[0].contentEquals("genero")) {
			if (recebido[1].contentEquals("get"))
				getGenero(out, recebido);
			else if (recebido[1].contentEquals("getAll"))
				getAllGenero(out);
			else if (recebido[1].contentEquals("add"))
				addGenero(out, recebido);
			else if (recebido[1].contentEquals("delete"))
				deleteGenero(out, recebido);
			else if (recebido[1].contentEquals("update"))
				updateGenero(out, recebido);
		} else if (recebido[0].contentEquals("perfil")) {
			if (recebido[1].contentEquals("get"))
				getPerfil(out, recebido);
			else if (recebido[1].contentEquals("getAll"))
				getAllPerfil(out);
			else if (recebido[1].contentEquals("add"))
				addPerfil(out, recebido);
			else if (recebido[1].contentEquals("delete"))
				deletePerfil(out, recebido);
			else if (recebido[1].contentEquals("update"))
				updatePerfil(out, recebido);
		} else if (recebido[0].contentEquals("pergunta")) {
			if (recebido[1].contentEquals("get"))
				getPergunta(out, recebido);
			else if (recebido[1].contentEquals("getAll"))
				getAllPergunta(out);
			else if (recebido[1].contentEquals("add"))
				addPergunta(out, recebido);
			else if (recebido[1].contentEquals("delete"))
				deletePergunta(out, recebido);
			else if (recebido[1].contentEquals("update"))
				updatePergunta(out, recebido);
		}

		out.flush();
		out.close();
		in.close();
	}

	private static void updatePergunta(ObjectOutputStream out, String[] recebido) throws IOException {
		Pergunta pergunta = new Pergunta(Integer.parseInt(recebido[2]), recebido[3], recebido[4]);
		new PerguntaDAO().update(pergunta);
	}

	private static void deletePergunta(ObjectOutputStream out, String[] recebido) throws IOException {
		Pergunta pergunta = new Pergunta(Integer.parseInt(recebido[2]), recebido[3], recebido[4]);
		new PerguntaDAO().delete(pergunta);
	}

	private static void addPergunta(ObjectOutputStream out, String[] recebido) throws IOException {
		Pergunta pergunta = new Pergunta(Integer.parseInt(recebido[2]), recebido[3], recebido[4]);
		new PerguntaDAO().add(pergunta);
	}

	private static void getAllPergunta(ObjectOutputStream out) throws IOException {
		String msg = "";
		List<Pergunta> perguntas = new PerguntaDAO().getAll();
		if (perguntas == null)
			out.writeUTF("404");
		else
			for (Pergunta pergunta : perguntas)
				msg = msg.concat(pergunta.getId() + ";" + pergunta.getTitulo() + ";" + pergunta.getDescricao() + ";");
		out.writeUTF(msg);
	}

	private static void getPergunta(ObjectOutputStream out, String[] recebido) throws IOException {
		Pergunta pergunta = new PerguntaDAO().get(recebido[2]);
		if (pergunta == null) {
			out.writeUTF("404");
		} else {
			out.writeUTF(pergunta.getId() + ";" + pergunta.getTitulo() + ";" + pergunta.getDescricao() + ";");
		}
	}

	private static void updatePerfil(ObjectOutputStream out, String[] recebido) throws IOException {
		Perfil perfil = new Perfil(Integer.parseInt(recebido[2]), recebido[3], recebido[4], recebido[5],
				Boolean.valueOf(recebido[6]));
		new PerfilDAO().update(perfil);
	}

	private static void deletePerfil(ObjectOutputStream out, String[] recebido) throws IOException {
		Perfil perfil = new Perfil(Integer.parseInt(recebido[2]), recebido[3], recebido[4], recebido[5],
				Boolean.valueOf(recebido[6]));
		new PerfilDAO().delete(perfil);
	}

	private static void addPerfil(ObjectOutputStream out, String[] recebido) throws IOException {
		Perfil perfil = new Perfil(Integer.parseInt(recebido[2]), recebido[3], recebido[4], recebido[5],
				Boolean.valueOf(recebido[6]));
		new PerfilDAO().add(perfil);
	}

	private static void getAllPerfil(ObjectOutputStream out) throws IOException {
		String msg = "";
		List<Perfil> perfis = new PerfilDAO().getAll();
		if (perfis == null)
			out.writeUTF("404");
		else
			for (Perfil perfil : perfis)
				msg = msg.concat(perfil.getId() + ";" + perfil.getNome() + ";" + perfil.getIdioma() + ";"
						+ perfil.getPermissao() + ";" + String.valueOf(perfil.isMenorIdade() + ";"));
		out.writeUTF(msg);
	}

	private static void getPerfil(ObjectOutputStream out, String[] recebido) throws IOException {
		Perfil perfil = new PerfilDAO().get(recebido[2]);
		if (perfil == null) {
			out.writeUTF("404");
		} else {
			out.writeUTF(perfil.getId() + ";" + perfil.getNome() + ";" + perfil.getIdioma() + ";"
					+ perfil.getPermissao() + ";" + String.valueOf(perfil.isMenorIdade() + ";"));
		}
	}

	private static void updateGenero(ObjectOutputStream out, String[] recebido) throws IOException {
		Genero genero = new Genero(Integer.parseInt(recebido[2]), recebido[3]);
		new GeneroDAO().update(genero);
	}

	private static void deleteGenero(ObjectOutputStream out, String[] recebido) throws IOException {
		Genero genero = new Genero(Integer.parseInt(recebido[2]), recebido[3]);
		new GeneroDAO().delete(genero);
	}

	private static void addGenero(ObjectOutputStream out, String[] recebido) throws IOException {
		Genero genero = new Genero(Integer.parseInt(recebido[2]), recebido[3]);
		new GeneroDAO().add(genero);
	}

	private static void getAllGenero(ObjectOutputStream out) throws IOException {
		String msg = "";
		List<Genero> generos = new GeneroDAO().getAll();
		if (generos == null)
			out.writeUTF("404");
		else
			for (Genero genero : generos)
				msg = msg.concat(genero.getId() + ";" + genero.getNome() + ";");
		out.writeUTF(msg);
	}

	private static void getGenero(ObjectOutputStream out, String[] recebido) throws IOException {
		Genero genero = new GeneroDAO().get(recebido[2]);
		if (genero == null) {
			out.writeUTF("404");
		} else {
			out.writeUTF(genero.getId() + ";" + genero.getNome() + ";");
		}
	}

	private static void updateSerie(ObjectOutputStream out, String[] recebido) throws IOException {
		Serie serie = new Serie(recebido[2], Integer.parseInt(recebido[3]), Integer.parseInt(recebido[4]));
		new SerieDAO().update(serie);
	}

	private static void deleteSerie(ObjectOutputStream out, String[] recebido) throws IOException {
		Serie serie = new Serie(recebido[2], Integer.parseInt(recebido[3]), Integer.parseInt(recebido[4]));
		new SerieDAO().delete(serie);
	}

	private static void addSerie(ObjectOutputStream out, String[] recebido) throws IOException {
		Serie serie = new Serie(recebido[2], Integer.parseInt(recebido[3]), Integer.parseInt(recebido[4]));
		new SerieDAO().add(serie);
	}

	private static void getAllSerie(ObjectOutputStream out) throws IOException {
		String msg = "";
		List<Serie> series = new SerieDAO().getAll();
		if (series == null)
			out.writeUTF("404");
		else
			for (Serie serie : series)
				msg = msg.concat(serie.getTitulo() + ";" + serie.getNumTemporada() + ";" + serie.getAno() + ";");
		out.writeUTF(msg);
	}

	private static void getSerie(ObjectOutputStream out, String[] recebido) throws IOException {
		Serie serie = new SerieDAO().get(recebido[2]);
		if (serie == null) {
			out.writeUTF("404");
		} else {
			out.writeUTF(serie.getTitulo() + ";" + serie.getNumTemporada() + ";" + serie.getAno() + ";");
		}
	}

	private static void updateFilme(ObjectOutputStream out, String[] recebido) throws IOException {
		Filme filme = new Filme(recebido[2], Integer.parseInt(recebido[3]), recebido[4],
				new GeneroDAO().get(recebido[5]));
		new FilmeDAO().update(filme);
	}

	private static void deleteFilme(ObjectOutputStream out, String[] recebido) throws IOException {
		Filme filme = new Filme(recebido[2], Integer.parseInt(recebido[3]), recebido[4],
				new GeneroDAO().get(recebido[5]));
		new FilmeDAO().delete(filme);
	}

	private static void addFilme(ObjectOutputStream out, String[] recebido) throws IOException {
		Filme filme = new Filme(recebido[2], Integer.parseInt(recebido[3]), recebido[4],
				new GeneroDAO().get(recebido[5]));
		new FilmeDAO().add(filme);
	}

	private static void getAllFilme(ObjectOutputStream out) throws IOException {
		String msg = "";
		List<Filme> films = new FilmeDAO().getAll();
		if (films == null)
			out.writeUTF("404");
		else
			for (Filme filme : films)
				msg = msg.concat(filme.getTitulo() + ";" + filme.getAno() + ";" + filme.getDiretor() + ";"
						+ filme.getGenero() + ";");
		out.writeUTF(msg);
	}

	private static void getFilme(ObjectOutputStream out, String[] recebido) throws IOException {
		Filme filme = new FilmeDAO().get(recebido[2]);
		if (filme == null) {
			out.writeUTF("404");
		} else {
			out.writeUTF(filme.getTitulo() + ";" + filme.getAno() + ";" + filme.getDiretor() + ";" + filme.getGenero()
					+ ";");
		}
	}

	private static void updateUser(ObjectOutputStream out, String[] recebido) throws IOException {
		User user = new User(recebido[2], recebido[3], recebido[4], recebido[5], Float.parseFloat(recebido[6]));
		new UserDAO().update(user);
	}

	private static void deleteUser(ObjectOutputStream out, String[] recebido) throws IOException {
		User user = new User(recebido[2], recebido[3], recebido[4], recebido[5], Float.parseFloat(recebido[6]));
		new UserDAO().delete(user);
	}

	private static void addUser(ObjectOutputStream out, String[] recebido) throws IOException {
		User user = new User(recebido[2], recebido[3], recebido[4], recebido[5], Float.parseFloat(recebido[6]));
		new UserDAO().add(user);
	}

	private static void getAllUser(ObjectOutputStream out) throws IOException {
		String msg = "";
		List<User> users = new UserDAO().getAll();
		if (users == null)
			out.writeUTF("404");
		else
			for (User user : users)
				msg = msg.concat(user.getName() + ";" + user.getEmail() + ";" + user.getSenha() + ";" + user.getPlano()
						+ ";" + user.getValorMensal() + ";");
		out.writeUTF(msg);
	}

	private static void getUser(ObjectOutputStream out, String[] recebido) throws IOException {
		User user = new UserDAO().get(recebido[2]);
		if (user == null) {
			out.writeUTF("404");
		} else {
			out.writeUTF(user.getName() + ";" + user.getEmail() + ";" + user.getSenha() + ";" + user.getPlano() + ";"
					+ user.getValorMensal() + ";");
		}
	}

	private static ServerSocket openSocket() throws PortException {
		int port = 1024;
		while (port <= 65535) {
			try {
				return new ServerSocket(port);
			} catch (IOException ex) {
				port++;
			}
		}
		throw new PortException();
	}

	private static void printServerInfo() throws NetDeviceException {
		try {
			System.out.println("-----------------------------------");
			System.out.println("Informações do servidor:");
			String hostname = InetAddress.getLocalHost().getHostName();
			System.out.println("Hostname: " + hostname);
			Enumeration<NetworkInterface> myNetInterfaces = NetworkInterface.getNetworkInterfaces();
			while (myNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = myNetInterfaces.nextElement();
				System.out.println("Interface: " + netInterface.getName());
				Enumeration<InetAddress> interfaceAddrs = netInterface.getInetAddresses();
				while (interfaceAddrs.hasMoreElements()) {
					InetAddress addr = interfaceAddrs.nextElement();
					System.out.println("  " + addr.getHostAddress());
				}
			}
			System.out.println("-----------------------------------");
		} catch (SocketException e1) {
			throw new NetDeviceException();
		} catch (UnknownHostException e) {
			throw new NetDeviceException();
		}
	}
}

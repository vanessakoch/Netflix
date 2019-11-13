package ifsc.edu.poo2.Netflix;

import ifsc.edu.poo2.Netflix.database.Conn;
import ifsc.edu.poo2.Netflix.database.Strings;
import ifsc.edu.poo2.Netflix.entities.Genero;
import ifsc.edu.poo2.Netflix.entities.GeneroDAO;
import ifsc.edu.poo2.Netflix.entities.Perfil;
import ifsc.edu.poo2.Netflix.entities.PerfilDAO;
import ifsc.edu.poo2.Netflix.entities.Pergunta;
import ifsc.edu.poo2.Netflix.entities.PerguntaDAO;
import ifsc.edu.poo2.Netflix.entities.User;
import ifsc.edu.poo2.Netflix.entities.UserDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	private static Stage stage;
	private static Scene enterScreen;
	private static Scene homeSerieScreen;
	private static Scene usersScreen;
	private static Scene helpScreen;
	private static Scene signatureScreen;
	private static Scene signatureContaScreen;
	private static Scene pagamentoScreen;
	private static Scene questionScreen;
	private static Scene addSerieScreen;
	private static Scene contaScreen;
	private static Scene videoScreen;
	private static Scene addFilmeScreen;
	private static Scene homeFilmScreen;
	private static Scene userListScreen;
	private static Scene selecionaFilmeScreen;
	private static Scene selecionaSerieScreen;
	private static Scene reproducaoFilmeScreen;
	private static Scene reproducaoSerieScreen;

	@Override
	public void start(Stage primaryStage) throws Exception {

		if (UserDAO.getUsers().isEmpty()) {
			UserDAO.addUser(new User("Admin", "netflix@netflix.com", "admin", "Premium", 0));
		}

		if (PerfilDAO.getPerfis().isEmpty()) {
			PerfilDAO.addPerfil(new Perfil(1, "Pagador", "Português", "Todos os níveis de maturidade", false));
			PerfilDAO.addPerfil(new Perfil(2, "Parasita 1", "Português", "Todos os níveis de maturidade", false));
			PerfilDAO.addPerfil(new Perfil(3, "Parasita 2", "Português", "Todos os níveis de maturidade", false));
			PerfilDAO.addPerfil(new Perfil(4, "Parasita 3", "Português", "Baixos níveis de maturidade", true));
		}
		if (GeneroDAO.getGenero().isEmpty()) {
			GeneroDAO.addGenero(new Genero("Terror"));
			GeneroDAO.addGenero(new Genero("Ação"));
			GeneroDAO.addGenero(new Genero("Comédia"));
			GeneroDAO.addGenero(new Genero("Comédia Romântica"));
			GeneroDAO.addGenero(new Genero("Suspense"));
		}

		if (PerguntaDAO.getPergunta().isEmpty()) {
			PerguntaDAO.addPergunta(new Pergunta("O quê é a Netflix?", Strings.oqueNetflix));
			PerguntaDAO.addPergunta(new Pergunta("Quanto custa a Netflix?", Strings.custoNetflix));
			PerguntaDAO.addPergunta(new Pergunta("Onde posso assistir?", Strings.ondeNetflix));
			PerguntaDAO.addPergunta(new Pergunta("Como faço para cancelar?", Strings.cancelarNetflix));
			PerguntaDAO.addPergunta(new Pergunta("Onde eu posso assistir a Netflix?", Strings.assistirNetflix));
			PerguntaDAO.addPergunta(
					new Pergunta("Como funciona o período de utilização gratuíta?", Strings.utilizarNetflix));
		}

		Conn.getEntityManager().close();
		stage = primaryStage;

		Parent fxmlEnter = FXMLLoader.load(getClass().getResource("enterApp.fxml"));
		enterScreen = new Scene(fxmlEnter, 1360, 700);

		
		Parent fxmlUsers = FXMLLoader.load(getClass().getResource("usersApp.fxml"));
		usersScreen = new Scene(fxmlUsers, 1360, 700);

		Parent fxmlHomeSerie = FXMLLoader.load(getClass().getResource("homeSerieApp.fxml"));
		homeSerieScreen = new Scene(fxmlHomeSerie, 1360, 700);

		Parent fxmlHelp = FXMLLoader.load(getClass().getResource("helpApp.fxml"));
		helpScreen = new Scene(fxmlHelp, 1360, 700);

		Parent fxmlSignature = FXMLLoader.load(getClass().getResource("signatureApp.fxml"));
		signatureScreen = new Scene(fxmlSignature, 1360, 700);

		Parent fxmlSignatureConta = FXMLLoader.load(getClass().getResource("signatureContaApp.fxml"));
		signatureContaScreen = new Scene(fxmlSignatureConta, 1360, 700);

		Parent fxmlPagamento = FXMLLoader.load(getClass().getResource("pagamentoApp.fxml"));
		pagamentoScreen = new Scene(fxmlPagamento, 1360, 700);

		Parent fxmlQuestion = FXMLLoader.load(getClass().getResource("questionsApp.fxml"));
		questionScreen = new Scene(fxmlQuestion, 1360, 700);

		Parent fxmlAddSerie = FXMLLoader.load(getClass().getResource("addSerieApp.fxml"));
		addSerieScreen = new Scene(fxmlAddSerie, 1360, 700);

		Parent fxmlConta = FXMLLoader.load(getClass().getResource("contaApp.fxml"));
		contaScreen = new Scene(fxmlConta, 1360, 700);

		Parent fxmlVideo = FXMLLoader.load(getClass().getResource("configurarVideoApp.fxml"));
		videoScreen = new Scene(fxmlVideo, 1360, 700);

		Parent fxmladdFilme = FXMLLoader.load(getClass().getResource("addFilmeApp.fxml"));
		addFilmeScreen = new Scene(fxmladdFilme, 1360, 700);

		Parent fxmlHomeFilme = FXMLLoader.load(getClass().getResource("homeFilmeApp.fxml"));
		homeFilmScreen = new Scene(fxmlHomeFilme, 1360, 700);

		Parent fxmlUserList = FXMLLoader.load(getClass().getResource("usersListApp.fxml"));
		userListScreen = new Scene(fxmlUserList, 1360, 700);

		Parent fxmlSelecionaFilme = FXMLLoader.load(getClass().getResource("selecionaFilmeApp.fxml"));
		selecionaFilmeScreen = new Scene(fxmlSelecionaFilme, 1360, 700);

		Parent fxmlSelecionaSerie = FXMLLoader.load(getClass().getResource("selecionaSerieApp.fxml"));
		selecionaSerieScreen = new Scene(fxmlSelecionaSerie, 1360, 700);

		Parent fxmlReproduzFilme = FXMLLoader.load(getClass().getResource("reproducaoFilmeApp.fxml"));
		reproducaoFilmeScreen = new Scene(fxmlReproduzFilme);

		Parent fxmlReproduzSerie = FXMLLoader.load(getClass().getResource("reproducaoSerieApp.fxml"));
		reproducaoSerieScreen = new Scene(fxmlReproduzSerie);

		primaryStage.setScene(enterScreen);
		primaryStage.show();

	}

	public static void changeScreen(String scr) {
		switch (scr) {
		case "enter":
			stage.setScene(enterScreen);
			break;
		case "home":
			stage.setScene(homeSerieScreen);
			break;
		case "help":
			stage.setScene(helpScreen);
			break;
		case "users":
			stage.setScene(usersScreen);
			break;
		case "signature":
			stage.setScene(signatureScreen);
			break;
		case "signatureConta":
			stage.setScene(signatureContaScreen);
			break;
		case "pagamento":
			stage.setScene(pagamentoScreen);
			break;
		case "question":
			stage.setScene(questionScreen);
			break;
		case "addSerie":
			stage.setScene(addSerieScreen);
			break;
		case "conta":
			stage.setScene(contaScreen);
			break;
		case "video":
			stage.setScene(videoScreen);
			break;
		case "addFilme":
			stage.setScene(addFilmeScreen);
			break;
		case "filmHome":
			stage.setScene(homeFilmScreen);
			break;
		case "userList":
			stage.setScene(userListScreen);
			break;
		case "selecionaFilme":
			stage.setScene(selecionaFilmeScreen);
			break;
		case "selecionaSerie":
			stage.setScene(selecionaSerieScreen);
			break;
		case "reproduzFilme":
			stage.setScene(reproducaoFilmeScreen);
			break;
		case "reproduzSerie":
			stage.setScene(reproducaoSerieScreen);
			break;
		case "exit":
			stage.close();
			break;

		default:
			break;
		}
	}

	@Override
	public void stop() throws Exception {
		Conn.closeConn();
		super.stop();
	}

}
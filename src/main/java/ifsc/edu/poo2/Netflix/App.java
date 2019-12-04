package ifsc.edu.poo2.Netflix;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
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
	private static Scene addPerfilScreen;
	private static Scene generoScreen;
	private static Scene loadScreen;
	private static Scene loadLoginScreen;
	private static Scene loadFinalScreen;
	private static Scene loadSairScreen;

	@Override
	public void start(Stage primaryStage) throws Exception {

		stage = primaryStage;

		Parent fxmlLoad = FXMLLoader.load(getClass().getResource("loading.fxml"));
		loadScreen = new Scene(fxmlLoad);

		primaryStage.setScene(loadScreen);
		primaryStage.show();

	}

	public static void changeScreen(String scr) throws IOException {
		switch (scr) {
		case "enter":
			Parent fxmlEnter = FXMLLoader.load(App.class.getResource("enterApp.fxml"));
			enterScreen = new Scene(fxmlEnter, 1360, 700);
			stage.setScene(enterScreen);
			break;
		case "home":
			Parent fxmlHomeSerie = FXMLLoader.load(App.class.getResource("homeSerieApp.fxml"));
			homeSerieScreen = new Scene(fxmlHomeSerie, 1360, 700);
			stage.setScene(homeSerieScreen);
			break;
		case "help":
			Parent fxmlHelp = FXMLLoader.load(App.class.getResource("helpApp.fxml"));
			helpScreen = new Scene(fxmlHelp, 1360, 700);
			stage.setScene(helpScreen);
			break;
		case "users":
			Parent fxmlUsers = FXMLLoader.load(App.class.getResource("usersApp.fxml"));
			usersScreen = new Scene(fxmlUsers, 1360, 700);
			stage.setScene(usersScreen);
			break;
		case "signature":
			Parent fxmlSignature = FXMLLoader.load(App.class.getResource("signatureApp.fxml"));
			signatureScreen = new Scene(fxmlSignature, 1360, 700);
			stage.setScene(signatureScreen);
			break;
		case "signatureConta":
			Parent fxmlSignatureConta = FXMLLoader.load(App.class.getResource("signatureContaApp.fxml"));
			signatureContaScreen = new Scene(fxmlSignatureConta, 1360, 700);
			stage.setScene(signatureContaScreen);
			break;
		case "pagamento":
			Parent fxmlPagamento = FXMLLoader.load(App.class.getResource("pagamentoApp.fxml"));
			pagamentoScreen = new Scene(fxmlPagamento, 1360, 700);
			stage.setScene(pagamentoScreen);
			break;
		case "question":
			Parent fxmlQuestion = FXMLLoader.load(App.class.getResource("questionsApp.fxml"));
			questionScreen = new Scene(fxmlQuestion, 1360, 700);
			stage.setScene(questionScreen);
			break;
		case "addSerie":
			Parent fxmlAddSerie = FXMLLoader.load(App.class.getResource("addSerieApp.fxml"));
			addSerieScreen = new Scene(fxmlAddSerie, 1360, 700);
			stage.setScene(addSerieScreen);
			break;
		case "conta":
			Parent fxmlConta = FXMLLoader.load(App.class.getResource("contaApp.fxml"));
			contaScreen = new Scene(fxmlConta, 1360, 700);
			stage.setScene(contaScreen);
			break;
		case "video":
			Parent fxmlVideo = FXMLLoader.load(App.class.getResource("configurarVideoApp.fxml"));
			videoScreen = new Scene(fxmlVideo, 1360, 700);
			stage.setScene(videoScreen);
			break;
		case "addFilme":
			Parent fxmladdFilme = FXMLLoader.load(App.class.getResource("addFilmeApp.fxml"));
			addFilmeScreen = new Scene(fxmladdFilme, 1360, 700);
			stage.setScene(addFilmeScreen);
			break;
		case "filmHome":
			Parent fxmlHomeFilme = FXMLLoader.load(App.class.getResource("homeFilmeApp.fxml"));
			homeFilmScreen = new Scene(fxmlHomeFilme, 1360, 700);
			stage.setScene(homeFilmScreen);
			break;
		case "userList":
			Parent fxmlUserList = FXMLLoader.load(App.class.getResource("usersListApp.fxml"));
			userListScreen = new Scene(fxmlUserList, 1360, 700);
			stage.setScene(userListScreen);
			break;
		case "selecionaFilme":
			Parent fxmlSelecionaFilme = FXMLLoader.load(App.class.getResource("selecionaFilmeApp.fxml"));
			selecionaFilmeScreen = new Scene(fxmlSelecionaFilme, 1360, 700);
			stage.setScene(selecionaFilmeScreen);
			break;
		case "selecionaSerie":
			Parent fxmlSelecionaSerie = FXMLLoader.load(App.class.getResource("selecionaSerieApp.fxml"));
			selecionaSerieScreen = new Scene(fxmlSelecionaSerie, 1360, 700);
			stage.setScene(selecionaSerieScreen);
			break;
		case "reproduzFilme":
			Parent fxmlReproduzFilme = FXMLLoader.load(App.class.getResource("reproducaoFilmeApp.fxml"));
			reproducaoFilmeScreen = new Scene(fxmlReproduzFilme);
			stage.setScene(reproducaoFilmeScreen);
			break;
		case "reproduzSerie":
			Parent fxmlReproduzSerie = FXMLLoader.load(App.class.getResource("reproducaoSerieApp.fxml"));
			reproducaoSerieScreen = new Scene(fxmlReproduzSerie);
			stage.setScene(reproducaoSerieScreen);
			break;
		case "addPerfil":
			Parent fxmlAddPerfil = FXMLLoader.load(App.class.getResource("addPerfil.fxml"));
			addPerfilScreen = new Scene(fxmlAddPerfil, 1360, 700);
			stage.setScene(addPerfilScreen);
			break;
		case "generos":
			Parent fxmlGenero = FXMLLoader.load(App.class.getResource("generos.fxml"));
			generoScreen = new Scene(fxmlGenero, 1360, 700);
			stage.setScene(generoScreen);
			break;
		case "load":
			stage.setScene(loadScreen);
			break;
		case "loadLogin":
			Parent fxmlLoginLoad = FXMLLoader.load(App.class.getResource("loadingLogin.fxml"));
			loadLoginScreen = new Scene(fxmlLoginLoad);
			stage.setScene(loadLoginScreen);
			break;
		case "loadFinal":
			Parent fxmlFinalLoad = FXMLLoader.load(App.class.getResource("loadingFinal.fxml"));
			loadFinalScreen = new Scene(fxmlFinalLoad);
			stage.setScene(loadFinalScreen);
			break;
		case "loadSair":
			Parent fxmlSairLoad = FXMLLoader.load(App.class.getResource("loadingSair.fxml"));
			loadSairScreen = new Scene(fxmlSairLoad);
			stage.setScene(loadSairScreen);
			break;
		case "exit":
			Platform.exit();
			break;
		default:
			break;
		}
	}

}
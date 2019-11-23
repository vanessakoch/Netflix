package ifsc.edu.poo2.Netflix;

import com.jfoenix.controls.JFXButton;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;

public class HomeSerieController {
	@FXML
	private ImageView logoNetflix;

	@FXML
	private JFXButton btnSerie;

	@FXML
	private JFXButton btnFilme;

	@FXML
	private JFXButton btnRecentes;

	@FXML
	private JFXButton btnLista;

	@FXML
	private JFXButton btnAssistir;

	@FXML
	private JFXButton btnAddLista;

	@FXML
	private JFXButton btnInfo;

	@FXML
	private Label lblPopulares;

	@FXML
	private ImageView imgLaCasaPapel;

	@FXML
	private ImageView imgSuits;

	@FXML
	private ImageView imgBreakingBad;

	@FXML
	private ImageView imgTheRanch;

	@FXML
	private ImageView icoNotificacao;

	@FXML
	private ImageView icoUser;

	@FXML
	private ImageView icoPesquisa;

	@FXML
	private MenuButton menuUser;

	@FXML
	private MenuItem opçPerfis;

	@FXML
	private MenuItem opçConta;

	@FXML
	private MenuItem opçVideo;

	@FXML
	private MenuItem opçAjuda;

	@FXML
	private MenuItem opçSair;

	@FXML
	private MenuItem opçFechar;

	@FXML
	private MenuItem opçUsers;

	@FXML
	public void menuFechar() {
		Platform.exit();
		App.changeScreen("exit");
	}

	@FXML
	public void menuSair() {
		App.changeScreen("enter");
	}

	@FXML
	public void menuAjuda() {
		App.changeScreen("help");
	}

	@FXML
	public void menuUsers() {
		App.changeScreen("userList");
	}

	@FXML
	public void irGenero() {
		App.changeScreen("generos");
	}
	
	@FXML
	public void irFilme() {
		App.changeScreen("filmHome");
	}

	@FXML
	public void minhaLista() {
		App.changeScreen("addSerie");
	}

	@FXML
	public void gerenciaConta() {
		App.changeScreen("conta");
	}

	@FXML
	public void gerenciaPerfis() {
		App.changeScreen("users");
	}

	@FXML
	public void configuraVideo() {
		App.changeScreen("video");
	}
	
	public void selecionaSerie() {
		App.changeScreen("selecionaSerie");
	}
}

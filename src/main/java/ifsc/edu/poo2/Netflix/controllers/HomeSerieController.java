package ifsc.edu.poo2.Netflix.controllers;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import ifsc.edu.poo2.Netflix.App;
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
	public void menuFechar() throws IOException {
		App.changeScreen("loadFinal");
	}

	@FXML
	public void menuSair() throws IOException {
		App.changeScreen("loadSair");
	}

	@FXML
	public void menuAjuda() throws IOException {
		App.changeScreen("help");
	}

	@FXML
	public void menuUsers() throws IOException {
		App.changeScreen("userList");
	}

	@FXML
	public void irGenero() throws IOException {
		App.changeScreen("generos");
	}

	@FXML
	public void irFilme() throws IOException {
		App.changeScreen("filmHome");
	}

	@FXML
	public void minhaLista() throws IOException {
		App.changeScreen("addSerie");
	}

	@FXML
	public void gerenciaConta() throws IOException {
		App.changeScreen("conta");
	}

	@FXML
	public void gerenciaPerfis() throws IOException {
		App.changeScreen("users");
	}

	@FXML
	public void configuraVideo() throws IOException {
		App.changeScreen("video");
	}

	public void selecionaSerie() throws IOException {
		App.changeScreen("selecionaSerie");
	}
}

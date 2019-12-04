package ifsc.edu.poo2.Netflix.controllers;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import ifsc.edu.poo2.Netflix.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class HelpController {

	@FXML
	private Label lblAjuda;

	@FXML
	private TextField txtAjuda;

	@FXML
	private JFXButton btnHome;

	@FXML
	private JFXButton btnSair;

	@FXML
	private Label lblContato;

	@FXML
	private StackPane paneChat;

	@FXML
	private JFXButton btnBatePapo;

	@FXML
	private StackPane paneLigar;

	@FXML
	private JFXButton btnLigar;

	@FXML
	private Button btnSearch;

	@FXML
	public void dialogoLigar() {
		JFXDialogLayout content = new JFXDialogLayout();
		content.setHeading(new Text("Ligue para nós no 0800-761-4631"));
		content.setBody(new Text("\nOferecemos atendimento em português das 8:00 às\n23:00 BRT. "
				+ "Atendimento em inglês disponível 24/7.\nPara ser atendido mais rápido, digite o código abaixo\nquando solicitado, durante a chamada:\n"
				+ "\n343712"));
		JFXDialog dialogLigar = new JFXDialog(paneLigar, content, JFXDialog.DialogTransition.CENTER);
		JFXButton btnDone = new JFXButton("Okay");
		btnDone.setStyle("-fx-background-color: rgb(77,102,204);-fx-text-fill: WHITE;");
		btnDone.setOnAction(e -> {
			dialogLigar.close();
		});
		content.setActions(btnDone);
		dialogLigar.show();
	}

	@FXML
	public void dialogoChat() {
		JFXDialogLayout content = new JFXDialogLayout();
		content.setHeading(new Text("Descreva seu problema"));
		TextField txtChat = new TextField();
		txtChat.setPrefHeight(400);
		content.setBody(txtChat);
		JFXDialog dialogChat = new JFXDialog(paneChat, content, JFXDialog.DialogTransition.CENTER);
		JFXButton btnEnviar = new JFXButton("Enviar");
		btnEnviar.setStyle("-fx-background-color: red;-fx-text-fill: WHITE;");
		btnEnviar.setOnAction(e -> {
			dialogChat.close();
		});
		content.setActions(btnEnviar);
		dialogChat.show();
	}

	@FXML
	public void sair() throws IOException {
		App.changeScreen("exit");
	}

	@FXML
	public void homeScreen() throws IOException {
		App.changeScreen("home");
	}

	@FXML
	public void search() throws IOException {
		App.changeScreen("question");
	}

}

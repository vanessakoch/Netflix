package ifsc.edu.poo2.Netflix.controllers;

import com.jfoenix.controls.JFXButton;

import ifsc.edu.poo2.Netflix.App;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ReproducaoFilmeController {

	@FXML
	private Label lblPerfil;

	@FXML
	private Label lblUser;

	@FXML
	private Label lblFilme;

	@FXML
	private JFXButton btnVoltar;

	@FXML
	private JFXButton btnPlay;

	public void getLabels() {
		lblUser.setText(EnterController.loginName);
		lblPerfil.setText(UsersController.selecionado.getNome());
		lblFilme.setText(SelecionaFilmeController.filmeSelecionado.getTitulo());
	}

	@FXML
	public void retornar() {
		lblUser.setText("");
		lblPerfil.setText("");
		lblFilme.setText("");
		App.changeScreen("filmHome");
	}

}

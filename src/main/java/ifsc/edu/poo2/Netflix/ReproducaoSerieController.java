package ifsc.edu.poo2.Netflix;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ReproducaoSerieController {

	@FXML
	private Label lblPerfil;

	@FXML
	private Label lblUser;

	@FXML
	private Label lblSerie;

	@FXML
	private JFXButton btnVoltar;

	@FXML
	private JFXButton btnPlay;

	@FXML
	void getLabels(ActionEvent event) {
		lblUser.setText(EnterController.loginName);
		lblPerfil.setText(UsersController.selecionado.getNome());
		lblSerie.setText(SelecionaSerieController.serieSelecionada.getTitulo());
	}

	@FXML
	void retornar(ActionEvent event) {
		lblUser.setText("");
		lblPerfil.setText("");
		lblSerie.setText("");
		App.changeScreen("home");
	}

}

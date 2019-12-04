package ifsc.edu.poo2.Netflix.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;

import ifsc.edu.poo2.Netflix.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ReproducaoSerieController implements Initializable {

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
	private JFXSpinner spinner;

	@FXML
	private Label lblErro;

	Thread loadingThread;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	void getLabels(ActionEvent event) {
		if (lblUser.getText().isEmpty() || lblPerfil.getText().isEmpty() || lblSerie.getText().isEmpty()) {
			lblUser.setText(EnterController.loginName);
			lblPerfil.setText(UsersController.selecionado.getNome());
			lblSerie.setText(SelecionaSerieController.serieSelecionada.getTitulo());
			loadingThread = new Thread(new ReproducaoSerieRunnable(spinner, lblErro, this));
			loadingThread.start();
		}
	}

	@FXML
	void retornar(ActionEvent event) throws IOException {
		lblUser.setText("");
		lblPerfil.setText("");
		lblSerie.setText("");
		if (loadingThread != null) {
			loadingThread.stop();
		}
		App.changeScreen("home");
	}

}

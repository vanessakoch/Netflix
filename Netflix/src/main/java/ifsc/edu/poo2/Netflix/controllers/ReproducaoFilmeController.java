package ifsc.edu.poo2.Netflix.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import ifsc.edu.poo2.Netflix.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ReproducaoFilmeController implements Initializable {

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

	@FXML
	private JFXSpinner spinner;

	@FXML
	private Label lblErro;

	Thread loadingThread;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void getLabels() {
		if (lblUser.getText().isEmpty() || lblPerfil.getText().isEmpty() || lblFilme.getText().isEmpty()) {
			lblUser.setText(EnterController.loginName);
			lblPerfil.setText(UsersController.selecionado.getNome());
			lblFilme.setText(SelecionaFilmeController.filmeSelecionado.getTitulo());
			loadingThread = new Thread(new ReproducaoFilmeRunnable(spinner, lblErro, this));
			loadingThread.start();
		}
	}

	@FXML
	public void retornar() throws IOException {
		lblUser.setText("");
		lblPerfil.setText("");
		lblFilme.setText("");
		if (loadingThread != null) {
			loadingThread.stop();
		}
		App.changeScreen("filmHome");
	}

}

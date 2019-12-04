package ifsc.edu.poo2.Netflix.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import ifsc.edu.poo2.Netflix.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class LoadingLoginController implements Initializable {
	@FXML
	private JFXProgressBar progressBar;

	@FXML
	private JFXButton btnCancelar;

	@FXML
	private Label lblExiste;

	Thread loadingThread;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadingThread = new Thread(new LoadingLoginRunnable(progressBar, lblExiste, this));
		loadingThread.start();
	}

	public void closeWindow() throws IOException {
		App.changeScreen("users");
	}

	public void cancelar() throws IOException {
		loadingThread.stop();
		App.changeScreen("enter");

	}

}
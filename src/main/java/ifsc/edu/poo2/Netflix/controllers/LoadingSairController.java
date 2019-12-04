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

public class LoadingSairController implements Initializable {
	@FXML
	private JFXProgressBar progressBar;

	Thread loadingThread;

	@FXML
	private Label lblExiste;

	@FXML
	private JFXButton btnCancelar;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadingThread = new Thread(new LoadingSairRunnable(progressBar, lblExiste, this));
		loadingThread.start();
	}

	public void closeWindow() throws IOException {
		loadingThread.stop();
		App.changeScreen("enter");
	}

	public void cancelar() throws IOException {
		loadingThread.stop();
		App.changeScreen("home");

	}
}
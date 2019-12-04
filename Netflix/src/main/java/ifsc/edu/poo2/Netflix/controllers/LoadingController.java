package ifsc.edu.poo2.Netflix.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXProgressBar;
import ifsc.edu.poo2.Netflix.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class LoadingController implements Initializable {
	@FXML
	private JFXProgressBar progressBar;

	Thread loadingThread;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadingThread = new Thread(new LoadingRunnable(progressBar, this));
		loadingThread.start();
	}

	public void closeWindow() throws IOException {
		App.changeScreen("enter");
	}

	public void cancelar() throws IOException {
		loadingThread.stop();
		App.changeScreen("exit");

	}

}
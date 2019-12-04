package ifsc.edu.poo2.Netflix.controllers;

import java.io.IOException;

import com.jfoenix.controls.JFXProgressBar;

import ifsc.edu.poo2.Netflix.App;
import ifsc.edu.poo2.Netflix.database.UserDAO;
import ifsc.edu.poo2.Netflix.entities.User;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class LoadingLoginRunnable implements Runnable {
	private JFXProgressBar progressBar;
	private LoadingLoginController loadingController;
	private Label lblExiste;

	public LoadingLoginRunnable(JFXProgressBar progressBar, Label lblExiste,
			LoadingLoginController loadingLoginController) {
		this.progressBar = progressBar;
		this.loadingController = loadingLoginController;
		this.lblExiste = lblExiste;
	}

	@Override
	public void run() {
		updateProgress(0);
		UserDAO dao = new UserDAO();
		boolean existe = false;
		try {
			for (User u : dao.getAll()) {
				if (EnterController.loginEmail.contentEquals(u.getEmail())
						&& EnterController.loginSenha.contentEquals(u.getSenha())) {
					EnterController.loginName = u.getName();
					lblExiste.setTextFill(Color.BLUE);
					updateLabel("Buscando usuario na base de dados ...");
					existe = true;
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e1) {
					}
					updateLabel("Carregando tabelas ... ");
					updateProgress(progressBar.getProgress() + 0.5);
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e1) {
					}
					updateLabel("Pronto");
					updateProgress(progressBar.getProgress() + 0.5);
				}
			}
			if (existe) {
				Platform.runLater(() -> {
					try {
						updateProgress(progressBar.getProgress() + 0.5);
						loadingController.closeWindow();
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
			} else {
				lblExiste.setTextFill(Color.RED);
				updateLabel("Usuario não existe, cancele e tente novamente!");
				Thread.interrupted();
				App.changeScreen("enter");
			}

		} catch (Exception e) {
			try {
				loadingController.cancelar();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void updateProgress(double value) {
		Platform.runLater(() -> {
			progressBar.setProgress(value);
		});
	}
	private void updateLabel(String text) {
		Platform.runLater(() -> {
			lblExiste.setText(text);
		});
	}
	
}

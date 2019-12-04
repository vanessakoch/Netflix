package ifsc.edu.poo2.Netflix.controllers;

import java.io.IOException;

import com.jfoenix.controls.JFXProgressBar;

import ifsc.edu.poo2.Netflix.database.UserDAO;
import javafx.application.Platform;

public class LoadingRunnable implements Runnable {
	private JFXProgressBar progressBar;
	private LoadingController loadingController;

	public LoadingRunnable(JFXProgressBar progressBar, LoadingController loadingController) {
		this.progressBar = progressBar;
		this.loadingController = loadingController;
	}

	@Override
	public void run() {
		updateProgress(0);
		while (true) {
			try {
				new UserDAO().getAll();
				Platform.runLater(() -> {
					try {
						loadingController.closeWindow();
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
				return;
			} catch (Exception e) {
				if (progressBar.getProgress() > 0.5)
					updateProgress(0);
				else
					updateProgress(progressBar.getProgress() + 0.5);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
				}
			}
		}
	}

	private void updateProgress(double value) {
		Platform.runLater(() -> {
			progressBar.setProgress(value);
		});
	}

}

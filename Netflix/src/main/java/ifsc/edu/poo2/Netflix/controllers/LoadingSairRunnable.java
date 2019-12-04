package ifsc.edu.poo2.Netflix.controllers;

import java.io.IOException;
import com.jfoenix.controls.JFXProgressBar;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class LoadingSairRunnable implements Runnable {
	private JFXProgressBar progressBar;
	private LoadingSairController loadingController;
	private Label lblExiste;

	public LoadingSairRunnable(JFXProgressBar progressBar, Label lblExiste, LoadingSairController loadingController) {
		this.progressBar = progressBar;
		this.loadingController = loadingController;
		this.lblExiste = lblExiste;
	}

	@Override
	public void run() {
		updateProgress(0);
		updateLabel("Gravando com segurança ...");
		while (true) {
			if (progressBar.getProgress() == 0.5) {
				updateLabel("Salvando alterações ...");
			}
			if (progressBar.getProgress() == 1) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				Platform.runLater(() -> {
					try {
						loadingController.closeWindow();
					} catch (IOException e) {
						e.printStackTrace();
					}

				});
			} else {
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

	private void updateLabel(String text) {
		Platform.runLater(() -> {
			lblExiste.setText(text);
		});
	}
}

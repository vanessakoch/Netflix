package ifsc.edu.poo2.Netflix.controllers;

import java.io.IOException;
import com.jfoenix.controls.JFXProgressBar;
import ifsc.edu.poo2.Netflix.controllers.LoadingFinalController;
import javafx.application.Platform;

public class LoadingFinalRunnable implements Runnable {
	private JFXProgressBar progressBar;
	private LoadingFinalController loadingController;

	public LoadingFinalRunnable(JFXProgressBar progressBar, LoadingFinalController loadingController) {
		this.progressBar = progressBar;
		this.loadingController = loadingController;
	}

	@Override
	public void run() {
		updateProgress(0);
		while (true) {
			if (progressBar.getProgress() == 1) {

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
}

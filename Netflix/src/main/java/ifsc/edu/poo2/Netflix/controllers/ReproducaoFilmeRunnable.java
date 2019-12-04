package ifsc.edu.poo2.Netflix.controllers;

import com.jfoenix.controls.JFXSpinner;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class ReproducaoFilmeRunnable implements Runnable {
	private JFXSpinner spinner;
	private Label lblErro;
	double progress;

	public ReproducaoFilmeRunnable(JFXSpinner spinner, Label lblErro, ReproducaoFilmeController loadingController) {
		this.spinner = spinner;
		this.lblErro = lblErro;
	}

	@Override
	public void run() {
		updateProgress(0);
		progress = 0;
		while (progress < 0.9) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			updateProgress(progress);
			progress += 0.1;
		}
		updateLabel("O Netflix encontrou um erro. Código: ui-800-2");
	}

	private void updateProgress(double value) {
		Platform.runLater(() -> {
			spinner.setProgress(value);
		});
	}

	private void updateLabel(String text) {
		Platform.runLater(() -> {
			lblErro.setText(text);
		});
	}
}

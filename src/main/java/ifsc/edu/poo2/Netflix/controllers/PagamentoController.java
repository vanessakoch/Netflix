package ifsc.edu.poo2.Netflix.controllers;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import ifsc.edu.poo2.Netflix.App;
import javafx.fxml.FXML;

public class PagamentoController {

	@FXML
	private JFXButton btnEntrar;

	@FXML
	private JFXButton btnContinuar;

	@FXML
	public void btnEntrarAction() throws IOException {
		App.changeScreen("enter");
	}

	@FXML
	public void btnContinuarAction() throws IOException {
		App.changeScreen("video");
	}

}

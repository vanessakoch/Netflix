package ifsc.edu.poo2.Netflix.controllers;

import com.jfoenix.controls.JFXButton;

import ifsc.edu.poo2.Netflix.App;
import javafx.fxml.FXML;

public class PagamentoController {

	@FXML
	private JFXButton btnEntrar;

	@FXML
	private JFXButton btnContinuar;

	@FXML
	public void btnEntrarAction() {
		App.changeScreen("enter");
	}

	@FXML
	public void btnContinuarAction() {
		App.changeScreen("video");
	}

}

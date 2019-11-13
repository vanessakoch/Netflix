package ifsc.edu.poo2.Netflix;

import com.jfoenix.controls.JFXButton;

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

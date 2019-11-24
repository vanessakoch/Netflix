package ifsc.edu.poo2.Netflix.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;

import ifsc.edu.poo2.Netflix.App;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class ConfigurarVideoController {

	@FXML
	private JFXButton btnInicio;

	@FXML
	private JFXButton btnLista;

	@FXML
	private JFXRadioButton radioAutomatico;

	@FXML
	private JFXRadioButton radioBaixo;

	@FXML
	private JFXRadioButton radioMedio;

	@FXML
	private JFXRadioButton radioAlto;

	@FXML
	private JFXCheckBox checkAutomatico;

	@FXML
	private JFXButton btnSalvar;

	@FXML
	private JFXButton btnCancelar;

	final ToggleGroup group = new ToggleGroup();

	@FXML
	public void addRadio() {
		radioAutomatico.setToggleGroup(group);
		radioBaixo.setToggleGroup(group);
		radioMedio.setToggleGroup(group);
		radioAlto.setToggleGroup(group);
	}

	@FXML
	public void voltarInicio() {
		App.changeScreen("enter");
	}

	@FXML
	public void lista() {
		App.changeScreen("minhaLista");
	}

}

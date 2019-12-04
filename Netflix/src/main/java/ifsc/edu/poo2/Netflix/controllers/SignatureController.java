package ifsc.edu.poo2.Netflix.controllers;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import ifsc.edu.poo2.Netflix.App;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SignatureController {

	@FXML
	private Label lblPlano;

	@FXML
	private Label lblTroca;

	@FXML
	private JFXListView<?> listBeneficios;

	@FXML
	private JFXListView<?> listBasico;

	@FXML
	private JFXListView<?> listPadrao;

	@FXML
	private JFXListView<?> listPremium;

	@FXML
	private JFXButton btnBasico;

	@FXML
	private JFXButton btnPadrao;

	@FXML
	private JFXButton btnPremium;

	@FXML
	private JFXButton btnInicio;

	@FXML
	private JFXButton btnSair;

	public static String assinatura;

	public static float assinar;

	@FXML
	public void sair() throws IOException {
		App.changeScreen("exit");
	}

	@FXML
	public void enterScreen() throws IOException {
		App.changeScreen("enter");
	}

	@FXML
	public void assinaturaBasica() throws IOException {
		assinatura = "Basico";
		assinar = 21.90f;
		App.changeScreen("signatureConta");
	}

	@FXML
	public void assinaturaPadrao() throws IOException {
		assinatura = "Padrao";
		assinar = 32.90f;
		App.changeScreen("signatureConta");
	}

	@FXML
	public void assinaturaPremium() throws IOException {
		assinatura = "Premium";
		assinar = 45.90f;
		App.changeScreen("signatureConta");
	}

}

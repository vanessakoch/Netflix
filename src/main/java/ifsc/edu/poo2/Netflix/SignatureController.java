package ifsc.edu.poo2.Netflix;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
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
	public void sair() {
		App.changeScreen("exit");
	}

	@FXML
	public void enterScreen() {
		App.changeScreen("enter");
	}

	@FXML
	public void assinaturaBasica() {
		assinatura = "Basico";
		assinar = 21.90f;
		App.changeScreen("signatureConta");
	}

	@FXML
	public void assinaturaPadrao() {
		assinatura = "Padrao";
		assinar = 32.90f;
		App.changeScreen("signatureConta");
	}

	@FXML
	public void assinaturaPremium() {
		assinatura = "Premium";
		assinar = 45.90f;
		App.changeScreen("signatureConta");
	}

}

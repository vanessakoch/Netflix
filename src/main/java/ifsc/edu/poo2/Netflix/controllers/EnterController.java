package ifsc.edu.poo2.Netflix.controllers;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import ifsc.edu.poo2.Netflix.App;
import ifsc.edu.poo2.Netflix.database.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class EnterController {

	@FXML
	private TextField txtLogin;

	@FXML
	private TextField txtSenha;

	@FXML
	private JFXButton btnEntrar;

	@FXML
	private JFXCheckBox checkLembrar;

	@FXML
	private JFXButton btnAjuda;

	@FXML
	private Label lblEntrar;

	@FXML
	private ImageView logoNetflix;

	@FXML
	private JFXButton btnAssinar;

	public static String loginName = "Vanessa";
	public static String loginEmail = null;
	public static String loginSenha = null;

	UserDAO dao = new UserDAO();

	@FXML
	public void btnEntrarAction() throws IOException {
		loginEmail = txtLogin.getText();
		loginSenha = txtSenha.getText();
		App.changeScreen("loadLogin");

	}

	@FXML
	public void btnAssinarAction() throws IOException {
		App.changeScreen("signature");
	}

	@FXML
	public void btnAjudaAction() throws IOException {
		App.changeScreen("question");
	}

}

package ifsc.edu.poo2.Netflix;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import ifsc.edu.poo2.Netflix.entities.User;
import ifsc.edu.poo2.Netflix.entities.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

	@FXML
	public void btnEntrarAction() throws IOException {
		boolean validar = false;
		for (User u : UserDAO.getUsers()) {
			if (txtLogin.getText().contentEquals(u.getEmail()) && txtSenha.getText().contentEquals(u.getSenha())) {
				validar = true;
				loginName = u.getName();
				App.changeScreen("users");
				break;
			}
		}
		if (!validar) {
			Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
			dialogoErro.setTitle("Atenção");
			dialogoErro.setHeaderText("Dados incorretos");
			dialogoErro.showAndWait();
		}
	}

	@FXML
	public void btnAssinarAction() {
		App.changeScreen("signature");
	}

	@FXML
	public void btnAjudaAction() {
		App.changeScreen("question");
	}

}

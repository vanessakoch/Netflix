package ifsc.edu.poo2.Netflix;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXToggleButton;

import ifsc.edu.poo2.Netflix.entities.User;
import ifsc.edu.poo2.Netflix.entities.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class SignatureContaController {

	@FXML
	private JFXButton btnEntrar;

	@FXML
	private JFXButton btnContinuar;

	@FXML
	private JFXToggleButton toggleLembrar;

	@FXML
	private JFXCheckBox checkMan;

	@FXML
	private JFXCheckBox checkWoman;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtSenha;

	@FXML
	public void btnContinuarAction() {
		boolean jaExiste = false;
		if (!txtNome.getText().isEmpty() && !txtEmail.getText().isEmpty() && !txtSenha.getText().isEmpty()) {
			for(int i = 0; i < UserDAO.getUsers().size(); i++) {
				if(txtNome.getText().equals(UserDAO.getUsers().get(i).getName())){
					jaExiste = true;
				}
				if(txtEmail.getText().equals(UserDAO.getUsers().get(i).getEmail()) && txtSenha.getText().equals(UserDAO.getUsers().get(i).getSenha())) {
					jaExiste = true;
				}
			}
			if(jaExiste == false) {
				User novoUsuario = new User(txtNome.getText(), txtEmail.getText(), txtSenha.getText(),
						SignatureController.assinatura, SignatureController.assinar);
				UserDAO.addUser(novoUsuario);
				App.changeScreen("pagamento");
			}else {
				Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
				dialogoErro.setTitle("Atenção");
				dialogoErro.setHeaderText("Este nome já existe! Digite outro");
				dialogoErro.showAndWait();
			}
		} else {
			Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
			dialogoErro.setTitle("Atenção");
			dialogoErro.setHeaderText("Você precisa preencher os dados para cadastrar!");
			dialogoErro.showAndWait();
		}
		txtNome.clear();
		txtEmail.clear();
		txtSenha.clear();
	}

	@FXML
	public void btnEntrarAction() {
		App.changeScreen("enter");
	}

	@FXML
	public void check() {
		if (checkMan.isSelected()) {
			checkWoman.setDisable(true);
		} else {
			checkWoman.setDisable(false);
		}
		if (checkWoman.isSelected()) {
			checkMan.setDisable(true);
		} else {
			checkMan.setDisable(false);
		}
	}

}

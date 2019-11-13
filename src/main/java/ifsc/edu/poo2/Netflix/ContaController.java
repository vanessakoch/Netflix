package ifsc.edu.poo2.Netflix;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;

import ifsc.edu.poo2.Netflix.entities.PerfilDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ContaController implements Initializable {

	@FXML
	private TextField txtNome;

	@FXML
	private JFXCheckBox checkCrianca;

	@FXML
	private JFXComboBox<String> comboIdioma;

	@FXML
	private JFXComboBox<String> comboPermissao;

	@FXML
	private JFXButton btnSalvar;

	@FXML
	private JFXButton btnCancelar;

	@FXML
	private JFXButton btnHome;

	@FXML
	private Label lblPerfilSelecionado;

	public void initialize(URL location, ResourceBundle resources) {
		comboIdioma.setItems(listaIdioma);
		comboPermissao.setItems(listaPermissao);
	}

	private ObservableList<String> listaIdioma = FXCollections.observableArrayList("Português", "Inglês", "Japonês",
			"Italiano");

	private ObservableList<String> listaPermissao = FXCollections.observableArrayList("Todos os níveis de maturidade",
			"Médios níveis de maturidade", "Baixos níveis de maturidade");

	@FXML
	public void retornar() {
		lblPerfilSelecionado.setText("");
		App.changeScreen("users");
	}

	public void verPerfil() {
		lblPerfilSelecionado.setText(UsersController.selecionado.toString());
	}

	public void atualizar() {
		if (!txtNome.getText().isEmpty()) {
			UsersController.selecionado.setNome(txtNome.getText());
		}
		if (checkCrianca.isSelected()) {
			UsersController.selecionado.setMenorIdade(true);
		} else {
			UsersController.selecionado.setMenorIdade(false);
		}
		if (!comboIdioma.getSelectionModel().isEmpty()) {
			UsersController.selecionado.setIdioma(comboIdioma.getSelectionModel().getSelectedItem());
		}
		if (!comboPermissao.getSelectionModel().isEmpty()) {
			UsersController.selecionado.setPermissao(comboPermissao.getSelectionModel().getSelectedItem());
		}
		PerfilDAO.update(UsersController.selecionado);
		txtNome.clear();
		checkCrianca.setSelected(false);
		lblPerfilSelecionado.setText("");
		App.changeScreen("users");
	}

	@FXML
	public void home() {
		lblPerfilSelecionado.setText("");
		App.changeScreen("home");
	}

}

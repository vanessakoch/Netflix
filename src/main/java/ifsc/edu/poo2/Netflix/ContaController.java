package ifsc.edu.poo2.Netflix;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;

import ifsc.edu.poo2.Netflix.entities.Perfil;
import ifsc.edu.poo2.Netflix.entities.PerfilDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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

	UsersController uc = new UsersController();
	
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

	public void addPerfil() {
		Perfil novo = new Perfil();
		if (!txtNome.getText().isEmpty() && !comboIdioma.getSelectionModel().isEmpty()
				&& !comboPermissao.getSelectionModel().isEmpty()) {
			if (checkCrianca.isSelected()) {
				novo = new Perfil(txtNome.getText(), comboIdioma.getSelectionModel().getSelectedItem(),
						comboPermissao.getSelectionModel().getSelectedItem(), true);
			} else {
				novo = new Perfil(txtNome.getText(), comboIdioma.getSelectionModel().getSelectedItem(),
						comboPermissao.getSelectionModel().getSelectedItem(), false);
			}

			PerfilDAO.addPerfil(novo);
			txtNome.clear();
			checkCrianca.setSelected(false);
			App.changeScreen("users");

		} else {
			Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
			dialogoErro.setTitle("Atenção");
			dialogoErro.setHeaderText("Preencha todos os dados!");
			dialogoErro.showAndWait();
		}
	}

	public void deletePerfil() {
		try {
			if (!PerfilDAO.getPerfis().isEmpty()) {
				PerfilDAO.delete(UsersController.selecionado);
				App.changeScreen("users");

			}
		} catch (Exception e) {
			Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
			dialogoErro.setTitle("Atenção");
			dialogoErro.setHeaderText("Filme não foi selecionado");
			dialogoErro.showAndWait();
		}
	}
	
	@FXML
	public void home() {
		lblPerfilSelecionado.setText("");
		App.changeScreen("home");
	}

}
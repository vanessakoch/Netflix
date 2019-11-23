package ifsc.edu.poo2.Netflix;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;

import ifsc.edu.poo2.Netflix.entities.FilmeDAO;
import ifsc.edu.poo2.Netflix.entities.Perfil;
import ifsc.edu.poo2.Netflix.entities.PerfilDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class UsersController implements Initializable {

	@FXML
	private ImageView logoNetflix;

	@FXML
	private AnchorPane paneUsers;

	@FXML
	private JFXButton btnGerenciaPerfis;

	@FXML
	private Label lblQuemAssiste;

	@FXML
	private JFXButton btnEntrar;

	@FXML
	private JFXRadioButton perfil1;

	@FXML
	private JFXRadioButton perfil2;

	@FXML
	private JFXRadioButton perfil3;

	@FXML
	private JFXRadioButton perfil4;

	private ToggleGroup radioGroup = new ToggleGroup();;

	@FXML
	public void initialize(URL location, ResourceBundle resources) {
		addTexto();
		perfil1.setPadding(new Insets(10));
		perfil2.setPadding(new Insets(10));
		perfil3.setPadding(new Insets(10));
		perfil4.setPadding(new Insets(10));
		perfil1.setToggleGroup(radioGroup);
		perfil2.setToggleGroup(radioGroup);
		perfil3.setToggleGroup(radioGroup);
		perfil4.setToggleGroup(radioGroup);

	}

	public void addTexto() {
		if (!PerfilDAO.getPerfis().isEmpty()) {
			if (PerfilDAO.getPerfis().size() == 1) {
				perfil1.setText(PerfilDAO.getPerfis().get(0).getNome().toUpperCase());
				perfil2.setText("Adicione");
				perfil3.setText("Adicione");
				perfil4.setText("Adicione");
			}
			if (PerfilDAO.getPerfis().size() == 2) {
				perfil1.setText(PerfilDAO.getPerfis().get(0).getNome().toUpperCase());
				perfil2.setText(PerfilDAO.getPerfis().get(1).getNome().toUpperCase());
				perfil3.setText("Adicione");
				perfil4.setText("Adicione");
			}
			if (PerfilDAO.getPerfis().size() == 3) {
				perfil1.setText(PerfilDAO.getPerfis().get(0).getNome().toUpperCase());
				perfil2.setText(PerfilDAO.getPerfis().get(1).getNome().toUpperCase());
				perfil3.setText(PerfilDAO.getPerfis().get(2).getNome().toUpperCase());
				perfil4.setText("Adicione");
			}
			if (PerfilDAO.getPerfis().size() == 4) {
				perfil1.setText(PerfilDAO.getPerfis().get(0).getNome().toUpperCase());
				perfil2.setText(PerfilDAO.getPerfis().get(1).getNome().toUpperCase());
				perfil3.setText(PerfilDAO.getPerfis().get(2).getNome().toUpperCase());
				perfil4.setText(PerfilDAO.getPerfis().get(3).getNome().toUpperCase());
			}
		}
	}

	@FXML
	void userChoice(ActionEvent event) {
		verSelecionado();
		System.out.println(PerfilDAO.getPerfis().size());
		if (radioGroup.getSelectedToggle() != null && selecionado != null) {
			App.changeScreen("home");
		} else {
			Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
			dialogoErro.setTitle("Atenção");
			dialogoErro.setHeaderText("Adicione um perfil ou selecione um existente!");
			dialogoErro.showAndWait();
		}
	}

	public static Perfil selecionado;

	public Perfil verSelecionado() {
		if (getPerfil1().isSelected() && PerfilDAO.getPerfis().size() >= 1) {
			return selecionado = PerfilDAO.getPerfis().get(0);
		} else if (getPerfil2().isSelected() && PerfilDAO.getPerfis().size() >= 2) {
			return selecionado = PerfilDAO.getPerfis().get(1);
		} else if (getPerfil3().isSelected() && PerfilDAO.getPerfis().size() >= 3) {
			return selecionado = PerfilDAO.getPerfis().get(2);
		} else if (getPerfil4().isSelected() && PerfilDAO.getPerfis().size() == 4) {
			return selecionado = PerfilDAO.getPerfis().get(3);
		}
		return selecionado = null;
	}

	@FXML
	public void gerenciaConta() {
		verSelecionado();

		if (radioGroup.getSelectedToggle() != null && selecionado != null) {
			App.changeScreen("conta");
		} else {
			Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
			dialogoErro.setTitle("Atenção");
			dialogoErro.setHeaderText("Adicione um perfil ou selecione um existente!");
			dialogoErro.showAndWait();
		}
	}

	@FXML
	public void adicionarPerfil() {
		if (PerfilDAO.getPerfis().size() <= 3) {
			App.changeScreen("addPerfil");
		} else {
			Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
			dialogoErro.setTitle("Limite de perfis excedido!");
			dialogoErro.setHeaderText("Para adicionar mais perfil, apague algum da lista!");
			dialogoErro.showAndWait();
		}
	}

	public JFXRadioButton getPerfil1() {
		return perfil1;
	}

	public JFXRadioButton getPerfil2() {
		return perfil2;
	}

	public JFXRadioButton getPerfil3() {
		return perfil3;
	}

	public JFXRadioButton getPerfil4() {
		return perfil4;
	}

	public ToggleGroup getRadioGroup() {
		return radioGroup;
	}

}
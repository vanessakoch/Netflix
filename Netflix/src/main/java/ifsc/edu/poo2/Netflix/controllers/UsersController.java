package ifsc.edu.poo2.Netflix.controllers;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;

import ifsc.edu.poo2.Netflix.App;
import ifsc.edu.poo2.Netflix.database.PerfilDAO;
import ifsc.edu.poo2.Netflix.entities.Perfil;
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
		try {
			addTexto();
		} catch (IOException e) {
			e.printStackTrace();
		}
		perfil1.setPadding(new Insets(10));
		perfil2.setPadding(new Insets(10));
		perfil3.setPadding(new Insets(10));
		perfil4.setPadding(new Insets(10));
		perfil1.setToggleGroup(radioGroup);
		perfil2.setToggleGroup(radioGroup);
		perfil3.setToggleGroup(radioGroup);
		perfil4.setToggleGroup(radioGroup);

	}

	public void addTexto() throws UnknownHostException, IOException {
		PerfilDAO dao = new PerfilDAO();
		if (!new PerfilDAO().getAll().isEmpty()) {
			if (dao.getAll().size() == 1) {
				perfil1.setText(dao.getAll().get(0).getNome().toUpperCase());
				perfil2.setText("Adicione");
				perfil3.setText("Adicione");
				perfil4.setText("Adicione");
			} else if (new PerfilDAO().getAll().size() == 2) {
				perfil1.setText(dao.getAll().get(0).getNome().toUpperCase());
				perfil2.setText(dao.getAll().get(1).getNome().toUpperCase());
				perfil3.setText("Adicione");
				perfil4.setText("Adicione");
			} else if (new PerfilDAO().getAll().size() == 3) {
				perfil1.setText(dao.getAll().get(0).getNome().toUpperCase());
				perfil2.setText(dao.getAll().get(1).getNome().toUpperCase());
				perfil3.setText(dao.getAll().get(2).getNome().toUpperCase());
				perfil4.setText("Adicione");
			} else if (new PerfilDAO().getAll().size() == 4) {
				perfil1.setText(dao.getAll().get(0).getNome().toUpperCase());
				perfil2.setText(dao.getAll().get(1).getNome().toUpperCase());
				perfil3.setText(dao.getAll().get(2).getNome().toUpperCase());
				perfil4.setText(dao.getAll().get(3).getNome().toUpperCase());
			}
		} else if (new PerfilDAO().getAll().isEmpty()) {
			perfil1.setText("Adicione");
			perfil2.setText("Adicione");
			perfil3.setText("Adicione");
			perfil4.setText("Adicione");
		}
	}

	@FXML
	void userChoice(ActionEvent event) throws UnknownHostException, IOException {
		verSelecionado();
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

	public Perfil verSelecionado() throws UnknownHostException, IOException {
		PerfilDAO dao = new PerfilDAO();
		if (getPerfil1().isSelected() && dao.getAll().size() >= 1) {
			return selecionado = dao.getAll().get(0);
		} else if (getPerfil2().isSelected() && dao.getAll().size() >= 2) {
			return selecionado = dao.getAll().get(1);
		} else if (getPerfil3().isSelected() && dao.getAll().size() >= 3) {
			return selecionado = dao.getAll().get(2);
		} else if (getPerfil4().isSelected() && dao.getAll().size() == 4) {
			return selecionado = dao.getAll().get(3);
		}
		return selecionado = null;
	}

	@FXML
	public void gerenciaConta() throws UnknownHostException, IOException {
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
	public void adicionarPerfil() throws UnknownHostException, IOException {
		if (new PerfilDAO().getAll().size() <= 3) {
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

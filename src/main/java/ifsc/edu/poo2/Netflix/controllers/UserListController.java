package ifsc.edu.poo2.Netflix.controllers;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import ifsc.edu.poo2.Netflix.App;
import ifsc.edu.poo2.Netflix.database.UserDAO;
import ifsc.edu.poo2.Netflix.entities.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class UserListController implements Initializable {

	@FXML
	private JFXButton btnVoltar;

	@FXML
	private Label lblContaAtiva;

	@FXML
	private TableView<User> tableUser;

	@FXML
	private TableColumn<User, String> colName;

	@FXML
	private TableColumn<User, String> colEmail;

	@FXML
	private TableColumn<User, String> colSenha;

	@FXML
	private TableColumn<User, String> colPlano;

	@FXML
	private TableColumn<User, Float> colValor;

	@FXML
	private JFXButton btnAdd;

	@FXML
	private JFXButton btnEdit;

	@FXML
	private JFXButton btnDelete;

	@FXML
	public void initialize(URL location, ResourceBundle resources) {
		updateList();

		colName.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
		colEmail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
		colSenha.setCellValueFactory(new PropertyValueFactory<User, String>("senha"));
		colPlano.setCellValueFactory(new PropertyValueFactory<User, String>("plano"));
		colValor.setCellValueFactory(new PropertyValueFactory<User, Float>("valorMensal"));
		tableUser.setEditable(true);
		colEmail.setCellFactory(TextFieldTableCell.forTableColumn());
		colSenha.setCellFactory(TextFieldTableCell.forTableColumn());
		colPlano.setCellFactory(TextFieldTableCell.forTableColumn());
	}

	public void updateList() {
		tableUser.setItems(null);
		try {
			tableUser.setItems((ObservableList<User>) new UserDAO().getAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changeEmailCellEvent(CellEditEvent<?, ?> editedCell) throws UnknownHostException, IOException {
		User userSelected = tableUser.getSelectionModel().getSelectedItem();
		userSelected.setEmail(editedCell.getNewValue().toString());
		new UserDAO().update(userSelected);
	}

	public void changePasswordCellEvent(CellEditEvent<?, ?> editedCell) throws UnknownHostException, IOException {
		User userSelected = tableUser.getSelectionModel().getSelectedItem();
		userSelected.setSenha(editedCell.getNewValue().toString());
		new UserDAO().update(userSelected);
	}

	public void changePlanoCellEvent(CellEditEvent<?, ?> editedCell) throws UnknownHostException, IOException {
		User userSelected = tableUser.getSelectionModel().getSelectedItem();
		if (tableUser.getSelectionModel().getSelectedItem() != null) {
			if (editedCell.getNewValue().equals("Basico") || editedCell.getNewValue().equals("basico")) {
				userSelected.setPlano(editedCell.getNewValue().toString());
				userSelected.setValorMensal(21.90f);

			} else if (editedCell.getNewValue().equals("Padrao") || editedCell.getNewValue().equals("padrao")) {
				userSelected.setPlano(editedCell.getNewValue().toString());
				userSelected.setValorMensal(32.90f);

			} else if (editedCell.getNewValue().equals("Premium") || editedCell.getNewValue().equals("premium")) {
				userSelected.setPlano(editedCell.getNewValue().toString());
				userSelected.setValorMensal(45.90f);
			} else {
				Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
				dialogoErro.setTitle("Falha de atualização");
				dialogoErro.setHeaderText("Este plano não existe");
				dialogoErro.showAndWait();
			}
			new UserDAO().update(userSelected);
			updateList();
		} else {
			Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
			dialogoErro.setTitle("Atenção");
			dialogoErro.setHeaderText("Seleciona ao menos um usuário!");
			dialogoErro.showAndWait();
		}
	}

	public void deletar() {
		UserDAO dao = new UserDAO();
		User userSelected = tableUser.getSelectionModel().getSelectedItem();
		boolean usado = false;
		if (userSelected.getName().equals(EnterController.loginName)
				|| userSelected.getName().equalsIgnoreCase("Admin")) {
			usado = true;
		}
		if (!usado) {
			dao.delete(userSelected);
			updateList();

		} else {
			Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
			dialogoErro.setTitle("Atenção");
			dialogoErro.setHeaderText("Esse usuário está em uso e não pode ser deletado.");
			dialogoErro.showAndWait();
		}
	}

	@FXML
	void botaoAdd(ActionEvent event) throws IOException {
		App.changeScreen("signature");
	}

	@FXML
	void retornar(ActionEvent event) throws IOException {
		App.changeScreen("home");
	}

}

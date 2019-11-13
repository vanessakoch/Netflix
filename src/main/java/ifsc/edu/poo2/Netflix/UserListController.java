package ifsc.edu.poo2.Netflix;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import ifsc.edu.poo2.Netflix.entities.*;
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
	private TableColumn<User, String> colRegistro;

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
		addUsers();
		colRegistro.setCellValueFactory(new PropertyValueFactory<User, String>("registerDate"));
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
	
	public void addUsers() {
		tableUser.setItems(UserDAO.getUsers());
	}

	public void changeEmailCellEvent(CellEditEvent<?, ?> editedCell) {
		User userSelected = tableUser.getSelectionModel().getSelectedItem();
		for (int i = 0; i < UserDAO.getUsers().size(); i++) {
			if (userSelected.getEmail().equals(UserDAO.getUsers().get(i).getEmail())) {
				userSelected.setEmail(editedCell.getNewValue().toString());
				UserDAO.update(UserDAO.getUsers().get(i));
			}
		}
	}

	public void changePasswordCellEvent(CellEditEvent<?, ?> editedCell) {
		User userSelected = tableUser.getSelectionModel().getSelectedItem();
		for (int i = 0; i < UserDAO.getUsers().size(); i++) {
			if (userSelected.getSenha().equals(UserDAO.getUsers().get(i).getSenha())
					&& userSelected.getName().equals(UserDAO.getUsers().get(i).getName())) {
				userSelected.setSenha(editedCell.getNewValue().toString());
				UserDAO.update(UserDAO.getUsers().get(i));

			}
		}
	}

	public void changePlanoCellEvent(CellEditEvent<?, ?> editedCell) {
		User userSelected = tableUser.getSelectionModel().getSelectedItem();
		for (int i = 0; i < UserDAO.getUsers().size(); i++) {
			if (userSelected.getPlano().equalsIgnoreCase("Basico") || userSelected.getPlano().equalsIgnoreCase("Padrao")
					|| userSelected.getPlano().equalsIgnoreCase("Premium")) {
				if (userSelected.getPlano().equals(UserDAO.getUsers().get(i).getPlano())) {
					if (userSelected.getPlano().equalsIgnoreCase("Basico")) {
						userSelected.setPlano(editedCell.getNewValue().toString());
						userSelected.setValorMensal(21.90f);
					}
					if (userSelected.getPlano().equalsIgnoreCase("Padrao")) {
						userSelected.setPlano(editedCell.getNewValue().toString());
						userSelected.setValorMensal(32.90f);
					}
					if (userSelected.getPlano().equalsIgnoreCase("Premium")) {
						userSelected.setPlano(editedCell.getNewValue().toString());
						userSelected.setValorMensal(45.90f);
					}
					UserDAO.update(UserDAO.getUsers().get(i));
				}

			}
		}
	}

	public void deletar() {
		User userSelected = tableUser.getSelectionModel().getSelectedItem();
		if(!userSelected.getName().equals(EnterController.loginName)) {
			UserDAO.delete(userSelected);
		}else {
			Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
			dialogoErro.setTitle("Atenção");
			dialogoErro.setHeaderText("Esse usuário está em uso e não pode ser deletado.");
			dialogoErro.showAndWait();
		}
	}

	@FXML
	void botaoAdd(ActionEvent event) {
		App.changeScreen("signature");
	}

	@FXML
	void retornar(ActionEvent event) {
		App.changeScreen("home");
	}

}

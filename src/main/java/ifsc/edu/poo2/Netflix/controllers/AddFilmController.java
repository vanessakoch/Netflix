package ifsc.edu.poo2.Netflix.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;

import ifsc.edu.poo2.Netflix.App;
import ifsc.edu.poo2.Netflix.database.FilmeDAO;
import ifsc.edu.poo2.Netflix.database.GeneroDAO;
import ifsc.edu.poo2.Netflix.entities.Filme;
import ifsc.edu.poo2.Netflix.entities.Genero;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class AddFilmController implements Initializable {

	@FXML
	private JFXButton btnHome;

	@FXML
	private JFXListView<Filme> listFilme;

	@FXML
	private JFXButton btnFilme;

	@FXML
	private JFXButton btnRemoveFilme;

	@FXML
	private JFXButton btnEditaFilme;

	@FXML
	private TextField txtFilme;

	@FXML
	private TextField txtAno;

	@FXML
	private JFXComboBox<Genero> comboGenero;

	@FXML
	private TextField txtDiretor;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateList();
		try {
			comboGenero.setItems((ObservableList<Genero>) new GeneroDAO().getAll());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateList() {
		listFilme.setItems(null);
		try {
			listFilme.setItems((ObservableList<Filme>) new FilmeDAO().getAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void addFilmeLista() {
		try {
			if (!txtDiretor.getText().isEmpty() && !txtAno.getText().isEmpty() && !txtFilme.getText().isEmpty()
					&& !comboGenero.getSelectionModel().isEmpty()) {
				boolean existe = false;

				for (int i = 0; i < new FilmeDAO().getAll().size(); i++) {
					if (txtFilme.getText().equalsIgnoreCase(new FilmeDAO().getAll().get(i).getTitulo())) {
						existe = true;
						break;
					}
				}
				if (existe == false) {
					Filme filme = new Filme(txtFilme.getText(), Integer.parseInt(txtAno.getText()),
							txtDiretor.getText(), comboGenero.getSelectionModel().getSelectedItem());
					new FilmeDAO().add(filme);
					updateList();
				} else {
					Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
					dialogoErro.setTitle("Atenção");
					dialogoErro.setHeaderText("Esse título já existe!");
					dialogoErro.showAndWait();
				}

				txtFilme.clear();
				txtDiretor.clear();
				txtAno.clear();
			}

		} catch (Exception e) {
			Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
			dialogoErro.setTitle("Atenção");
			dialogoErro.setHeaderText("Contém dados incorretos");
			dialogoErro.showAndWait();
		}
	}

	@FXML
	public void removeFilme() {
		try {
			if (!new FilmeDAO().getAll().isEmpty()) {
				new FilmeDAO().delete(listFilme.getSelectionModel().getSelectedItem());
				updateList();
			}
		} catch (Exception e) {
			Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
			dialogoErro.setTitle("Atenção");
			dialogoErro.setHeaderText("Filme não foi selecionado");
			dialogoErro.showAndWait();
		}
	}

	@FXML
	public void editaFilme() {
		try {
			if (txtFilme.getText().isEmpty()) {
				if (!listFilme.getItems().isEmpty() && !txtDiretor.getText().isEmpty() && !txtAno.getText().isEmpty()) {
					listFilme.getSelectionModel().getSelectedItem().setDiretor(txtDiretor.getText());
					listFilme.getSelectionModel().getSelectedItem().setAno(Integer.parseInt(txtAno.getText()));
					new FilmeDAO().update(listFilme.getSelectionModel().getSelectedItem());

				} else if (!listFilme.getItems().isEmpty() && !txtDiretor.getText().isEmpty()) {
					listFilme.getSelectionModel().getSelectedItem().setDiretor(txtDiretor.getText());
					new FilmeDAO().update(listFilme.getSelectionModel().getSelectedItem());

				} else if (!listFilme.getItems().isEmpty() && !txtAno.getText().isEmpty()) {
					listFilme.getSelectionModel().getSelectedItem().setAno(Integer.parseInt(txtAno.getText()));
					new FilmeDAO().update(listFilme.getSelectionModel().getSelectedItem());

				} else if (!listFilme.getItems().isEmpty() && !txtDiretor.getText().isEmpty()
						&& !txtAno.getText().isEmpty()) {
					listFilme.getSelectionModel().getSelectedItem().setDiretor(txtDiretor.getText());
					listFilme.getSelectionModel().getSelectedItem().setAno(Integer.parseInt(txtAno.getText()));
					new FilmeDAO().update(listFilme.getSelectionModel().getSelectedItem());
				}
				updateList();

			} else {
				Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
				dialogoErro.setTitle("Atenção");
				dialogoErro.setHeaderText("Título não pode ser alterado!");
				dialogoErro.showAndWait();
			}
			txtFilme.clear();
			txtDiretor.clear();
			txtAno.clear();
		} catch (Exception e) {
			Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
			dialogoErro.setTitle("Atenção");
			dialogoErro.setHeaderText("Dados incorretos para edição!");
			dialogoErro.showAndWait();
		}
	}

	@FXML
	public void retornar() throws IOException {
		App.changeScreen("filmHome");
	}

}

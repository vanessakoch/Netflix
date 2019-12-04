package ifsc.edu.poo2.Netflix.controllers;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import ifsc.edu.poo2.Netflix.App;
import ifsc.edu.poo2.Netflix.database.FilmeDAO;
import ifsc.edu.poo2.Netflix.database.GeneroDAO;
import ifsc.edu.poo2.Netflix.entities.Genero;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

public class GeneroController implements Initializable {

	@FXML
	private JFXButton btnHome;

	@FXML
	private JFXListView<Genero> generoLista;

	@FXML
	private JFXButton btnAdd;

	@FXML
	private JFXButton btnCancela;

	@FXML
	private JFXTextField txtEditar;

	@FXML
	private JFXButton btnEdita;
	GeneroDAO dao = new GeneroDAO();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateList();

	}

	public void updateList() {
		GeneroDAO dao = new GeneroDAO();
		generoLista.setItems(null);
		try {
			generoLista.setItems((ObservableList<Genero>) dao.getAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void addGenero() {
		try {
			if (!txtEditar.getText().isEmpty()) {
				Genero genero = new Genero(txtEditar.getText());
				dao.add(genero);
			}
			txtEditar.clear();
			updateList();

		} catch (Exception e) {
			Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
			dialogoErro.setTitle("Atenção");
			dialogoErro.setHeaderText("Insira um nome para poder editar!");
			dialogoErro.showAndWait();
		}
	}

	@FXML
	public void removeGenero() {
		try {
			if (!dao.getAll().isEmpty() && generoLista.getSelectionModel().getSelectedItem() != null) {
				boolean existe = false;
				for (int i = 0; i < new FilmeDAO().getAll().size(); i++) {
					if (generoLista.getSelectionModel().getSelectedItem().getNome()
							.equalsIgnoreCase(new FilmeDAO().getAll().get(i).getGenero().getNome())) {
						existe = true;
						break;
					}
				}
				if (!existe) {
					dao.delete(generoLista.getSelectionModel().getSelectedItem());
					updateList();
				} else {
					Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
					dialogoErro.setTitle("ACESSO NEGADO");
					dialogoErro.setHeaderText("Este gênero está em uso!");
					dialogoErro.showAndWait();
				}
			}

		} catch (Exception e) {
			Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
			dialogoErro.setTitle("Atenção");
			dialogoErro.setHeaderText("Filme não foi selecionado");
			dialogoErro.showAndWait();
		}
	}

	@FXML
	public void editaGenero() throws UnknownHostException, IOException {
		if (!txtEditar.getText().isEmpty()) {
			if (!generoLista.getItems().isEmpty() && generoLista.getSelectionModel().getSelectedItem() != null) {
				generoLista.getSelectionModel().getSelectedItem().setNome(txtEditar.getText());
				dao.update(generoLista.getSelectionModel().getSelectedItem());
			}
			generoLista.setItems(null);
			generoLista.setItems((ObservableList<Genero>) dao.getAll());
			txtEditar.clear();
			updateList();
		} else {
			Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
			dialogoErro.setTitle("Atenção");
			dialogoErro.setHeaderText("Selecione ao menos um item!");
			dialogoErro.showAndWait();
		}

	}

	@FXML
	void retornar(ActionEvent event) throws IOException {
		App.changeScreen("filmHome");
	}

}

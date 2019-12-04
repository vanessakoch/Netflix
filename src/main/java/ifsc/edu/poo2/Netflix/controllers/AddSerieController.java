package ifsc.edu.poo2.Netflix.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import ifsc.edu.poo2.Netflix.App;
import ifsc.edu.poo2.Netflix.database.SerieDAO;
import ifsc.edu.poo2.Netflix.entities.Serie;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class AddSerieController implements Initializable {

	@FXML
	private JFXButton btnHome;

	@FXML
	private JFXListView<Serie> listSerie;

	@FXML
	private JFXButton btnRemoveSerie;

	@FXML
	private JFXButton btnSerie;

	@FXML
	private JFXButton btnEditaSerie;

	@FXML
	private TextField txtTemporadas;

	@FXML
	private TextField txtAno;

	@FXML
	private TextField txtSerie;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateList();
	}

	public void updateList() {
		SerieDAO dao = new SerieDAO();
		listSerie.setItems(null);
		try {
			listSerie.setItems((ObservableList<Serie>) dao.getAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void adicionaSerie() {
		try {
			if (!txtTemporadas.getText().isEmpty() && !txtAno.getText().isEmpty() && !txtSerie.getText().isEmpty()) {
				boolean existe = false;
				for (int i = 0; i < new SerieDAO().getAll().size(); i++) {
					if (txtSerie.getText().equalsIgnoreCase(new SerieDAO().getAll().get(i).getTitulo())) {
						existe = true;
						break;
					}
				}
				if (existe == false) {
					Serie serie = new Serie(txtSerie.getText(), Integer.parseInt(txtTemporadas.getText()),
							Integer.parseInt(txtAno.getText()));
					new SerieDAO().add(serie);
					updateList();
				} else {
					Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
					dialogoErro.setTitle("Atenção");
					dialogoErro.setHeaderText("Esse título já existe!");
					dialogoErro.showAndWait();
				}
				txtSerie.clear();
				txtTemporadas.clear();
				txtAno.clear();

			}
		} catch (Exception e) {
			Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
			dialogoErro.setTitle("Atenção");
			dialogoErro.setHeaderText("Contém dados incorretos");
			dialogoErro.showAndWait();
			System.out.println("Não foi possível adicionar, verifique inconsistencias!");
		}
	}

	@FXML
	public void removeSerie() {
		try {
			if (!new SerieDAO().getAll().isEmpty())
				new SerieDAO().delete(listSerie.getSelectionModel().getSelectedItem());
			updateList();

		} catch (Exception e) {
			Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
			dialogoErro.setTitle("Atenção");
			dialogoErro.setHeaderText("Série não foi selecionada");
			dialogoErro.showAndWait();
		}
	}

	@FXML
	public void editaSerie() {
		try {
			if (txtSerie.getText().isEmpty()) {
				if (!listSerie.getItems().isEmpty() && !txtTemporadas.getText().isEmpty()
						&& !txtAno.getText().isEmpty()) {
					listSerie.getSelectionModel().getSelectedItem()
							.setNumTemporada(Integer.parseInt(txtTemporadas.getText()));
					listSerie.getSelectionModel().getSelectedItem().setAno(Integer.parseInt(txtAno.getText()));
					new SerieDAO().update(listSerie.getSelectionModel().getSelectedItem());

				} else if (!listSerie.getItems().isEmpty() && !txtTemporadas.getText().isEmpty()) {
					listSerie.getSelectionModel().getSelectedItem()
							.setNumTemporada(Integer.parseInt(txtTemporadas.getText()));
					new SerieDAO().update(listSerie.getSelectionModel().getSelectedItem());

				} else if (!listSerie.getItems().isEmpty() && !txtAno.getText().isEmpty()) {
					listSerie.getSelectionModel().getSelectedItem().setAno(Integer.parseInt(txtAno.getText()));
					new SerieDAO().update(listSerie.getSelectionModel().getSelectedItem());
				}

			} else {
				Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
				dialogoErro.setTitle("Atenção");
				dialogoErro.setHeaderText("Título não pode ser alterado!");
				dialogoErro.showAndWait();
			}
			updateList();
			txtSerie.clear();
			txtTemporadas.clear();
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
		App.changeScreen("home");
	}

}

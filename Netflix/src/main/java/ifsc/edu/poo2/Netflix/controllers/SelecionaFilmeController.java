package ifsc.edu.poo2.Netflix.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import ifsc.edu.poo2.Netflix.App;
import ifsc.edu.poo2.Netflix.database.FilmeDAO;
import ifsc.edu.poo2.Netflix.entities.Filme;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

public class SelecionaFilmeController implements Initializable {

	@FXML
	private JFXButton btnHome;

	@FXML
	private JFXListView<Filme> filmeLista;

	@FXML
	private JFXButton btnAssiste;

	@FXML
	private JFXButton btnCancela;

	public static Filme filmeSelecionado = null;

	FilmeDAO dao = new FilmeDAO();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		addFilme();
	}

	public void addFilme() {
		try {
			filmeLista.setItems((ObservableList<Filme>) new FilmeDAO().getAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void retornar() throws IOException {
		App.changeScreen("filmHome");
	}

	public void assistir() throws IOException {
		if (!filmeLista.getItems().isEmpty() && !filmeLista.getSelectionModel().isEmpty()) {
			filmeSelecionado = filmeLista.getSelectionModel().getSelectedItem();
			App.changeScreen("reproduzFilme");
		} else {
			Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
			dialogoErro.setTitle("Atenção");
			dialogoErro.setHeaderText("Selecione um filme antes!");
			dialogoErro.showAndWait();
		}
	}

}

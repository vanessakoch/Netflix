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

public class SelecionaSerieController implements Initializable {

	@FXML
	private JFXButton btnHome;

	@FXML
	private JFXListView<Serie> serieLista;

	@FXML
	private JFXButton btnAssiste;

	@FXML
	private JFXButton btnCancela;

	public static Serie serieSelecionada = null;

	@FXML
	public void initialize(URL location, ResourceBundle resources) {
		addSerie();
	}

	public void addSerie() {
		try {
			serieLista.setItems((ObservableList<Serie>) new SerieDAO().getAll());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void retornar() throws IOException {
		App.changeScreen("home");
	}

	public void assistir() throws IOException {
		if (!serieLista.getItems().isEmpty() && !serieLista.getSelectionModel().isEmpty()) {
			serieSelecionada = serieLista.getSelectionModel().getSelectedItem();
			App.changeScreen("reproduzSerie");
		} else {
			Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
			dialogoErro.setTitle("Atenção");
			dialogoErro.setHeaderText("Selecione uma série antes!");
			dialogoErro.showAndWait();
		}
	}

}

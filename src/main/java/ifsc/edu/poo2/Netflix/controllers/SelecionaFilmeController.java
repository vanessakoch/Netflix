package ifsc.edu.poo2.Netflix.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import ifsc.edu.poo2.Netflix.App;
import ifsc.edu.poo2.Netflix.entities.Filme;
import ifsc.edu.poo2.Netflix.entities.FilmeDAO;
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
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		filmeLista.setItems(FilmeDAO.getFilm());
	}

	@FXML
	public void retornar() {
		App.changeScreen("filmHome");
	}
	
	public void assistir() {
		if(!filmeLista.getItems().isEmpty() && !filmeLista.getSelectionModel().isEmpty()) {
			filmeSelecionado = filmeLista.getSelectionModel().getSelectedItem();
			App.changeScreen("reproduzFilme");
		}
		else {
			Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
			dialogoErro.setTitle("Atenção");
			dialogoErro.setHeaderText("Selecione um filme antes!");
			dialogoErro.showAndWait();
		}
	}
	
	
}

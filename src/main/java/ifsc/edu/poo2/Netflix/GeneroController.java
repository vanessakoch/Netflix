package ifsc.edu.poo2.Netflix;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import ifsc.edu.poo2.Netflix.entities.Filme;
import ifsc.edu.poo2.Netflix.entities.FilmeDAO;
import ifsc.edu.poo2.Netflix.entities.Genero;
import ifsc.edu.poo2.Netflix.entities.GeneroDAO;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		generoLista.setItems(GeneroDAO.getGenero());
	}

	@FXML
	public void addGenero() {
		try {
			if (!txtEditar.getText().isEmpty()) {
				Genero genero = new Genero(txtEditar.getText());
				GeneroDAO.addGenero(genero);
				txtEditar.clear();
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
	public void removeGenero() {
		try {
			if (!GeneroDAO.getGenero().isEmpty()) {
				GeneroDAO.delete(generoLista.getSelectionModel().getSelectedItem());
			}
		} catch (Exception e) {
			Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
			dialogoErro.setTitle("Atenção");
			dialogoErro.setHeaderText("Filme não foi selecionado");
			dialogoErro.showAndWait();
		}
	}
	
	@FXML
	public void editaGenero() {
		try {
			if (!txtEditar.getText().isEmpty()) {
				if (!generoLista.getItems().isEmpty()) {
					generoLista.getSelectionModel().getSelectedItem().setNome(txtEditar.getText());
					GeneroDAO.update(generoLista.getSelectionModel().getSelectedItem());
				}
				generoLista.setItems(null);
				generoLista.setItems(GeneroDAO.getGenero());
				txtEditar.clear();
			} else {
				Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
				dialogoErro.setTitle("Atenção");
				dialogoErro.setHeaderText("Título não pode ser alterado!");
				dialogoErro.showAndWait();
			}

		} catch (Exception e) {
			Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
			dialogoErro.setTitle("Atenção");
			dialogoErro.setHeaderText("Dados incorretos para edição!");
			dialogoErro.showAndWait();
		}
	}

	@FXML
	void retornar(ActionEvent event) {
		App.changeScreen("filmHome");
	}

}

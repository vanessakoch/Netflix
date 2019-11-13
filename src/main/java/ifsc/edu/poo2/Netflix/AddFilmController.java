package ifsc.edu.poo2.Netflix;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;

import ifsc.edu.poo2.Netflix.entities.Filme;
import ifsc.edu.poo2.Netflix.entities.FilmeDAO;
import ifsc.edu.poo2.Netflix.entities.Genero;
import ifsc.edu.poo2.Netflix.entities.GeneroDAO;
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
		listFilme.setItems(FilmeDAO.getFilm());
		comboGenero.setItems(GeneroDAO.getGenero());
	}

	@FXML
	public void addFilmeLista() {
		try {
			if (!txtDiretor.getText().isEmpty() && !txtAno.getText().isEmpty() && !txtFilme.getText().isEmpty()
					&& !comboGenero.getSelectionModel().isEmpty()) {
				Filme filme = new Filme(txtFilme.getText(), Integer.parseInt(txtAno.getText()), txtDiretor.getText(),
						comboGenero.getValue());
				FilmeDAO.addFilm(filme);
				txtFilme.clear();
				txtDiretor.clear();
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
	public void removeFilme() {
		try {
			if (!FilmeDAO.getFilm().isEmpty()) {
				FilmeDAO.delete(listFilme.getSelectionModel().getSelectedItem());
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
					FilmeDAO.update(listFilme.getSelectionModel().getSelectedItem());

				} else if (!listFilme.getItems().isEmpty() && !txtDiretor.getText().isEmpty()) {
					listFilme.getSelectionModel().getSelectedItem().setDiretor(txtDiretor.getText());
					FilmeDAO.update(listFilme.getSelectionModel().getSelectedItem());

				} else if (!listFilme.getItems().isEmpty() && !txtAno.getText().isEmpty()) {
					listFilme.getSelectionModel().getSelectedItem().setAno(Integer.parseInt(txtAno.getText()));
					FilmeDAO.update(listFilme.getSelectionModel().getSelectedItem());

				} else if (!listFilme.getItems().isEmpty() && !txtDiretor.getText().isEmpty()
						&& !txtAno.getText().isEmpty()) {
					listFilme.getSelectionModel().getSelectedItem().setDiretor(txtDiretor.getText());
					listFilme.getSelectionModel().getSelectedItem().setAno(Integer.parseInt(txtAno.getText()));
					FilmeDAO.update(listFilme.getSelectionModel().getSelectedItem());
				}
			} else {
				Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
				dialogoErro.setTitle("Atenção");
				dialogoErro.setHeaderText("Título não pode ser alterado!");
				dialogoErro.showAndWait();
			}
			listFilme.setItems(null);
			listFilme.setItems(FilmeDAO.getFilm());

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
	public void retornar() {
		App.changeScreen("filmHome");
	}

}

package ifsc.edu.poo2.Netflix;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import ifsc.edu.poo2.Netflix.entities.Serie;
import ifsc.edu.poo2.Netflix.entities.SerieDAO;
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

	@FXML
	public void initialize(URL location, ResourceBundle resources) {
		listSerie.setItems(SerieDAO.getSerie());
	}

	@FXML
	public void adicionaSerie() {
		try {
			if (!txtTemporadas.getText().isEmpty() && !txtAno.getText().isEmpty() && !txtSerie.getText().isEmpty()) {
				Serie serie = new Serie(txtSerie.getText(), Integer.parseInt(txtTemporadas.getText()),
						Integer.parseInt(txtAno.getText()));
				SerieDAO.addSerie(serie);
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
			if (!SerieDAO.getSerie().isEmpty())
				SerieDAO.delete(listSerie.getSelectionModel().getSelectedItem());
		}catch(Exception e) {
			Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
			dialogoErro.setTitle("Atenção");
			dialogoErro.setHeaderText("Série não foi selecionada");
			dialogoErro.showAndWait();
		}
	}

	@FXML
	public void editaSerie() {
		try {
			if(txtSerie.getText().isEmpty()) {
				if (!listSerie.getItems().isEmpty() && !txtTemporadas.getText().isEmpty() && !txtAno.getText().isEmpty()) {
					listSerie.getSelectionModel().getSelectedItem().setNumTemporada(Integer.parseInt(txtTemporadas.getText()));
					listSerie.getSelectionModel().getSelectedItem().setAno(Integer.parseInt(txtAno.getText()));
					SerieDAO.update(listSerie.getSelectionModel().getSelectedItem());
	
				} else if (!listSerie.getItems().isEmpty() && !txtTemporadas.getText().isEmpty()) {
					listSerie.getSelectionModel().getSelectedItem().setNumTemporada(Integer.parseInt(txtTemporadas.getText()));
					SerieDAO.update(listSerie.getSelectionModel().getSelectedItem());
	
				} else if (!listSerie.getItems().isEmpty() && !txtAno.getText().isEmpty()) {
					listSerie.getSelectionModel().getSelectedItem().setAno(Integer.parseInt(txtAno.getText()));
					SerieDAO.update(listSerie.getSelectionModel().getSelectedItem());
				}
			}else {
				Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
				dialogoErro.setTitle("Atenção");
				dialogoErro.setHeaderText("Título não pode ser alterado!");
				dialogoErro.showAndWait();	
			}
			listSerie.setItems(null);
			listSerie.setItems(SerieDAO.getSerie());
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
	public void retornar() {
		App.changeScreen("home");
	}
}

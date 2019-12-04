package ifsc.edu.poo2.Netflix.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import ifsc.edu.poo2.Netflix.App;
import ifsc.edu.poo2.Netflix.database.PerguntaDAO;
import ifsc.edu.poo2.Netflix.entities.Pergunta;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class QuestionController implements Initializable {

	@FXML
	private JFXButton btnHome;

	@FXML
	private JFXButton btnNaoEncontrei;

	@FXML
	private JFXComboBox<Pergunta> comboPergunta;

	@FXML
	private Label lblDescricao;

	@FXML
	public void initialize(URL location, ResourceBundle resources) {
		updateList();
	}

	public void updateList() {
		PerguntaDAO dao = new PerguntaDAO();
		comboPergunta.setItems(null);
		try {
			comboPergunta.setItems((ObservableList<Pergunta>) dao.getAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void comboLabel() {
		lblDescricao.setText(comboPergunta.getValue().getDescricao().toString());
	}

	@FXML
	public void retornar() throws IOException {
		App.changeScreen("home");
	}

	@FXML
	public void help() throws IOException {
		App.changeScreen("help");
	}

}

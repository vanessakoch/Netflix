package ifsc.edu.poo2.Netflix;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import ifsc.edu.poo2.Netflix.entities.Pergunta;
import ifsc.edu.poo2.Netflix.entities.PerguntaDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class QuestionController {

	@FXML
	private JFXButton btnHome;

	@FXML
	private JFXButton btnNaoEncontrei;

	@FXML
	private JFXComboBox<Pergunta> comboPergunta;

	@FXML
	private Label lblDescricao;

	@FXML
	public void initialize() {
		comboPergunta.setItems(PerguntaDAO.getPergunta());
	}

	@FXML
	public void comboLabel() {
		lblDescricao.setText(comboPergunta.getValue().getDescricao().toString());
	}

	@FXML
	public void retornar() {
		App.changeScreen("home");
	}

	@FXML
	public void help() {
		App.changeScreen("help");
	}

}

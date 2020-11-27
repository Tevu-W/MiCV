package dad.favafx.micv.experiencia;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class ExperienciaController implements Initializable {

	// view

	@FXML
	private BorderPane view;

	@FXML
	private TableView<?> experienciaView;

	@FXML
	private TableColumn<?, ?> desdeColumn;

	@FXML
	private TableColumn<?, ?> hastaColumn;

	@FXML
	private TableColumn<?, ?> denominacionColumn;

	@FXML
	private TableColumn<?, ?> empleadorColumn;

	@FXML
	private Button añadirExperienciaButton;

	@FXML
	private Button eliminarExperienciaButton;
	
	public ExperienciaController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ExperienciaView.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	public BorderPane getView() {
		return view;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	@FXML
	void addExperiencia(ActionEvent event) {

	}

	@FXML
	void deleteExperiencia(ActionEvent event) {

	}

}

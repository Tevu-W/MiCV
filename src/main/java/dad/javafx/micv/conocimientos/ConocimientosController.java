package dad.javafx.micv.conocimientos;

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

public class ConocimientosController implements Initializable {

	@FXML
	private BorderPane view;

	@FXML
	private TableView<?> conocimientosView;
	
	@FXML
    private TableColumn<?, ?> denomColumn;

    @FXML
    private TableColumn<?, ?> nivelColumn;

	@FXML
	private Button añadirConocimientoButton;

	@FXML
	private Button AñadirIdiomaButton;

	@FXML
	private Button EliminarConocimientoButton;

	public ConocimientosController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ConocimientosView.fxml"));
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
	void addConocimiento(ActionEvent event) {

	}

	@FXML
	void addIdioma(ActionEvent event) {

	}

	@FXML
	void deleteConocimiento(ActionEvent event) {

	}

}

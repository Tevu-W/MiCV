package dad.javafx.micv.conocimientos;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.micv.model.Conocimiento;
import dad.javafx.micv.model.Nivel;
import javafx.fxml.Initializable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;

public class ConocimientosController implements Initializable {

	//model
	private ListProperty<Conocimiento> habilidades = new SimpleListProperty<Conocimiento>(FXCollections.observableArrayList());
	
	
	@FXML
	private BorderPane view;

	@FXML
	private TableView<Conocimiento> conocimientosView;
	
	@FXML
    private TableColumn<Conocimiento, String> denomColumn;

    @FXML
    private TableColumn<Conocimiento, Nivel> nivelColumn;

	@FXML
	private Button añadirConocimientoButton;

	@FXML
	private Button AñadirIdiomaButton;

	@FXML
	private Button EliminarConocimientoButton;
	
	//Ventana sin idioma
	
	private Stage stageSinIdioma;
	
	@FXML
    private GridPane viewSinIdioma;

    @FXML
    private ComboBox<Nivel> nivelComboSinIdioma;

    @FXML
    private Button reinicioSinIdioma;

    @FXML
    private Button crearConocimientoSinIdioma;

    @FXML
    private Button canclearSinIdioma;

    

	public ConocimientosController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ConocimientosView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public BorderPane getView() {
		return view;
	}
	
	@FXML
    void onCancelarSinIdioma(ActionEvent event) {

    }

    @FXML
    void onCrearConocimientoSinIdioma(ActionEvent event) {

    }

    @FXML
    void onReinicioSinIdioma(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		denomColumn.setCellValueFactory(v -> v.getValue().denominacionProperty());
		nivelColumn.setCellValueFactory(v -> v.getValue().nivelProperty());
		
		denomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		nivelColumn.setCellFactory(ComboBoxTableCell.forTableColumn(Nivel.values()));
		
		habilidades.addListener((o, ov, nv) -> onConocimientoChanged(o, ov, nv));
		
	}

	private void onConocimientoChanged(ObservableValue<? extends ObservableList<Conocimiento>> o,
			ObservableList<Conocimiento> ov, ObservableList<Conocimiento> nv) {
		
		if( ov != null ) {
			conocimientosView.itemsProperty().unbindBidirectional(habilidades);
		}
		
		if( nv != null ) {
			conocimientosView.itemsProperty().bindBidirectional(habilidades);
		}
		
	}

	@FXML
	void addConocimiento(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddConocimientoSinIdioma.fxml"));
		loader.setController(this);
		loader.load();
		
		stageSinIdioma = new Stage();
		Scene scene = new Scene(loader.getRoot(), 600, 200);
		stageSinIdioma.setTitle("Nuevo Conocimiento");
		stageSinIdioma.initModality(Modality.WINDOW_MODAL);
		// Hasta que no se cierre la ventana, la ventana padre está abierta
		stageSinIdioma.initOwner(view.getScene().getWindow());
		stageSinIdioma.setScene(scene);
		stageSinIdioma.setResizable(true);
		stageSinIdioma.show();
	}

	@FXML
	void addIdioma(ActionEvent event) {

	}

	@FXML
	void deleteConocimiento(ActionEvent event) {

	}

	public ListProperty<Conocimiento> habilidadesProperty() {
		return this.habilidades;
	}
	

	public ObservableList<Conocimiento> getHabilidades() {
		return this.habilidadesProperty().get();
	}
	

	public void setHabilidades(final ObservableList<Conocimiento> habilidades) {
		this.habilidadesProperty().set(habilidades);
	}
	

}

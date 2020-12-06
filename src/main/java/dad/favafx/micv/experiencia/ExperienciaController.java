package dad.favafx.micv.experiencia;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.model.Experiencia;
import javafx.fxml.Initializable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ExperienciaController implements Initializable {

	//model
	private ListProperty<Experiencia> experiencias = new SimpleListProperty<Experiencia>(FXCollections.observableArrayList());
	
	// view

	@FXML
	private BorderPane view;

	@FXML
	private TableView<Experiencia> experienciaView;

	@FXML
	private TableColumn<Experiencia, LocalDate> desdeColumn;

	@FXML
	private TableColumn<Experiencia, LocalDate> hastaColumn;

	@FXML
	private TableColumn<Experiencia, String> denominacionColumn;

	@FXML
	private TableColumn<Experiencia, String> empleadorColumn;

	@FXML
	private Button añadirExperienciaButton;

	@FXML
	private Button eliminarExperienciaButton;
	
	//Ventana nueva 
	
	private Stage stage;
	
	@FXML
    private BorderPane viewAddExperiencia;

    @FXML
    private TextField denominacionField;

    @FXML
    private TextField empleadorField;

    @FXML
    private DatePicker desdePicker;

    @FXML
    private DatePicker hastaPicker;

    @FXML
    private Button crearExperienciaButton;

    @FXML
    private Button cancelarExperienciaButton;

    @FXML
    void onCancelarExperienciaButton(ActionEvent event) {
    	stage.close();
    }

    @FXML
    void onCrearExperienciaButton(ActionEvent event) {
    	Experiencia exp = new Experiencia();
    	exp.setDenominacion(denominacionField.getText());
    	exp.setEmpleador(empleadorField.getText());
    	exp.setDesde(desdePicker.getValue());
    	exp.setHasta(hastaPicker.getValue());
    	
    	experiencias.add(exp);
    }
	
	
	
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
		desdeColumn.setCellValueFactory(v -> v.getValue().desdeProperty());
		hastaColumn.setCellValueFactory(v -> v.getValue().hastaProperty());
		denominacionColumn.setCellValueFactory(v -> v.getValue().denominacionProperty());
		empleadorColumn.setCellValueFactory(v -> v.getValue().empleadorProperty());

		desdeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		hastaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		denominacionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		empleadorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		experiencias.addListener((o, ov, nv) -> onExperienciaChanged(o, ov, nv));

	}

	private void onExperienciaChanged(ObservableValue<? extends ObservableList<Experiencia>> o,
			ObservableList<Experiencia> ov, ObservableList<Experiencia> nv) {
		
		if( ov != null ) {
			experienciaView.itemsProperty().unbindBidirectional(experiencias);
		}
		
		if( nv != null ) {
			experienciaView.itemsProperty().bindBidirectional(experiencias);
		}
		
	}

	@FXML
	void addExperiencia(ActionEvent event) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddExperiencia.fxml"));
		loader.setController(this);
		loader.load();
		
		stage = new Stage();
		Scene scene = new Scene(loader.getRoot(), 600, 200);
		stage.setTitle("Nueva experiencia");
		stage.initModality(Modality.WINDOW_MODAL);
		// Hasta que no se cierre la ventana, la ventana padre está abierta
		stage.initOwner(view.getScene().getWindow());
		stage.setScene(scene);
		stage.setResizable(true);
		stage.show();
	}

	@FXML
	void deleteExperiencia(ActionEvent event) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmacion de eliminación");
		alert.setHeaderText("Cuidado, vas a borrar una experiencia.");
		alert.setContentText("¿Estás seguro/a?");

		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			experiencias.remove(experienciaView.getSelectionModel().getSelectedItem());
		}
		
	}

	public final ListProperty<Experiencia> experienciasProperty() {
		return this.experiencias;
	}
	

	public final ObservableList<Experiencia> getExperiencias() {
		return this.experienciasProperty().get();
	}
	

	public final void setExperiencias(final ObservableList<Experiencia> experiencias) {
		this.experienciasProperty().set(experiencias);
	}
	

}

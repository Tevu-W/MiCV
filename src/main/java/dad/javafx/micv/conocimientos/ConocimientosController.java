package dad.javafx.micv.conocimientos;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.model.Conocimiento;
import dad.javafx.micv.model.Idioma;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class ConocimientosController implements Initializable {

	// model
	private ListProperty<Conocimiento> habilidades = new SimpleListProperty<Conocimiento>(
			FXCollections.observableArrayList());

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
	private Button AñadirIdiomaButton1;

	@FXML
	private Button EliminarConocimientoButton;

	// Ventana sin idioma

	private Stage stageSinIdioma;

	@FXML
	private GridPane viewSinIdioma;

	@FXML
	private TextField denominacionSinIdiomaField;

	@FXML
	private ComboBox<Nivel> nivelComboSinIdioma;

	@FXML
	private Button reinicioSinIdioma;

	@FXML
	private Button crearConocimientoSinIdioma;

	@FXML
	private Button cancelarSinIdioma;

	// Ventana con idioma

	private Stage stageConIdioma;

	@FXML
	private GridPane viewConIdioma;

	@FXML
	private TextField denominacionFieldConIdioma;

	@FXML
	private TextField certificacionField;

	@FXML
	private ComboBox<Nivel> nivelComboConIdioma;

	@FXML
	private Button reinicioButtonConIdioma;

	@FXML
	private Button crearConocimientoButtonConIdioma;

	@FXML
	private Button cancelarButtonConIdioma;

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
		stageSinIdioma.close();
	}

	@FXML
	void onCrearConocimientoSinIdioma(ActionEvent event) {

		Conocimiento conocimiento = new Conocimiento();
		conocimiento.setDenominacion(denominacionSinIdiomaField.getText());
		conocimiento.setNivel(nivelComboSinIdioma.getSelectionModel().getSelectedItem());

		habilidades.add(conocimiento);

	}

	@FXML
	void onReinicioSinIdioma(ActionEvent event) {
		nivelComboSinIdioma.getSelectionModel().clearSelection();
	}

	@FXML
	void OnCancelarConIdioma(ActionEvent event) {
		stageConIdioma.close();
	}

	@FXML
	void OnCrearConocimientoConIdioma(ActionEvent event) {

		Idioma idioma = new Idioma();
		idioma.setCertificacion(certificacionField.getText());

		Conocimiento conocimiento = new Conocimiento();
		conocimiento.setDenominacion(denominacionFieldConIdioma.getText());
		conocimiento.setNivel(nivelComboConIdioma.getSelectionModel().getSelectedItem());
		conocimiento.setIdioma(idioma);

		habilidades.add(conocimiento);

	}

	@FXML
	void onReinicioConIdioma(ActionEvent event) {
		nivelComboConIdioma.getSelectionModel().clearSelection();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		try {
			nivelComboSinIdioma.getItems().setAll(Nivel.values());

		} catch (NullPointerException e) {
		}

		try {
			nivelComboConIdioma.getItems().setAll(Nivel.values());

		} catch (NullPointerException e) {
		}

		denomColumn.setCellValueFactory(v -> v.getValue().denominacionProperty());
		nivelColumn.setCellValueFactory(v -> v.getValue().nivelProperty());

		denomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		nivelColumn.setCellFactory(ComboBoxTableCell.forTableColumn(Nivel.values()));

		habilidades.addListener((o, ov, nv) -> onConocimientoChanged(o, ov, nv));

	}

	private void onConocimientoChanged(ObservableValue<? extends ObservableList<Conocimiento>> o,
			ObservableList<Conocimiento> ov, ObservableList<Conocimiento> nv) {

		if (ov != null) {
			conocimientosView.itemsProperty().unbindBidirectional(habilidades);
		}

		if (nv != null) {
			conocimientosView.itemsProperty().bindBidirectional(habilidades);
		}

	}

	@FXML
	void onAñadirConocimientoConIdioma(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddConocimientoConIdioma.fxml"));
		loader.setController(this);
		loader.load();

		stageConIdioma = new Stage();
		Scene scene = new Scene(loader.getRoot(), 600, 200);
		stageConIdioma.setTitle("Nuevo conocimiento");
		stageConIdioma.initModality(Modality.WINDOW_MODAL);
		// Hasta que no se cierre la ventana, la ventana padre está abierta
		stageConIdioma.initOwner(view.getScene().getWindow());
		stageConIdioma.setScene(scene);
		stageConIdioma.getIcons().add(new Image("/images/cv64x64.png"));
		stageConIdioma.setResizable(true);
		stageConIdioma.show();

	}

	@FXML
	void onAñadirConocimientoSinIdioma(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddConocimientoSinIdioma.fxml"));
		loader.setController(this);
		loader.load();

		stageSinIdioma = new Stage();
		Scene scene = new Scene(loader.getRoot(), 600, 200);
		stageSinIdioma.setTitle("Nuevo conocimiento");
		stageSinIdioma.initModality(Modality.WINDOW_MODAL);
		// Hasta que no se cierre la ventana, la ventana padre está abierta
		stageSinIdioma.initOwner(view.getScene().getWindow());
		stageSinIdioma.setScene(scene);
		stageSinIdioma.getIcons().add(new Image("/images/cv64x64.png"));
		stageSinIdioma.setResizable(true);
		stageSinIdioma.show();

	}

	@FXML
	void onEliminarConocimiento(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmacion de eliminación");
		alert.setHeaderText("Cuidado, vas a borrar un conocimiento.");
		alert.setContentText("¿Estás seguro/a?");

		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			habilidades.remove(conocimientosView.getSelectionModel().getSelectedItem());
		}
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

package dad.javafx.micv.formacion;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import dad.javafx.micv.model.Titulo;
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
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

public class FormacionController implements Initializable {

	// model
	private ListProperty<Titulo> formacion = new SimpleListProperty<Titulo>(FXCollections.observableArrayList());

	// view

	@FXML
	private BorderPane view;

	@FXML
	private TableView<Titulo> formacionView;

	@FXML
	private TableColumn<Titulo, LocalDate> desdeColumn;

	@FXML
	private TableColumn<Titulo, LocalDate> hastaColumn;

	@FXML
	private TableColumn<Titulo, String> denominacionColumn;

	@FXML
	private TableColumn<Titulo, String> organizadorColumn;

	@FXML
	private Button añadirFormacionButton;

	@FXML
	private Button eliminarFormacionButton;

	// Ventana nueva

	private Stage stage;

	@FXML
	private BorderPane viewAdd;

	@FXML
	private TextField denominacionField;

	@FXML
	private TextField organizadorField;

	@FXML
	private DatePicker desdePicker;

	@FXML
	private DatePicker hastaPicker;

	@FXML
	private Button crearButton;

	@FXML
	private Button cancelarButton;

	@FXML
	void onCancelarButton(ActionEvent event) {
		stage.close();
	}

	@FXML
	void onCrearButton(ActionEvent event) {

		Titulo titulo = new Titulo();
		titulo.setDenominacion(denominacionField.getText());
		titulo.setOrganizador(organizadorField.getText());
		titulo.setDesde(desdePicker.getValue());
		titulo.setHasta(hastaPicker.getValue());

		formacion.add(titulo);

	}

	public FormacionController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FormacionView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		desdeColumn.setCellValueFactory(v -> v.getValue().desdeProperty());
		hastaColumn.setCellValueFactory(v -> v.getValue().hastaProperty());
		denominacionColumn.setCellValueFactory(v -> v.getValue().denominacionProperty());
		organizadorColumn.setCellValueFactory(v -> v.getValue().organizadorProperty());

		desdeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		hastaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		denominacionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		organizadorColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		formacion.addListener((o, ov, nv) -> onFormacionChanged(o, ov, nv));

	}

	private void onFormacionChanged(ObservableValue<? extends ObservableList<Titulo>> o, ObservableList<Titulo> ov,
			ObservableList<Titulo> nv) {

		if (ov != null) {

			formacionView.itemsProperty().unbindBidirectional(formacion);

		}

		if (nv != null) {

			formacionView.itemsProperty().bindBidirectional(formacion);

		}

	}

	public BorderPane getView() {
		return view;
	}

	public

	@FXML void addFormacion(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddConocimiento.fxml"));
		loader.setController(this);
		loader.load();

		stage = new Stage();
		Scene scene = new Scene(loader.getRoot(), 600, 200);
		stage.setTitle("Nuevo título");
		stage.initModality(Modality.WINDOW_MODAL);
		// Hasta que no se cierre la ventana, la ventana padre está abierta
		stage.initOwner(view.getScene().getWindow());
		stage.setScene(scene);
		stage.getIcons().add(new Image("/images/cv64x64.png"));
		stage.setResizable(true);
		stage.show();
	}

	@FXML
	void deleteFormacion(ActionEvent event) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmacion de eliminación");
		alert.setHeaderText("Cuidado, vas a borrar una formación.");
		alert.setContentText("¿Estás seguro/a?");

		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			formacion.remove(formacionView.getSelectionModel().getSelectedItem());
		}

	}

	public final ListProperty<Titulo> formacionProperty() {
		return this.formacion;
	}

	public final ObservableList<Titulo> getFormacion() {
		return this.formacionProperty().get();
	}

	public final void setFormacion(final ObservableList<Titulo> formacion) {
		this.formacionProperty().set(formacion);
	}

}

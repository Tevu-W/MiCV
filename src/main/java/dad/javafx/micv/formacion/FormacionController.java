package dad.javafx.micv.formacion;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

public class FormacionController implements Initializable {

	//model
	private ListProperty<Titulo> formacion = new SimpleListProperty<Titulo>(FXCollections.observableArrayList());
	
	private addConocimientoWindow ventana;
	
	//view
	
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
		
		if( ov != null ) {
			
			formacionView.itemsProperty().unbindBidirectional(formacion);
			
		}
		
		if( nv != null ) {
			
			formacionView.itemsProperty().bindBidirectional(formacion);
			
		}
		
		
		
	}


	public BorderPane getView() {
		return view;
	}
	
	public 
	
	@FXML
    void addFormacion(ActionEvent event) {
		try {
			
			ventana = new addConocimientoWindow();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void deleteFormacion(ActionEvent event) {

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

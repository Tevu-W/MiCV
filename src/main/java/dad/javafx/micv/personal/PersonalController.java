package dad.javafx.micv.personal;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.model.Nacionalidad;
import dad.javafx.micv.model.Personal;
import javafx.fxml.Initializable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;

public class PersonalController implements Initializable {

	//model
	
	private ObjectProperty<Personal> personal = new SimpleObjectProperty<>();
	
	
	//view
	
	@FXML
    private GridPane view;

    @FXML
    private TextField identificacionText;

    @FXML
    private TextField nombreText;

    @FXML
    private TextField apellidosText;

    @FXML
    private DatePicker nacimientoPicker;

    @FXML
    private TextField codigoPostalText;

    @FXML
    private TextField localidadText;

    @FXML
    private ComboBox<String> paisCombo;

    @FXML
    private ListView<Nacionalidad> nacionalidadView; 

    @FXML
    private TextArea direccionArea;

    @FXML
    private Button añadirNacionalidadButton;

    @FXML
    private Button quitarNacionalidadButton;

    
    public PersonalController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PersonalView.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		personal.addListener((o, ov, nv) -> onPersonalChanged(o, ov, nv));
		
	}
	
	private void onPersonalChanged(ObservableValue<? extends Personal> o, Personal ov, Personal nv) {
		
		//System.out.println("ov= " +ov + " | nv = " +nv);
		
		if( ov != null ) {
			
			identificacionText.textProperty().unbindBidirectional(ov.identificacionProperty());
			nombreText.textProperty().unbindBidirectional(ov.nombreProperty());
			apellidosText.textProperty().unbindBidirectional(ov.apellidosProperty());
			nacimientoPicker.valueProperty().unbindBidirectional(ov.fechaNacimientoProperty());
			codigoPostalText.textProperty().unbindBidirectional(ov.codigoPostalProperty());
			localidadText.textProperty().unbindBidirectional(ov.localidadProperty());
			//Duda de desdbindeo
			//paisCombo.valueProperty().unbindBidirectional(ov.paisProperty());
			nacionalidadView.itemsProperty().unbindBidirectional(ov.nacionalidadesProperty());
			direccionArea.textProperty().unbindBidirectional(ov.direccionProperty());
			
		}
		
		if( nv != null ) {
			
			identificacionText.textProperty().bindBidirectional(nv.identificacionProperty());
			nombreText.textProperty().bindBidirectional(nv.nombreProperty());
			apellidosText.textProperty().bindBidirectional(nv.apellidosProperty());
			nacimientoPicker.valueProperty().bindBidirectional(nv.fechaNacimientoProperty());
			codigoPostalText.textProperty().bindBidirectional(nv.codigoPostalProperty());
			localidadText.textProperty().bindBidirectional(nv.localidadProperty());
			//Duda
			//paisCombo.valueProperty().unb
			nacionalidadView.itemsProperty().bindBidirectional(nv.nacionalidadesProperty());
			direccionArea.textProperty().bindBidirectional(nv.direccionProperty());
			
		}
	}

	public GridPane getView() {
		return view;
	}
	
	@FXML
    void onAñadirNacionalidadButton(ActionEvent event) {
		
    }

    @FXML
    void onQuitarNacionalidadButton(ActionEvent event) {

    }

	public final ObjectProperty<Personal> personalProperty() {
		return this.personal;
	}
	

	public final Personal getPersonal() {
		return this.personalProperty().get();
	}
	

	public final void setPersonal(final Personal personal) {
		this.personalProperty().set(personal);
	}
	


}

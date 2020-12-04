package dad.javafx.micv.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import dad.javafx.micv.model.Nacionalidad;
import dad.javafx.micv.model.Personal;
import javafx.fxml.Initializable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PersonalController implements Initializable {

	//model
	
	private ObjectProperty<Personal> personal = new SimpleObjectProperty<>();
	private ListProperty<Nacionalidad> nacionalidades = new SimpleListProperty<Nacionalidad>(FXCollections.observableArrayList());
	private ObjectProperty<Nacionalidad> seleccionado = new SimpleObjectProperty<Nacionalidad>();
	
	
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
		
		//Nacionalidades
		InputStream stream = getClass().getResourceAsStream("/csv/nacionalidades.csv");
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String linea;
		
		try {
			while ((linea = reader.readLine()) != null) {
				Nacionalidad nacionalidad = new Nacionalidad(linea);
				nacionalidades.add(nacionalidad);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		paisCombo.getItems().addAll(devolver_paises());
		
		seleccionado.bind(nacionalidadView.getSelectionModel().selectedItemProperty());
	
	}
	
	private void onPersonalChanged(ObservableValue<? extends Personal> o, Personal ov, Personal nv) {
		
		
		if( ov != null ) {
			
			identificacionText.textProperty().unbindBidirectional(ov.identificacionProperty());
			nombreText.textProperty().unbindBidirectional(ov.nombreProperty());
			apellidosText.textProperty().unbindBidirectional(ov.apellidosProperty());
			nacimientoPicker.valueProperty().unbindBidirectional(ov.fechaNacimientoProperty());
			codigoPostalText.textProperty().unbindBidirectional(ov.codigoPostalProperty());
			localidadText.textProperty().unbindBidirectional(ov.localidadProperty());
			paisCombo.valueProperty().unbindBidirectional(ov.paisProperty());
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
			paisCombo.valueProperty().bindBidirectional(nv.paisProperty());
			nacionalidadView.itemsProperty().bindBidirectional(nv.nacionalidadesProperty());
			direccionArea.textProperty().bindBidirectional(nv.direccionProperty());
			
		}
	}
	
	
	public ListProperty<String> devolver_paises() {
		
		ListProperty<String> paises = new SimpleListProperty<String>(FXCollections.observableArrayList());
		InputStream stream = getClass().getResourceAsStream("/csv/paises.csv");
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String linea;
		
		try {
			while ((linea = reader.readLine()) != null) {
				paises.add(linea);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return paises;
	}
	

	public GridPane getView() {
		return view;
	}
	
	@FXML
    void onAñadirNacionalidadButton(ActionEvent event) {
		
		ChoiceDialog<Nacionalidad> dialogo_nacionalidad = new ChoiceDialog<Nacionalidad>(nacionalidades.get(0), nacionalidades);
		
		dialogo_nacionalidad.setTitle("Nueva nacionalidad");
		dialogo_nacionalidad.setHeaderText("Añadir nacionalidad");
		dialogo_nacionalidad.setContentText("Seleccione una nacionalidad");
		
		Optional<Nacionalidad> result = dialogo_nacionalidad.showAndWait();
		if(result.isPresent() && !getPersonal().getNacionalidades().contains(result.get()) ) {
			getPersonal().getNacionalidades().add(result.get());
		}
		
    }

    @FXML
    void onQuitarNacionalidadButton(ActionEvent event) {
    	getPersonal().getNacionalidades().remove(seleccionado.get());
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

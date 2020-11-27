package dad.javafx.micv;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.favafx.micv.experiencia.ExperienciaController;
import dad.javafx.micv.conocimientos.ConocimientosController;
import dad.javafx.micv.contacto.ContactoController;
import dad.javafx.micv.formacion.FormacionController;
import dad.javafx.micv.model.CV;
import dad.javafx.micv.personal.PersonalController;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable{

	//controllers
	private PersonalController personalController = new PersonalController();
	private ContactoController contactoController = new ContactoController();
	private FormacionController formacionController = new FormacionController();
	private ExperienciaController experienciaController = new ExperienciaController();
	private ConocimientosController conocimientosController = new ConocimientosController();
	
	//model
	private ObjectProperty<CV> cv = new SimpleObjectProperty<>();
	
	//view
	
	@FXML
    private BorderPane view;

    @FXML
    private Tab PersonalTab;

    @FXML
    private Tab ContactoTab;

    @FXML
    private Tab FormacionTab;

    @FXML
    private Tab ExperienciaTab;

    @FXML
    private Tab ConocimientosTab;
    
    
    public MainController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
		loader.setController(this);
		loader.load();
	}
    
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
    	PersonalTab.setContent(personalController.getView());
    	ContactoTab.setContent(contactoController.getView());
    	FormacionTab.setContent(formacionController.getView());
    	ExperienciaTab.setContent(experienciaController.getView());
    	ConocimientosTab.setContent(conocimientosController.getView());
    	
    	cv.addListener((o, ov, nv) -> onCVChanged(o, ov, nv));
    	
    	cv.set(new CV());
	}
    
    private void onCVChanged(ObservableValue<? extends CV> o, CV ov, CV nv) {
    	
    	if( ov != null ) {
    		personalController.personalProperty().unbind();
    		//desbindear el resto de controllers
    	}
    	
    	if( nv != null ) {
    		personalController.personalProperty().bind(nv.personalProperty());
    		//bindear el resto de controllers
    	}
    }
    
    public BorderPane getView() {
		return view;
	}

    @FXML
    void OnSalirAction(ActionEvent event) {
    	Platform.exit();
    }

    @FXML
    void onAbrirAction(ActionEvent event) {

    }

    @FXML
    void onGuardarAction(ActionEvent event) {

    }

    @FXML
    void onNuevoAction(ActionEvent event) {
    	System.out.println("Has pulsado nuevo");
    	cv.set(new CV());
    }

	
	
	
}

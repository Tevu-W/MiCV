package dad.javafx.micv.contacto;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ContactoController implements Initializable {

	
	
	
	//view
	
	@FXML
    private SplitPane view;
	
	@FXML
    private TableView<?> telefonosView;

    @FXML
    private TableColumn<?, ?> numeroColumn;

    @FXML
    private TableColumn<?, ?> tipoColumn;

    @FXML
    private Button añadirTelefonoButton;

    @FXML
    private Button eliminarTelefonoButton;

    @FXML
    private TableView<?> correoView;

    @FXML
    private TableColumn<?, ?> emailColumn;

    @FXML
    private Button añadirCorreoButton;

    @FXML
    private Button eliminarCorreoButton;

    @FXML
    private TableColumn<?, ?> urlColumn;

    @FXML
    private Button añadirWebButton;

    @FXML
    private Button eliminarWebButton;
    
    
    public ContactoController() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ContactoView.fxml"));
    	loader.setController(this);
    	loader.load();
	}
    
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
    	
	}
    
    public SplitPane getView() {
		return view;
	}
 
    @FXML
    void addCorreo(ActionEvent event) {

    }

    @FXML
    void addTelefono(ActionEvent event) {

    }

    @FXML
    void addWeb(ActionEvent event) {

    }

    @FXML
    void deleteTelefono(ActionEvent event) {

    }

    @FXML
    void deleteWeb(ActionEvent event) {

    }

    @FXML
    void eliminarCorreo(ActionEvent event) {

    }
	
	
	

}

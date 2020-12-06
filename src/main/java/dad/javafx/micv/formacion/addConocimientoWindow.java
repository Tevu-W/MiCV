package dad.javafx.micv.formacion;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class addConocimientoWindow implements Initializable{

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

    public addConocimientoWindow() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FormacionView.fxml"));
		loader.setController(this);
		loader.load();
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
		
	}
	
	@FXML
    void onCancelarButton(ActionEvent event) {
		
    }

    @FXML
    void onCrearButton(ActionEvent event) {
    	System.out.println("Hola mundo");
    }
	
	public BorderPane getViewAdd() {
		return viewAdd;
	}
	
}

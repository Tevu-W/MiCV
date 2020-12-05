package dad.javafx.micv.contacto;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.model.CV;
import dad.javafx.micv.model.Contacto;
import dad.javafx.micv.model.Email;
import dad.javafx.micv.model.Telefono;
import dad.javafx.micv.model.TipoTelefono;
import dad.javafx.micv.model.Web;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class ContactoController implements Initializable {

	//model
	private ObjectProperty<Contacto> contacto = new SimpleObjectProperty<Contacto>();
	private ListProperty<Telefono> telefonos = new SimpleListProperty<Telefono>(FXCollections.observableArrayList());
	//private ListProperty<Email> emails = new SimpleListProperty<Email>(FXCollections.observableArrayList());
	//private ListProperty<Web> webs = new SimpleListProperty<Web>(FXCollections.observableArrayList());
	
	
	//view
	
	@FXML
    private SplitPane view;
	
	@FXML
    private TableView<Telefono> telefonosView;

    @FXML
    private TableColumn<Telefono, String> numeroColumn;

    @FXML
    private TableColumn<Telefono, TipoTelefono> tipoColumn;

    @FXML
    private Button añadirTelefonoButton;

    @FXML
    private Button eliminarTelefonoButton;

    @FXML
    private TableView<Email> correoView;

    @FXML
    private TableColumn<Email, String> emailColumn;

    @FXML
    private Button añadirCorreoButton;

    @FXML
    private Button eliminarCorreoButton;
    
    @FXML
    private TableView<Web> webView;

    @FXML
    private TableColumn<Web, String> urlColumn;

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
    	
    	contacto.addListener((o, ov, nv) -> onContactChanged(o, ov, nv));
    	
    	numeroColumn.setCellValueFactory(v -> v.getValue().numeroProperty());
    	tipoColumn.setCellValueFactory(v -> v.getValue().tipoProperty());
    	emailColumn.setCellValueFactory(v -> v.getValue().direccionProperty());
    	urlColumn.setCellValueFactory(v -> v.getValue().urlProperty());
    	
    	numeroColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    	tipoColumn.setCellFactory(ComboBoxTableCell.forTableColumn(TipoTelefono.values()));
    	emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    	urlColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    	
    	
	}
    
    private void onContactChanged(ObservableValue<? extends Contacto> o, Contacto ov, Contacto nv) {
		// TODO Auto-generated method stub
		
    	if( ov != null ) {
    		
    		telefonosView.itemsProperty().unbindBidirectional(ov.telefonosProperty());
    		
    	}
    	
    	if( nv != null ) {
    		
    		telefonosView.itemsProperty().bindBidirectional(nv.telefonosProperty());
    		
    	}
    	
	}


	public SplitPane getView() {
		return view;
	}
 
    @FXML
    void addCorreo(ActionEvent event) {

    }

    @FXML
    void addTelefono(ActionEvent event) {

    	Dialog<Pair<String, TipoTelefono>> dialog = new Dialog<>();
    	dialog.setTitle("Nuevo teléfono");
    	dialog.setHeaderText("Introduzca el nuevo número de teléfono");
    	    	
    	//Set de los tipos de boton
    	ButtonType añadir = new ButtonType("Añadir", ButtonData.OK_DONE);
    	ButtonType cancel = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
    	dialog.getDialogPane().getButtonTypes().addAll(añadir, cancel);
    	
    	//Creacion del panel principal
    	GridPane grid = new GridPane();
    	grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(20, 150, 10, 10));
    	
    	TextField numero = new TextField();
    	numero.setPromptText("Número de teléfono");
    	ComboBox<TipoTelefono> comboTelefono = new ComboBox<TipoTelefono>();
    	comboTelefono.setPromptText("Seleccione un tipo");
    	comboTelefono.getItems().addAll(TipoTelefono.values());
    	
    	grid.add(new Label("Número: "), 0, 0);
    	grid.add(numero, 1, 0);
    	grid.add(new Label("Tipo: "), 0, 1);
    	grid.add(comboTelefono, 1, 1);
    	
    	dialog.getDialogPane().setContent(grid);
    	
    	dialog.setResultConverter(dialogButton -> {
    		if(dialogButton == añadir) {
    			return new Pair<>(numero.getText(), comboTelefono.getSelectionModel().getSelectedItem());
    		}
    		return null;
    	});
    	
    	Optional<Pair<String, TipoTelefono>> result = dialog.showAndWait();
    	result.ifPresent(onActionMuestra -> {
    		System.out.println("Telefono:" +onActionMuestra.getKey() + ", Tipo:" +onActionMuestra.getValue());
    		Telefono telef = new Telefono();
    		telef.setNumero(onActionMuestra.getKey());
    		telef.setTipo(onActionMuestra.getValue());
    		//getContacto().getTelefonos().add(telef); Lo mismo que lo de abajo
    		contacto.getValue().getTelefonos().add(telef);
    	});
    	
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


	public final ObjectProperty<Contacto> contactoProperty() {
		return this.contacto;
	}
	


	public final Contacto getContacto() {
		return this.contactoProperty().get();
	}
	


	public final void setContacto(final Contacto contacto) {
		this.contactoProperty().set(contacto);
	}
	
	
	
	

}

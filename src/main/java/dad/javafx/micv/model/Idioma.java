package dad.javafx.micv.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Idioma {

	private StringProperty certificacion = new SimpleStringProperty();

	public StringProperty certificacionProperty() {
		return this.certificacion;
	}
	

	public String getCertificacion() {
		return this.certificacionProperty().get();
	}
	

	public void setCertificacion(final String certificacion) {
		this.certificacionProperty().set(certificacion);
	}
	
	
	
	
}

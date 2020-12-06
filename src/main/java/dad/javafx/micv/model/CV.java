package dad.javafx.micv.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CV {

	private ObjectProperty<Personal> personal = new SimpleObjectProperty<Personal>(new Personal());
	private ObjectProperty<Contacto> contacto = new SimpleObjectProperty<Contacto>(new Contacto());
	private ListProperty<Titulo> formacion = new SimpleListProperty<Titulo>(FXCollections.observableArrayList());
	private ListProperty<Experiencia> experiencias = new SimpleListProperty<Experiencia>(
			FXCollections.observableArrayList());
	private ListProperty<Conocimiento> habilidades = new SimpleListProperty<Conocimiento>(
			FXCollections.observableArrayList());

	public final ObjectProperty<Personal> personalProperty() {
		return this.personal;
	}

	public final Personal getPersonal() {
		return this.personalProperty().get();
	}

	public final void setPersonal(final Personal personal) {
		this.personalProperty().set(personal);
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

	public final ListProperty<Titulo> formacionProperty() {
		return this.formacion;
	}

	public final ObservableList<Titulo> getFormacion() {
		return this.formacionProperty().get();
	}

	public final void setFormacion(final ObservableList<Titulo> formacion) {
		this.formacionProperty().set(formacion);
	}

	public final ListProperty<Experiencia> experienciasProperty() {
		return this.experiencias;
	}

	public final ObservableList<Experiencia> getExperiencias() {
		return this.experienciasProperty().get();
	}

	public final void setExperiencias(final ObservableList<Experiencia> experiencias) {
		this.experienciasProperty().set(experiencias);
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

	/*
	 * public static void main(String[] args) {
	 * 
	 * CV cv = new CV(); cv.getPersonal().setNombre("Chuck");
	 * cv.getPersonal().setApellidos("Norris");
	 * cv.getPersonal().getNacionalidades().add(new Nacionalidad("estadounidense"));
	 * 
	 * Gson gson = FxGson.fullBuilder().setPrettyPrinting().create();
	 * 
	 * String json = gson.toJson(cv); // convertir modelo de datos a json
	 * (marshalling)
	 * 
	 * System.out.println(json);
	 * 
	 * cv = gson.fromJson(json, CV.class); // convertir json a modelo de datos
	 * (unmarshalling)
	 * 
	 * }
	 */

}

package aed.hibernate.controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;

import aed.hibernate.funcionalidad.Funciones;
import aed.hibernate.model.Contratos;
import aed.hibernate.model.Equipos;
import aed.hibernate.model.EquiposObservaciones;
import aed.hibernate.model.Futbolistas;
import aed.hibernate.model.Ligas;
import aed.hibernate.utils.HibernateUtilGeneraTablas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Controller implements Initializable{

	public Controller() throws IOException {

		this.setTablasGeneradas(false);

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/View.fxml"));
		loader.setController(this);
		loader.load();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		

	}

	@FXML
	void onGenerarTablas(ActionEvent event) {

		if (!this.isTablasGeneradas()) {

			Session sesion = HibernateUtilGeneraTablas.getSessionFactory().openSession();

			sesion.beginTransaction();

			sesion.close();

			this.setTablasGeneradas(true);

		} else {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Aviso");
			alert.setHeaderText(null);
			alert.setContentText("¡Las tablas ya están generadas!");

			alert.showAndWait();

		}

	}

	//////////////////// INSERCIONES ////////////////////

	@FXML
	void onActionInsertaLiga(ActionEvent event) {

		if (textField_insertaLiga_codLiga.getText().matches("") | textField_insertaLiga_nomLiga.getText().matches("")) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Información");
			alert.setHeaderText(null);
			alert.setContentText("No pueden quedar campos vacíos");

			alert.showAndWait();

		} else {

			Funciones funcion = new Funciones();

			Ligas liga = new Ligas();

			liga.setCodLiga(textField_insertaLiga_codLiga.getText());

			liga.setNomLiga(textField_insertaLiga_nomLiga.getText());

			funcion.insertarLiga(liga);

			this.rellenar_combo_codLiga();

		}

	}

	@FXML
	void onActionInsertaEquipo(ActionEvent event) {

		if (textField_insertaEquipo_nomEquipo.getText().matches("")
				| textField_insertaEquipo_localidad.getText().matches("")) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Información");
			alert.setHeaderText(null);
			alert.setContentText("No pueden quedar campos vacíos");

			alert.showAndWait();

		} else {

			Equipos equipo = new Equipos();

			equipo.setNomEquipo(textField_insertaEquipo_nomEquipo.getText());

			equipo.setLocalidad(textField_insertaEquipo_localidad.getText());

			if (checkBox_insertaEquipo_internacional.isSelected()) {

				equipo.setInternacional(true);

			} else {

				equipo.setInternacional(false);

			}

			Funciones funcion = new Funciones();

			List<Ligas> list_ligas = funcion.getLigas();

			for (int x = 0; x < list_ligas.size(); x++) {

				if (list_ligas.get(x).getCodLiga()
						.matches(combo_insertaEquipo_codLiga.getSelectionModel().getSelectedItem())) {

					equipo.setLigas_objeto(list_ligas.get(x));

				}

			}

			funcion.insertarEquipo(equipo);

			this.rellenar_combo_codEquipo();

			// RELLENAMOS EL COMBO DE EQUIPOS DE LA PESTAÑA ELIMINAR

			this.rellenar_combo_nomEquipo();

		}

	}

	@FXML
	void onActionInsertaFutbolista(ActionEvent event) {

		if (textField_insertaFutbolista_codDNIoNIE.getText().matches("")
				| textField_insertaFutbolista_nacionalidad.getText().matches("")
				| textField_insertaFutbolista_nombre.getText().matches("")) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Información");
			alert.setHeaderText(null);
			alert.setContentText("No pueden quedar campos vacíos");

			alert.showAndWait();

		}

		try {

			if (textField_insertaFutbolista_codDNIoNIE.getText().length() < 9
					| textField_insertaFutbolista_codDNIoNIE.getText().length() > 9
					| Character.isDigit(textField_insertaFutbolista_codDNIoNIE.getText()
							.charAt(textField_insertaFutbolista_codDNIoNIE.getText().length() - 1))
					| !textField_insertaFutbolista_codDNIoNIE.getText().substring(0, 8).matches("[0-9]+")) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Información");
				alert.setHeaderText(null);
				alert.setContentText("Debes introducir un DNI válido");

				alert.showAndWait();

			} else {

				Futbolistas futbolista = new Futbolistas();

				futbolista.setCodDNIoNIE(textField_insertaFutbolista_codDNIoNIE.getText());

				futbolista.setNacionalidad(textField_insertaFutbolista_nacionalidad.getText());

				futbolista.setNombre(textField_insertaFutbolista_nombre.getText());

				Funciones funcion = new Funciones();

				funcion.insertarFutbolista(futbolista);

				this.rellenar_combo_codDNIoNIE();

			}

		} catch (StringIndexOutOfBoundsException e) {
			// TODO: handle exception

			e.printStackTrace();

		}

	}

	@FXML
	void onActionInsertaContrato(ActionEvent event) {

		if (textField_insertaContrato_fechaInicio.getText().matches("")
				| textField_insertaContrato_fechaFin.getText().matches("")
				| textField_insertaContrato_precioAnual.getText().matches("")
				| textField_insertaContrato_precioRescision.getText().matches("")) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Información");
			alert.setHeaderText(null);
			alert.setContentText("No pueden quedar campos vacíos");

			alert.showAndWait();

		} else {

			Funciones funcion = new Funciones();

			List<Equipos> list_equipos = funcion.getEquipos();

			List<Futbolistas> list_futbolistas = funcion.getFutbolistas();
			
			Contratos contrato = new Contratos();

			try {

				contrato.setFechaInicio(
						new SimpleDateFormat("yyyy-MM-dd").parse(textField_insertaContrato_fechaInicio.getText()));

				contrato.setFechaFin(
						new SimpleDateFormat("yyyy-MM-dd").parse(textField_insertaContrato_fechaFin.getText()));

			} catch (ParseException e) {

				System.out.println("Fallo en fecha del contrato. Comprobar fecha.");

			}

			contrato.setPrecioAnual(Integer.parseInt(textField_insertaContrato_precioAnual.getText()));

			contrato.setPrecioRecision(Integer.parseInt(textField_insertaContrato_precioRescision.getText()));
			
			for (int i = 0; i < list_futbolistas.size(); i++) {

				if (list_futbolistas.get(i).getCodDNIoNIE()
						.matches(comboBox_insertaContrato_codDNIoNIE.getSelectionModel().getSelectedItem())) {

					contrato.setFutbolistas_objeto(list_futbolistas.get(i));

				}

			}

			for (int x = 0; x < list_equipos.size(); x++) {

				if (list_equipos.get(x).getCodEquipo() == Integer
						.parseInt(comboBox_insertaContrato_codEquipo.getSelectionModel().getSelectedItem())) {

					contrato.setEquipos_objeto(list_equipos.get(x));

				}

			}

			funcion.insertarContrato(contrato);

		}

	}

	@FXML
	void onActionInsertaObservacion(ActionEvent event) {

		EquiposObservaciones observacion = new EquiposObservaciones();

		Funciones funcion = new Funciones();

		List<Equipos> list_equipos = funcion.getEquipos();

		for (int i = 0; i < list_equipos.size(); i++) {

			if (list_equipos.get(i).getCodEquipo() == comboBox_insertaObservacion_codEquipo.getSelectionModel()
					.getSelectedItem()) {

				observacion.setEquipo(list_equipos.get(i));

			}

		}

		observacion.setObservaciones(textField_insertaObservacion_observacion.getText());

		funcion.insertarObservacion(observacion);

	}

	//////////////////// ELIMINACIONES ////////////////////

	@FXML
	void onActionEliminarEquipo(ActionEvent event) {

		Funciones funcion = new Funciones();

		int codEquipo = 0;

		List<Equipos> list_equipos = funcion.getEquipos();

		List<Contratos> list_contratos = funcion.getContratos();
		
		for (int i = 0; i < list_equipos.size(); i++) {

			if (list_equipos.get(i).getNomEquipo()
					.matches(comboBox_eliminarEquipos_nomEquipo.getSelectionModel().getSelectedItem())) {

				codEquipo = list_equipos.get(i).getCodEquipo();

			}

		}
						
				
		for (int i = 0; i < list_contratos.size(); i++) {
			
			if(list_contratos.get(i).getEquipos_objeto().getCodEquipo() == codEquipo) {
								
				funcion.eliminarContrato(list_contratos.get(i).getCodContrato());
								
			}
						
		}

		funcion.eliminarEquipo(codEquipo);

		this.rellenar_combo_nomEquipo();
		this.rellenar_combo_codEquipo();

	}

	@FXML
	void onActionEliminarContrato(ActionEvent event) {

		Funciones funcion = new Funciones();

		List<Contratos> list_contratos = funcion.getContratos();

		for (int i = 0; i < list_contratos.size(); i++) {

			if (list_contratos.get(i).getFutbolistas_objeto().getCodDNIoNIE()
					.matches(comboBox_eliminarContratos_codDNIoNIE.getSelectionModel().getSelectedItem())) {

				funcion.eliminarContrato(list_contratos.get(i).getCodContrato());

			}

		}

		this.rellenar_combo_codDNIoNIE();

	}

	//////////////////// ACTUALIZAR ////////////////////
	
    @FXML
    void onActionActualizar(ActionEvent event) {
    	
    	Funciones funcion = new Funciones();
    	
    	List<Equipos> list_equipo = funcion.getEquipos();
    	
    	Equipos equipo = new Equipos();
    	
    	for (int i = 0; i < list_equipo.size(); i++) {
			
    		if(list_equipo.get(i).getNomEquipo().matches(comboBox_elegirEquipos_actualizar.getSelectionModel().getSelectedItem())) {
    			
    			equipo = list_equipo.get(i);
    			
    		}
    		
		}
    	
    	textField_actualizar_codEquipo.setText(String.valueOf(equipo.getCodEquipo()));
    	
    	equipo.getLigas_objeto().setCodLiga(comboBox_actualizar_codLiga.getSelectionModel().getSelectedItem());
    	
    	equipo.setNomEquipo(textField_actualizar_nomEquipo.getText());
    	
    	equipo.setLocalidad(textField_actualizar_localidad.getText());
    	
		
		if(checkcBox_actualizar_internacional.isSelected()) {
			
			equipo.setInternacional(true);
			
		} else {
			
			equipo.setInternacional(false);
			
		}
		
		funcion.actualizarEquipo(equipo);
		
		this.rellenar_combo_codEquipo();

    }

	//////////////////// VISUALIZAR OBSERVACIONES ////////////////////
	

    @FXML
    void onActionVisualizarEquiposObservaciones(ActionEvent event) {
    	
    	Funciones funcion = new Funciones();
    	
    	textArea_visualizarEquiposObservaciones.setText(funcion.verObservacionesEquipos());

    }

	//////////////////// VISUALIZAR TODO ////////////////////
	

    @FXML
    void onActionVisualizarTodo(ActionEvent event) {
    	
    	Funciones funcion = new Funciones();
    	
    	textArea_visualizarTodo.setText(funcion.visualizarTodo());

    }


	//////////////////// RELLENAR COMBOS ////////////////////

	private void rellenar_combo_codLiga() {

		Funciones funcion = new Funciones();

		List<Ligas> list_ligas = funcion.getLigas();

		combo_insertaEquipo_codLiga.getItems().clear();
		comboBox_actualizar_codLiga.getItems().clear();

		for (int i = 0; i < list_ligas.size(); i++) {

			combo_insertaEquipo_codLiga.getItems().add(list_ligas.get(i).getCodLiga());
			comboBox_actualizar_codLiga.getItems().add(list_ligas.get(i).getCodLiga());

		}

		combo_insertaEquipo_codLiga.getSelectionModel().selectFirst();
		comboBox_actualizar_codLiga.getSelectionModel().selectFirst();

	}

	private void rellenar_combo_codEquipo() {

		Funciones funcion = new Funciones();

		List<Equipos> list_equipos = funcion.getEquipos();

		comboBox_insertaContrato_codEquipo.getItems().clear();
		comboBox_insertaObservacion_codEquipo.getItems().clear();

		for (int i = 0; i < list_equipos.size(); i++) {

			comboBox_insertaContrato_codEquipo.getItems().add(String.valueOf(list_equipos.get(i).getCodEquipo()));
			comboBox_insertaObservacion_codEquipo.getItems().add(list_equipos.get(i).getCodEquipo());
		}

		comboBox_insertaContrato_codEquipo.getSelectionModel().selectFirst();
		comboBox_insertaObservacion_codEquipo.getSelectionModel().selectFirst();

	}

	private void rellenar_combo_codDNIoNIE() {

		Funciones funcion = new Funciones();

		List<Futbolistas> list_futbolistas = funcion.getFutbolistas();

		comboBox_insertaContrato_codDNIoNIE.getItems().clear();
		comboBox_eliminarContratos_codDNIoNIE.getItems().clear();

		for (int i = 0; i < list_futbolistas.size(); i++) {

			comboBox_insertaContrato_codDNIoNIE.getItems().add(list_futbolistas.get(i).getCodDNIoNIE());
			comboBox_eliminarContratos_codDNIoNIE.getItems().add(list_futbolistas.get(i).getCodDNIoNIE());

		}

		comboBox_insertaContrato_codDNIoNIE.getSelectionModel().selectFirst();
		comboBox_eliminarContratos_codDNIoNIE.getSelectionModel().selectFirst();

	}

	private void rellenar_combo_nomEquipo() {

		Funciones funcion = new Funciones();

		List<Equipos> list_equipos = funcion.getEquipos();

		comboBox_eliminarEquipos_nomEquipo.getItems().clear();
		comboBox_elegirEquipos_actualizar.getItems().clear();

		for (int i = 0; i < list_equipos.size(); i++) {

			comboBox_eliminarEquipos_nomEquipo.getItems().add(list_equipos.get(i).getNomEquipo());
			comboBox_elegirEquipos_actualizar.getItems().add(list_equipos.get(i).getNomEquipo());

		}

		comboBox_eliminarEquipos_nomEquipo.getSelectionModel().selectFirst();
		comboBox_elegirEquipos_actualizar.getSelectionModel().selectFirst();

	}
	

    @FXML
    void onActionCargarDatosPhpMyAdmin(ActionEvent event) {
    	
		this.rellenar_combo_codDNIoNIE();
		this.rellenar_combo_codEquipo();
		this.rellenar_combo_nomEquipo();
		this.rellenar_combo_codLiga();

    }


	public boolean isTablasGeneradas() {
		return tablasGeneradas;
	}

	public void setTablasGeneradas(boolean tablasGeneradas) {
		this.tablasGeneradas = tablasGeneradas;
	}

	public TabPane getView_tabPane() {
		return view_tabPane;
	}

	private boolean tablasGeneradas;

	@FXML
	private TabPane view_tabPane;

	@FXML
	private TextField textField_insertaLiga_codLiga;

	@FXML
	private TextField textField_insertaLiga_nomLiga;

	@FXML
	private TextField textField_insertaEquipo_nomEquipo;

	@FXML
	private TextField textField_insertaEquipo_localidad;

	@FXML
	private CheckBox checkBox_insertaEquipo_internacional;

    @FXML
    private CheckBox checkcBox_actualizar_internacional;

	@FXML
	private TextField textField_insertaContrato_fechaInicio;

	@FXML
	private TextField textField_insertaContrato_fechaFin;

	@FXML
	private TextField textField_insertaContrato_precioAnual;

	@FXML
	private TextField textField_insertaContrato_precioRescision;

	@FXML
	private TextField textField_insertaFutbolista_codDNIoNIE;

	@FXML
	private TextField textField_insertaFutbolista_nombre;

	@FXML
	private TextField textField_insertaFutbolista_nacionalidad;

	@FXML
	private TextField textField_insertaObservacion_observacion;
	
    @FXML
    private TextField textField_actualizar_codEquipo;

    @FXML
    private TextField textField_actualizar_nomEquipo;

    @FXML
    private TextField textField_actualizar_localidad;

	@FXML
	private ComboBox<String> combo_insertaEquipo_codLiga;

	@FXML
	private ComboBox<String> comboBox_insertaContrato_codEquipo;

	@FXML
	private ComboBox<String> comboBox_insertaContrato_codDNIoNIE;

	@FXML
	private ComboBox<Integer> comboBox_insertaObservacion_codEquipo;

	@FXML
	private ComboBox<String> comboBox_eliminarContratos_codDNIoNIE;

	@FXML
	private ComboBox<String> comboBox_eliminarEquipos_nomEquipo;
	
    @FXML
    private ComboBox<String> comboBox_elegirEquipos_actualizar;   

    @FXML
    private ComboBox<String> comboBox_actualizar_codLiga;
	
    @FXML
    private TextArea textArea_visualizarTodo;
    
    @FXML
    private TextArea textArea_visualizarEquiposObservaciones;

}

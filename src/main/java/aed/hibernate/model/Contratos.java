package aed.hibernate.model;

import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name="contratos")

public class Contratos {
	
	@Id   //hay que poner esto para indicar que es clave primaria
	@Column(columnDefinition="int(11)")
	@GeneratedValue(strategy=GenerationType.IDENTITY)  //esto se coloca porque es de tipo autonumérico y para que Eclipse reconozca el ID.
	private int codContrato;
	
/*	@Column(columnDefinition="char(9)")
	private String codDNIoNIE;*/
	
/*	@Column(columnDefinition="int(11)")
	private int codEquipo;*/
	
	@ManyToOne(cascade= {CascadeType.ALL}, fetch=FetchType.EAGER) //aquí me interesa que si elimino un futbolista, se eliminen las relaciones que hay con los contratos
	@JoinColumn(name="codDNIoNIE")
	private Futbolistas futbolistas_objeto;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.REFRESH}, fetch=FetchType.EAGER) //SI LE PONÍA ALL AQUÍ ME SALÍA TODO EL RATO EL SIGUIENTE FALLO A LA HORA DE ELIMINAR LOS CONTRATOS 
	
	/*Caused by: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Cannot delete or update a parent row: a foreign key constraint fails 
	 * (`futboldb`.`contratos`, CONSTRAINT `FK9429xjyumdq70mugof57j6pv5` FOREIGN KEY (`codEquipo`) REFERENCES `equipos` (`codEquipo`))
	 * 
	 * TUVE QUE PASARLO A PERsIST Y REFRESH. Es decir, con ALL pretendía eliminar la clave primaria de la tabla Equipos, algo que no puedo por es mi clave foránea y hay otros contratos que dependen
	 * de esa clave foránea también, osea en tal caso tendría que haber eliminado todos los contratos asociados para poder luego eliminar esa clave primaria de equipos, es decir, el equipo
	 * 
	 */

	//si se actualiza un contrato quiero que se actualice la relación que hay también
	@JoinColumn(name="codEquipo")
	private Equipos equipos_objeto;
	
	@Column(columnDefinition="date")
	private Date  fechaInicio;
	
	@Column(columnDefinition="date")
	private Date  fechaFin;
	
	@Column(columnDefinition="int(11)")
	private int precioAnual;
	
	@Column(columnDefinition="int(11)")
	private int precioRecision;


	public Contratos() {  //constructor por defecto
		super();
	}
	
	
	public Contratos(Date fechaInicio, Date fechaFin, int precioAnual, int precioRecision,
			Equipos equipos_objeto, Futbolistas futbolistas_objeto) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.precioAnual = precioAnual;
		this.precioRecision = precioRecision;
		this.equipos_objeto = equipos_objeto;
		this.futbolistas_objeto = futbolistas_objeto;
	}


	//creamos un método que nos permita leer los datos, sobreescribimos el método toString() entonces
	
	@Override
	public String toString() {
		return "Contratos [codContrato=" + codContrato + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", precioAnual=" + precioAnual
				+ ", precioRecision=" + precioRecision + "]";
	}	
	
	//en los getters y setters si incluimos el Id

	public int getCodContrato() {
		return codContrato;
	}

/*
	public String getCodDNIoNIE() {
		return codDNIoNIE;
	}

	public void setCodDNIoNIE(String codDNIoNIE) {
		this.codDNIoNIE = codDNIoNIE;
	}*/
/*
	public int getCodEquipo() {
		return codEquipo;
	}

	public void setCodEquipo(int codEquipo) {
		this.codEquipo = codEquipo;
	}*/


	public int getPrecioAnual() {
		return precioAnual;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public Date getFechaFin() {
		return fechaFin;
	}


	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	public void setPrecioAnual(int precioAnual) {
		this.precioAnual = precioAnual;
	}

	public int getPrecioRecision() {
		return precioRecision;
	}

	public void setPrecioRecision(int precioRecision) {
		this.precioRecision = precioRecision;
	}
	
	public Equipos getEquipos_objeto() {
		return equipos_objeto;
	}

	public void setEquipos_objeto(Equipos equipos_objeto) {
		this.equipos_objeto = equipos_objeto;
	}
	
	public Futbolistas getFutbolistas_objeto() {
		return futbolistas_objeto;
	}

	public void setFutbolistas_objeto(Futbolistas futbolistas_objeto) {
		this.futbolistas_objeto = futbolistas_objeto;
	}


}

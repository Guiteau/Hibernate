package aed.hibernate.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="equiposObservaciones")

public class EquiposObservaciones implements Serializable{
	
	private static final long serialVersionUID = 4L;   
	
	@Id   //hay que poner esto para indicar que es clave primaria
	@GeneratedValue(generator = "myForeign")
	@GenericGenerator( name = "myForeign", strategy = "foreign",
	parameters = {@org.hibernate.annotations.Parameter(name = "property", value = "equipo")})
	int codEquipo;  //PARA QUE EL ESQUEMA ME QUEDE IGUAL TENDRÍA QUE COMENTAR ESTO AL PRINCIPIO
	
	//tuve que borrar el campo codEquipo para que fuera compartida la primaryKey
	
	@OneToOne(cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
	//@JoinColumn(name="codEquipo") 
	@PrimaryKeyJoinColumn
	private Equipos equipo;

	@Column(columnDefinition="varchar(200)")
	private String observaciones;

	public EquiposObservaciones() {
		super();
	}
	
	public EquiposObservaciones(String observaciones) {
		super();
		this.observaciones = observaciones;
	}

	@Override
	public String toString() {
		return "EquiposObservaciones [observaciones=" + observaciones + "]";
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	public Equipos getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipos equipo) {
		this.equipo = equipo;
	}

}

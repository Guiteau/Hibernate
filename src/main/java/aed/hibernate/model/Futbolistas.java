package aed.hibernate.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="futbolistas")

public class Futbolistas {
	
	@Id   //hay que poner esto para indicar que es clave primaria
	@Column(columnDefinition="char(9)")
	private String codDNIoNIE;
	
	@Column(columnDefinition="varchar(50)")
	private String nombre;
	
	@Column(columnDefinition="varchar(40)")
	private String nacionalidad;
	
	@OneToMany (cascade= {CascadeType.ALL}, fetch=FetchType.EAGER, mappedBy="futbolistas_objeto" ) //si borro un futbolista, eliminaría todos sus contratos asociados
	List<Contratos> contratos_list = new ArrayList<Contratos>(); 


	public Futbolistas() {
		super();
	}

	public Futbolistas(String codDNIoNIE, String nombre, String nacionalidad) {
		super();
		this.codDNIoNIE = codDNIoNIE;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}
	

	@Override
	public String toString() {
		return "Futbolistas [codDNIoNIE=" + codDNIoNIE + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + "]";
	}

	public String getCodDNIoNIE() {
		return codDNIoNIE;
	}

	public void setCodDNIoNIE(String codDNIoNIE) {
		this.codDNIoNIE = codDNIoNIE;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	public List<Contratos> getContratos_list() {
		return contratos_list;
	}

	public void setContratos_list(List<Contratos> contratos_list) {
		this.contratos_list = contratos_list;
	}

}

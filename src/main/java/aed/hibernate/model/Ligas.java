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
@Table(name="ligas")

public class Ligas {
	
	@Id   //hay que poner esto para indicar que es clave primaria
	@Column(columnDefinition="char(5)")
	private String codLiga;
	
	@Column(columnDefinition="varchar(50)")
	private String nomLiga;
	
	@OneToMany(cascade={CascadeType.PERSIST,CascadeType.REFRESH}, fetch=FetchType.EAGER, mappedBy="ligas_objeto")  //con mappedBy le indico el campo que he creado en la clase Equipos que hace el @ManyToOne
																						//me interesa que si elimino la liga, se borren las relaciones que hay con los equipos
	private List<Equipos> equipos_list = new ArrayList<Equipos>(); 
	
	
	public Ligas() {
		super();
	}

	public Ligas(String codLiga, String nomLiga) {
		super();
		this.codLiga = codLiga;
		this.nomLiga = nomLiga;
	}
	
 
	@Override
	public String toString() {
		return "Ligas [codLiga=" + codLiga + ", nomLiga=" + nomLiga + "]";
	}

	public String getCodLiga() {
		return codLiga;
	}

	public void setCodLiga(String codLiga) {
		this.codLiga = codLiga;
	}

	public String getNomLiga() {
		return nomLiga;
	}

	public void setNomLiga(String nomLiga) {
		this.nomLiga = nomLiga;
	}
	
	public List<Equipos> getEquipos_list() {
		return equipos_list;
	}

	public void setEquipos_list(List<Equipos> equipos_list) {
		this.equipos_list = equipos_list;
	}

	
}

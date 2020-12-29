package aed.hibernate.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="equipos")

public class Equipos{
	
	@Id   //hay que poner esto para indicar que es clave primaria
	@Column(columnDefinition="int (11)", name="codEquipo")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codEquipo;
		
	@Column(columnDefinition="varchar(40)")
	private String nomEquipo;
/*
	@Column(columnDefinition="char(5)")
	private String codLiga;*/
	
	@Column(columnDefinition="varchar(60)")
	private String localidad;
	
	@Column(columnDefinition="bit")  ///cuidado aquí porque esto es un dato de tipo tinyint, entonces hay que ponerlo como bit
	private boolean internacional;  //esto aquí es porque es un dato de tipo tinyint en MySQL
	
	@OneToOne(cascade= {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy="equipo") //si elimino un equipo, no puedo eliminar la observación por la clave foránea asociada
	@PrimaryKeyJoinColumn //SI COLOCO ESTO EL ESQUEMA NO ME QUEDA IGUAL QUE EN EL PDF
	private EquiposObservaciones equiposObservacion;
	
	
	@OneToMany(cascade= {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy="equipos_objeto") //con mappedBy le indico el campo que he creado en la clase Contratos que hace el @ManyToOne
																		 //si elimino un equipo que elimine los contratos asociados
	private List<Contratos> contratos_list = new ArrayList<Contratos>(); 
	
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REFRESH}, fetch=FetchType.EAGER) //sólo me interesa persist y refresh
	@JoinColumn(name="codLiga")
	private Ligas ligas_objeto;
	
	
	public Equipos() {
		super();
	}


	public Equipos(String nomEquipo, String localidad, boolean internacional, Ligas ligas_objeto) {
		super();
		this.nomEquipo = nomEquipo;
		this.localidad = localidad;
		this.internacional = internacional;
		this.ligas_objeto = ligas_objeto;
	}

	@Override
	public String toString() {
		return "Equipos [codEquipo=" + codEquipo + ", nomEquipo=" + nomEquipo + ", localidad="
				+ localidad + ", internacional=" + internacional + "]";
	}

	public int getCodEquipo() {
		return codEquipo;
	}

	public String getNomEquipo() {
		return nomEquipo;
	}

	public void setNomEquipo(String nomEquipo) {
		this.nomEquipo = nomEquipo;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
	public boolean isInternacional() {
		return internacional;
	}

	public void setInternacional(boolean internacional) {
		this.internacional = internacional;
	}

	public List<Contratos> getContratos_list() {
		return contratos_list;
	}

	public void setContratos_list(List<Contratos> contratos_list) {
		this.contratos_list = contratos_list;
	}

	public Ligas getLigas_objeto() {
		return ligas_objeto;
	}

	public void setLigas_objeto(Ligas ligas_objeto) {
		this.ligas_objeto = ligas_objeto;
	}

	public EquiposObservaciones getEquiposObservacion() {
		return equiposObservacion;
	}

	public void setEquiposObservacion(EquiposObservaciones equiposObservacion) {
		this.equiposObservacion = equiposObservacion;
	}
	
}

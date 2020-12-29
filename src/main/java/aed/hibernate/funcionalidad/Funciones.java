package aed.hibernate.funcionalidad;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import aed.hibernate.model.Contratos;
import aed.hibernate.model.Equipos;
import aed.hibernate.model.EquiposObservaciones;
import aed.hibernate.model.Futbolistas;
import aed.hibernate.model.Ligas;
import aed.hibernate.utils.HibernateUtil;

public class Funciones {

	/* VISUALIZACIÓN DE LAS TABLAS - CONSULTAS */

	public List<Ligas> getLigas() {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = session.beginTransaction();

		Query query = session.createQuery("from Ligas");

		List<Ligas> list_ligas = null;

		list_ligas = query.getResultList();

		transaction.commit();

		session.close();

		return list_ligas;

	}

	public List<Equipos> getEquipos() {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = session.beginTransaction();

		Query query = session.createQuery("from Equipos");

		List<Equipos> list_equipos = null;

		list_equipos = query.getResultList();

		transaction.commit();

		session.close();

		return list_equipos;

	}

	public List<Futbolistas> getFutbolistas() {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = session.beginTransaction();

		Query query = session.createQuery("from Futbolistas");

		List<Futbolistas> list_futbolistas = null;

		list_futbolistas = query.getResultList();

		transaction.commit();

		session.close();

		return list_futbolistas;

	}

	public List<Contratos> getContratos() {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = session.beginTransaction();

		Query query = session.createQuery("from Contratos");

		List<Contratos> list_contratos = null;

		list_contratos = query.getResultList();

		transaction.commit();

		session.close();

		return list_contratos;

	}

	public String verObservacionesEquipos() { // Funciones.java
		
		String contenido = "";

		Session sesion = HibernateUtil.getSessionFactory().openSession();

		sesion.beginTransaction();

		String consulta = "SELECT E.codEquipo, E.nomEquipo, E.ligas_objeto, E.localidad, E.internacional, EO.observaciones FROM Equipos E LEFT JOIN EquiposObservaciones EO ON E.codEquipo = EO.codEquipo";
		
		///CUIDADO AQUÍ PORQUE HAY QUE TENER EN CUENTA LO QUE HICE PARA VISUALIZAR EL CÓDIGO DE LA LIGA

		List<Object[]> list_resultado = sesion.createQuery(consulta).getResultList(); // recibiremos un listado de
																						// objetos

		Ligas liga = new Ligas();

		System.out.println();

		for (Object[] result : list_resultado) {
			contenido += "--------------------------------------------------------------------------------\n";

			liga = (Ligas) result[2];

			if (result[5] == null) { // si el campo observaciones está vacío

				contenido += "-codEquipo: " + result[0] + "\n -nomEquipo: " + result[1] + "\n -codLiga: "
						+ liga.getCodLiga() + "\n -localidad: "

						+ result[3] + "\n -internacional: " + result[4] + "\n  -No hay observaciones";

			} else {

				contenido += "-codEquipo: " + result[0] + "\n -nomEquipo: " + result[1] + "\n -codLiga: "
						+ liga.getCodLiga() + "\n -localidad: "

						+ result[3] + "\n -internacional: " + result[4] + "\n -Observaciones:" + result[5];

			}

			contenido += "\n--------------------------------------------------------------------------------";

		}

		sesion.getTransaction().commit();

		sesion.close();
		
		return contenido;

	}

	public String visualizarTodo() { // Funciones.java
		
		String todo = "";

		Session sesion = HibernateUtil.getSessionFactory().openSession();

		sesion.beginTransaction();

		Query query = sesion.createQuery("from Contratos C");

		List<Contratos> list_contratos = query.getResultList();

		System.out.println();

		for (Contratos C : list_contratos) {

			todo += "--------------------------------------------------------------------------------\n" +

					"Código equipo: " + C.getEquipos_objeto().getCodEquipo() +

							"\nNombre equipo: " + C.getEquipos_objeto().getNomEquipo() +

							"\nCodigo liga: " + C.getEquipos_objeto().getLigas_objeto().getCodLiga() +

							"\nLocalidad: " + C.getEquipos_objeto().getLocalidad() +

							"\nInternacional: " + C.getEquipos_objeto().isInternacional() +

							"\nNombre liga: " + C.getEquipos_objeto().getLigas_objeto().getNomLiga() +

							"\nDNI o NIE Futbolista: " + C.getFutbolistas_objeto().getCodDNIoNIE() +

							"\nNombre futbolista: " + C.getFutbolistas_objeto().getNombre() +

							"\nNacionalidad: " + C.getFutbolistas_objeto().getNacionalidad() +

							"\nCódigo contrato: " + C.getCodContrato() +

							"\nPrecio anual: " + C.getPrecioAnual() +

							"\nPrecio rescisión: " + C.getPrecioRecision() +

			"\n--------------------------------------------------------------------------------";

		}

		sesion.getTransaction().commit();

		sesion.close();
		
		return todo;

	}

	/* INSERCIÓN EN LAS TABLAS - INSERCIONES */

	public void insertarLiga(Ligas liga) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = session.beginTransaction();

		session.save(liga);

		transaction.commit();

		session.close();

	}

	public void insertarEquipo(Equipos equipo) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = session.beginTransaction();

		session.save(equipo);

		transaction.commit();

		session.close();

	}

	public void insertarFutbolista(Futbolistas futbolista) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = session.beginTransaction();

		session.save(futbolista);

		transaction.commit();

		session.close();

	}

	public void insertarContrato(Contratos contrato) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = session.beginTransaction();

		session.save(contrato);

		transaction.commit();

		session.close();

	}

	public void insertarObservacion(EquiposObservaciones observacion) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = session.beginTransaction();

		session.save(observacion);

		transaction.commit();

		session.close();

	}

	/* ELIMINACIÓN DE REGISTROS EN LAS TABLAS - ELIMINACIONES */

	public void eliminarEquipo(int codEquipo) {
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		
		sesion.beginTransaction();
		
		Equipos equipo = sesion.find(Equipos.class, codEquipo);
		
	//	equipo.getContratos_list().clear(); //esto aquí no hace nada
				
		sesion.close();
		
		//DEBEREMOS ELIMINAR LOS CONTRATOS ASOCIADOS AL EQUIPO TAMBIÉN DEBIDO A LA CONSTRAINT QUE NO PERMITE ELIMINAR EL EQUIPO SIN ELIMINAR SUS CONTRATOS PREVIAMENTE
		
		Session session = HibernateUtil.getSessionFactory().openSession(); //ahora eliminaremos la observación para luego eliminar el equipo
		
		Transaction transaction = session.beginTransaction();
		
		EquiposObservaciones observacion = new EquiposObservaciones();
			
		observacion.setEquipo(equipo); ///esto hay que mirarlo
		
		session.delete(observacion);
		
		session.delete(equipo);
		
		transaction.commit();
		
		session.close();
		
	}

	public void eliminarContrato(int codContrato) {
				
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		
		sesion.beginTransaction();
		
		Contratos contrato = sesion.find(Contratos.class, codContrato);
		
		sesion.close();

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = session.beginTransaction();

		session.remove(contrato);

		transaction.commit();
		
		session.close();
		
	}

	/* ACTUALIZACIÓN DE LAS TABLAS - ACTUALIZACIONES */

	public void actualizarEquipo(Equipos equipo) {

		Session sesion = HibernateUtil.getSessionFactory().openSession();

		sesion.beginTransaction();

		sesion.update(equipo);

		sesion.getTransaction().commit();

		sesion.close();

	}

	public void actualizarContrato(Contratos contrato) {

		Session sesion = HibernateUtil.getSessionFactory().openSession();

		sesion.beginTransaction();

		sesion.update(contrato);

		sesion.getTransaction().commit();

		sesion.close();

	}

}

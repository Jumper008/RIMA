/*
 *Author: Equipo 5
 *
 *Version: 1.0.0
 *
 */
 package controls;
 
 import java.util.*;
 import java.io.*;
 import java.util.Date;
 import entities.*;
 
 public class calcularTiempoSuscripcion {
	 Persona pePersona;
	 private transient Conexion conConexion;

	 public calcularTiempoSuscripcion() {
		 conConexion = new Conexion();
		 pePersona = new Persona(conConexion);
	 }
	 
	 //Valida si la Persona existe en la base de datos
   	 public boolean validarPersona(int iIDPersona){            
       return(pePersona.corroborarExistencia(iIDPersona));
     }

	 public long getTiempoSuscripcion(int iIDPersona) {

		Date fechaIngreso = pePersona.consultarFechaIngreso( iIDPersona );
		Date fechaVencimiento = pePersona.consultarFechaVencimiento( iIDPersona );

		// dif en milisegundos
		long tiempoSuscripcion = fechaIngreso.getTime() - fechaVencimiento.getTime();

		// calcular la diferencia en dias
        tiempoSuscripcion = tiempoSuscripcion / (24 * 60 * 60 * 1000);

        return tiempoSuscripcion;
	}			
 }
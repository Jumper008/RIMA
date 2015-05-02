/*
 *Author: Equipo 5
 *
 *Version: 1.0.0
 *
 */
 package com.rima.controls;
 
 import java.util.*;
 import java.io.*;
 import java.util.Date;
 import com.rima.entities.*;
 
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

	 public void getTiempoSuscripcion(int iIDPersona) {

		Date fechaIngreso = consultarFechaIngreso( iIDPersona );
		Date fechaVencimiento = consultarFechaVencimiento( iIDPersona );

		// dif en milisegundos
		tiempo = fechaIngreso.getTime() - fechaVencimiento.getTime() 

	}			
 }
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
 
 public class Login {
	 Persona pePersona;
	 private transient Conexion conConexion;
	 
	 public Login() {
		 conConexion = new Conexion();
		 pePersona = new Persona(conConexion);
	 }
	 
	 //Valida si la información introducida por el usuario en el login es valida
	 public boolean corroborarInformacion(String sCorreo, String sContrasena) {
		 return pePersona.corroborarInformacion(sCorreo, sContrasena);
	 }
 }
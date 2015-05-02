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
 
 public class altaAutor {
	 Autor auAutor;
	 private transient Conexion conConexion;
	 
	 public altaAutor() {
		 conConexion = new Conexion();
		 auAutor = new Autor(conConexion);
	 }
	 
	 public void agregarAutor(String sNombre, String sCorreo, String sContrasena, 
	 		Date dFechaNacimiento, Date dFechaIngreso, Date dFechaVencimiento, boolean bActivo) {
		 int iIDPersona = auAutor.generarID();
		 auAutor.setiIDPersona(iIDPersona);
		 auAutor.setsNombre(sNombre);
		 auAutor.setsCorreo(sCorreo);
		 auAutor.setsContrasena(sContrasena);
		 auAutor.setdFechaNacimiento(dFechaNacimiento);
		 auAutor.setdFechaIngreso(dFechaIngreso);
		 auAutor.setdFechaVencimiento(dFechaVencimiento);
		 auAutor.setbActivo(bActivo);
		 auAutor.agregarAutor(auAutor);
	 }
 }
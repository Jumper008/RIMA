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
 
 public class altaAutor {
	 Autor auAutor;
	 
	 public altaAutor() {
		 auAutor = new Autor();
	 }
	 
	 public void agregarAutor(String sNombre, String sCorreo, String sContrasena, 
	 		Date dFechaNacimiento, Date dFechaIngreso, Date dFechaVencimiento) {
		 int iIDPersona = auAutor.generarID();
		 auAutor.setiIDPersona(iIDPersona);
		 auAutor.setsNombre(sNombre);
		 auAutor.setsCorreo(sCorreo);
		 auAutor.setsContrasena(sContrasena);
		 auAutor.setdFechaNacimiento(dFechaNacimiento);
		 auAutor.setdFechaIngreso(dFechaIngreso);
		 auAutor.setdFechaVencimiento(dFechaVencimiento);
		 auAutor.setbActivo(true);
		 auAutor.agregarAutor(auAutor);
		 System.out.println("entra al sistema");
	 }
 }
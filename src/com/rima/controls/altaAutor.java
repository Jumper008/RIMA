/*
 *Author: Equipo 5
 *
 *Version: 1.0.0
 *
 */
 package com.rima.controls;
 
 import java.util.*;
 import java.sql.*;
 import java.io.*;
 import java.util.Date;
 import com.rima.entities.*;
 
 public class altaAutor {
	 Autor auAutor;
	 private transient Conexion conConexion;
	 protected Statement stmt;
	 
	 public altaAutor() {
		 conConexion = new Conexion();
		 auAutor = new Autor(conConexion);
	 }
	 
	 public boolean agregarAutor(String sNombre, String sCorreo, String sContrasena, 
	 		Date dFechaNacimiento, Date dFechaIngreso, Date dFechaVencimiento, boolean bActivo) {
		 try {
			 stmt.executeQuery("SELECT COUNT(*) FROM Persona");
		 	 ResultSet rsiIDPersona = stmt.getResultSet();
			 int iIDPersona = rsiIDPersona.getInt("COUNT(*)") + 1;
			 auAutor.setiIDPersona(iIDPersona);
			 auAutor.setsNombre(sNombre);
			 auAutor.setsCorreo(sCorreo);
			 auAutor.setsContrasena(sContrasena);
			 auAutor.setdFechaNacimiento(dFechaNacimiento);
			 auAutor.setdFechaIngreso(dFechaIngreso);
			 auAutor.setdFechaVencimiento(dFechaVencimiento);
			 auAutor.setbActivo(bActivo);
			 auAutor.agregarAutor(auAutor);
			 return true;
		 } catch (SQLException e) {System.out.println ("Cannot execute agregarAutor()" + e);}
		 return false;
	 }
 }
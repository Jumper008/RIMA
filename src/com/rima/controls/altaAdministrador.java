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
 
 public class altaAdministrador {
	 Administrador adAdministrador;
	 private transient Conexion conConexion;
	 protected Statement stmt;
	 
	 public altaAdministrador() {
		 conConexion = new Conexion();
		 adAdministrador = new Administrador(conConexion);
	 }
	 
	 public boolean agregarAdministrador(String sNombre, String sCorreo, String sContrasena, 
	 		Date dFechaNacimiento, Date dFechaIngreso, Date dFechaVencimiento, boolean bActivo) {
		 stmt.executeQuery("SELECT COUNT(*) FROM Persona");
		 ResultSet rsiIDPersona = stmt.getResultSet();
		 int iIDPersona = rsiIDPersona.getInt("COUNT(*)") + 1;
		 adAdministrador.setiIDPersona(iIDPersona);
		 adAdministrador.setsNombre(sNombre);
		 adAdministrador.setsCorreo(sCorreo);
		 adAdministrador.setsContrasena(sContrasena);
		 adAdministrador.setdFechaNacimiento(dFechaNacimiento);
		 adAdministrador.setdFechaIngreso(dFechaIngreso);
		 adAdministrador.setdFechaVencimiento(dFechaVencimiento);
		 adAdministrador.setbActivo(bActivo);
		 adAdministrador.agregarAdministrador(adAdministrador);
	 }
 }
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
 
 public class altaAdministrador {
	 Administrador adAdministrador;
	 private transient Conexion conConexion;
	 
	 public altaAdministrador() {
		 conConexion = new Conexion();
		 adAdministrador = new Administrador(conConexion);
	 }
	 
	 public void agregarAdministrador(String sNombre, String sCorreo, String sContrasena, 
	 		Date dFechaNacimiento, Date dFechaIngreso, Date dFechaVencimiento, boolean bActivo) {
		 int iIDPersona = adAdministrador.generarID();
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
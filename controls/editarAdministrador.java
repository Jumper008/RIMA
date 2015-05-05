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
 
 public class editarAdministrador {
	 Administrador adAdministrador;
	 private transient Conexion conConexion;
	 
	 public editarAdministrador() {
		 conConexion = new Conexion();
		 adAdministrador = new Administrador(conConexion);
	 }
	 
	 //Valida si el Administrador existe en la base de datos
   	 public boolean validarAdministrador(int iIDAdministrador){            
       return(adAdministrador.corroborarExistencia(iIDAdministrador));
     }

     //El iIDAdministrador no fue información metida por el usuario
	 public void updateAdministrador(int iIDAdministrador, String sNombre, String sCorreo, String sContrasena, 
	 		Date dFechaNacimiento) {
	 	 adAdministrador.setiIDPersona(iIDAdministrador);
		 adAdministrador.setsNombre(sNombre);
		 adAdministrador.setsCorreo(sCorreo);
		 adAdministrador.setsContrasena(sContrasena);
		 adAdministrador.setdFechaNacimiento(dFechaNacimiento);

		 adAdministrador.editarAdministrador(adAdministrador);
	 }
 }
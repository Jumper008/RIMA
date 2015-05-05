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
 
 public class editarJuez {
	 Juez juJuez;
	 private transient Conexion conConexion;
	 
	 public editarJuez() {
		 conConexion = new Conexion();
		 juJuez = new Juez(conConexion);
	 }
	 
	 //Valida si el Juez existe en la base de datos
   	 public boolean validarJuez(int iIDJuez){            
       return(juJuez.corroborarExistencia(iIDJuez));
     }

     //El iIDJuez no fue información metida por el usuarioJuez
	 public void updateJuezByJuez(int iIDJuez, String sNombre, String sCorreo, String sContrasena, 
	 		Date dFechaNacimiento) {
	 	 
	 	 juJuez = juJuez.consultarInformacion(iIDJuez);
		 juJuez.setsNombre(sNombre);
		 juJuez.setsCorreo(sCorreo);
		 juJuez.setsContrasena(sContrasena);
		 juJuez.setdFechaNacimiento(dFechaNacimiento);

		 juJuez.editarAutor(juJuez);
	 }

	  //El admin puede cambiar TODO menos el IDJuez
	 public void updateJuezByAdmin(int iIDJuez, String sNombre, String sCorreo, String sContrasena, 
	 		Date dFechaNacimiento, Date dFechaIngreso, Date dFechaVencimiento, boolean bActivo, boolean bJuezActivo) {
	 	 juJuez.setiIDPersona(iIDJuez);
		 juJuez.setsNombre(sNombre);
		 juJuez.setsCorreo(sCorreo);
		 juJuez.setsContrasena(sContrasena);
		 juJuez.setdFechaNacimiento(dFechaNacimiento);
		 juJuez.setdFechaIngreso(dFechaIngreso);
		 juJuez.setdFechaVencimiento(dFechaVencimiento);
		 juJuez.setbActivo(bActivo);
		 juJuez.setbJuezActivo(bJuezActivo);

		 juJuez.editarJuez(juJuez);
	 }
 }
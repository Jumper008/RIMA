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
 
 public class editarJuez {
	 Juez juJuez;
	 
	 public editarJuez() {
		 juJuez = new Juez();
	 }
	 
	 //Valida si el Juez existe en la base de datos
   	 public boolean validarJuez(int iIDJuez){            
       return(juJuez.corroborarExistencia(iIDJuez));
     }

	 public void updateJuezByJuez(int iIDJuez, String sNombre, String sCorreo, String sContrasena, 
	 		Date dFechaNacimiento) {
	 	 
	 	 juJuez = juJuez.consultarInformacion(iIDJuez);
		 juJuez.setsNombre(sNombre);
		 juJuez.setsCorreo(sCorreo);
		 juJuez.setsContrasena(sContrasena);
		 juJuez.setdFechaNacimiento(dFechaNacimiento);

		 juJuez.editarJuez(juJuez, iIDJuez);
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

		 juJuez.editarJuez(juJuez, iIDJuez);
	 }
 }
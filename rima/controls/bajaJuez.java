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
 
 public class bajaJuez {
	 Juez juJuez;

	 public bajaJuez() {
		 juJuez = new Juez();
	 }

	 //Valida si el Juez existe en la base de datos
   	 public boolean validarJuez(int iIDJuez){            
       return(juJuez.corroborarExistencia(iIDJuez));
     }
	 
	 public void eliminarJuez(int iIDJuez) {
		 juJuez.desactivarPersona(iIDJuez);
	 }
 }
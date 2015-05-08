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
 
 public class bajaAdministrador {
	 Administrador adAdministrador;

	 public bajaAdministrador() {
		 adAdministrador = new Administrador();
	 }

	 //Valida si el administrador existe en la base de datos
   	 public boolean validarAdministrador(int iIDAdministrador){            
       return(adAdministrador.corroborarExistencia(iIDAdministrador));
     }
	 
	 public void eliminarAdministrador(int iIDAdministrador) {
		 adAdministrador.desactivarPersona(iIDAdministrador);
	}
 }
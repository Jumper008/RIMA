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
 
 public class login {
	 Persona pePersona;
	 
	 public login() {
		 pePersona = new Persona();
	 }
	 
	 //Valida si la información introducida por el usuario en el login es valida
	 public boolean corroborarInformacion(String sCorreo, String sContrasena) {
		 return pePersona.corroborarInformacion(sCorreo, sContrasena);
	 }
 }
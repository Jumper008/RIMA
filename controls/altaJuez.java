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
 
 public class altaJuez {
	 Juez juJuez;

	 public altaJuez() {
		 juJuez = new Juez();
	 }
	 
	 public void agregarJuez(String sNombre, String sCorreo, String sContrasena, 
	 		Date dFechaNacimiento, Date dFechaIngreso, Date dFechaVencimiento, boolean bActivo) {

		 int iIDPersona = juJuez.generarID();
		 juJuez.setiIDPersona(iIDPersona);
		 juJuez.setsNombre(sNombre);
		 juJuez.setsCorreo(sCorreo);
		 juJuez.setsContrasena(sContrasena);
		 juJuez.setdFechaNacimiento(dFechaNacimiento);
		 juJuez.setdFechaIngreso(dFechaIngreso);
		 juJuez.setdFechaVencimiento(dFechaVencimiento);
		 juJuez.setbActivo(bActivo);
		 juJuez.agregarJuezByAdmin(juJuez);			
	 }
 }
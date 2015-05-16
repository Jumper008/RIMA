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
	 
	 public boolean agregarJuez(int iIDPersona) {

		//int iIDPersona = juJuez.generarID();
		System.out.println("Dando valor al id: " + Integer.toString(iIDPersona));
		 //System.out.println("Dando valor al id: " + Integer.toString(juJuez.iIDPersona));
		 //juJuez.setsNombre(sNombre);
		 //juJuez.setsCorreo(sCorreo);
		// juJuez.setsContrasena(sContrasena);
		 //juJuez.setdFechaNacimiento(dFechaNacimiento);
		// juJuez.setdFechaIngreso(dFechaIngreso);
		// juJuez.setdFechaVencimiento(dFechaVencimiento);
		 return juJuez.agregarJuezByAdmin(iIDPersona);	
	 }
 }
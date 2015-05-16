/*
 *Author: Equipo 5
 *
 *Version: 1.0.0
 *
 */
 package controls;
 
 import java.util.*;
 import java.io.*;
 import entities.*;
 
 public class proponerArticulo {
	 Articulo arArticulo;
	 
	 public proponerArticulo() {
		 arArticulo = new Articulo();
	 }
	 
	 public boolean agregarArticulo(String sNombre, String sResumen, int iIDAutor) {
		 arArticulo.setiIDArticulo(arArticulo.generarID());
		 arArticulo.setsNombre(sNombre);
		 arArticulo.setsResumen(sResumen);
		 arArticulo.setbPublicado(false);
		 arArticulo.setiContador(0);
		 arArticulo.setiIDRevista(-1);
		 return arArticulo.agregarArticulo(arArticulo, iIDAutor);
	 }
 }
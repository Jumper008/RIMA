/*
 *Author: Equipo 5
 *
 *Version: 1.0.0
 *
 */
 package com.rima.controls;
 
 import java.util.*;
 import java.io.*;
 import com.rima.entities.*;
 
 public class proponerArticulo {
	 Articulo arArticulo;
	 private transient Conexion conConexion;
	 
	 public proponerArticulo() {
		 conConexion = new Conexion();
		 arArticulo = new Articulo(conConexion);
	 }
	 
	 public boolean agregarArticulo(String sNombre, String sResumen) {
		 arArticulo.setiIDArticulo(arArticulo.generarID());
		 arArticulo.setsNombre(sNombre);
		 arArticulo.setsResumen(sResumen);
		 arArticulo.setbPublicado(false);
		 arArticulo.setiContador(0);
		 arArticulo.setiIDRevista(-1);
		 return arArticulo.agregarArticulo(arArticulo);
	 }
 }
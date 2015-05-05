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
 
 public class mostrarListaRevistas {
	 Revista reRevista;
	 private transient Conexion conConexion;
	 
	 public mostrarListaRevistas() {
		 conConexion = new Conexion();
		 reRevista = new Revista(conConexion);
	 }
	 
	 public Vector<Revista> obtenerListaRevistas() {
		 return reRevista.mostrarRevistasPublicadas();
	 }
 }
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
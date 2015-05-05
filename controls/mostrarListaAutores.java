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
 
 public class mostrarListaAutores {
	 Autor auAutor;
	 private transient Conexion conConexion;
	 
	 public mostrarListaAutores() {
		 conConexion = new Conexion();
		 auAutor = new Autor(conConexion);
	 }
	 
	 public Vector<Autor> obtenerListaAutores() {
		 return auAutor.mostrarNombreDeAutores();
	 }
 }
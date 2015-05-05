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
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
 
 public class mostrarListaArticulosAutor {
	 Articulo arArticulo;
	 private transient Conexion conConexion;
	 
	 public mostrarListaArticulosAutor() {
		 conConexion = new Conexion();
		 arArticulo = new Articulo(conConexion);
	 }
	 
	 public Vector<Articulo> obtenerListaArticulosAutor(int iIDPersona) {
		 return arArticulo.consultarArticulosPropios(iIDPersona);
	 }
 }
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
 
 public class mostrarListaArticulosPublicadosAutor {
	 Articulo arArticulo;
	 private transient Conexion conConexion;
	 
	 public mostrarListaArticulosPublicadosAutor() {
		 conConexion = new Conexion();
		 arArticulo = new Articulo(conConexion);
	 }
	 
	 public Vector<Articulo> obtenerListaArticulosPublicadosAutor(int iIDPersona) {
		 return arArticulo.consultarArticulosAutor(iIDPersona);
	 }
 }
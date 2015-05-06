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
 
 public class verArticulosPropuestosPropios {
	 Articulo arArticulo;
	 private transient Conexion conConexion;
	 
	 public verArticulosPropuestosPropios() {
		 conConexion = new Conexion();
		 arArticulo = new Articulo(conConexion);
	 }
	 
	 public Vector<Articulo> obtenerArticulosPropios(int iIDPersona) {
		 return arArticulo.consultarArticulosPropios(iIDPersona);
	 }
 }
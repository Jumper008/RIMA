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
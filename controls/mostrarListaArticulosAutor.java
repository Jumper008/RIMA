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
 
 public class mostrarListaArticulosAutor {
	 Articulo arArticulo;
	 Autor auAutor;
	 private transient Conexion conConexion;
	 
	 public mostrarListaArticulosAutor() {
		 conConexion = new Conexion();
		 arArticulo = new Articulo(conConexion);
		 auAutor = new Autor(conConexion);
	 }

	 //Valida si el autor por el que se buscan los artículos existe en la base de datos
   	 public boolean validarAutor(int iIDAutor){            
       return(auAutor.corroborarExistencia(iIDAutor));
     }

	 public Vector<Articulo> obtenerListaArticulosAutor(int iIDPersona) {
		 return arArticulo.consultarArticulosPropios(iIDPersona);
	 }
 }
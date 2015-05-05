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
 
 public class verArticulo {
	 Articulo arArticulo;
	 private transient Conexion conConexion;
	 
	 public verArticulo() {
		 conConexion = new Conexion();
		 arArticulo = new Articulo(conConexion);
	 }
	 
	 public boolean validarArticulo(int iIDArticulo) {
		 return arArticulo.corroborarExistencia(iIDArticulo);
	 }
	 
	 public Articulo obtenerArticulo(int iIDArticulo) {
		 return arArticulo.consultarInformacion(iIDArticulo);
	 }
 }
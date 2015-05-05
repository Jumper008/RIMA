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
 
 public class seleccionar {
	 Articulo arArticulo;
	 private transient Conexion conConexion;
	 
	 public seleccionar() {
		 conConexion = new Conexion();
		 arArticulo = new Articulo(conConexion);
	 }
	 
	 public boolean validarArticulo(int iIDArticulo) {
		 return arArticulo.corroborarExistencia(iIDArticulo);
	 }
	 
	 public boolean publicarEnRevista(int iIDArticulo, int iIDRevista) {
		 return arArticulo.agregarARevista(iIDArticulo, iIDRevista);
	 }
 }
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
 
 public class verArticuloPublicado {
	 Articulo arArticulo;
	 
	 public verArticuloPublicado() {
		 arArticulo = new Articulo();
	 }
	 
	 public boolean validarArticulo(int iIDArticulo) {
		 return arArticulo.corroborarExistencia(iIDArticulo);
	 }
	 
	 public Articulo obtenerArticuloPublicado(int iIDArticulo) {
		 arArticulo = arArticulo.consultarInformacion(iIDArticulo);
		 if (!arArticulo.getbPublicado()) {
			 arArticulo = null;
		 }
		 return arArticulo;
	 }
 }
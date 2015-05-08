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
	 
	 public verArticulosPropuestosPropios() {
		 arArticulo = new Articulo();
	 }
	 
	 public Vector<Articulo> obtenerArticulosPropios(int iIDPersona) {
		 return arArticulo.consultarArticulosPropios(iIDPersona);
	 }
 }
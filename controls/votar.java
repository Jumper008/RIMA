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
 
 public class votar {
	 Articulo arArticulo;
	 
	 public votar() {
		 arArticulo = new Articulo();
	 }
	 
	 public boolean validarArticulo(int iIDArticulo) {
		 return arArticulo.corroborarExistencia(iIDArticulo);
	 }
	 
	 public void votarArticulo(int iIDArticulo) {
		 arArticulo.aumentarVotos(iIDArticulo);
	 }
 }
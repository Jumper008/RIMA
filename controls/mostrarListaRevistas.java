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
 
 public class mostrarListaRevistas {
	 Revista reRevista;
	 
	 public mostrarListaRevistas() {
		 reRevista = new Revista();
	 }
	 
	 public Vector<Revista> obtenerListaRevistas() {
		 return reRevista.mostrarRevistasPublicadas();
	 }
 }
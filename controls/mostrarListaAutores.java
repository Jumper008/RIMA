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
 
 public class mostrarListaAutores {
	 Autor auAutor;
	 
	 public mostrarListaAutores() {
		 auAutor = new Autor();
	 }
	 
	 public Vector<Autor> obtenerListaAutores() {
		 return auAutor.mostrarNombreDeAutores();
	 }
 }
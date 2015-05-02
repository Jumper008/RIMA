/*
 *Author: Equipo 5
 *
 *Version: 1.0.0
 *
 */
 package com.rima.controls;
 
 import java.util.*;
 import java.io.*;
 import java.util.Date;
 import com.rima.entities.*;
 
 public class altaRevista {
	 Revista reRevista;
	 private transient Conexion conConexion;

	 public altaRevista() {
		 conConexion = new Conexion();
		 reRevista = new Revista(conConexion);
	 }
	 
	 public void agregarRevista(Date dFechaPublicacion, int iNumPaginas, boolean bPublicada) {

	 	int iIDRevista = reRevista.generarID();
	 	
		reRevista.setiIDRevista(iIDRevista);
		reRevista.setdFechaPublicacion(dFechaPublicacion);
		reRevista.setiNumPaginas(iNumPaginas);
		reRevista.setbPublicada(bPublicada);
		reRevista.agregarRevista(reRevista);
	}			
 }
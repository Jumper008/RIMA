/*
 *Author: Equipo 5
 *
 *Version: 1.0.0
 *
 */
 package controls;
 
 import java.util.*;
 import java.io.*;
 import java.util.Date;
 import entities.*;
 
 public class altaRevista {
	 Revista reRevista;

	 public altaRevista() {
		 reRevista = new Revista();
	 }
	 
	 public void agregarRevista(String dFechaPublicacion, int iNumPaginas, boolean bPublicada) {

	 	int iIDRevista = reRevista.generarID();
	 	
		reRevista.setiIDRevista(iIDRevista);
		reRevista.setdFechaPublicacion(dFechaPublicacion);
		reRevista.setiNumPaginas(iNumPaginas);
		reRevista.setbPublicada(bPublicada);
		reRevista.agregarRevista(reRevista);
	}			
 }
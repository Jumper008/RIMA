/*
 *Author: Equipo 5
 *
 *Version: 1.0.0
 *
 */
package com.rima.entities;

import java.util.*;
import java.sql.*;
import java.io.*;
import java.util.Date;
import java.util.Calendar;

public class Autor {

	public Vector <Integer> consultarPublicaciones( int iIDPersona ) {
		Vector<Integer> vID = new Vector<Integer>();
		return vID;
	}
	
	public Vector <Integer> consultarPublicacionesPropias( int iIDPersona ) {
		Vector<Integer> vID = new Vector<Integer>();
		return vID;
	} 
	
	public Vector <String> mostrarNombreDeAutores() {
		Vector<String> vNombres = new Vector<String>();
		return vNombres;
	}
	
	public boolean agregarAutor( Autor autAutor ) {
		return true;
	}
	
	public boolean eidtarAutor( Autor autAutor ) {
		return true;
	}
	
	public Autor consultarInformacion( int iIDPersona ) {
		Autor auAutor = new Autor();
		return auAutor;
	}
}

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

public class Articulo {
	protected int iIDArticulo;
	protected String sNombre;
	protected String sResumen;
	protected boolean bPublicado;
	protected int iContador;

	public void consultarInformacion( int iIDArticulo ) {

	}

	public boolean consultarArticulosAutor( int iIDArticulo ) {
		return true;
	}

	public void consultarArticulosPropios( Vector<Integer> iIDArticulo ) {

	}

	public boolean aumentarVotos( int iIDArticulo ) {
		return true;
	}

	public Vector<Integer> getArticulosVotados() {
		Vector<Integer> vIDArticulos = new Vector<Integer>();
		return vIDArticulos;
	}

	public boolean agregarARevista( int iIDArticulo ) {
		return true;
	}

	public boolean agregarArticulo( Articulo articulo ) {
		return true;
	}
}

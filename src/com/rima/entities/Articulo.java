<<<<<<< Updated upstream
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

public class Articulo {
	
	protected int iIDArticulo;
	protected String sNombre;
	protected String sResumen;
	protected boolean bPublicado;
	protected int iContador;
	private transient Conexion conn;
	private Statement stmt;

		public Articulo(Conexion conn) {
		this.conn = conn;
        }
	
	public Articulo() {
		iIDArticulo = 0;
		sNombre = "";
		sResumen = "";
		bPublicado = false;
		iContador = 0;
	}
	
	public Articulo(int iIDArticulo, String sNombre, String sResumen, boolean bPublicadO, int iContador) {
		this.iIDArticulo = iIDArticulo;
		this.sNombre = sNombre;
		this.sResumen = sResumen;
		this.bPublicadO = bPublicado;
		this.iContador = iContador;
	}

	public Articulo consultarInformacion( int iIDArticulo ) {
		Articulo articulo = new articulo();
		try{
		     stmt.executeQuery ("SELECT * FROM Articulo WHERE iIDArticulo = " + iIDArticulo);
		     ResultSet rs = stmt.getResultSet();
		     while(rs.next()) {
			int _iIDArticulo = rs.getInt("iIDArticulo");
			String _strNombre = rs.getString("sNombre");
			String _strResumen = rs.getString("sResumen");
			boolean _bPublicado = rs.getBoolean("bPublicado");
			int _iContador = rs.getInt("iContador");
			Articulo arArticulo = new Articulo(_iIDArticulo,_strNombre,_strResumen,_bPublicadO,_iContador);
		     }
		     return arArticulo;
		  } catch (SQLException e) { return null;}
	}

	// regresa solo los artículos publicados de un autor
	public Vector<Articulo> consultarArticulosAutor( int iIDAutor ) {
		Vector<Articulo> vArticulos = new Vector<Articulo>();
		try{
		     stmt.executeQuery ("SELECT * FROM Articulo, Autor WHERE iIDAutor = " + iIDAutor + "and bPublicado = " + true );
		     ResultSet rs = stmt.getResultSet();
		     while(rs.next()) {
			int _iIDArticulo = rs.getInt("iIDArticulo");
			String _strNombre = rs.getString("sNombre");
			String _strResumen = rs.getString("sResumen");
			boolean _bPublicado = rs.getBoolean("bPublicado");
			int _iContador = rs.getInt("iContador");
			Articulo arArticulo = new Articulo(_iIDArticulo,_strNombre,_strResumen,_bPublicadO,_iContador);
			vArticulos.add(arArticulo);
		     }
		     return vArticulos;
		  } catch (SQLException e) { return null;}
	}

	// regresa todos los artículos, publicados o no, de un autor
	public Vector<Articulo> consultarArticulosPropios( int iIDAutor ) {
		Vector<Articulo> vArticulos = new Vector<Articulo>();
		try{
		     stmt.executeQuery ("SELECT * FROM Articulo, Autor WHERE iIDAutor = " + iIDAutor + "and bPublicado = " + true );
		     ResultSet rs = stmt.getResultSet();
		     while(rs.next()) {
			int _iIDArticulo = rs.getInt("iIDArticulo");
			String _strNombre = rs.getString("sNombre");
			String _strResumen = rs.getString("sResumen");
			boolean _bPublicado = rs.getBoolean("bPublicado");
			int _iContador = rs.getInt("iContador");
			Articulo arArticulo = new Articulo(_iIDArticulo,_strNombre,_strResumen,_bPublicadO,_iContador);
			vArticulos.add(arArticulo);
		     }
		     return vArticulos;
		  } catch (SQLException e) { return null;}
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

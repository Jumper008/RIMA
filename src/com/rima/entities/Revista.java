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

public class Revista {
	protected int iIDRevista;
	protected Date dFechaPublicacion;
	protected int iNumPaginas;
	protected boolean bPublicada;
	private transient Conexion conn;
	private Statement stmt;
	
        public Revista(Conexion conn) {
		this.conn = conn;
        }
	
	public Revista() {
		iIDRevista = 0;
		dFechaPublicacion = new Date();
		iNumPaginas = 0;
		bPublicada = false;
	}
	
	public Revista(int iIDRevista, Date dFechaPublicacion, int iNumPaginas, boolean bPublicada) {
		this.iIDRevista = iIDRevista;
		this.dFechaPublicacion = dFechaPublicacion;;
		this.iNumPaginas = iNumPaginas;
		this.bPublicada = bPublicada;
	}

	public Vector<Revista> mostrarRevistasPublicadas() {
		Vector<Revista> vRevistas = new Vector<Revista>();
		Calendar cal = Calendar.getInstance();
		try{
		     stmt.executeQuery ("SELECT * FROM Revista WHERE bPublicada = " + true);
		     ResultSet rs = stmt.getResultSet();
		     while(rs.next()) {
			int _iIDRevista = rs.getInt("iIDRevista");
			String _strDate = rs.getString("dFechaPublicacion");
			int iday = Integer.parseInt(_strDate.substring(8,9));
			int imonth = Integer.parseInt(_strDate.substring(5,6));
			int iyear = Integer.parseInt(_strDate.substring(0,3));
			cal.set(Calendar.DATE, iday);
			cal.set(Calendar.MONTH, imonth);
			cal.set(Calendar.YEAR, iyear);
			Date _dFechaPublicacion = cal.getTime();
			int _iNumPaginas = rs.getInt("iNumPaginas");
			boolean _bPublicada = rs.getBoolean("bPublicada");
			Revista reRevista = new Revista(_iIDRevista,_dFechaPublicacion,_iNumPaginas,_bPublicada);
			vRevistas.add(reRevista);
		     }
		     return vRevistas;
		  } catch (SQLException e) { return null;}
	}
	
	public boolean agregaraRevista( Revista reRevista ) {
		Calendar cal = Calendar.getInstance();
		int iIDRevista = reRevista.iIDRevista;
		Date dFechaPublicacion = reRevista.dFechaPublicacion;
		cal.setTime(dFechaPublicacion);
		int iday = cal.get(Calendar.DATE);
		int imonth = cal.get(Calendar.MONTH);
		int iyear = cal.get(Calendar.YEAR);
		String sDate = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
		int iNumPaginas = reRevista.iNumPaginas;
		boolean bPublicada = reRevista.bPublicada;
		try {
			String s = "INSERT INTO Revista (iIDRevista, dFechaPublicacion, iNumPaginas, bPublicada)" +
			 " VALUES ("+ iIDRevista + " , '" + sDate + " , '"
			 + iNumPaginas + " , '" + bPublicada + " )"; 
			conn.stmt.executeUpdate(s);
			
		    } catch (SQLException e) {System.out.println ("Cannot execute agregaraRevista()" + e);}
		return true;
	}
}

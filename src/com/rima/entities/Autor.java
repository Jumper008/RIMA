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

public class Autor extends Persona{

	public Autor(Conexion conn){
		this.conn = conn;
	}

	public Autor(){
		super();
	}

	public Vector <Integer> consultarPublicaciones( int iIDPersona ) {
		Vector<Integer> vAutor = new Vector<Integer>();
		try{
			stm.executeQuery("SELECT * FROM ARTICULO WHERE iIDAutor = " + iIDPersona + "and bPublicado = " + true);
			ResultSet rs = stm.getResultSet();
			while(rs.next()) {
			int _iIDArticulo = rs.getInt("iIDArticulo");
			String _strNombre = rs.getString("sNombre");
			String _strResumen = rs.getString("sResumen");
			boolean _bPublicado = rs.getBoolean("bPublicado");
			int _iContador = rs.getInt("iContador");
			Autor arAutor = new Autor(_iIDAutor,_strNombre,_strResumen,_bPublicadO,_iContador);
			vAutor.add(arAutor);
		    }
		}
		return vID;
	}
	
	//consultar las publicaciones propias
	public Vector <Integer> consultarPublicacionesPropias( int iIDPersona ) {
		Vector<Integer> vID = new Vector<Integer>();
		try{
		     stmt.executeQuery ("SELECT * FROM Articulo, Autor WHERE iIDAutor = " + iIDPersona + "and bPublicado = " + true );
		     ResultSet rs = stmt.getResultSet();
		     while(rs.next()) {
			int _iIDArticulo = rs.getInt("iIDArticulo");
			String _strNombre = rs.getString("sNombre");
			String _strResumen = rs.getString("sResumen");
			boolean _bPublicado = rs.getBoolean("bPublicado");
			int _iContador = rs.getInt("iContador");
			Autor arAutor = new Autor(_iIDAutor,_strNombre,_strResumen,_bPublicadO,_iContador);
			vAutor.add(arAutor);
		     }
		     return vAutor;
		  } catch (SQLException e) { return null;}
		return vID;
	} 
	
	public Vector <String> mostrarNombreDeAutores() {
		Vector<String> vNombres = new Vector<String>();
		try{
			stm.executeQuery("SELECT sNombre FROM AUTOR");
			ResultSet rs = stmt.getResultSet();
			while(rs.next()) {
				int _iIDAutor = rs.getInt("iIDAutor");
				String _strNombre = rs.getString("sNombre");
				Autor arAutor = new Autor(_iIDAutor, _strNombre);
				vNombres.add(arAutor);
			}
		}
		return vNombres;
	}
	
	public boolean agregarAutor( Autor autAutor ) {
		Calendar cal = Calendar.getInstance();
		int iIDAutor = auAutor.iIDPersona;
		String sNombre = auAutor.sNombre;
		String sCorreo = autAutor.sCorreo;
		Date dFechaNacimiento = autAutor.dFechaNacimiento;
		cal.setTime(dFechaNacimiento);
		int iday = cal.get(Calendar.DATE);
		int imonth = cal.get(Calendar.MONTH);
		int iyear = cal.get(Calendar.YEAR);
		String sDate = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
		String sContrasena = autAutor.sContrasena;
		Date dFechaIngreso = autAutor.dFechaIngreso;
		cal.setTime(dFechaNacimiento);
		int iday = cal.get(Calendar.DATE);
		int imonth = cal.get(Calendar.MONTH);
		int iyear = cal.get(Calendar.YEAR);
		String sDateNa = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);

		try{
			if(corroborarExistencia(iIDAutor)){
				String s = "INSERT INTO Autor (iIDAutor, sNombre, sCorreo, dFechaNacimiento, sContrasena, dFechaIngreso)"+
			 	"VALUES ("+ iIDAutor + " , '" + sNombre + " , '"
			 	+ sCorreo + " , '" + sDate + ",'" + sContrasena + ",'"
				+ sDateNa+ " )";
				conn.stmt.executeUpdate(s);
				return true;
			}
			else
				return false;
			
		} catch (SQLException e) {System.out.println ("Cannot execute agregarAutor()"+ e);}
	}
	
	public boolean editarAutor( Autor autAutor ) {
		try {
			Calendar cal = Calendar.getInstance();
			int iIDAutor = auAutor.iIDAutor;
			String sNombre = auAutor.sNombre;
			String sCorreo = autAutor.sCorreo;
			Date dFechaNacimiento = autAutor.dFechaNacimiento;
			cal.setTime(dFechaNacimiento);
			int iday = cal.get(Calendar.DATE);
			int imonth = cal.get(Calendar.MONTH);
			int iyear = cal.get(Calendar.YEAR);
			String sDate = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
			String sContrasena = autAutor.sContrasena;
			Date dFechaIngreso = autAutor.dFechaIngreso;
			cal.setTime(dFechaNacimiento);
			int iday = cal.get(Calendar.DATE);
			int imonth = cal.get(Calendar.MONTH);
			int iyear = cal.get(Calendar.YEAR);
			String sDateNa = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);


			String sN = "UPDATE Autor SET sNombre = " + sNombre + " WHERE iIDAutor = " + iIDAutor;
			conn.stmt.executeUpdate(sN);

			String sC = "UPDATE Autor SET sCorreo = " + sCorreo + " WHERE iIDAutor = " + iIDAutor;
			conn.stmt.executeUpdate(sC);

			String sD = "UPDATE Autor SET dFechaNacimiento = " + sDate + " WHERE iIDAutor = " + iIDAutor;
			conn.stmt.executeUpdate(sD);

			String sCo = "UPDATE Autor SET sContrasena = " + sContrasena + " WHERE iIDAutor = " + iIDAutor;
			conn.stmt.executeUpdate(sCo);

			String sDI = "UPDATE Autor SET dFechaIngreso = " + sDateNa + " WHERE iIDAutor = " + iIDAutor;
			conn.stmt.executeUpdate(sDI);

		     } catch (SQLException e) {System.out.println ("Cannot execute editarAutor()" + e);}
		return true;
	}
	
	public Autor consultarInformacion( int iIDPersona ) {
		
		try{
			stm.executeQuery("SELECT sNombre FROM Autor Where iIDAutor= " + iIDPersona);
			ResultSet rsNombre = stmt.getResultSet();

			stm.executeQuery("SELECT sCorreo FROM Autor Where iIDAutor= " + iIDPersona);
			ResultSet rsCorreo = stmt.getResultSet();

			stm.executeQuery("SELECT sFechaNacimiento FROM Autor Where iIDAutor= " + iIDPersona);
			ResultSet rsFechaNacimiento = stmt.getResultSet();

			stm.executeQuery("SELECT sContrasena FROM Autor Where iIDAutor= " + iIDPersona);
			ResultSet rsContrasena = stmt.getResultSet();

			stm.executeQuery("SELECT sFechaIngreso FROM Autor Where iIDAutor= " + iIDPersona);
			ResultSet rsFechaIngreso = stmt.getResultSet();
			Autor auAutor = new Autor(rsNombre, rsCorreo, rsFechaNacimiento, rsContrasena, rsFechaIngreso);
		}
	
		return auAutor;
	}	
}
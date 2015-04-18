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
	
	protected int iIDPersona;
	protected String sNombre;
	protected String sCorreo;
	protected String sContrasena;
	protected Date dFechaNacimiento;
	protected Date dFechaIngreso;
	protected Date dFechaVencimiento;
	protected boolean bActivo;
	private transient Conexion conn;
	private Statement stm;
	
        public Autor(Conexion conn) {
		this.conn = conn;
        }
	
	public Autor() {
		iIDPersona = 0;
		sNombre = "";
		sCorreo = "";
		sContrasena = "";
		dFechaNacimiento = new Date();
		dFechaIngreso = new Date();
		dFechaVencimiento = new Date();
		bActivo = false;
	}
	
	public Autor(int iIDPersona, String sNombre, String sCorreo, String sContrasena, Date dFechaNacimiento,
		       Date dFechaIngreso, Date dFechaVencimiento, boolean bActivo) {
		this.iIDPersona = iIDPersona;
		this.sNombre = sNombre;
		this.sCorreo = sCorreo;
		this.sContrasena = sContrasena;
		this.dFechaNacimiento = dFechaNacimiento;
		this.dFechaIngreso = dFechaIngreso;
		this.dFechaVencimiento = dFechaVencimiento;
		this.bActivo = bActivo;
	}

	public Vector <Articulo> consultarPublicaciones( int iIDPersona ) {
		Vector<Articulo> vArticulo = new Vector<Articulo>();
		try{
			stm.executeQuery("SELECT * FROM Articulo WHERE iIDAutor = " + iIDPersona + "and bPublicado = " + true);
			ResultSet rs = stm.getResultSet();
			while(rs.next()) {
			int _iIDArticulo = rs.getInt("iIDArticulo");
			String _strNombre = rs.getString("sNombre");
			String _strResumen = rs.getString("sResumen");
			boolean _bPublicado = rs.getBoolean("bPublicado");
			int _iContador = rs.getInt("iContador");
			Articulo arArticulo = new Articulo(_iIDArticulo,_strNombre,_strResumen,_bPublicado,_iContador);
			vArticulo.add(arArticulo);
		    }
		}catch (SQLException e) {System.out.println ("Cannot execute consultarPublicaciones()" + e);}
		return vArticulo;
	}
	
	//consultar las publicaciones propias
	public Vector <Articulo> consultarPublicacionesPropias( int iIDPersona ) {
		Vector<Articulo> vArticulo = new Vector<Articulo>();
		try{
		     stmt.executeQuery ("SELECT * FROM Articulo, Autor WHERE iIDAutor = " + iIDPersona );
		     ResultSet rs = stmt.getResultSet();
		     while(rs.next()) {
			int _iIDArticulo = rs.getInt("iIDArticulo");
			String _strNombre = rs.getString("sNombre");
			String _strResumen = rs.getString("sResumen");
			boolean _bPublicado = rs.getBoolean("bPublicado");
			int _iContador = rs.getInt("iContador");
			Articulo arArticulo = new Articulo(_iIDArticulo,_strNombre,_strResumen,_bPublicado,_iContador);
			vArticulo.add(arArticulo);
		     }
		  } catch (SQLException e) { return null;}
		  return vArticulo;
	} 
	
	public Vector <String> mostrarNombreDeAutores() {
		Vector<String> vNombres = new Vector<String>();
		try{
			stm.executeQuery("SELECT sNombre FROM Autor");
			ResultSet rs = stm.getResultSet();
			while(rs.next()) {
				int _iIDAutor = rs.getInt("iIDAutor");
				String _strNombre = rs.getString("sNombre");
				vNombres.add(_strNombre);
			}
		}catch (SQLException e) {System.out.println ("Cannot execute mostrarNombreDeAutores()" + e);}
		return vNombres;
	}
	
	public boolean agregarAutor( Autor autAutor ) {
		Calendar cal = Calendar.getInstance();
		int iIDAutor = autAutor.iIDPersona;
		String sNombre = autAutor.sNombre;
		String sCorreo = autAutor.sCorreo;
		Date dFechaNacimiento = autAutor.dFechaNacimiento;
		cal.setTime(dFechaNacimiento);
		int iday = cal.get(Calendar.DATE);
		int imonth = cal.get(Calendar.MONTH);
		int iyear = cal.get(Calendar.YEAR);
		String sDate = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
		String sContrasena = autAutor.sContrasena;
		Date dFechaIngreso = autAutor.dFechaIngreso;
		cal.setTime(dFechaIngreso);
		int iday1 = cal.get(Calendar.DATE);
		int imonth1 = cal.get(Calendar.MONTH);
		int iyear1 = cal.get(Calendar.YEAR);
		String sDateIn = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
		Date dFechaVencimiento = autAutor.dFechaVencimiento;
		cal.setTime(dFechaVencimiento);
		int iday2 = cal.get(Calendar.DATE);
		int imonth2 = cal.get(Calendar.MONTH);
		int iyear2 = cal.get(Calendar.YEAR);
		String sDateVen = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);

		try{
			if(corroborarExistencia(iIDAutor)){
				String s = "INSERT INTO Autor (iIDAutor, sNombre, sCorreo, sContrsena, dFechaNacimiento, dFechaIngreso, dFechaIngreso, dFechaVencimiento)"+
			 	"VALUES ("+ iIDAutor + " , '" + sNombre + " , '"
			 	+ sCorreo + " , '" + sContrasena + ",'" + sDate + ",'"
				+ sDateIn+ ",'" + sDateVen+ " )";
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
			int iIDAutor = autAutor.iIDPersona;
			String sNombre = autAutor.sNombre;
			String sCorreo = autAutor.sCorreo;
			Date dFechaNacimiento = autAutor.dFechaNacimiento;
			cal.setTime(dFechaNacimiento);
			int iday = cal.get(Calendar.DATE);
			int imonth = cal.get(Calendar.MONTH);
			int iyear = cal.get(Calendar.YEAR);
			String sDate = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
			String sContrasena = autAutor.sContrasena;
			Date dFechaIngreso = autAutor.dFechaIngreso;
			cal.setTime(dFechaIngreso);
			int iday1 = cal.get(Calendar.DATE);
			int imonth1 = cal.get(Calendar.MONTH);
			int iyear1 = cal.get(Calendar.YEAR);
			String sDateIn = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
			Date dFechaVencimiento = autAutor.dFechaVencimiento;
			cal.setTime(dFechaVencimiento);
			int iday2 = cal.get(Calendar.DATE);
			int imonth2 = cal.get(Calendar.MONTH);
			int iyear2 = cal.get(Calendar.YEAR);
			String sDateVen = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);


			String sN = "UPDATE Autor SET sNombre = " + sNombre + " WHERE iIDAutor = " + iIDAutor;
			conn.stmt.executeUpdate(sN);

			String sC = "UPDATE Autor SET sCorreo = " + sCorreo + " WHERE iIDAutor = " + iIDAutor;
			conn.stmt.executeUpdate(sC);

			String sD = "UPDATE Autor SET dFechaNacimiento = " + sDate + " WHERE iIDAutor = " + iIDAutor;
			conn.stmt.executeUpdate(sD);

			String sCo = "UPDATE Autor SET sContrasena = " + sContrasena + " WHERE iIDAutor = " + iIDAutor;
			conn.stmt.executeUpdate(sCo);

			String sDI = "UPDATE Autor SET dFechaIngreso = " + sDateIn + " WHERE iIDAutor = " + iIDAutor;
			conn.stmt.executeUpdate(sDI);

			String sDV = "UPDATE Autor SET dFechaVencimiento =" + sDateVen + "WHERE iIDAutor = " + iIDAutor;

		     } catch (SQLException e) {System.out.println ("Cannot execute editarAutor()" + e);}
		return true;
	}
	
	public Autor consultarInformacion( int iIDPersona ) {
		Date dFechaVencimiento = new Date();
		Date dFechaIngreso = new Date();
		Date dFechaNacimiento = new Date();
		Calendar cal = Calendar.getInstance();
		
		try{
			stm.executeQuery("SELECT iIDAutor FROM Autor Where iIDAutor = " + iIDPersona);
			ResultSet rsiIDAutor = stm.getResultSet();
			int iIDPersonaAux = rsiIDAutor.getInt("iIDAutor");

			stm.executeQuery("SELECT sNombre FROM Autor Where iIDAutor = " + iIDPersona);
			ResultSet rsNombre = stm.getResultSet();
			String sNombre = rsNombre.getString("sNombre");

			stm.executeQuery("SELECT sCorreo FROM Autor Where iIDAutor = " + iIDPersona);
			ResultSet rsCorreo = stm.getResultSet();
			String sCorreo = rsCorreo.getString("sCorreo");

			stm.executeQuery("SELECT dFechaNacimiento FROM Autor Where iIDAutor = " + iIDPersona);
			ResultSet rsFechaNacimiento = stm.getResultSet();
			String _strDate = rsFechaNacimiento.getString("dFechaNacimiento");
			int day = Integer.parseInt(_strDate.substring(8,9));
			int month = Integer.parseInt(_strDate.substring(5,6));
			int year = Integer.parseInt(_strDate.substring(0,3));
			cal.set(Calendar.DATE, day);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.YEAR, year);
			dFechaNacimiento = cal.getTime();

			stm.executeQuery("SELECT sContrasena FROM Autor Where iIDAutor = " + iIDPersona);
			ResultSet rsContrasena = stm.getResultSet();
			String sContrasena = rsContrasena.getString("sContrasena");
			
			stm.executeQuery("SELECT bActivo FROM Autor Where iIDAutor = " + iIDPersona);
			ResultSet rsActivo = stm.getResultSet();
			boolean bActivo = rsActivo.getBoolean("bActivo");

			stm.executeQuery("SELECT dFechaIngreso FROM Autor Where iIDAutor = " + iIDPersona);
			ResultSet rsFechaIngreso = stm.getResultSet();
			_strDate = rsFechaIngreso.getString("dFechaIngreso");
			day = Integer.parseInt(_strDate.substring(8,9));
			month = Integer.parseInt(_strDate.substring(5,6));
			year = Integer.parseInt(_strDate.substring(0,3));
			cal.set(Calendar.DATE, day);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.YEAR, year);
			dFechaIngreso = cal.getTime();

			stm.executeQuery("SELECT dFechaVencimiento FROM Autor WHERE iIDAutor = "+ iIDPersona);
			ResultSet rsFechaVencimiento = stm.getResultSet();
			_strDate = rsFechaNacimiento.getString("dFechaVencimiento");
			day = Integer.parseInt(_strDate.substring(8,9));
			month = Integer.parseInt(_strDate.substring(5,6));
			year = Integer.parseInt(_strDate.substring(0,3));
			cal.set(Calendar.DATE, day);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.YEAR, year);
			dFechaVencimiento = cal.getTime();

			Autor auAutor = new Autor(iIDPersonaAux, sNombre, sCorreo, sContrasena, dFechaNacimiento, dFechaIngreso, dFechaVencimiento, bActivo);
			
		}catch (SQLException e) {System.out.println ("Cannot execute consultarInformacion()" + e);}
	
		return auAutor;
	}
}
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
			stm.executeQuery("SELECT sNombre FROM AUTOR");
			ResultSet rs = stmt.getResultSet();
			while(rs.next()) {
				int _iIDAutor = rs.getInt("iIDAutor");
				String _strNombre = rs.getString("sNombre");
				Autor arAutor = new Autor(_iIDAutor, _strNombre);
				vNombres.add(arAutor);
			}
		}catch (SQLException e) {System.out.println ("Cannot execute mostrarNombreDeAutores()" + e);}
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
		
		try{
			stm.executeQuery("SELECT iIDAutor FROM Autor Where iIDAutor = " + iIDPersona);
			ResultSet rsiIDAutor = stmt.getResultSet();

			stm.executeQuery("SELECT sNombre FROM Autor Where iIDAutor = " + iIDPersona);
			ResultSet rsNombre = stmt.getResultSet();

			stm.executeQuery("SELECT sCorreo FROM Autor Where iIDAutor = " + iIDPersona);
			ResultSet rsCorreo = stmt.getResultSet();

			stm.executeQuery("SELECT sFechaNacimiento FROM Autor Where iIDAutor = " + iIDPersona);
			ResultSet rsFechaNacimiento = stmt.getResultSet();

			stm.executeQuery("SELECT sContrasena FROM Autor Where iIDAutor = " + iIDPersona);
			ResultSet rsContrasena = stmt.getResultSet();

			stm.executeQuery("SELECT sFechaIngreso FROM Autor Where iIDAutor = " + iIDPersona);
			ResultSet rsFechaIngreso = stmt.getResultSet();

			stm.executeQuery("SELECT sFechaVencimiento FROM Autor WHERE iIDAutor = "+ iIDPersona);
			ResultSet rsFechaVencimiento = stm.getResultSet();

			Autor auAutor = new Autor(rsiIDAutor, rsNombre, rsCorreo, rsFechaNacimiento, rsContrasena, rsFechaNacimiento, rsFechaIngreso, rsFechaVencimiento);
		}catch (SQLException e) {System.out.println ("Cannot execute consultarInformacion()" + e);}
	
		return auAutor;
	}	
}
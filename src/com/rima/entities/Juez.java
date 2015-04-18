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

public class Juez extends Autor{

	protected int iIDPersona;
	protected String sNombre;
	protected String sCorreo;
	protected String sContrasena;
	protected Date dFechaNacimiento;
	protected Date dFechaIngreso;
	protected Date dFechaVencimiento;
	protected boolean bActivo;
	private transient Conexion conn;
	private Statement stmt;
	
        public Juez(Conexion conn) {
		this.conn = conn;
        }
	
	public Juez() {
		iIDPersona = 0;
		sNombre = "";
		sCorreo = "";
		sContrasena = "";
		dFechaNacimiento = new Date();
		dFechaIngreso = new Date();
		dFechaVencimiento = new Date();
		bActivo = false;
	}
	
	public Juez(int iIDPersona, String sNombre, String sCorreo, String sContrasena, Date dFechaNacimiento,
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


	public boolean agregarJuez( Juez juJuez ) {
		Calendar cal = Calendar.getInstance();
		int iIDJuez = juJuez.iIDPersona;
		String sNombre = juJuez.sNombre;
		String sCorreo = juJuez.sCorreo;
		Date dFechaNacimiento = juJuez.dFechaNacimiento;
		cal.setTime(dFechaNacimiento);
		int iday = cal.get(Calendar.DATE);
		int imonth = cal.get(Calendar.MONTH);
		int iyear = cal.get(Calendar.YEAR);
		String sDate = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
		String sContrasena = juJuez.sContrasena;
		Date dFechaIngreso = juJuez.dFechaIngreso;
		cal.setTime(dFechaIngreso);
		int iday1 = cal.get(Calendar.DATE);
		int imonth1 = cal.get(Calendar.MONTH);
		int iyear1 = cal.get(Calendar.YEAR);
		String sDateIn = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
		Date dFechaVencimiento = juJuez.dFechaVencimiento;
		cal.setTime(dFechaVencimiento);
		int iday2 = cal.get(Calendar.DATE);
		int imonth2 = cal.get(Calendar.MONTH);
		int iyear2 = cal.get(Calendar.YEAR);
		String sDateVen = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);

		try{
			if(corroborarExistencia(iIDJuez)){
				String s = "INSERT INTO Juez (iIDJuez, sNombre, sCorreo, sContrsena, dFechaNacimiento, dFechaIngreso, dFechaIngreso, dFechaVencimiento)"+
			 	"VALUES ("+ iIDJuez + " , '" + sNombre + " , '"
			 	+ sCorreo + " , '" + sContrasena + ",'" + sDate + ",'"
				+ sDateIn+ ",'" + sDateVen+ " )";
				conn.stmt.executeUpdate(s);
				return true;
			}
			else
				return false;
			
		} catch (SQLException e) {System.out.println ("Cannot execute agregarJuez()"+ e);}
		return false;
	}
	
	public boolean editarJuez( Juez juJuez ) {
		try {
			Calendar cal = Calendar.getInstance();
			int iIDJuez = juJuez.iIDPersona;
			String sNombre = juJuez.sNombre;
			String sCorreo = juJuez.sCorreo;
			Date dFechaNacimiento = juJuez.dFechaNacimiento;
			cal.setTime(dFechaNacimiento);
			int iday = cal.get(Calendar.DATE);
			int imonth = cal.get(Calendar.MONTH);
			int iyear = cal.get(Calendar.YEAR);
			String sDate = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
			String sContrasena = juJuez.sContrasena;
			Date dFechaIngreso = juJuez.dFechaIngreso;
			cal.setTime(dFechaIngreso);
			int iday1 = cal.get(Calendar.DATE);
			int imonth1 = cal.get(Calendar.MONTH);
			int iyear1 = cal.get(Calendar.YEAR);
			String sDateIn = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
			Date dFechaVencimiento = juJuez.dFechaVencimiento;
			cal.setTime(dFechaVencimiento);
			int iday2 = cal.get(Calendar.DATE);
			int imonth2 = cal.get(Calendar.MONTH);
			int iyear2 = cal.get(Calendar.YEAR);
			String sDateVen = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);


			String sN = "UPDATE Juez SET sNombre = " + sNombre + " WHERE iIDJuez = " + iIDJuez;
			conn.stmt.executeUpdate(sN);

			String sC = "UPDATE Juez SET sCorreo = " + sCorreo + " WHERE iIDJuez = " + iIDJuez;
			conn.stmt.executeUpdate(sC);

			String sD = "UPDATE Juez SET dFechaNacimiento = " + sDate + " WHERE iIDJuez = " + iIDJuez;
			conn.stmt.executeUpdate(sD);

			String sCo = "UPDATE Juez SET sContrasena = " + sContrasena + " WHERE iIDJuez = " + iIDJuez;
			conn.stmt.executeUpdate(sCo);

			String sDI = "UPDATE Juez SET dFechaIngreso = " + sDateIn + " WHERE iIDJuez = " + iIDJuez;
			conn.stmt.executeUpdate(sDI);

			String sDV = "UPDATE Juez SET dFechaVencimiento =" + sDateVen + "WHERE iIDJuez = " + iIDJuez;

		     } catch (SQLException e) {System.out.println ("Cannot execute editarJuez()" + e);}
		return true;
	}
	
	public Juez consultarInformacion( int iIDPersona ) {
		Date dFechaVencimiento = new Date();
		Date dFechaIngreso = new Date();
		Date dFechaNacimiento = new Date();
		Calendar cal = Calendar.getInstance();
		Juez juJuez = new Juez();
		
		try{
			stmt.executeQuery("SELECT iIDJuez FROM Juez Where iIDJuez = " + iIDPersona);
			ResultSet rsiIDJuez = stmt.getResultSet();
			int iIDPersonaAux = rsiIDJuez.getInt("iIDJuez");

			stmt.executeQuery("SELECT sNombre FROM Juez Where iIDJuez = " + iIDPersona);
			ResultSet rsNombre = stmt.getResultSet();
			String sNombre = rsNombre.getString("sNombre");

			stmt.executeQuery("SELECT sCorreo FROM Juez Where iIDJuez = " + iIDPersona);
			ResultSet rsCorreo = stmt.getResultSet();
			String sCorreo = rsCorreo.getString("sCorreo");

			stmt.executeQuery("SELECT dFechaNacimiento FROM Juez Where iIDJuez = " + iIDPersona);
			ResultSet rsFechaNacimiento = stmt.getResultSet();
			String _strDate = rsFechaNacimiento.getString("dFechaNacimiento");
			int day = Integer.parseInt(_strDate.substring(8,9));
			int month = Integer.parseInt(_strDate.substring(5,6));
			int year = Integer.parseInt(_strDate.substring(0,3));
			cal.set(Calendar.DATE, day);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.YEAR, year);
			dFechaNacimiento = cal.getTime();

			stmt.executeQuery("SELECT sContrasena FROM Juez Where iIDJuez = " + iIDPersona);
			ResultSet rsContrasena = stmt.getResultSet();
			String sContrasena = rsContrasena.getString("sContrasena");
			
			stmt.executeQuery("SELECT bActivo FROM Juez Where iIDJuez = " + iIDPersona);
			ResultSet rsActivo = stmt.getResultSet();
			boolean bActivo = rsActivo.getBoolean("bActivo");

			stmt.executeQuery("SELECT dFechaIngreso FROM Juez Where iIDJuez = " + iIDPersona);
			ResultSet rsFechaIngreso = stmt.getResultSet();
			_strDate = rsFechaIngreso.getString("dFechaIngreso");
			day = Integer.parseInt(_strDate.substring(8,9));
			month = Integer.parseInt(_strDate.substring(5,6));
			year = Integer.parseInt(_strDate.substring(0,3));
			cal.set(Calendar.DATE, day);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.YEAR, year);
			dFechaIngreso = cal.getTime();

			stmt.executeQuery("SELECT dFechaVencimiento FROM Juez WHERE iIDJuez = "+ iIDPersona);
			ResultSet rsFechaVencimiento = stmt.getResultSet();
			_strDate = rsFechaNacimiento.getString("dFechaVencimiento");
			day = Integer.parseInt(_strDate.substring(8,9));
			month = Integer.parseInt(_strDate.substring(5,6));
			year = Integer.parseInt(_strDate.substring(0,3));
			cal.set(Calendar.DATE, day);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.YEAR, year);
			dFechaVencimiento = cal.getTime();

			juJuez = new Juez(iIDPersonaAux, sNombre, sCorreo, sContrasena, dFechaNacimiento, dFechaIngreso, dFechaVencimiento, bActivo);
		
		}catch (SQLException e) {System.out.println ("Cannot execute consultarInformacion()" + e);}
		return juJuez;
	}
}
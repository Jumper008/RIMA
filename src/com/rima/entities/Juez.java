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

public class Juez{
	
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
		int iIDJuez = juJuez.iIDJuez;
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
		cal.setTime(dFechaNacimiento);
		int iday = cal.get(Calendar.DATE);
		int imonth = cal.get(Calendar.MONTH);
		int iyear = cal.get(Calendar.YEAR);
		String sDateNa = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);

		try{
			if(corroborarExistencia(iIDAutor)){
				String s = "INSERT INTO Juez (iIDJuez, sNombre, sCorreo, dFechaNacimiento, sContrasena, dFechaIngreso)"+
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
	
	public boolean editarAutor( Juez juJuez ) {
		try {
			Calendar cal = Calendar.getInstance();
			int iIDJuez = juJuez.iIDJuez;
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


			String sN = "UPDATE Juez SET sNombre = " + sNombre + " WHERE iIDJuez = " + iIDJuez;
			conn.stmt.executeUpdate(sN);

			String sC = "UPDATE Juez SET sCorreo = " + sCorreo + " WHERE iIDJuez = " + iIDJuez;
			conn.stmt.executeUpdate(sC);

			String sD = "UPDATE Juez SET dFechaNacimiento = " + sDate + " WHERE iIDJuez = " + iIDJuez;
			conn.stmt.executeUpdate(sD);

			String sCo = "UPDATE Juez SET sContrasena = " + sContrasena + " WHERE iIDJuez = " + iIDJuez;
			conn.stmt.executeUpdate(sCo);

			String sDI = "UPDATE Juez SET dFechaIngreso = " + sDateNa + " WHERE iIDJuez = " + iIDJuez;
			conn.stmt.executeUpdate(sDI);

		     } catch (SQLException e) {System.out.println ("Cannot execute editarJuez()" + e);}
		return true;
	}
	
	public Juez consultarInformacion( int iIDPersona ) {
		
		try{
			stm.executeQuery("SELECT sNombre FROM Juez Where iIDJuez= " + iIDPersona);
			ResultSet rsNombre = stmt.getResultSet();

			stm.executeQuery("SELECT sCorreo FROM Juez Where iIDAutor= " + iIDPersona);
			ResultSet rsCorreo = stmt.getResultSet();

			stm.executeQuery("SELECT sFechaNacimiento FROM Juez Where iIDJuez= " + iIDPersona);
			ResultSet rsFechaNacimiento = stmt.getResultSet();

			stm.executeQuery("SELECT sContrasena FROM Juez Where iIDJuez= " + iIDPersona);
			ResultSet rsContrasena = stmt.getResultSet();

			stm.executeQuery("SELECT sFechaIngreso FROM Juez Where iIDJuez= " + iIDPersona);
			ResultSet rsFechaIngreso = stmt.getResultSet();
			Juez = new Juez(rsNombre, rsCorreo, rsFechaNacimiento, rsContrasena, rsFechaIngreso);
		} catch (SQLException e) {System.out.println ("Cannot execute consultarInformacion()" + e);}
	
		return juJuez;
	}
}
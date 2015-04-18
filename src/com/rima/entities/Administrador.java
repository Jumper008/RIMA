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

public class Administrador extends Persona{

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
	
    public Administrador(Conexion conn) {
		this.conn = conn;
    }
	
	public Administrador() {
		iIDPersona = 0;
		sNombre = "";
		sCorreo = "";
		sContrasena = "";
		dFechaNacimiento = new Date();
		dFechaIngreso = new Date();
		dFechaVencimiento = new Date();
		bActivo = false;
	}
	
	public Administrador(int iIDPersona, String sNombre, String sCorreo, String sContrasena, Date dFechaNacimiento,
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
	public boolean agregarAdministrador( Administrador admAdministrador ) {
		Calendar cal = Calendar.getInstance();
		int iIDAdministrador = admAdministrador.iIDPersona;
		String sNombre = admAdministrador.sNombre;
		String sCorreo = admAdministrador.sCorreo;
		Date dFechaNacimiento = admAdministrador.dFechaNacimiento;
		cal.setTime(dFechaNacimiento);
		int iday = cal.get(Calendar.DATE);
		int imonth = cal.get(Calendar.MONTH);
		int iyear = cal.get(Calendar.YEAR);
		String sDate = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
		String sContrasena = admAdministrador.sContrasena;
		Date dFechaIngreso = admAdministrador.dFechaIngreso;
		cal.setTime(dFechaIngreso);
		int iday1 = cal.get(Calendar.DATE);
		int imonth1 = cal.get(Calendar.MONTH);
		int iyear1 = cal.get(Calendar.YEAR);
		String sDateIn = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
		Date dFechaVencimiento = admAdministrador.dFechaVencimiento;
		cal.setTime(dFechaVencimiento);
		int iday2 = cal.get(Calendar.DATE);
		int imonth2 = cal.get(Calendar.MONTH);
		int iyear2 = cal.get(Calendar.YEAR);
		String sDateVen = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);

		try{
			if(corroborarExistencia(iIDAdministrador)){
				String s = "INSERT INTO Administrador (iIDAdministrador, sNombre, sCorreo, sContrsena, dFechaNacimiento, dFechaIngreso, dFechaIngreso, dFechaVencimiento)"+
			 	"VALUES ("+ iIDAdministrador + " , '" + sNombre + " , '"
			 	+ sCorreo + " , '" + sContrasena + ",'" + sDate + ",'"
				+ sDateIn+ ",'" + sDateVen+ " )";
				conn.stmt.executeUpdate(s);
				return true;
			}
			else
				return false;
			
		} catch (SQLException e) {System.out.println ("Cannot execute agregarAdministrador()"+ e);}
		return false;
	}

	public boolean editarAdministrador( Administrador admAdministrador ) {
		try {
			Calendar cal = Calendar.getInstance();
			int iIDAdministrador = admAdministrador.iIDPersona;
			String sNombre = admAdministrador.sNombre;
			String sCorreo = admAdministrador.sCorreo;
			Date dFechaNacimiento = admAdministrador.dFechaNacimiento;
			cal.setTime(dFechaNacimiento);
			int iday = cal.get(Calendar.DATE);
			int imonth = cal.get(Calendar.MONTH);
			int iyear = cal.get(Calendar.YEAR);
			String sDate = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
			String sContrasena = admAdministrador.sContrasena;
			Date dFechaIngreso = admAdministrador.dFechaIngreso;
			cal.setTime(dFechaIngreso);
			int iday1 = cal.get(Calendar.DATE);
			int imonth1 = cal.get(Calendar.MONTH);
			int iyear1 = cal.get(Calendar.YEAR);
			String sDateIn = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
			Date dFechaVencimiento = admAdministrador.dFechaVencimiento;
			cal.setTime(dFechaVencimiento);
			int iday2 = cal.get(Calendar.DATE);
			int imonth2 = cal.get(Calendar.MONTH);
			int iyear2 = cal.get(Calendar.YEAR);
			String sDateVen = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);


			String sN = "UPDATE Autor SET sNombre = " + sNombre + " WHERE iIDAdministrador = " + iIDAdministrador;
			conn.stmt.executeUpdate(sN);

			String sC = "UPDATE Autor SET sCorreo = " + sCorreo + " WHERE iIDAdministrador = " + iIDAdministrador;
			conn.stmt.executeUpdate(sC);

			String sD = "UPDATE Autor SET dFechaNacimiento = " + sDate + " WHERE iIDAdministrador = " + iIDAdministrador;
			conn.stmt.executeUpdate(sD);

			String sCo = "UPDATE Autor SET sContrasena = " + sContrasena + " WHERE iIDAdministrador = " + iIDAdministrador;
			conn.stmt.executeUpdate(sCo);

			String sDI = "UPDATE Autor SET dFechaIngreso = " + sDateIn + " WHERE iIDAdministrador = " + iIDAdministrador;
			conn.stmt.executeUpdate(sDI);

			String sDV = "UPDATE Autor SET dFechaVencimiento =" + sDateVen + "WHERE iIDAdministrador = " + iIDAdministrador;

		     } catch (SQLException e) {System.out.println ("Cannot execute editarAutor()" + e);}
		return true;	
	}
	
	public Administrador consultarInformacion( int iIDPersona ) {
		Date dFechaVencimiento = new Date();
		Date dFechaIngreso = new Date();
		Date dFechaNacimiento = new Date();
		Calendar cal = Calendar.getInstance();
		Administrador admAdministrador = new Administrador();
		
		try{
			stmt.executeQuery("SELECT iIDAdministrador FROM Autor Where iIDAdministrador = " + iIDPersona);
			ResultSet rsiIDAdministrador = stmt.getResultSet();
			int iIDPersonaAux = rsiIDAdministrador.getInt("iIDAdministrador");

			stmt.executeQuery("SELECT sNombre FROM Administrador Where iIDAdministrador = " + iIDPersona);
			ResultSet rsNombre = stmt.getResultSet();
			String sNombre = rsNombre.getString("sNombre");

			stmt.executeQuery("SELECT sCorreo FROM Administrador Where iIDAdministrador = " + iIDPersona);
			ResultSet rsCorreo = stmt.getResultSet();
			String sCorreo = rsCorreo.getString("sCorreo");

			stmt.executeQuery("SELECT dFechaNacimiento FROM Administrador Where iIDAdministrador = " + iIDPersona);
			ResultSet rsFechaNacimiento = stmt.getResultSet();
			String _strDate = rsFechaNacimiento.getString("dFechaNacimiento");
			int day = Integer.parseInt(_strDate.substring(8,9));
			int month = Integer.parseInt(_strDate.substring(5,6));
			int year = Integer.parseInt(_strDate.substring(0,3));
			cal.set(Calendar.DATE, day);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.YEAR, year);
			dFechaNacimiento = cal.getTime();

			stmt.executeQuery("SELECT sContrasena FROM Administrador Where iIDAdministrador = " + iIDPersona);
			ResultSet rsContrasena = stmt.getResultSet();
			String sContrasena = rsContrasena.getString("sContrasena");
			
			stmt.executeQuery("SELECT bActivo FROM Administrador Where iIDAdministrador = " + iIDPersona);
			ResultSet rsActivo = stmt.getResultSet();
			boolean bActivo = rsActivo.getBoolean("bActivo");

			stmt.executeQuery("SELECT dFechaIngreso FROM Administrador Where iIDAdministrador = " + iIDPersona);
			ResultSet rsFechaIngreso = stmt.getResultSet();
			_strDate = rsFechaIngreso.getString("dFechaIngreso");
			day = Integer.parseInt(_strDate.substring(8,9));
			month = Integer.parseInt(_strDate.substring(5,6));
			year = Integer.parseInt(_strDate.substring(0,3));
			cal.set(Calendar.DATE, day);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.YEAR, year);
			dFechaIngreso = cal.getTime();

			stmt.executeQuery("SELECT dFechaVencimiento FROM Administrador WHERE iIDAdministrador = "+ iIDPersona);
			ResultSet rsFechaVencimiento = stmt.getResultSet();
			_strDate = rsFechaNacimiento.getString("dFechaVencimiento");
			day = Integer.parseInt(_strDate.substring(8,9));
			month = Integer.parseInt(_strDate.substring(5,6));
			year = Integer.parseInt(_strDate.substring(0,3));
			cal.set(Calendar.DATE, day);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.YEAR, year);
			dFechaVencimiento = cal.getTime();

			admAdministrador = new Administrador(iIDPersonaAux, sNombre, sCorreo, sContrasena, dFechaNacimiento, dFechaIngreso, dFechaVencimiento, bActivo);
		}catch (SQLException e) {System.out.println ("Cannot execute consultarInformacion()" + e);}
		return admAdministrador;
	}
}
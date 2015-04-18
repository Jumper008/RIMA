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

public class Administrador extends Persona {
	
	public Administrador() {
            super();
        }
        
	public Administrador( int iIDPersona, String sNombre, String sCorreo, 
	       String sContrasena, Date dFechaNacimiento, Date dFechaIngreso, 
	       Date dFechaVencimiento, boolean bActivo) {
	       
	    super(iIDPersona, sNombre, sCorreo, sContrasena, dFechaNacimiento, 
		   dFechaIngreso, dFechaVencimiento, bActivo);
	}
	
	public boolean agregarAdministrador( Administrador admAdministrador ) {
		Calendar cal = Calendar.getInstance();
		int iIDAdministrador = admAdministrador.iIDAdministrador;
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
		cal.setTime(dFechaNacimiento);
		int iday = cal.get(Calendar.DATE);
		int imonth = cal.get(Calendar.MONTH);
		int iyear = cal.get(Calendar.YEAR);
		String sDateNa = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);

		try{
			if(corroborarExistencia(iIDAdministrador)){
				String s = "INSERT INTO Administrador (iIDAdministrador, sNombre, sCorreo, dFechaNacimiento, sContrasena, dFechaIngreso)"+
			 	"VALUES ("+ iIDAutor + " , '" + sNombre + " , '"
			 	+ sCorreo + " , '" + sDate + ",'" + sContrasena + ",'"
				+ sDateNa+ " )";
				conn.stmt.executeUpdate(s);
				return true;
			}
			
		} catch (SQLException e) {System.out.println ("Cannot execute agregarAdministrador()" + e);}

		return true;
	}

	public boolean editarAdministrador( Administrador admAdministrador ) {
		try {
			Calendar cal = Calendar.getInstance();
			int iIDAdministrador = admAdministrador.iIDAdministrador;
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
			cal.setTime(dFechaNacimiento);
			int iday = cal.get(Calendar.DATE);
			int imonth = cal.get(Calendar.MONTH);
			int iyear = cal.get(Calendar.YEAR);
			String sDateNa = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);


			String sN = "UPDATE Autor SET sNombre = " + sNombre + " WHERE iIDAdministrador = " + iIDAdministrador;
			conn.stmt.executeUpdate(sN);

			String sC = "UPDATE Autor SET sCorreo = " + sCorreo + " WHERE iIDAdministrador = " + iIDAdministrador;
			conn.stmt.executeUpdate(sC);

			String sD = "UPDATE Autor SET dFechaNacimiento = " + sDate + " WHERE iIDAdministrador = " + iIDAdministrador;
			conn.stmt.executeUpdate(sD);

			String sCo = "UPDATE Autor SET sContrasena = " + sContrasena + " WHERE iIDAdministrador = " + iIDAdministrador;
			conn.stmt.executeUpdate(sCo);

			String sDI = "UPDATE Autor SET dFechaIngreso = " + sDateNa + " WHERE iIDAdministrador = " + iIDAdministrador;
			conn.stmt.executeUpdate(sDI);

		     } catch (SQLException e) {System.out.println ("Cannot execute editarAdministrador()" + e);}
		return true;	
	}
	
	public Administrador consultarInformacion( int iIDPersona ) {
		
		try{
			stm.executeQuery("SELECT sNombre FROM Autor Where iIDAdministrador= " + iIDPersona);
			ResultSet rsNombre = stmt.getResultSet();

			stm.executeQuery("SELECT sCorreo FROM Autor Where iIDAdministrador= " + iIDPersona);
			ResultSet rsCorreo = stmt.getResultSet();

			stm.executeQuery("SELECT sFechaNacimiento FROM Autor Where iIDAdministrador= " + iIDPersona);
			ResultSet rsFechaNacimiento = stmt.getResultSet();

			stm.executeQuery("SELECT sContrasena FROM Autor Where iIDAdministrador= " + iIDPersona);
			ResultSet rsContrasena = stmt.getResultSet();

			stm.executeQuery("SELECT sFechaIngreso FROM Autor Where iIDAdministrador= " + iIDPersona);
			ResultSet rsFechaIngreso = stmt.getResultSet();
			Administrador admAdministrador = new Administrador(rsNombre, rsCorreo, rsFechaNacimiento, rsContrasena, rsFechaIngreso);
		} catch (SQLException e) { return null;}
	
		return admAdministrador;
	}
}

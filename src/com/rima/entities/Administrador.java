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
	
	public Administrador(Conexion conn) {
		super(conn);
	}
	
	public Administrador() {
		super();
	}
	
	public Administrador(int iIDPersona, String sNombre, String sCorreo, String sContrasena, Date dFechaNacimiento,
		       Date dFechaIngreso, Date dFechaVencimiento, boolean bActivo) {
		super(iIDPersona, sNombre, sCorreo, sContrasena, dFechaNacimiento, dFechaIngreso, dFechaVencimiento, bActivo);
	}
	
	public boolean agregarAdministrador( Administrador admAdministrador ) {
		int iIDAdministrador = admAdministrador.iIDPersona;
		String sNombre = admAdministrador.sNombre;
		String sCorreo = admAdministrador.sCorreo;
                
                Calendar cal = Calendar.getInstance();
		Date dFechaNacimiento = admAdministrador.dFechaNacimiento;
		cal.setTime(dFechaNacimiento);
		
                int iday = cal.get(Calendar.DATE);
		int imonth = cal.get(Calendar.MONTH);
		int iyear = cal.get(Calendar.YEAR);
		String sDate = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
		String sContrasena = admAdministrador.sContrasena;
		Date dFechaIngreso = admAdministrador.dFechaIngreso;
		cal.setTime(dFechaIngreso);
		iday = cal.get(Calendar.DATE);
		imonth = cal.get(Calendar.MONTH);
		iyear = cal.get(Calendar.YEAR);
		String sDateIn = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
		Date dFechaVencimiento = admAdministrador.dFechaVencimiento;
		cal.setTime(dFechaVencimiento);
		iday = cal.get(Calendar.DATE);
		imonth = cal.get(Calendar.MONTH);
		iyear = cal.get(Calendar.YEAR);
		String sDateVen = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);

		try{
			if(corroborarExistencia(iIDAdministrador)){
                            // Agregar entrada en Persona
                            String sQueryPersona = "INSERT INTO Persona "
                                    + "(iIDAdministrador, sNombre, sCorreo, "
                                    + "sContrsena, dFechaNacimiento, "
                                    + "dFechaIngreso, dFechaIngreso, "
                                    + "dFechaVencimiento)"
                                    + "VALUES ("+ iIDAdministrador + " , '" 
                                    + sNombre + " , '" + sCorreo + " , '" 
                                    + sContrasena + ",'" + sDate + ",'" 
                                    + sDateIn+ ",'" + sDateVen+ " )";
                            
                            // Agregar entrada en Administrador
                            String sQueryAdministrador = "INSERT INTO Administrador "
                                    + "(iIDPersona) VALUES (" 
                                    + iIDAdministrador + ")";
                            
                            conn.stmt.executeUpdate(sQueryPersona);
                            conn.stmt.executeUpdate(sQueryAdministrador);
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
                iday = cal.get(Calendar.DATE);
                imonth = cal.get(Calendar.MONTH);
                iyear = cal.get(Calendar.YEAR);
                String sDateIn = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
                Date dFechaVencimiento = admAdministrador.dFechaVencimiento;
                cal.setTime(dFechaVencimiento);
                iday = cal.get(Calendar.DATE);
                imonth = cal.get(Calendar.MONTH);
                iyear = cal.get(Calendar.YEAR);
                String sDateVen = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
                
                // Actualización en tabla Persona
                String sQueryPersona;
                sQueryPersona = "UPDATE Persona SET "
                        + "sNombre = " + sNombre + ","
                        + "sCorreo = " + sCorreo + ","
                        + "dFechaNacimiento = " + sDate + ","
                        + "sContrasena = " + sContrasena + ","
                        + "dFechaIngreso = " + sDateIn + ","
                        + "dFechaVencimiento = " + sDateVen + " "
                        + " WHERE iIPersona = " + iIDAdministrador;
                conn.stmt.executeUpdate(sQueryPersona);
                
                // Actualización en tabla Administrador
                    // (no es necesaria)
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
                    stmt.executeQuery("SELECT * FROM Persona "
                            + "WHERE iIDPersona = " + iIDPersona);
                    ResultSet rsQuery = stmt.getResultSet();
                    if ( rsQuery.next() ) {
                        int iIDPersonaAux = rsQuery.getInt("iIDAdministrador");
                        String sNombre = rsQuery.getString("sNombre");
                        String sCorreo = rsQuery.getString("sCorreo");
                        
                        String _strDate = rsQuery.getString("dFechaNacimiento");
			int day = Integer.parseInt(_strDate.substring(8,9));
			int month = Integer.parseInt(_strDate.substring(5,6));
			int year = Integer.parseInt(_strDate.substring(0,3));
			cal.set(Calendar.DATE, day);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.YEAR, year);
			dFechaNacimiento = cal.getTime();
                        
                        String sContrasena = rsQuery.getString("sContrasena");
                        boolean bActivo = rsQuery.getBoolean("bActivo");
                        
                        String _strDate2 = rsQuery.getString("dFechaIngreso");
			day = Integer.parseInt(_strDate2.substring(8,9));
			month = Integer.parseInt(_strDate2.substring(5,6));
			year = Integer.parseInt(_strDate2.substring(0,3));
			cal.set(Calendar.DATE, day);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.YEAR, year);
			dFechaIngreso = cal.getTime();
                        
                        String _strDate3 = rsQuery.getString("dFechaVencimiento");
			day = Integer.parseInt(_strDate3.substring(8,9));
			month = Integer.parseInt(_strDate3.substring(5,6));
			year = Integer.parseInt(_strDate3.substring(0,3));
			cal.set(Calendar.DATE, day);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.YEAR, year);
			dFechaVencimiento = cal.getTime();
                        
                        admAdministrador = new Administrador(iIDPersonaAux, 
                                sNombre, sCorreo, sContrasena, dFechaNacimiento, 
                                dFechaIngreso, dFechaVencimiento, bActivo);
                    }
                    else {  // No se encontró el administrador a modificar
                        return admAdministrador;    // Regresa un objeto vacío
                    }
		} catch (SQLException e) {System.out.println ("Cannot execute consultarInformacion()" + e);}
		
                return admAdministrador;    // Regresar un objeto vacío si hubo un error en el query
	}
}
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

public class Cliente extends Persona{
	protected String sCuentaBancaria;

	public Cliente(Conexion conn) {
		super(conn);
	}
	
	public Cliente() {
		super();
		this.sCuentaBancaria = "";
	}
	
	public Cliente(int iIDPersona, String sNombre, String sCorreo, String sContrasena, Date dFechaNacimiento,
		       Date dFechaIngreso, Date dFechaVencimiento, boolean bActivo, String sCuentaBancaria) {
		super(iIDPersona, sNombre, sCorreo, sContrasena, dFechaNacimiento, dFechaIngreso, dFechaVencimiento, bActivo);
		this.sCuentaBancaria = sCuentaBancaria;
	}

	public boolean realizarPagoRenovacion( int iIDPersona ) {
		return true;
	}
	
	public boolean pagarSuscripcion( int iIDPersona ) {
		return true;
	}
	
	public Vector<Cliente> consultarClientesRenovar() {
		Vector < Cliente > vecClientes = new Vector < Cliente >();
		
		Calendar cal = Calendar.getInstance();
		
		int iAno = cal.get(Calendar.YEAR);
		int iMes = cal.get(Calendar.MONTH);
		int iDia = cal.get(Calendar.DATE);
		
		String sFechaActual = "'" + Integer.toString(iAno) + '-' + 
			Integer.toString(iMes) + '-' + 
			Integer.toString(iDia) + "'";   //Fecha actual en formato 'aaaa-mm-dd'
		
		try {
		stmt.executeQuery ("SELECT * FROM Cliente WHERE dFechaVencimiento >= " + sFechaActual );
		ResultSet rs = stmt.getResultSet();    
		
		while( rs.next() ) {
		    int iIDPersona = rs.getInt("iIDPersona");
		    String sNombre = rs.getString("sNombre");
		    String sCorreo = rs.getString("sCorreo");
		    String sContrasena = rs.getString("sContrasena");
		    
		    //dFechaNacimiento
		    String sFechaNacimiento = rs.getString("dFechaNacimiento");
		    iDia = Integer.parseInt(sFechaNacimiento.substring(8,9));
		    iMes = Integer.parseInt(sFechaNacimiento.substring(5,6));
		    iAno = Integer.parseInt(sFechaNacimiento.substring(0,3));
		    cal.set(Calendar.DATE, iDia);
		    cal.set(Calendar.MONTH, iMes);
		    cal.set(Calendar.YEAR, iAno);
		    
		    Date dFechaNacimiento = cal.getTime();
		    
		    //dFechaIngreso
		    String sFechaIngreso = rs.getString("dFechaIngreso");
		    iDia = Integer.parseInt(sFechaIngreso.substring(8,9));
		    iMes = Integer.parseInt(sFechaIngreso.substring(5,6));
		    iAno = Integer.parseInt(sFechaIngreso.substring(0,3));
		    cal.set(Calendar.DATE, iDia);
		    cal.set(Calendar.MONTH, iMes);
		    cal.set(Calendar.YEAR, iAno);
		    
		    Date dFechaIngreso = cal.getTime();
		    
		    //dFechaVencimiento
		    String sFechaVencimiento = rs.getString("dFechaVencimiento");
		    iDia = Integer.parseInt(sFechaVencimiento.substring(8,9));
		    iMes = Integer.parseInt(sFechaVencimiento.substring(5,6));
		    iAno = Integer.parseInt(sFechaVencimiento.substring(0,3));
		    cal.set(Calendar.DATE, iDia);
		    cal.set(Calendar.MONTH, iMes);
		    cal.set(Calendar.YEAR, iAno);
		    
		    Date dFechaVencimiento = cal.getTime();
		    
		    boolean bActivo = rs.getBoolean("bActivo");
		    
		    String sCuentaBancaria = rs.getString("sCuentaBancaria");
		    
		    vecClientes.add(new Cliente( iIDPersona, sNombre, sCorreo, 
			    sContrasena, dFechaNacimiento, dFechaIngreso, 
			    dFechaVencimiento, bActivo, sCuentaBancaria ));
		}
		
		Vector<Cliente> vClientes = new Vector<Cliente>();
		} catch (SQLException e) {System.out.println ("Cannot execute consultarClientesRenovar()" + e);}
		
		return vecClientes;
	}
	
	public Vector<Cliente> consultarClientesVigentes() {
		Vector < Cliente > vecClientes = new Vector < Cliente >();
		    
		    Calendar cal = Calendar.getInstance();
		    
		    int iAno = cal.get(Calendar.YEAR);
		    int iMes = cal.get(Calendar.MONTH);
		    int iDia = cal.get(Calendar.DATE);
		    
		    String sFechaActual = "'" + Integer.toString(iAno) + '-' + 
			    Integer.toString(iMes) + '-' + 
			    Integer.toString(iDia) + "'";   //Fecha actual en formato 'aaaa-mm-dd'
		    
		    try {
		    stmt.executeQuery ("SELECT * FROM Cliente WHERE dFechaVencimiento < " + sFechaActual );
		    ResultSet rs = stmt.getResultSet();
		    
		    while( rs.next() ) {
			int iIDPersona = rs.getInt("iIDPersona");
			String sNombre = rs.getString("sNombre");
			String sCorreo = rs.getString("sCorreo");
			String sContrasena = rs.getString("sContrasena");
			
			//dFechaNacimiento
			String sFechaNacimiento = rs.getString("dFechaNacimiento");
			iDia = Integer.parseInt(sFechaNacimiento.substring(8,9));
			iMes = Integer.parseInt(sFechaNacimiento.substring(5,6));
			iAno = Integer.parseInt(sFechaNacimiento.substring(0,3));
			cal.set(Calendar.DATE, iDia);
			cal.set(Calendar.MONTH, iMes);
			cal.set(Calendar.YEAR, iAno);
			
			Date dFechaNacimiento = cal.getTime();
			
			//dFechaIngreso
			String sFechaIngreso = rs.getString("dFechaIngreso");
			iDia = Integer.parseInt(sFechaIngreso.substring(8,9));
			iMes = Integer.parseInt(sFechaIngreso.substring(5,6));
			iAno = Integer.parseInt(sFechaIngreso.substring(0,3));
			cal.set(Calendar.DATE, iDia);
			cal.set(Calendar.MONTH, iMes);
			cal.set(Calendar.YEAR, iAno);
			
			Date dFechaIngreso = cal.getTime();
			
			//dFechaVencimiento
			String sFechaVencimiento = rs.getString("dFechaVencimiento");
			iDia = Integer.parseInt(sFechaVencimiento.substring(8,9));
			iMes = Integer.parseInt(sFechaVencimiento.substring(5,6));
			iAno = Integer.parseInt(sFechaVencimiento.substring(0,3));
			cal.set(Calendar.DATE, iDia);
			cal.set(Calendar.MONTH, iMes);
			cal.set(Calendar.YEAR, iAno);
			
			Date dFechaVencimiento = cal.getTime();
			
			boolean bActivo = rs.getBoolean("bActivo");
			
			String sCuentaBancaria = rs.getString("sCuentaBancaria");
			
			vecClientes.add(new Cliente( iIDPersona, sNombre, sCorreo, 
				sContrasena, dFechaNacimiento, dFechaIngreso, 
				dFechaVencimiento, bActivo, sCuentaBancaria ));
		    }
		    
		    Vector<Cliente> vClientes = new Vector<Cliente>();
		    } catch (SQLException e) {System.out.println ("Cannot execute consultarClientesVigentes()" + e);}
		    
		    return vecClientes;
	}
	
	public boolean agregarCliente( Cliente clCliente ) {
		Calendar cal = Calendar.getInstance();
		int iIDCliente = clCliente.iIDPersona;
		String sNombre = clCliente.sNombre;
		String sCorreo = clCliente.sCorreo;
		Date dFechaNacimiento = clCliente.dFechaNacimiento;
		cal.setTime(dFechaNacimiento);
		int iday = cal.get(Calendar.DATE);
		int imonth = cal.get(Calendar.MONTH);
		int iyear = cal.get(Calendar.YEAR);
		String sDate = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
		String sContrasena = clCliente.sContrasena;
		Date dFechaIngreso = clCliente.dFechaIngreso;
		cal.setTime(dFechaIngreso);
		int iday1 = cal.get(Calendar.DATE);
		int imonth1 = cal.get(Calendar.MONTH);
		int iyear1 = cal.get(Calendar.YEAR);
		String sDateIn = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
		Date dFechaVencimiento = clCliente.dFechaVencimiento;
		cal.setTime(dFechaVencimiento);
		int iday2 = cal.get(Calendar.DATE);
		int imonth2 = cal.get(Calendar.MONTH);
		int iyear2 = cal.get(Calendar.YEAR);
		String sDateVen = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
	
		try{
		    if(!corroborarExistencia(iIDCliente)){
			String s = "INSERT INTO Autor (iIDCliente, sNombre, sCorreo, sContrsena, dFechaNacimiento, dFechaIngreso, dFechaIngreso, dFechaVencimiento)"+
			"VALUES ("+ iIDCliente + " , '" + sNombre + " , '"
			+ sCorreo + " , '" + sContrasena + ",'" + sDate + ",'"
			+ sDateIn+ ",'" + sDateVen+ " )";
			conn.stmt.executeUpdate(s);
			return true;
		    }
		    else
			return false;
		    
		} catch (SQLException e) {System.out.println ("Cannot execute agregarCliente()"+ e);}
		return false;
	}
	
	public boolean editarCliente( Cliente clCliente ) {
		try {
		    Calendar cal = Calendar.getInstance();
		    int iIDCliente = clCliente.iIDPersona;
		    String sNombre = clCliente.sNombre;
		    String sCorreo = clCliente.sCorreo;
		    Date dFechaNacimiento = clCliente.dFechaNacimiento;
		    cal.setTime(dFechaNacimiento);
		    int iday = cal.get(Calendar.DATE);
		    int imonth = cal.get(Calendar.MONTH);
		    int iyear = cal.get(Calendar.YEAR);
		    String sDate = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
		    String sContrasena = clCliente.sContrasena;
		    Date dFechaIngreso = clCliente.dFechaIngreso;
		    cal.setTime(dFechaIngreso);
		    int iday1 = cal.get(Calendar.DATE);
		    int imonth1 = cal.get(Calendar.MONTH);
		    int iyear1 = cal.get(Calendar.YEAR);
		    String sDateIn = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
		    Date dFechaVencimiento = clCliente.dFechaVencimiento;
		    cal.setTime(dFechaVencimiento);
		    int iday2 = cal.get(Calendar.DATE);
		    int imonth2 = cal.get(Calendar.MONTH);
		    int iyear2 = cal.get(Calendar.YEAR);
		    String sDateVen = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
	
	
		    String sN = "UPDATE Autor SET sNombre = " + sNombre + " WHERE iIDCliente = " + iIDCliente;
		    conn.stmt.executeUpdate(sN);
	
		    String sC = "UPDATE Autor SET sCorreo = " + sCorreo + " WHERE iIDCliente = " + iIDCliente;
		    conn.stmt.executeUpdate(sC);
	
		    String sD = "UPDATE Autor SET dFechaNacimiento = " + sDate + " WHERE iIDCliente = " + iIDCliente;
		    conn.stmt.executeUpdate(sD);
	
		    String sCo = "UPDATE Autor SET sContrasena = " + sContrasena + " WHERE iIDCliente = " + iIDCliente;
		    conn.stmt.executeUpdate(sCo);
	
		    String sDI = "UPDATE Autor SET dFechaIngreso = " + sDateIn + " WHERE iIDCliente = " + iIDCliente;
		    conn.stmt.executeUpdate(sDI);
	
		    String sDV = "UPDATE Autor SET dFechaVencimiento =" + sDateVen + "WHERE iIDCliente = " + iIDCliente;
	
		     } catch (SQLException e) {System.out.println ("Cannot execute editarCliente()" + e);}
		return true;
	}
	
	public Cliente consultarInformacion( int iIDPersona ) {
		Date dFechaVencimiento = new Date();
		Date dFechaIngreso = new Date();
		Date dFechaNacimiento = new Date();
		Calendar cal = Calendar.getInstance();
		Cliente clCliente = new Cliente();
		
		try{
		    stmt.executeQuery("SELECT iIDCliente FROM Cliente Where iIDCliente= " + iIDPersona);
		    ResultSet rsiIDAutor = stmt.getResultSet();
		    int iIDPersonaAux = rsiIDAutor.getInt("iIDAutor");
	
		    stmt.executeQuery("SELECT sNombre FROM Cliente Where iIDCliente = " + iIDPersona);
		    ResultSet rsNombre = stmt.getResultSet();
		    String sNombre = rsNombre.getString("sNombre");
	
		    stmt.executeQuery("SELECT sCorreo FROM Cliente Where iIDCliente = " + iIDPersona);
		    ResultSet rsCorreo = stmt.getResultSet();
		    String sCorreo = rsCorreo.getString("sCorreo");
	
		    stmt.executeQuery("SELECT dFechaNacimiento FROM Cliente Where iIDCliente = " + iIDPersona);
		    ResultSet rsFechaNacimiento = stmt.getResultSet();
		    String _strDate = rsFechaNacimiento.getString("dFechaNacimiento");
		    int day = Integer.parseInt(_strDate.substring(8,9));
		    int month = Integer.parseInt(_strDate.substring(5,6));
		    int year = Integer.parseInt(_strDate.substring(0,3));
		    cal.set(Calendar.DATE, day);
		    cal.set(Calendar.MONTH, month);
		    cal.set(Calendar.YEAR, year);
		    dFechaNacimiento = cal.getTime();
	
		    stmt.executeQuery("SELECT sContrasena FROM Cliente Where iIDCliente = " + iIDPersona);
		    ResultSet rsContrasena = stmt.getResultSet();
		    String sContrasena = rsContrasena.getString("sContrasena");
		    
		    stmt.executeQuery("SELECT bActivo FROM Cliente Where iIDCliente = " + iIDPersona);
		    ResultSet rsActivo = stmt.getResultSet();
		    boolean bActivo = rsActivo.getBoolean("bActivo");
		    
		    stmt.executeQuery("SELECT sCuentaBancaria FROM Cliente Where iIDCliente = " + iIDPersona);
		    ResultSet rsCuentaBancaria = stmt.getResultSet();
		    String sCuentaBancaria = rsCuentaBancaria.getString("sCuentaBancaria");
	
		    stmt.executeQuery("SELECT dFechaIngreso FROM Cliente Where iIDCliente = " + iIDPersona);
		    ResultSet rsFechaIngreso = stmt.getResultSet();
		    _strDate = rsFechaIngreso.getString("dFechaIngreso");
		    day = Integer.parseInt(_strDate.substring(8,9));
		    month = Integer.parseInt(_strDate.substring(5,6));
		    year = Integer.parseInt(_strDate.substring(0,3));
		    cal.set(Calendar.DATE, day);
		    cal.set(Calendar.MONTH, month);
		    cal.set(Calendar.YEAR, year);
		    dFechaIngreso = cal.getTime();
	
		    stmt.executeQuery("SELECT dFechaVencimiento FROM Cliente WHERE iIDCliente = "+ iIDPersona);
		    ResultSet rsFechaVencimiento = stmt.getResultSet();
		    _strDate = rsFechaNacimiento.getString("dFechaVencimiento");
		    day = Integer.parseInt(_strDate.substring(8,9));
		    month = Integer.parseInt(_strDate.substring(5,6));
		    year = Integer.parseInt(_strDate.substring(0,3));
		    cal.set(Calendar.DATE, day);
		    cal.set(Calendar.MONTH, month);
		    cal.set(Calendar.YEAR, year);
		    dFechaVencimiento = cal.getTime();
	
		    clCliente = new Cliente(iIDPersonaAux, sNombre, sCorreo, sContrasena, dFechaNacimiento, dFechaIngreso, dFechaVencimiento, bActivo, sCuentaBancaria);
		    return clCliente;
		}catch (SQLException e) {System.out.println ("Cannot execute consultarInformacion()" + e);}
	    return clCliente;
	}
        
        void setsCuentaBancaria( String sCuentaBancaria ) {
            this.sCuentaBancaria = sCuentaBancaria;
        }
        
        String getsCuentaBancaria() {
            return sCuentaBancaria;
        }
}
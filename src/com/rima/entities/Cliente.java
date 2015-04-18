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

public class Cliente {
	protected String sCuentaBancaria;
	private transient Conexion conn;
	private Statement stmt;
        
        public Cliente() {
            super();
            sCuentaBancaria = "";
        }
        
        public Cliente( int iIDPersona, String sNombre, String sCorreo, 
                String sContrasena, Date dFechaNacimiento, Date dFechaIngreso, 
                Date dFechaVencimiento, boolean bActivo, 
                String sCuentaBancaria ) {
            
            super(iIDPersona, sNombre, sCorreo, sContrasena, dFechaNacimiento, 
                    dFechaIngreso, dFechaVencimiento, bActivo);
            
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
		int iIDCliente = clCliente.iIDAd;
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
		cal.setTime(dFechaNacimiento);
		int iday = cal.get(Calendar.DATE);
		int imonth = cal.get(Calendar.MONTH);
		int iyear = cal.get(Calendar.YEAR);
		String sDateNa = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);

		try{
			if(corroborarExistencia(iIDCliente)){
				String s = "INSERT INTO Administrador (iIDAdministrador, sNombre, sCorreo, dFechaNacimiento, sContrasena, dFechaIngreso)"+
			 	"VALUES ("+ iIDAutor + " , '" + sNombre + " , '"
			 	+ sCorreo + " , '" + sDate + ",'" + sContrasena + ",'"
				+ sDateNa+ " )";
				conn.stmt.executeUpdate(s);
				return true;
			}
			
		} catch (SQLException e) {System.out.println ("Cannot execute agregarCliente()" + e);}

		return true;
	}
	
	public boolean editarCliente( Cliente clCliente ) {
		try {
			Calendar cal = Calendar.getInstance();
			int iIDCliente = clCliente.iIDCliente;
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
			cal.setTime(dFechaNacimiento);
			int iday = cal.get(Calendar.DATE);
			int imonth = cal.get(Calendar.MONTH);
			int iyear = cal.get(Calendar.YEAR);
			String sDateNa = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);


			String sN = "UPDATE Autor SET sNombre = " + sNombre + " WHERE iIDCliente = " + iIDCliente;
			conn.stmt.executeUpdate(sN);

			String sC = "UPDATE Autor SET sCorreo = " + sCorreo + " WHERE iIDCliente =" + iIDCliente;
			conn.stmt.executeUpdate(sC);

			String sD = "UPDATE Autor SET dFechaNacimiento = " + sDate + " WHERE iIDCliente = " + iIDCliente;
			conn.stmt.executeUpdate(sD);

			String sCo = "UPDATE Autor SET sContrasena = " + sContrasena + " WHERE iIDCliente = " + iIDCliente;
			conn.stmt.executeUpdate(sCo);

			String sDI = "UPDATE Autor SET dFechaIngreso = " + sDateNa + " WHERE iIDCliente = " + iIDCliente;
			conn.stmt.executeUpdate(sDI);

		     } catch (SQLException e) {System.out.println ("Cannot execute editarCliente()" + e);}
		return true;
	}
	
	public Cliente consultarInformacion( int iIDPersona ) {
		try{
			stm.executeQuery("SELECT sNombre FROM Autor Where iIDCliente= " + iIDPersona);
			ResultSet rsNombre = stmt.getResultSet();

			stm.executeQuery("SELECT sCorreo FROM Autor Where iIDCliente= " + iIDPersona);
			ResultSet rsCorreo = stmt.getResultSet();

			stm.executeQuery("SELECT sFechaNacimiento FROM Autor Where iIDCliente= " + iIDPersona);
			ResultSet rsFechaNacimiento = stmt.getResultSet();

			stm.executeQuery("SELECT sContrasena FROM Autor Where iIDCliente= " + iIDPersona);
			ResultSet rsContrasena = stmt.getResultSet();

			stm.executeQuery("SELECT sFechaIngreso FROM Autor Where iIDCliente= " + iIDPersona);
			ResultSet rsFechaIngreso = stmt.getResultSet();

			Cliente clCliente = new Cliente(rsNombre, rsCorreo, rsFechaNacimiento, rsContrasena, rsFechaIngreso);
		} catch (SQLException e) { return null;}
	
		return clCliente;
	}
}
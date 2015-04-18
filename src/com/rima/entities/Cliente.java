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
		return true;
	}
	
	public boolean editarCliente( Cliente clCliente ) {
		return true;
	}
	
	public Cliente consultarInformacion( int iIDPersona ) {
		Cliente clCliente = new Cliente();
		return clCliente;
	}

}	
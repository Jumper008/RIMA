/*
 *Author: Equipo 5
 *
 *Version: 1.0.0
 *
 */
package entities;

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

    public void setsCuentaBancaria( String sCuentaBancaria ) {
    this.sCuentaBancaria = sCuentaBancaria;
    }
    
    public String getsCuentaBancaria() {
        return sCuentaBancaria;
    }

    public boolean realizarPagoSuscripcion( int iIDPersona, String sCuentaBancaria, int iAnos ) {
        return true;
    }

    public boolean realizarPagoRenovacion( int iIDPersona, int iAnos ) {  
        return true;
    }

    public Vector<Cliente> consultarClientesRenovar() {
        Vector <Cliente> vecClientes = new Vector < Cliente >();

        Calendar cal = Calendar.getInstance();
		Date sCurrentDate = cal.getTime();

        try {
        	stmt.executeQuery ("SELECT * FROM Cliente cli, Persona per WHERE cli.iIDPersona = per.iIDPersona" );

            ResultSet rs = stmt.getResultSet();    

            while( rs.next() ) {
            	//dFechaVencimiento
                String sFechaVencimiento = rs.getString("dFechaVencimiento");
                int iDia = Integer.parseInt(sFechaVencimiento.substring(8,9));
                int iMes = Integer.parseInt(sFechaVencimiento.substring(5,6));
                int iAno = Integer.parseInt(sFechaVencimiento.substring(0,3));
                cal.set(Calendar.DATE, iDia);
                cal.set(Calendar.MONTH, iMes);
                cal.set(Calendar.YEAR, iAno);

                Date dFechaVencimiento = cal.getTime();

                if(dFechaVencimiento.getTime() <= sCurrentDate.getTime()) {

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

                    boolean bActivo = rs.getBoolean("bActivo");

                    String sCuentaBancaria = rs.getString("sCuentaBancaria");

                    vecClientes.add(new Cliente( iIDPersona, sNombre, sCorreo, 
                            sContrasena, dFechaNacimiento, dFechaIngreso, 
                            dFechaVencimiento, bActivo, sCuentaBancaria ));
            	}
        	}
        } catch (SQLException e) {System.out.println ("Cannot execute consultarClientesRenovar()" + e);}

        return vecClientes;
    }

    public Vector<Cliente> consultarClientesVigentes() {
        Vector <Cliente> vecClientes = new Vector < Cliente >();

        Calendar cal = Calendar.getInstance();
		Date dCurrentDate = cal.getTime();

        try {
        	stmt.executeQuery ("SELECT * FROM Cliente cli, Persona per WHERE cli.iIDPersona = per.iIDPersona" );

            ResultSet rs = stmt.getResultSet();    

            while( rs.next() ) {
            	//dFechaVencimiento
                String sFechaVencimiento = rs.getString("dFechaVencimiento");
                int iDia = Integer.parseInt(sFechaVencimiento.substring(8,9));
                int iMes = Integer.parseInt(sFechaVencimiento.substring(5,6));
                int iAno = Integer.parseInt(sFechaVencimiento.substring(0,3));
                cal.set(Calendar.DATE, iDia);
                cal.set(Calendar.MONTH, iMes);
                cal.set(Calendar.YEAR, iAno);

                Date dFechaVencimiento = cal.getTime();

                if(dFechaVencimiento.getTime() > dCurrentDate.getTime()) {

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

                    boolean bActivo = rs.getBoolean("bActivo");

                    String sCuentaBancaria = rs.getString("sCuentaBancaria");

                    vecClientes.add(new Cliente( iIDPersona, sNombre, sCorreo, 
                            sContrasena, dFechaNacimiento, dFechaIngreso, 
                            dFechaVencimiento, bActivo, sCuentaBancaria ));
            	}
        	}
        } catch (SQLException e) {System.out.println ("Cannot execute consultarClientesRenovar()" + e);}

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
            iday = cal.get(Calendar.DATE);
            imonth = cal.get(Calendar.MONTH);
            iyear = cal.get(Calendar.YEAR);
            String sDateIn = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
            
            Date dFechaVencimiento = clCliente.dFechaVencimiento;
            cal.setTime(dFechaVencimiento);
            iday = cal.get(Calendar.DATE);
            imonth = cal.get(Calendar.MONTH);
            iyear = cal.get(Calendar.YEAR);
            String sDateVen = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
            
            String sCuentaBancaria = clCliente.getsCuentaBancaria();
            
            boolean bActivo = clCliente.bActivo;

            try{
                if(!corroborarExistencia(iIDCliente)){
                    // Etrada en tabla Persona
                    String sQueryPersona = "INSERT INTO Persona (iIDPersona, sNombre, sCorreo, sContrsena, dFechaNacimiento, dFechaIngreso, dFechaVencimiento, bActivo)"+
                            "VALUES (" + iIDCliente + " , '" 
                            + sNombre + "', '"
                            + sCorreo + "', '" 
                            + sContrasena + "', '" 
                            + sDate + "', '"
                            + sDateIn+ "', '" 
                            + sDateVen + "', " 
                            + bActivo + ")";
                    
                    // Entrada en tabla Cliente
                    String sQueryCliente = "INSERT INTO Cliente (iIDPersona, sCuentaBancaria) "
                            + "VALUES ( " + iIDCliente + ", '" + sCuentaBancaria + "' )";
                    
                    conn.stmt.executeUpdate(sQueryPersona);
                    conn.stmt.executeUpdate(sQueryCliente);
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
                iday = cal.get(Calendar.DATE);
                imonth = cal.get(Calendar.MONTH);
                iyear = cal.get(Calendar.YEAR);
                String sDateIn = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
                
                Date dFechaVencimiento = clCliente.dFechaVencimiento;
                cal.setTime(dFechaVencimiento);
                iday = cal.get(Calendar.DATE);
                imonth = cal.get(Calendar.MONTH);
                iyear = cal.get(Calendar.YEAR);
                String sDateVen = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
                
                boolean bActivo = clCliente.bActivo;
                
                String sQueryPersona = "UPDATE Persona SET "
                        + "sNombre = '" + sNombre + "',"
                        + "sCorreo = '" + sCorreo + "',"
                        + "dFechaNacimiento = '" + sDate + "',"
                        + "sContrasena = '" + sContrasena + "',"
                        + "dFechaIngreso = '" + sDateIn + "',"
                        + "dFechaVencimiento = '" + sDateVen + "', "
                        + "bActivo = " + bActivo + " "
                        + " WHERE iIDPersona = " + iIDCliente;

                String sQueryCliente = "UPDATE Cliente SET "
                        + "sCuentaBancaria WHERE iIDPersona = " + iIDCliente;

                conn.stmt.executeUpdate(sQueryPersona);
                conn.stmt.executeUpdate(sQueryCliente);

            } catch (SQLException e) {System.out.println ("Cannot execute editarCliente()" + e);}
            return true;
    }

    public Cliente consultarInformacion( int iIDPersona ) {
            Date dFechaVencimiento = new Date();
            Date dFechaIngreso = new Date();
            Date dFechaNacimiento = new Date();
            Calendar cal = Calendar.getInstance();
            Cliente cliCliente = new Cliente();

            try{
                stmt.executeQuery("SELECT * Persona WHERE iIDPersona = " + iIDPersona);
                ResultSet rsQueryPersona = stmt.getResultSet();
                
                if ( rsQueryPersona.next() ) {   
                    String sNombre = rsQueryPersona.getString("sNomrbe");
                    String sCorreo = rsQueryPersona.getString("sCorreo");
                    
                    String _strDate = rsQueryPersona.getString("dFechaNacimiento");
                    int day = Integer.parseInt(_strDate.substring(8,9));
                    int month = Integer.parseInt(_strDate.substring(5,6));
                    int year = Integer.parseInt(_strDate.substring(0,3));
                    cal.set(Calendar.DATE, day);
                    cal.set(Calendar.MONTH, month);
                    cal.set(Calendar.YEAR, year);
                    dFechaNacimiento = cal.getTime();

                    String sContrasena = rsQueryPersona.getString("sContrasena");
                    boolean bActivo = rsQueryPersona.getBoolean("bActivo");

                    String _strDate2 = rsQueryPersona.getString("dFechaIngreso");
                    day = Integer.parseInt(_strDate2.substring(8,9));
                    month = Integer.parseInt(_strDate2.substring(5,6));
                    year = Integer.parseInt(_strDate2.substring(0,3));
                    cal.set(Calendar.DATE, day);
                    cal.set(Calendar.MONTH, month);
                    cal.set(Calendar.YEAR, year);
                    dFechaIngreso = cal.getTime();

                    String _strDate3 = rsQueryPersona.getString("dFechaVencimiento");
                    day = Integer.parseInt(_strDate3.substring(8,9));
                    month = Integer.parseInt(_strDate3.substring(5,6));
                    year = Integer.parseInt(_strDate3.substring(0,3));
                    cal.set(Calendar.DATE, day);
                    cal.set(Calendar.MONTH, month);
                    cal.set(Calendar.YEAR, year);
                    dFechaVencimiento = cal.getTime();

                    cliCliente = new Cliente(iIDPersona, sNombre, sCorreo, 
                            sContrasena, dFechaNacimiento, dFechaIngreso, 
                            dFechaVencimiento, bActivo, "");
                }
                else {
                    return null;
                }
                
                stmt.executeQuery("SELECT * Cliente WHERE iIDPersona = " + iIDPersona);
                ResultSet rsQueryCliente = stmt.getResultSet();
                
                if ( rsQueryCliente.next() ) {
                    String sCuentaBancaria = rsQueryCliente.getString("sCuentaBancaria");
                    
                    cliCliente.setsCuentaBancaria(sCuentaBancaria);
                }
                else {
                    return null;
                }
                
                return cliCliente;
            } catch (SQLException e) {System.out.println ("Cannot execute consultarInformacion()" + e);}
        
        return cliCliente;
    }
}
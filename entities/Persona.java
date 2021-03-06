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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Persona {
	
	protected int iIDPersona;
	protected String sNombre;
	protected String sCorreo;
	protected String sContrasena;
	protected Date dFechaNacimiento;
	protected Date dFechaIngreso;
	protected Date dFechaVencimiento;
	protected boolean bActivo;
	protected transient Connection conn;
	protected Statement stmt, stmt2;
	
	public Persona(){
      try {
        String userName = "root";
        String password = "";
        String url = "jdbc:mysql://localhost/rima";
        Class.forName ("com.mysql.jdbc.Driver").newInstance();
        conn = DriverManager.getConnection (url, userName, password);
        stmt = conn.createStatement();
        stmt2 = conn.createStatement();
        iIDPersona = 0;
		sNombre = "";
		sCorreo = "";
		sContrasena = "";
		dFechaNacimiento = new Date();
		dFechaIngreso = new Date();
		dFechaVencimiento = new Date();
		bActivo = false;
      }catch (Exception e) { System.out.println ("Cannot connect to database server"); }
   }
	
	public Persona(int iIDPersona, String sNombre, String sCorreo, String sContrasena, Date dFechaNacimiento,
		Date dFechaIngreso, Date dFechaVencimiento, boolean bActivo) {
		try {
			String userName = "root";
	        String password = "";
	        String url = "jdbc:mysql://localhost/rima";
	        Class.forName ("com.mysql.jdbc.Driver").newInstance();
	        conn = DriverManager.getConnection (url, userName, password);
	        stmt = conn.createStatement();
	        stmt2 = conn.createStatement();
			
			this.iIDPersona = iIDPersona;
			this.sNombre = sNombre;
			this.sCorreo = sCorreo;
			this.sContrasena = sContrasena;
			this.dFechaNacimiento = dFechaNacimiento;
			this.dFechaIngreso = dFechaIngreso;
			this.dFechaVencimiento = dFechaVencimiento;
			this.bActivo = bActivo;
		}catch (Exception e) { System.out.println ("Cannot connect to database server"); }
	}
	
	public void setiIDPersona( int iID ) {
	    if ( !corroborarExistencia( iID ) )
	        iIDPersona = iID;
        else
            iIDPersona = -1;
	}
	
	public int getiIDPersona() {
	    return iIDPersona;
	}
	
	public void setsNombre( String sNom ) {
	    sNombre = sNom;
	}
	
	public String getsNombre() {
	    return sNombre;
	}
	
	public void setsCorreo( String sCor ) {
	    sCorreo = sCor;;
	}
	
	public String getsCorreo() {
	    return sCorreo;
	}
	
	public void setsContrasena( String sCont ) {
	    sContrasena = sCont;
	}
	
	public String getsContrasena() {
	    return sContrasena;
	}
	
	public void setdFechaNacimiento( Date dFecha ) {
	    dFechaNacimiento = dFecha;
	}
	
	public Date getFechaNacimiento() {
	    return dFechaNacimiento; 
	}
	
	public void setdFechaIngreso( Date dFecha ) {
	    dFechaIngreso = dFecha;
	}
	
	public Date getdFechaIngreso() {
	    return dFechaIngreso;
	}
	
	public void setdFechaVencimiento( Date dFecha ) {
	    dFechaVencimiento = dFecha;
	}
	
	public Date getdFechaVencimiento() {
	    return dFechaVencimiento;
	}
	
	public void setbActivo( boolean bAct ) {
	    bActivo = bAct;
	}
	
	public boolean getbActivo() {
	    return bActivo;
	}
	
	public Date consultarFechaIngreso( int iIDPersona ) {
            Date dFechaVencimiento = new Date();
            Calendar cal = Calendar.getInstance();
            
            try{
				System.out.println("Llego");
                stmt.executeQuery ("SELECT * FROM Persona WHERE iIDPersona = " + iIDPersona);
                ResultSet rs = stmt.getResultSet();
                if(rs.next()) {
                   String _strDate = rs.getString("sFechaIngreso");
                   int day = Integer.parseInt(_strDate.substring(8,9));
                   int month = Integer.parseInt(_strDate.substring(5,6));
                   int year = Integer.parseInt(_strDate.substring(0,3));
                   cal.set(Calendar.DATE, day);
                   cal.set(Calendar.MONTH, month);
                   cal.set(Calendar.YEAR, year);
                   dFechaVencimiento = cal.getTime();
                   return dFechaVencimiento;
                }
                return null;
            } catch (SQLException e) {return null;}
        }
        
        public Date consultarFechaVencimiento( int iIDPersona ) {
		Date dFechaVencimiento = new Date();
		Calendar cal = Calendar.getInstance();
		try{
					System.out.println("Llego");
                    stmt.executeQuery ("SELECT * FROM Persona WHERE iIDPersona = " + iIDPersona);
                    ResultSet rs = stmt.getResultSet();
                    if(rs.next()) {
                       String _strDate = rs.getString("dFechaVencimiento");
                       int day = Integer.parseInt(_strDate.substring(8,9));
                       int month = Integer.parseInt(_strDate.substring(5,6));
                       int year = Integer.parseInt(_strDate.substring(0,3));
                       cal.set(Calendar.DATE, day);
                       cal.set(Calendar.MONTH, month);
                       cal.set(Calendar.YEAR, year);
                       dFechaVencimiento = cal.getTime();
                       return dFechaVencimiento;
                    }
                    return null;
                } catch (SQLException e) {return null;}
	}
	
	public boolean corroborarExistencia( int iIDPersona ) {
		try{
					System.out.println("Llego");
                    stmt.executeQuery ("SELECT * FROM Persona WHERE iIDPersona = " + iIDPersona);
                    ResultSet rs = stmt.getResultSet();
                    
                    if (rs.next()) {
						return true;
					}
					else {
						System.out.println("No se encontro");
						return false;
					}
                } catch (SQLException e) {
					System.out.println("Cannot execute generarID()" + e);
					return false;}
	}
	
	public boolean corroborarInformacion( String sCorreo, String sContrasena) {
		try{
					System.out.println("Llego");
					stmt.executeQuery ("SELECT * FROM Persona WHERE sCorreo = '" + sCorreo + "' AND sContrasena = '" + sContrasena + "'");
					ResultSet rs = stmt.getResultSet();
					
					if (rs.next()) {
						System.out.println("Usuario localizado.");
						return true;
					}
					else {
						System.out.println("Usuario no encontrado.");
						return false;
					}
		   } catch (SQLException e) {
			   System.out.println("Error: " + e);
			   return false;
			}
	}
	
	public boolean desactivarPersona( int iIDPersona ) {
		try {
			System.out.println("Se desactivara persona.");
			String s = "UPDATE Persona SET bActivo = " + "false" + " WHERE iIDPersona = " + iIDPersona;
			stmt.executeUpdate(s);
		     } catch (SQLException e) {System.out.println ("Cannot execute desactivarPersona()" + e);}
		return true;
	}
	
	public boolean activarPersona( int iIDPersona ) {
                try {
						System.out.println("Llego");
                        String s = "UPDATE Persona SET bActivo = " + true + " WHERE iIDPersona = " + iIDPersona;
                        stmt.executeUpdate(s);
                    } catch (SQLException e) {System.out.println ("Cannot execute activarPersona()" + e);}
                return true;
	}
	
        public int generarID() {
            try {
				System.out.println("generando ID");
                stmt.executeQuery("SELECT COUNT(*) FROM Persona");
                ResultSet rsiIDPersona = stmt.getResultSet();
                
                if ( rsiIDPersona.next() ) {
					System.out.println("ID generado");
                    return rsiIDPersona.getInt("COUNT(*)") + 1;
                }
            } catch (SQLException ex) { System.out.println("Cannot execute generarID()" + ex); }
            
            return -1;  // Regresa
        }
}

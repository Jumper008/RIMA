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
	protected transient Conexion conn;
	protected Statement stmt;
	
        public Persona(Conexion conn) {
		this.conn = conn;
        }
	
	public Persona() {
		iIDPersona = 0;
		sNombre = "";
		sCorreo = "";
		sContrasena = "";
		dFechaNacimiento = new Date();
		dFechaIngreso = new Date();
		dFechaVencimiento = new Date();
		bActivo = false;
	}
	
	public Persona(int iIDPersona, String sNombre, String sCorreo, String sContrasena, Date dFechaNacimiento,
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
                stmt.executeQuery ("SELECT * FROM Persona WHERE iIDPersona = " + iIDPersona);
                ResultSet rs = stmt.getResultSet();
                if(rs.next()) {
                   String _strDate = rs.getString("dFechaIngreso");
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
                    stmt.executeQuery ("SELECT * FROM Persona WHERE iIDPersona = " + iIDPersona);
                    ResultSet rs = stmt.getResultSet();
                    
                    if (rs.next()) {
						return true;
					}
					else {
						return false;
					}
                } catch (SQLException e) {return false;}
	}
	
	public boolean corroborarInformacion( String sCorreo, String sContrasena) {
		try{
					stmt.executeQuery ("SELECT * FROM Persona WHERE sCorreo = " + sCorreo + " AND sContrasena = " + sContrasena);
					ResultSet rs = stmt.getResultSet();
					
					if (rs.next()) {
						return true;
					}
					else {
						return false;
					}
		   } catch (SQLException e) {return false;}
	}
	
	public boolean desactivarPersona( int iIDPersona ) {
		try {
			String s = "UPDATE Persona SET bActivo = " + false + " WHERE iIDPersona = " + iIDPersona;
			conn.stmt.executeUpdate(s);
		     } catch (SQLException e) {System.out.println ("Cannot execute desactivarPersona()" + e);}
		return true;
	}
	
	public boolean activarPersona( int iIDPersona ) {
                try {
                        String s = "UPDATE Persona SET bActivo = " + true + " WHERE iIDPersona = " + iIDPersona;
                        conn.stmt.executeUpdate(s);
                    } catch (SQLException e) {System.out.println ("Cannot execute activarPersona()" + e);}
                return true;
	}
	
        public int generarID() {
            try {
                stmt.executeQuery("SELECT COUNT(*) FROM Persona AS NewId");
                ResultSet rsiIDPersona = stmt.getResultSet();
                
                if ( rsiIDPersona.next() ) {
                    return rsiIDPersona.getInt("NewId") + 1;
                }
            } catch (SQLException ex) { System.out.println("Cannot execute generarID()" + ex); }
            
            return -1;  // Regresa
        }
}

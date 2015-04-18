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

public class Persona {
	protected int iIDPersona;
	protected String sNombre;
	protected String sCorreo;
	protected String sContrasena;
	protected Date dFechaNacimiento;
	protected Date dFechaIngreso;
	protected Date dFechaVencimiento;
	protected boolean bActivo;
	
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
	
	public boolean corroborarExistencia( int iIDPersona ) {
		return true;
	}
	
	public boolean desactivarPersona( int iIDPersona ) {
		return true;
	}
	
	public boolean activarPersona( int iIDPersona ) {
		return true;
	}
	
}

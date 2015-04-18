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
	protected Date dFechaNacimeinto;
	protected Date dFechaIngreso;
	protected Date dFechaVencimiento;
	protected boolean bActivo;

	public void mostrarTiempoRestanteSuscripcion( int iIDPersona ) {

	}
	
	public boolean corroborarExistencia( int iIDPersona ) {
		
	}
	
	public boolean desactivarPersona( int iIDPersona ) {
		
	}
	
	public boolean activarPersona( int iIDPersona ) {
		
	}
	
}

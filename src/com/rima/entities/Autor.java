/*
 *Author: Equipo 5
 *
 *Version: 1.0.0
 *
 */
package com.rima.entities;

import java.util.*;
import java.sql.*;
import java.util.Date;
import java.util.Calendar;

public class Autor extends Persona{
	
	public Autor(Conexion conn) {
		super(conn);
	}
	
	public Autor() {
		super();
	}
	
	public Autor(int iIDPersona, String sNombre, String sCorreo, String sContrasena, Date dFechaNacimiento,
		       Date dFechaIngreso, Date dFechaVencimiento, boolean bActivo) {
		super(iIDPersona, sNombre, sCorreo, sContrasena, dFechaNacimiento, dFechaIngreso, dFechaVencimiento, bActivo);
	}

	// Consultar TODAS las publicaciones de un autor
        public Vector <Articulo> consultarPublicaciones( int iIDPersona ) {
		Vector<Articulo> vArticulo = new Vector<Articulo>();
		try{
                    stmt.executeQuery(
                            "SELECT Art.* FROM Articulos Art, AutorArticulo AuAr" +
                            "	WHERE Art.iIDArticulo IN ( SELECT AuAr.iIDArticulo FROM AutorArticulo" +
                            "						WHERE iIDAutor =" + iIDPersona + ") AND bPublicado = " + true );
                    
                    ResultSet rsQuery = stmt.getResultSet();
                    while ( rsQuery.next() ) {
                        int _iIDArticulo = rsQuery.getInt("iIDArticulo");
                        String _strNombre = rsQuery.getString("sNombre");
                        String _strResumen = rsQuery.getString("sResumen");
                        boolean _bPublicado = rsQuery.getBoolean("bPublicado");
                        int _iContador = rsQuery.getInt("iContador");
                        int _iIDRevista = rsQuery.getInt("iIDRevista");
                        
                        Articulo arArticulo = new Articulo(_iIDArticulo,_strNombre,_strResumen,_bPublicado,_iContador, _iIDRevista);
                        
                        vArticulo.add(arArticulo);
                    } 
		}catch (SQLException e) {System.out.println ("Cannot execute consultarPublicaciones()" + e);}
		
                return vArticulo;
	}
	
	// Consultar las publicaciones propias (tanto publicadas como no publicadas)
	public Vector <Articulo> consultarPublicacionesPropias( int iIDPersona ) {
		Vector<Articulo> vArticulo = new Vector<Articulo>();
		try{
                    stmt.executeQuery(
                            "SELECT Art.* FROM Articulos Art, AutorArticulo AuAr" +
                            "	WHERE Art.iIDArticulo IN ( SELECT AuAr.iIDArticulo FROM AutorArticulo" +
                            "						WHERE iIDAutor =" + iIDPersona + ") AND bPublicado = " + true );
                    
                    ResultSet rsQuery = stmt.getResultSet();
                    while ( rsQuery.next() ) {
                        int _iIDArticulo = rsQuery.getInt("iIDArticulo");
                        String _strNombre = rsQuery.getString("sNombre");
                        String _strResumen = rsQuery.getString("sResumen");
                        boolean _bPublicado = rsQuery.getBoolean("bPublicado");
                        int _iContador = rsQuery.getInt("iContador");
                        int _iIDRevista = rsQuery.getInt("iIDRevista");
                        
                        Articulo arArticulo = new Articulo(_iIDArticulo,_strNombre,_strResumen,_bPublicado,_iContador, _iIDRevista);
                        
                        vArticulo.add(arArticulo);
                    } 
		}catch (SQLException e) {System.out.println ("Cannot execute consultarPublicaciones()" + e);}
		
                return vArticulo;
	} 
	
	// Regresa el id y el nombre de los autores (el id sirve para poder identificar el autor después)
        public Vector <Autor> mostrarNombreDeAutores() {
		Vector<Autor> vAutores = new Vector<Autor>();
		try{
			stmt.executeQuery("SELECT * FROM Autor");
			ResultSet rsQuery = stmt.getResultSet();
                        Calendar cal = Calendar.getInstance();
                        
			while(rsQuery.next()) {
                            int iIDPersonaAux = rsQuery.getInt("iIDAutor");
                            String sNombre = rsQuery.getString("sNombre");
                            String sCorreo = rsQuery.getString("sCorreo");
                            String sContrasena = rsQuery.getString("sContrasena");

                            String _strDate = rsQuery.getString("dFechaNacimiento");
                            int day = Integer.parseInt(_strDate.substring(8,9));
                            int month = Integer.parseInt(_strDate.substring(5,6));
                            int year = Integer.parseInt(_strDate.substring(0,3));
                            cal.set(Calendar.DATE, day);
                            cal.set(Calendar.MONTH, month);
                            cal.set(Calendar.YEAR, year);
                            Date dFechaNacimiento = cal.getTime();

                            boolean bActivo = rsQuery.getBoolean("bActivo");

                            _strDate = rsQuery.getString("dFechaIngreso");
                            day = Integer.parseInt(_strDate.substring(8,9));
                            month = Integer.parseInt(_strDate.substring(5,6));
                            year = Integer.parseInt(_strDate.substring(0,3));
                            cal.set(Calendar.DATE, day);
                            cal.set(Calendar.MONTH, month);
                            cal.set(Calendar.YEAR, year);
                            Date dFechaIngreso = cal.getTime();

                            _strDate = rsQuery.getString("dFechaVencimiento");
                            day = Integer.parseInt(_strDate.substring(8,9));
                            month = Integer.parseInt(_strDate.substring(5,6));
                            year = Integer.parseInt(_strDate.substring(0,3));
                            cal.set(Calendar.DATE, day);
                            cal.set(Calendar.MONTH, month);
                            cal.set(Calendar.YEAR, year);
                            Date dFechaVencimiento = cal.getTime();

                            vAutores.add( new Autor(iIDPersonaAux, sNombre, sCorreo, sContrasena, dFechaNacimiento, dFechaIngreso, dFechaVencimiento, bActivo) );
			}
		}catch (SQLException e) {System.out.println ("Cannot execute mostrarNombreDeAutores()" + e);}
		
                return vAutores;
	}
	
	public boolean agregarAutor( Autor autAutor ) {
		Calendar cal = Calendar.getInstance();
		int iIDAutor = autAutor.iIDPersona;
		String sNombre = autAutor.sNombre;
		String sCorreo = autAutor.sCorreo;
		Date dFechaNacimiento = autAutor.dFechaNacimiento;
		cal.setTime(dFechaNacimiento);
		int iday = cal.get(Calendar.DATE);
		int imonth = cal.get(Calendar.MONTH);
		int iyear = cal.get(Calendar.YEAR);
		String sDate = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
                String sContrasena = autAutor.sContrasena;
		Date dFechaIngreso = autAutor.dFechaIngreso;
		cal.setTime(dFechaIngreso);
		iday = cal.get(Calendar.DATE);
		imonth = cal.get(Calendar.MONTH);
		iyear = cal.get(Calendar.YEAR);
		String sDateIn = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
		Date dFechaVencimiento = autAutor.dFechaVencimiento;
		cal.setTime(dFechaVencimiento);
		iday = cal.get(Calendar.DATE);
		imonth = cal.get(Calendar.MONTH);
		iyear = cal.get(Calendar.YEAR);
		String sDateVen = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);

		try{
			if(!corroborarExistencia(iIDAutor)){
				String s = "INSERT INTO Autor (iIDAutor, sNombre, sCorreo, sContrsena, dFechaNacimiento, dFechaIngreso, dFechaIngreso, dFechaVencimiento)"+
			 	"VALUES ("+ iIDAutor + " , '" + sNombre + " , '"
			 	+ sCorreo + " , '" + sContrasena + ",'" + sDate + ",'"
				+ sDateIn+ ",'" + sDateVen+ " )";
				conn.stmt.executeUpdate(s);
				return true;
			}
			else
				return false;
			
		} catch (SQLException e) {System.out.println ("Cannot execute agregarAutor()"+ e);}
		return false;
	}
	
	public boolean editarAutor( Autor autAutor ) {
		try {
			Calendar cal = Calendar.getInstance();
			int iIDAutor = autAutor.iIDPersona;
			String sNombre = autAutor.sNombre;
			String sCorreo = autAutor.sCorreo;
			Date dFechaNacimiento = autAutor.dFechaNacimiento;
			cal.setTime(dFechaNacimiento);
			int iday = cal.get(Calendar.DATE);
			int imonth = cal.get(Calendar.MONTH);
			int iyear = cal.get(Calendar.YEAR);
			String sDate = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
			String sContrasena = autAutor.sContrasena;
			Date dFechaIngreso = autAutor.dFechaIngreso;
			cal.setTime(dFechaIngreso);
			iday = cal.get(Calendar.DATE);
			imonth = cal.get(Calendar.MONTH);
			iyear = cal.get(Calendar.YEAR);
			String sDateIn = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
			Date dFechaVencimiento = autAutor.dFechaVencimiento;
			cal.setTime(dFechaVencimiento);
			iday = cal.get(Calendar.DATE);
			imonth = cal.get(Calendar.MONTH);
			iyear = cal.get(Calendar.YEAR);
			String sDateVen = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);


			String sN = "UPDATE Autor SET sNombre = " + sNombre + " WHERE iIDAutor = " + iIDAutor;
			conn.stmt.executeUpdate(sN);

			String sC = "UPDATE Autor SET sCorreo = " + sCorreo + " WHERE iIDAutor = " + iIDAutor;
			conn.stmt.executeUpdate(sC);

			String sD = "UPDATE Autor SET dFechaNacimiento = " + sDate + " WHERE iIDAutor = " + iIDAutor;
			conn.stmt.executeUpdate(sD);

			String sCo = "UPDATE Autor SET sContrasena = " + sContrasena + " WHERE iIDAutor = " + iIDAutor;
			conn.stmt.executeUpdate(sCo);

			String sDI = "UPDATE Autor SET dFechaIngreso = " + sDateIn + " WHERE iIDAutor = " + iIDAutor;
			conn.stmt.executeUpdate(sDI);

			String sDV = "UPDATE Autor SET dFechaVencimiento =" + sDateVen + "WHERE iIDAutor = " + iIDAutor;

		     } catch (SQLException e) {System.out.println ("Cannot execute editarAutor()" + e);}
		return true;
	}
	
	public Autor consultarInformacion( int iIDPersona ) {
		Date dFechaVencimiento = new Date();
		Date dFechaIngreso = new Date();
		Date dFechaNacimiento = new Date();
		Calendar cal = Calendar.getInstance();
		Autor auAutor = new Autor();
		
		try{
			stmt.executeQuery("SELECT iIDAutor FROM Autor Where iIDAutor = " + iIDPersona);
			ResultSet rsiIDAutor = stmt.getResultSet();
			int iIDPersonaAux = rsiIDAutor.getInt("iIDAutor");

			stmt.executeQuery("SELECT sNombre FROM Autor Where iIDAutor = " + iIDPersona);
			ResultSet rsNombre = stmt.getResultSet();
			String sNombre = rsNombre.getString("sNombre");

			stmt.executeQuery("SELECT sCorreo FROM Autor Where iIDAutor = " + iIDPersona);
			ResultSet rsCorreo = stmt.getResultSet();
			String sCorreo = rsCorreo.getString("sCorreo");

			stmt.executeQuery("SELECT dFechaNacimiento FROM Autor Where iIDAutor = " + iIDPersona);
			ResultSet rsFechaNacimiento = stmt.getResultSet();
			String _strDate = rsFechaNacimiento.getString("dFechaNacimiento");
			int day = Integer.parseInt(_strDate.substring(8,9));
			int month = Integer.parseInt(_strDate.substring(5,6));
			int year = Integer.parseInt(_strDate.substring(0,3));
			cal.set(Calendar.DATE, day);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.YEAR, year);
			dFechaNacimiento = cal.getTime();

			stmt.executeQuery("SELECT sContrasena FROM Autor Where iIDAutor = " + iIDPersona);
			ResultSet rsContrasena = stmt.getResultSet();
			String sContrasena = rsContrasena.getString("sContrasena");
			
			stmt.executeQuery("SELECT bActivo FROM Autor Where iIDAutor = " + iIDPersona);
			ResultSet rsActivo = stmt.getResultSet();
			boolean bActivo = rsActivo.getBoolean("bActivo");

			stmt.executeQuery("SELECT dFechaIngreso FROM Autor Where iIDAutor = " + iIDPersona);
			ResultSet rsFechaIngreso = stmt.getResultSet();
			_strDate = rsFechaIngreso.getString("dFechaIngreso");
			day = Integer.parseInt(_strDate.substring(8,9));
			month = Integer.parseInt(_strDate.substring(5,6));
			year = Integer.parseInt(_strDate.substring(0,3));
			cal.set(Calendar.DATE, day);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.YEAR, year);
			dFechaIngreso = cal.getTime();

			stmt.executeQuery("SELECT dFechaVencimiento FROM Autor WHERE iIDAutor = "+ iIDPersona);
			ResultSet rsFechaVencimiento = stmt.getResultSet();
			_strDate = rsFechaNacimiento.getString("dFechaVencimiento");
			day = Integer.parseInt(_strDate.substring(8,9));
			month = Integer.parseInt(_strDate.substring(5,6));
			year = Integer.parseInt(_strDate.substring(0,3));
			cal.set(Calendar.DATE, day);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.YEAR, year);
			dFechaVencimiento = cal.getTime();

			auAutor = new Autor(iIDPersonaAux, sNombre, sCorreo, sContrasena, dFechaNacimiento, dFechaIngreso, dFechaVencimiento, bActivo);
		}catch (SQLException e) {System.out.println ("Cannot execute consultarInformacion()" + e);}
		return auAutor;
	}
}
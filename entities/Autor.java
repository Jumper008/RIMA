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

public class Autor extends Persona{
	
	public Autor() {
		super();
	}
	
	public Autor(int iIDPersona, String sNombre, String sCorreo, String sContrasena, Date dFechaNacimiento,
		       Date dFechaIngreso, Date dFechaVencimiento, boolean bActivo) {
		super(iIDPersona, sNombre, sCorreo, sContrasena, dFechaNacimiento, dFechaIngreso, dFechaVencimiento, bActivo);
	}

	// Consultar todas las publicaciones de un autor visibles al público general
        public Vector <Articulo> consultarPublicaciones( int iIDPersona ) {
		Vector<Articulo> vArticulo = new Vector<Articulo>();
		try{
                    System.out.println("Llego");
                    stmt.executeQuery(
                            "SELECT * FROM Articulos" +
                            "	WHERE iIDArticulo IN ( SELECT iIDArticulo FROM AutorArticulo" +
                            "                               WHERE iIDAutor =" + iIDPersona + ") AND bPublicado = " + true );
                    
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
	
	// Consultar TODAS las publicaciones de un autor (tanto publicadas como no publicadas)
	public Vector <Articulo> consultarPublicacionesPropias( int iIDPersona ) {
		Vector<Articulo> vArticulo = new Vector<Articulo>();
		try{
            System.out.println("Llego");
            stmt.executeQuery(
                    "SELECT * FROM Articulos" +
                    "	WHERE iIDArticulo IN ( SELECT iIDArticulo FROM AutorArticulo" +
                    "						WHERE iIDAutor =" + iIDPersona + ")" );
            
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
            System.out.println("Llego");
			stmt.executeQuery("SELECT * FROM Persona WHERE Tipo = Autor");
			ResultSet rsQuery = stmt.getResultSet();
                        Calendar cal = Calendar.getInstance();
                        
			while(rsQuery.next()) {
                            int iIDPersonaAux = rsQuery.getInt("iIDPersona");
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
        System.out.println("entra al autor");
		int iIDAutor = autAutor.iIDPersona;
		String sNombre = autAutor.sNombre;
		String sCorreo = autAutor.sCorreo;
        Calendar cal = Calendar.getInstance();
		
        Date dFechaNacimiento = autAutor.dFechaNacimiento;
            cal.setTime(dFechaNacimiento);
            int iday = cal.get(Calendar.DATE);
            int imonth = cal.get(Calendar.MONTH);
            int iyear = cal.get(Calendar.YEAR);
            String sMonth;
            String sDay;
            if (imonth < 10) {
                sMonth = '0' + Integer.toString(imonth);
            } else {
                sMonth = Integer.toString(imonth);;
            }
            if (iday < 10) {
                sDay = '0' + Integer.toString(iday);
            } else {
                sDay = Integer.toString(iday);
            }
            String sDate = Integer.toString(iyear) + "/" + sMonth + "/" + sDay;
                
            String sContrasena = autAutor.sContrasena;
		
            Date dFechaIngreso = autAutor.dFechaIngreso;
            cal.setTime(dFechaIngreso);
            iday = cal.get(Calendar.DATE);
            imonth = cal.get(Calendar.MONTH);
            iyear = cal.get(Calendar.YEAR);
            if (imonth < 10) {
                sMonth = '0' + Integer.toString(imonth);
            } else {
                sMonth = Integer.toString(imonth);;
            }
            if (iday < 10) {
                sDay = '0' + Integer.toString(iday);
            } else {
                sDay = Integer.toString(iday);
            }
            String sDateIn = Integer.toString(iyear) + "/" + sMonth + "/" + sDay;
            
            Date dFechaVencimiento = autAutor.dFechaIngreso;
            cal.setTime(dFechaVencimiento);
            iday = cal.get(Calendar.DATE);
            imonth = cal.get(Calendar.MONTH);
            iyear = cal.get(Calendar.YEAR) + 1;
            if (imonth < 10) {
                sMonth = '0' + Integer.toString(imonth);
            } else {
                sMonth = Integer.toString(imonth);;
            }
            if (iday < 10) {
                sDay = '0' + Integer.toString(iday);
            } else {
                sDay = Integer.toString(iday);
            }
            String sDateVen = Integer.toString(iyear) + "/" + sMonth + "/" + sDay;
            
                boolean bActivo = autAutor.bActivo;
                String sTipo = "Autor";
                
		try{
			if(!corroborarExistencia(iIDAutor)){
                            // Entrada en la tabla Persona
                            //System.out.println("agrega autor");
                            String sQueryPersona = "INSERT INTO Persona "  
                                   + "(iIDPersona, sNombre, sCorreo,"
                                   + "sContrasena, sFechaNacimiento, "
                                   + " sFechaIngreso, "
                                   + " sFechaVencimiento, bActivo, sTipo)"
                                    + "VALUES ("
                                    + iIDAutor + " , '" 
                                    + sNombre + "', '" 
                                    + sCorreo + "' , '" 
                                    + sContrasena + "', '" 
                                    + sDate + "', '" 
                                    + sDateIn+ "', '" 
                                    + sDateVen + "', " 
                                    + bActivo + ", '" 
                                    + sTipo + "' )";
                            
                            // Entrada en la tabla Autor
                            String sQueryAutor = "INSERT INTO Autor"
                            + "(iIDPersona, sTipo) VALUES ("
                             + iIDAutor + " , '"
                             + sTipo+  "')";
                       
                            stmt.executeUpdate(sQueryPersona);
                            System.out.println("Se agrego persona");
                            stmt.executeUpdate(sQueryAutor);
                            System.out.println("Se agrego autor");
                                
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
                        
                        boolean bActivo = autAutor.bActivo;
                        
                        System.out.println("Llego");
                        String sQuery = "UPDATE Persona SET "
                                + "sNombre = '" + sNombre + "',"
                                + "sCorreo = '" + sCorreo + "',"
                                + "dFechaNacimiento = '" + sDate + "', "
                                + "sContrasena = '" + sContrasena + "', "
                                + "dFechaIngreso = '" + sDateIn + "', "
                                + "dFechaVencimiento = '" + sDateVen + "', "
                                + "bActivo = " + bActivo
                                + " WHERE iIDPersona = " + iIDAutor;
			stmt.executeUpdate(sQuery);
                        
                    } catch (SQLException e) {System.out.println ("Cannot execute editarAutor()" + e);}
		return true;
	}
	
	public Autor consultarInformacion( int iIDPersona ) {
		Calendar cal = Calendar.getInstance();
		Autor auAutor = new Autor();
		
		try{
                    System.out.println("Llego");
                    stmt.executeQuery("SELECT * FROM Persona WHERE iIDPersona = " + iIDPersona);
                    ResultSet rsQuery = stmt.getResultSet();
                    
                    if ( rsQuery.next() ) { // Agregar 1er resultado de búsqueda solo si se encontró alguno.
                        int iIDPersonaAux = rsQuery.getInt("iIDPersona");
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

                        auAutor = new Autor(iIDPersonaAux, sNombre, sCorreo, sContrasena, dFechaNacimiento, dFechaIngreso, dFechaVencimiento, bActivo);
                    }
                    
		}catch (SQLException e) {System.out.println ("Cannot execute consultarInformacion()" + e);}
		
                return auAutor; // Regresa los resultados de la búsqueda.
	}
}
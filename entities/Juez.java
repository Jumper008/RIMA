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

public class Juez extends Autor{
    private boolean bJuezActivo;
	
	public Juez() {
		super();
        bJuezActivo = true;
	}
	
	public Juez(int iIDPersona, String sNombre, String sCorreo, String sContrasena, Date dFechaNacimiento,
		       Date dFechaIngreso, Date dFechaVencimiento, boolean bActivo, boolean bJuezActivo) {
		super(iIDPersona, sNombre, sCorreo, sContrasena, dFechaNacimiento, dFechaIngreso, dFechaVencimiento, bActivo);
                this.bJuezActivo = bJuezActivo;
	}
        
    public boolean getbJuezActivo() {
        return bJuezActivo;
    }
    
    public void setbJuezActivo( boolean bJuezActivo ) {
        this.bJuezActivo = bJuezActivo;
    }

	public boolean agregarJuezByAdmin(int iIDJuez ) {
		String sTipo;
		try{
                    if( corroborarExistencia(iIDJuez) ) { //Si ya existía, entonces era un autor, por lo que hay que desactivarlo como autor y activarlo como Juez
                        // Entrada en la tabla Juez
						String sQuery = "SELECT sTipo FROM Persona WHERE iIDPersona = " + iIDJuez;
						stmt.executeQuery(sQuery);
						ResultSet rsQuery = stmt.getResultSet();
                    
                    	if ( rsQuery.next() ) {
							sTipo = rsQuery.getString("sTipo");
						}
						else {
							System.out.println("No se encontro sTipo");
							return false;
						}
						
						if(sTipo.equals("Autor")) {
							System.out.println("Existe persona, agregando juez con ID: "+ Integer.toString(iIDJuez) + ".");
	                        String sQueryJuez = "INSERT INTO Juez"
								+ "(iIDPersona, bJuezActivo) VALUES ("
								+ iIDJuez + " , " 
								+ "true" + " )";
	                        
	                        // Actualización en la tabla Persona (el autor pasa de ser autor a juez)
	                        String sQueryPersona = "UPDATE Persona SET "
	                                + "sTipo = '" + "Juez"
	                                + "' WHERE iIDPersona = " + iIDJuez;
	                        
	                        System.out.println("Comenzando queries.");
							System.out.println(sQueryJuez);
							System.out.println(sQueryPersona);
							stmt.executeUpdate(sQueryJuez);
							System.out.println("Juez agregado exitosamente.");
	                        stmt.executeUpdate(sQueryPersona);
							System.out.println("Persona actualizada exitosamente.");
	                        
	                        return true;
						}
                    }
			
		} catch (SQLException e) {
			System.out.println ("Cannot execute agregarJuez()"+ e);
		}
		System.out.println("No se pudo corroborar la existencia del usuario");
       return false; // Si no se pudo agregar el Juez, regresar falso.
	}
	
	public boolean editarJuez( Juez juJuez, int iIDJuez ) {
		try {
			Calendar cal = Calendar.getInstance();
			String sNombre = juJuez.sNombre;
			String sCorreo = juJuez.sCorreo;
			
                        Date dFechaNacimiento = juJuez.dFechaNacimiento;
			cal.setTime(dFechaNacimiento);
			int iday = cal.get(Calendar.DATE);
			int imonth = cal.get(Calendar.MONTH);
			int iyear = cal.get(Calendar.YEAR);
			String sDate = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
			
                        String sContrasena = juJuez.sContrasena;
			
/*                        Date dFechaIngreso = juJuez.dFechaIngreso;
			cal.setTime(dFechaIngreso);
			iday = cal.get(Calendar.DATE);
			imonth = cal.get(Calendar.MONTH);
			iyear = cal.get(Calendar.YEAR);
			String sDateIn = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
			
                        Date dFechaVencimiento = juJuez.dFechaVencimiento;
			cal.setTime(dFechaVencimiento);
			iday = cal.get(Calendar.DATE);
			imonth = cal.get(Calendar.MONTH);
			iyear = cal.get(Calendar.YEAR);
			String sDateVen = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
*/
    
                        
                        // Actualización en la tabla Persona
						System.out.println("Llego");
                        String sQueryPersona = "UPDATE Persona SET "
                                + "sNombre = '" + sNombre + "', "
                                + "sCorreo = '" + sCorreo + "', "
                                + "sFechaNacimiento = '" + sDate + "', "
                                + "sContrasena = '" + sContrasena + "' "
                                + " WHERE iIDPersona = " + iIDJuez;
                        
                        // No es necesario actualizar la tabla Autor

                        stmt.executeUpdate(sQueryPersona);

		     } catch (SQLException e) {System.out.println ("Cannot execute editarJuez()" + e);}
		return true;
	}
	
	public Juez consultarInformacion( int iIDPersona ) {
		Date dFechaVencimiento = new Date();
		Date dFechaIngreso = new Date();
		Date dFechaNacimiento = new Date();
		Calendar cal = Calendar.getInstance();
		Juez juJuez = new Juez();
		
		try{
					System.out.println("Llego");
                    stmt.executeQuery("SELECT * FROM Persona"
                            + " WHERE iIDPersona = " + iIDPersona);
                    ResultSet rsQuery = stmt.getResultSet();
                    
                    if ( rsQuery.next() ) {
                        String sNombre = rsQuery.getString("sNombre");
                        String sCorreo = rsQuery.getString("sCorreo");
                        
                        String _strDate = rsQuery.getString("sFechaNacimiento");
			int day = Integer.parseInt(_strDate.substring(8,10));
			int month = Integer.parseInt(_strDate.substring(5,7));
			int year = Integer.parseInt(_strDate.substring(0,4));
			cal.set(Calendar.DATE, day);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.YEAR, year);
			dFechaNacimiento = cal.getTime();
                        
                        String sContrasena = rsQuery.getString("sContrasena");
                        boolean bActivo = rsQuery.getBoolean("bActivo");
						String sTipo = rsQuery.getString("sTipo");
                        
                        String _strDate2 = rsQuery.getString("sFechaIngreso");
			day = Integer.parseInt(_strDate2.substring(8,10));
			month = Integer.parseInt(_strDate2.substring(5,7));
			year = Integer.parseInt(_strDate2.substring(0,4));
			cal.set(Calendar.DATE, day);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.YEAR, year);
			dFechaIngreso = cal.getTime();
                        
                        String _strDate3 = rsQuery.getString("sFechaVencimiento");
			day = Integer.parseInt(_strDate3.substring(8,10));
			month = Integer.parseInt(_strDate3.substring(5,7));
			year = Integer.parseInt(_strDate3.substring(0,4));
			cal.set(Calendar.DATE, day);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.YEAR, year);
			dFechaVencimiento = cal.getTime();
                        
						System.out.println("Llego");
                        stmt.executeQuery("SELECT * FROM Juez WHERE iIDPersona = " + iIDPersona);
                        ResultSet rsQueryJuez = stmt.getResultSet();
                        
                        if ( rsQueryJuez.next() ) {
                            boolean bJuezActivo = rsQueryJuez.getBoolean("bJuezActivo");
                            
                            juJuez = new Juez( iIDPersona, 
                                sNombre, sCorreo, sContrasena, dFechaNacimiento, 
                                dFechaIngreso, dFechaVencimiento, bActivo, bJuezActivo );
                        }
                    }
                    
		} catch (SQLException e) {System.out.println ("Cannot execute consultarInformacion()" + e);}
		
                return juJuez;
	}
	
	public void setiIDPersona( int iID ) {
	        iIDPersona = iID;
	}
}
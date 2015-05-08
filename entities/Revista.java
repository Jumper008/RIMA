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

public class Revista {
	protected int iIDRevista;
	protected Date dFechaPublicacion;
	protected int iNumPaginas;
	protected boolean bPublicada;
	private transient Connection conn;
	private Statement stmt, stmt2;
	
    public Revista(){
      try {
        String userName = "root";
        String password = "";
        String url = "jdbc:mysql://localhost/rima";
        Class.forName ("com.mysql.jdbc.Driver").newInstance();
        conn = DriverManager.getConnection (url, userName, password);
        stmt = conn.createStatement();
        stmt2 = conn.createStatement();
        iIDRevista = 0;
		dFechaPublicacion = new Date();
		iNumPaginas = 0;
		bPublicada = false;
      }catch (Exception e) { System.out.println ("Cannot connect to database server"); }
   }
	
	public Revista(int iIDRevista, Date dFechaPublicacion, int iNumPaginas, boolean bPublicada) {
		this.iIDRevista = iIDRevista;
		this.dFechaPublicacion = dFechaPublicacion;;
		this.iNumPaginas = iNumPaginas;
		this.bPublicada = bPublicada;
	}
        
        public boolean getbPublicada() {
            return bPublicada;
        }
        
        public void setbPublicada( boolean bPublicada ) {
            this.bPublicada = bPublicada;
        }
        
        public Date getdFechaPublicacion() {
            return dFechaPublicacion;
        }
        
        public void setdFechaPublicacion( Date dFechaPublicacion ) {
            this.setdFechaPublicacion(dFechaPublicacion);
        }
        
        public int getiIDRevista() {
            return iIDRevista;
        }
        
        public void setiIDRevista( int iIDRevista ) {
            if ( !corroborarExistencia(iIDRevista) ) {
                this.iIDRevista = iIDRevista;
            }
            else {
                this.iIDRevista = -1;
            }
        }
        
        public int getiNumPaginas() {
            return iNumPaginas;
        }
        
        public void setiNumPaginas( int iNumPaginas ) {
            this.iNumPaginas = iNumPaginas;
        }

	public Vector<Revista> mostrarRevistasPublicadas() {
		Vector<Revista> vRevistas = new Vector<Revista>();
		Calendar cal = Calendar.getInstance();
		try{
            System.out.println("Llego");
		     stmt.executeQuery ("SELECT * FROM Revista WHERE bPublicada = " + true);
		     ResultSet rs = stmt.getResultSet();
		     while(rs.next()) {
			int _iIDRevista = rs.getInt("iIDRevista");
			String _strDate = rs.getString("dFechaPublicacion");
			int iday = Integer.parseInt(_strDate.substring(8,9));
			int imonth = Integer.parseInt(_strDate.substring(5,6));
			int iyear = Integer.parseInt(_strDate.substring(0,3));
			cal.set(Calendar.DATE, iday);
			cal.set(Calendar.MONTH, imonth);
			cal.set(Calendar.YEAR, iyear);
			Date _dFechaPublicacion = cal.getTime();
			int _iNumPaginas = rs.getInt("iNumPaginas");
			boolean _bPublicada = rs.getBoolean("bPublicada");
			Revista reRevista = new Revista(_iIDRevista,_dFechaPublicacion,_iNumPaginas,_bPublicada);
			vRevistas.add(reRevista);
		     }
		     return vRevistas;
		  } catch (SQLException e) { return null;}
	}
	
	public boolean agregarRevista( Revista reRevista ) {
		Calendar cal = Calendar.getInstance();
		int iIDRevista = reRevista.iIDRevista;
		Date dFechaPublicacion = reRevista.dFechaPublicacion;
		cal.setTime(dFechaPublicacion);
		int iday = cal.get(Calendar.DATE);
		int imonth = cal.get(Calendar.MONTH);
		int iyear = cal.get(Calendar.YEAR);
		String sDate = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
		int iNumPaginas = reRevista.iNumPaginas;
		boolean bPublicada = reRevista.bPublicada;
		try {
            System.out.println("Llego");
			String s = "INSERT INTO Revista (iIDRevista, dFechaPublicacion, iNumPaginas, bPublicada)" +
			 " VALUES ("+ iIDRevista + " , '" + sDate + " , '"
			 + iNumPaginas + " , '" + bPublicada + " )"; 
			stmt.executeUpdate(s);
			
		    } catch (SQLException e) {System.out.println ("Cannot execute agregaraRevista()" + e);}
		return true;
	}
        
        public boolean publicarRevista() {
            try {
                System.out.println("Llego");
                String sQuery = "UPDATE Revista SET bPublicada = " + true;
                stmt.executeUpdate(sQuery);
            } catch (SQLException e) {System.out.println ("Cannot execute editarAutor()" + e); return false;}
            
            return true;
        }
        
        public boolean corroborarExistencia( int iIDRevista ) {
		try{
                    System.out.println("Llego");
                    stmt.executeQuery ("SELECT * FROM Revista WHERE iIDRevista = " + iIDRevista);
                    ResultSet rs = stmt.getResultSet();
                    
                    return rs.next();
                } catch (SQLException e) {return false;}
	}
        
        public int generarID() {
            try {
                System.out.println("Llego");
                stmt.executeQuery("SELECT COUNT(*) FROM Revista AS NewId");
                ResultSet rsiIDRevista = stmt.getResultSet();
                
                if ( rsiIDRevista.next() ) {
                    return rsiIDRevista.getInt("NewId") + 1;
                }
            } catch (SQLException ex) { System.out.println("Cannot execute generarID()" + ex); }
            
            return -1;  // Regresa -1 en caso de que haya habido un error
        }
}

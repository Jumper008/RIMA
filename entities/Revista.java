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
        protected String sNombre; 
	protected String sFechaPublicacion;
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
		sFechaPublicacion = "";
		iNumPaginas = 0;
		bPublicada = false;
        sNombre = "";
      }catch (Exception e) { System.out.println ("Cannot connect to database server"); }
   }
	
	public Revista(int iIDRevista, String sNombre, String sFechaPublicacion, int iNumPaginas, boolean bPublicada) {
		this.iIDRevista = iIDRevista;
		this.sFechaPublicacion = sFechaPublicacion;;
		this.iNumPaginas = iNumPaginas;
		this.bPublicada = bPublicada;
                this.sNombre = sNombre;
	}
        
        public String getsNombre() {
            return sNombre;
        }
        
        public void setsNombre( String sNombre ) {
            this.sNombre = sNombre;
        }
        
        public boolean getbPublicada() {
            return bPublicada;
        }
        
        public void setbPublicada( boolean bPublicada ) {
            this.bPublicada = bPublicada;
        }
        
        public String getdFechaPublicacion() {
            return sFechaPublicacion;
        }
        
        
        public void setdFechaPublicacion( String sFechaPublicacion ) {
            this.sFechaPublicacion = sFechaPublicacion;
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
                     System.out.println("Obteniendo revistas");
		     stmt.executeQuery ("SELECT * FROM Revista WHERE bPublicada = " + true);
		     ResultSet rs = stmt.getResultSet();
		     while(rs.next()) {
			int _iIDRevista = rs.getInt("iIDRevista");
			String _strDate = rs.getString("sFechaPublicacion");
//			int iday = Integer.parseInt(_strDate.substring(8,9));
//			int imonth = Integer.parseInt(_strDate.substring(5,6));
//			int iyear = Integer.parseInt(_strDate.substring(0,3));
//			cal.set(Calendar.DATE, iday);
//			cal.set(Calendar.MONTH, imonth);
//			cal.set(Calendar.YEAR, iyear);
//			Date _dFechaPublicacion = cal.getTime();
			int _iNumPaginas = rs.getInt("iNumPaginas");
			boolean _bPublicada = rs.getBoolean("bPublicada");
                        String _sNombre = rs.getString("sNombre");
			Revista reRevista = new Revista(_iIDRevista, _sNombre, _strDate,_iNumPaginas,_bPublicada);
			vRevistas.add(reRevista);
		     }
		     return vRevistas;
		  } catch (SQLException e) {
              System.out.println ("Cannot execute mostrarRevistasPublicadas()" + e);
              return null;
          }
	}
	
	public boolean agregarRevista( Revista reRevista ) {
		Calendar cal = Calendar.getInstance();
		int iIDRevista = reRevista.iIDRevista;
		String sFechaPublicacion = reRevista.sFechaPublicacion;
		cal.setTime(Calendar.getInstance().getTime());
		int iday = cal.get(Calendar.DATE);
		int imonth = cal.get(Calendar.MONTH);
		int iyear = cal.get(Calendar.YEAR);
		String sDate = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
        String sNombre = reRevista.getsNombre();
		int iNumPaginas = reRevista.iNumPaginas;
		boolean bPublicada = reRevista.bPublicada;
		try {
            System.out.println("Llego");
			String s = "INSERT INTO Revista (iIDRevista, sNombre, sFechaPublicacion, iNumPaginas, bPublicada)" +
			 " VALUES ("+ iIDRevista + " , '" + sNombre + "', '" + sDate + "', "
			 + iNumPaginas + ", " + bPublicada + " )"; 
			stmt.executeUpdate(s);
			return true;
		    } catch (SQLException e) {
                System.out.println ("Cannot execute agregaraRevista()" + e);
                return false;
            }
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
                stmt.executeQuery("SELECT COUNT(*) FROM Revista");
                ResultSet rsiIDRevista = stmt.getResultSet();
                
                if ( rsiIDRevista.next() ) {
                    return rsiIDRevista.getInt("COUNT(*)") + 1;
                }
            } catch (SQLException ex) { System.out.println("Cannot execute generarID()" + ex); }
            
            return -1;  // Regresa -1 en caso de que haya habido un error
        }
}

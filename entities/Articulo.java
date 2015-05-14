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

public class Articulo {
	
	protected int iIDArticulo;
	protected String sNombre;
	protected String sResumen;
	protected boolean bPublicado;
	protected int iContador;
    protected int iIDRevista;
	private transient Connection conn;
	private Statement stmt, stmt2;

	public Articulo(){
      try {
        String userName = "root";
        String password = "";
        String url = "jdbc:mysql://localhost/rima";
        Class.forName ("com.mysql.jdbc.Driver").newInstance();
        conn = DriverManager.getConnection (url, userName, password);
        stmt = conn.createStatement();
        stmt2 = conn.createStatement();
        iIDArticulo = 0;
		sNombre = "";
		sResumen = "";
		bPublicado = false;
		iContador = 0;
        iIDRevista = 0;
      }catch (Exception e) { System.out.println ("Cannot connect to database server"); }
   }
	
	public Articulo(int iIDArticulo, String sNombre, String sResumen, boolean bPublicado, int iContador, int iIDRevista) {
		this.iIDArticulo = iIDArticulo;
		this.sNombre = sNombre;
		this.sResumen = sResumen;
		this.bPublicado = bPublicado;
		this.iContador = iContador;
        this.iIDRevista = iIDRevista;
	}
    
    public void setiIDArticulo( int iIDArticulo ) {
        this.iIDArticulo = iIDArticulo;
    }
    
    public int getiIDArticulo() {
        return this.iIDArticulo;
    }
    
    public void setsNombre( String sNombre ) {
        this.sNombre = sNombre;
    }
    
    public String getsNombre() {
        return this.sNombre;
    }
    
    public void setsResumen( String sResumen ) {
        this.sResumen = sResumen;
    }
    
    public String getsResumen() {
        return this.sResumen;
    }
    
    public void setbPublicado( boolean bPublicado ) {
        this.bPublicado = bPublicado;
    }
    
    public boolean getbPublicado() {
        return this.bPublicado;
    }
    
    public void setiContador( int iContador ) {
        this.iContador = iContador;
    }
    
    public int getiContador() {
        return this.iContador;
    }
    
    public void setiIDRevista( int iIDRevista ) {
        this.iIDRevista = iIDRevista;
    }
    
    public int getiIDRevista() {
        return this.iIDRevista;
    }

	public Articulo consultarInformacion( int iIDArticulo ) {
		Articulo arArticulo = new Articulo();
		try{
            System.out.println("Llego");
		     stmt.executeQuery ("SELECT * FROM Articulo WHERE iIDArticulo = " + iIDArticulo);
		     ResultSet rs = stmt.getResultSet();
		     while(rs.next()) {
    			int _iIDArticulo = rs.getInt("iIDArticulo");
    			String _strNombre = rs.getString("sNombre");
    			String _strResumen = rs.getString("sResumen");
    			boolean _bPublicado = rs.getBoolean("bPublicado");
    			int _iContador = rs.getInt("iContador");
                int _iIDRevista = rs.getInt("iIDRevista");
    			arArticulo = new Articulo(_iIDArticulo,_strNombre,_strResumen,_bPublicado,_iContador, _iIDRevista);
            }
		  } catch (SQLException e) { return null;}
		  return arArticulo;
	}

	// regresa solo los artículos publicados de un autor
	public Vector<Articulo> consultarArticulosAutor( int iIDAutor ) {
		Vector<Articulo> vArticulos = new Vector<Articulo>();
		try{
            System.out.println("Llego");
		     stmt.executeQuery ("SELECT * FROM Articulo, Autor WHERE iIDAutor = " + iIDAutor + "AND bPublicado = " + true );
		     ResultSet rs = stmt.getResultSet();
		     while(rs.next()) {
    			int _iIDArticulo = rs.getInt("iIDArticulo");
    			String _strNombre = rs.getString("sNombre");
    			String _strResumen = rs.getString("sResumen");
    			boolean _bPublicado = rs.getBoolean("bPublicado");
    			int _iContador = rs.getInt("iContador");
                int _iIDRevista = rs.getInt("iIDRevista");
    			Articulo arArticulo = new Articulo(_iIDArticulo,_strNombre,_strResumen,_bPublicado,_iContador, _iIDRevista);
    			vArticulos.add(arArticulo);
            }
		  } catch (SQLException e) { return null;}
		  return vArticulos;
	}

	// regresa todos los artículos, publicados o no, de un autor
	public Vector<Articulo> consultarArticulosPropios( int iIDAutor ) {
		Vector<Articulo> vArticulos = new Vector<Articulo>();
		try{
            System.out.println("Llego");
		     stmt.executeQuery ("SELECT * FROM Articulo, Autor WHERE iIDAutor = " + iIDAutor );
		     ResultSet rs = stmt.getResultSet();
		     while(rs.next()) {
    			int _iIDArticulo = rs.getInt("iIDArticulo");
    			String _strNombre = rs.getString("sNombre");
    			String _strResumen = rs.getString("sResumen");
    			boolean _bPublicado = rs.getBoolean("bPublicado");
    			int _iContador = rs.getInt("iContador");
                int _iIDRevista = rs.getInt("iIDRevista");
    			Articulo arArticulo = new Articulo(_iIDArticulo,_strNombre,_strResumen,_bPublicado,_iContador, _iIDRevista);
    			vArticulos.add(arArticulo);
		     }
		  } catch (SQLException e) { return null;}
		  return vArticulos;
	}

	public boolean aumentarVotos( int iIDArticulo ) {
            try {
                System.out.println("Llego");
                stmt.executeQuery ("SELECT iContador FROM Articulo WHERE iIDArticulo = " + iIDArticulo);
                ResultSet rs = stmt.getResultSet();
                if(rs.next()) {
                    int _iContador = rs.getInt("iContador");
                    _iContador++;
                    String s = "UPDATE Articulo SET iContador = " + _iContador + " WHERE iIDArticulo = " + iIDArticulo;
                    stmt.executeUpdate(s);
                    return true;
                }
                else {  // No se encontró el artículo del cual se desean aumentar los votos
                    return false;
                }

            } catch (SQLException e) {System.out.println ("Cannot execute aumentarVotos()" + e); return false; }
	}

	public Vector<Articulo> getArticulosVotados() {
            Vector<Articulo> vArticulos = new Vector<Articulo>();
            try{
                System.out.println("Llego");
                stmt.executeQuery ("SELECT * FROM Articulo WHERE iContador IS NOT NULL and iContador <> 0");
                ResultSet rs = stmt.getResultSet();
                while(rs.next()) {
                    boolean bPublicado = rs.getBoolean("bPublicado");
                    int iContador = rs.getInt("iContador");
                    String sNombre = rs.getString("sNombre");
                    String sResumen = rs.getString("sResumen");
                    int iIDArticulo = rs.getInt("iIDArticulo");
                    int iIDRevista = rs.getInt("iIDRevista");

                    vArticulos.add( new Articulo( iIDArticulo, sNombre, sResumen, 
                            bPublicado, iContador, iIDRevista ) );
                }
            } catch (SQLException e) { return null; }
            return vArticulos;
	}

	public boolean agregarARevista( int iIDArticulo, int iIDRevista ) {
            try {
                System.out.println("Llego");
                String s = "UPDATE Articulo SET iIDRevista = " + iIDRevista + ", bPublicado = " + true + " WHERE iIDArticulo = " + iIDArticulo;
                stmt.executeUpdate(s);
                return true;
            } catch (SQLException e) {System.out.println ("Cannot execute aumentarVotos()" + e); return false; }
	}

	public boolean agregarArticulo( Articulo arArticulo ) {
        int iIDArticulo = arArticulo.iIDArticulo;
        String sNombre = arArticulo.sNombre;
        String sResumen = arArticulo.sResumen;
        boolean bPublicado = arArticulo.bPublicado;
        int iContador = arArticulo.iContador;
        int iIDRevista = arArticulo.iIDRevista;
        try {
            System.out.println("Llego");
            String s = "INSERT INTO Articulo (iIDArticulo, sNombre, sResumen, bPublicado, iContador, iIDRevista)" +
                    " VALUES ("+ iIDArticulo + " , '" 
                    + sNombre + "', '"
                    + sResumen + "', " 
                    + bPublicado + ", '" 
                    + iContador + ", " 
                    + iIDRevista + " )";
            stmt.executeUpdate(s);
            return true;
        } catch (SQLException e) {System.out.println ("Cannot execute agregaraArticulo()" + e); return false; }
	}
        
        public boolean corroborarExistencia( int iIDArticulo ) {
            try{
                    System.out.println("Llego");
                    stmt.executeQuery ("SELECT * FROM Articulo WHERE iIDArticulo = " + iIDArticulo);
                    ResultSet rs = stmt.getResultSet();
                    
                    return rs.next();
                } catch (SQLException e) {return false;}
        }
    
        
        public int generarID() {
            try {
                System.out.println("Llego");
                stmt.executeQuery("SELECT COUNT(*) FROM Articulo");
                ResultSet rs = stmt.getResultSet();
                
                if ( rs.next() ) {
                    return rs.getInt("COUNT(*)") + 1;
                }
            } catch (SQLException ex) { System.out.println("Cannot execute generarID()" + ex); }
            
            return -1;  // Regresa -1 en caso de que haya habido un error
        }
}

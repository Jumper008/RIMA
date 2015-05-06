/*Author: Equipo 5
 *
 *Version: 1.0.0
 *
 */
 package interfaces;
 import controls.*;
 import javax.servlet.*;
 import javax.servlet.http.*;
 import java.io.*;
 import java.util.*;
 import java.util.Calendar;

 public class VentanaDeLecturaDeArticulos extends HttpServlet {	 	 
     HttpServletResponse thisResponse;
     HttpServletRequest thisRequest;
     PrintWriter out;
     verArticulo va;
     int iIDArticulo;
     Articulo articulo;
  
     public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {	
         thisResponse = response;
    	 thisRequest = request;
    	 thisResponse.setContentType("text/html");
    	 out = thisResponse.getWriter();
    	 //Preparar el encabezado de la pagina Web de respuesta
    	 out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">");
	     out.println("<HTML>");
	     out.println("<HEAD>");
	     out.println("<META http-equiv=Content-Type content=\"text/html\">");
	     out.println("</HEAD>");
	     out.println("<BODY>");
	     out.println("<TITLE>SEng Bytes & Bits</TITLE>");
	     out.println("<h2>RIMA</h2>");
	     out.println("<h3>Ver articulo</h3>");
    
	     String operacion = request.getParameter("operacion");
	    
	     if(operacion == null) { // El menú nos envia un parametro para indicar el inicio de una transaccion
	         iniciarConsulta();  
	     }
	    
	     else if(operacion.equals("ver")) {
	         obtenerArticulo();
	     }
  	 }
  
	 public void iniciarConsulta() {
	     out.println("<p>Ingrese el ID del articulo.</p>");
	     out.println("<form method=\"GET\" action=\"articulos\">");
	     out.println("<input type=\"hidden\" name=\"operacion\" value=\"ver\"/>");
	     out.println("<p>  IDArticulo <input type=\"int\" name=\"IDArticulo\" size=\"8\"></p>");
	     out.println("<p><input type=\"submit\" value=\"Ver\"></p>");
	     out.println("</form>");
	 
	     out.println("<form method=\"GET\" action=\"menu.html\">");
	     out.println("<p><input type=\"submit\" value=\"Cancelar\"></p>");
	     out.println("</form>");
	
	     out.println("</BODY>");
	     out.println("</HTML>");    
	 }
  
	 public void obtenerArticulo() {
	     va = new verArticulo();
	     //La funcion trim() elimina espacios antes y despues del valor
		 iIDArticulo = Integer.parseInt(thisRequest.getParameter("IDArticulo").trim());
		 boolean existe = va.validarArticulo(iIDArticulo);
	     if (existe) {
	     	articulo = va.obtenerArticulo(iIDArticulo);
	     	
	     }
	     else
	     {
	     	out.println("<p>El Articulo no existe.</p>");
	     }
	     out.println("<p>Pulse el boton para buscar otro articulo.</p>");
	     out.println("<form method=\"GET\" action=\"articulos\">");
	     out.println("<input type=\"hidden\" name=\"operacion\" value= null/>");
	     out.println("<p><input type=\"submit\" value=\"Intentar de nuevo\"></p>");
	     out.println("</form>");
	 
	     out.println("<form method=\"GET\" action=\"menu.html\">");
	     out.println("<p><input type=\"submit\" value=\"Terminar\"></p>");
	     out.println("</form>");

	     out.println("</BODY>");
	     out.println("</HTML>"); 
	 }

 }
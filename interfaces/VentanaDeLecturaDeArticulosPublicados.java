/*Author: Equipo 5
 *
 *Version: 1.0.0
 *
 */
 package interfaces;
 import controls.*;
 import entities.Articulo;
 import javax.servlet.*;
 import javax.servlet.http.*;
 import java.io.*;
 import java.util.*;
 import java.util.Calendar;

 public class VentanaDeLecturaDeArticulosPublicados extends HttpServlet {	 	 
     HttpServletResponse thisResponse;
     HttpServletRequest thisRequest;
     PrintWriter out;
     verArticuloPublicado vap;
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
	     out.println("<h3>Publicar articulo</h3>");
    
	     String operacion = request.getParameter("operacion");
	    
	     if(operacion == null) { // El menú nos envia un parametro para indicar el inicio de una transaccion
	         iniciarConsulta();  
	     }
	    
	     else if(operacion.equals("ver")) {
	         obtenerArticuloPublicado();
	     }
  	 }
  
	 public void iniciarConsulta() {
	     out.println("<p>Complete los valores indicados.</p>");
	     out.println("<form method=\"GET\" action=\"articulos_publicados\">");
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
  
	 public void obtenerArticuloPublicado() {
	     vap = new verArticuloPublicado();
	     //La funcion trim() elimina espacios antes y despues del valor
		 iIDArticulo = Integer.parseInt(thisRequest.getParameter("IDArticulo").trim());
		 boolean existe = vap.validarArticulo(iIDArticulo);
	     if (existe) {
	     	articulo = vap.obtenerArticuloPublicado(iIDArticulo);
	     	if (articulo.getbPublicado()) {
	     		out.println(articulo.getsNombre() + " | "+ articulo.getsResumen() + "Articulo publicado");
	     	}
	     }
	     else
	     {
	     	out.println("<p>El Articulo no existe.</p>");
	     }
	 }

 }
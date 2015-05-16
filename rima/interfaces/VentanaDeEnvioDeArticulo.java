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

 public class VentanaDeEnvioDeArticulo extends HttpServlet {	 	 
     HttpServletResponse thisResponse;
     HttpServletRequest thisRequest;
     PrintWriter out;
     proponerArticulo pa;
     String sNombre;
	 String sResumen;
  
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
	     out.println("<h3>Envio de Articulo</h3>");
    
	     String operacion = request.getParameter("operacion");
	    
	     if(operacion == null) { // El menú nos envia un parametro para indicar el inicio de una transaccion
	         iniciarEnvioArticulo();  
	     }
	    
	     else if(operacion.equals("agregar")) {
	         agregarArticulo();
	     }
	    
	     else if (operacion.equals("feedback")) {			     
	         desplegarFeedback();
	     }
  	 }
  
	 public void iniciarEnvioArticulo() {
	     out.println("<p>Complete los valores indicados.</p>");
	     out.println("<form method=\"GET\" action=\"envio_de_articulo\">");
	     out.println("<input type=\"hidden\" name=\"operacion\" value=\"agregar\"/>");
	     out.println("<p> Id del Autor  <input type=\"text\" name=\"idAutor\" size=\"20\"></p>");
	     out.println("<p> Nombre  <input type=\"text\" name=\"Nombre\" size=\"20\"></p>");
		 out.println("<p> Resumen  <textarea name=\"Resumen\" rows=\"4\" cols=\"50\"></textarea></p>");
	     out.println("<p><input type=\"submit\" value=\"Agregar\"></p>");
	     out.println("</form>");
	 
	     out.println("<form method=\"GET\" action=\"menu.html\">");
	     out.println("<p><input type=\"submit\" value=\"Cancelar\"></p>");
	     out.println("</form>");
		
	     out.println("</BODY>");
	     out.println("</HTML>");    
	 }
  
	 public void agregarArticulo() {
	     pa = new proponerArticulo();
	     //La funcion trim() elimina espacios antes y despues del valor
		 sNombre = thisRequest.getParameter("Nombre").trim();
		 sResumen = thisRequest.getParameter("Resumen").trim();
		 int iID = Integer.parseInt(thisRequest.getParameter("idAutor").trim());
	   boolean agregado = pa.agregarArticulo(sNombre, sResumen, iID);
	   
	   if (agregado) {
		 	out.println("<p>El Articulo ha sido agregado exitosamente.<p>");
		 }
		 else{
		 	out.println("<p>No se pudo agregar Articulo</p>");
		 }

		 desplegarFeedback();
	 }

	 public void desplegarFeedback() {
	     out.println("<p>Presione el boton para terminar.</p>");
	     out.println("<form method=\"GET\" action=\"menu.html\">");
	     out.println("<p><input type=\"submit\" value=\"Terminar\"name=\"B1\"></p>");
	     out.println("</form>");
	     out.println("</BODY>");
	     out.println("</HTML>");   
	 }
 }
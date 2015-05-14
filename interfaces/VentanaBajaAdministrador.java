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

 public class VentanaBajaAdministrador extends HttpServlet {	 	 
     HttpServletResponse thisResponse;
     HttpServletRequest thisRequest;
     PrintWriter out;
     bajaAdministrador ba;
     int iIDAdministrador;
  
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
	     out.println("<h3>Dar de baja un administrador</h3>");
          
	     String operacion = request.getParameter("operacion");
	    
	     if(operacion == null) { // El menú nos envia un parametro para indicar el inicio de una transaccion
	         iniciarBaja();  
	     }

	     else if(operacion.equals("validar")) {
              System.out.println("Validando existencia de administrador");
	         validarAdministrador();
	     }

	     else if(operacion.equals("eliminar")) {
	         eliminarAdministrador();
	     }
	    
	     else if (operacion.equals("feedback")) {			     
	         desplegarFeedback();
	     }
  	 }
  
	 public void iniciarBaja() {
	     out.println("<p>Indique la matrícula del Administrador a dar de baja</p>");
	     out.println("<form method=\"GET\" action=\"baja_admin\">");
	     out.println("<input type=\"hidden\" name=\"operacion\" value=\"validar\"/>");
	     out.println("<p> Matrícula  <input type=\"text\" name=\"Matricula\" size=\"8\"></p>");;
	     out.println("<p><input type=\"submit\" value=\"Dar de baja\"></p>");
	     out.println("</form>");
	 
	     out.println("<form method=\"GET\" action=\"menu.html\">");
	     out.println("<p><input type=\"submit\" value=\"Cancelar\"></p>");
	     out.println("</form>");
	
	     out.println("</BODY>");
	     out.println("</HTML>");    
	 }
  
  	 public void validarAdministrador() {
	    ba = new bajaAdministrador();
	    //La funcion trim() elimina espacios antes y despues del valor
	    iIDAdministrador = Integer.parseInt(thisRequest.getParameter("Matricula").trim());
         System.out.println(iIDAdministrador);
	    boolean existe = ba.validarAdministrador(iIDAdministrador);
	    if (existe) {
	        eliminarAdministrador();
	    }
	    else {
	    	out.println("<p>No existe administrador con esa matrícula, intente de nuevo</p>");
	        iniciarBaja();
	    }
     }
	 public void eliminarAdministrador() {
	     ba.eliminarAdministrador(iIDAdministrador);
		 desplegarFeedback();
	 }

	 public void desplegarFeedback() {
	     out.println("<p>El Administrador ha sido dado de baja.</p>");
	     out.println("<p>Presione el boton para terminar.</p>");
	     out.println("<form method=\"GET\" action=\"index.html\">");
	     out.println("<p><input type=\"submit\" value=\"Terminar\"name=\"B1\"></p>");
	     out.println("</form>");
	     out.println("</BODY>");
	     out.println("</HTML>");   
	 }
 }
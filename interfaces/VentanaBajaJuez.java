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

 public class VentanaBajaJuez extends HttpServlet {	 	 
     HttpServletResponse thisResponse;
     HttpServletRequest thisRequest;
     PrintWriter out;
     bajaJuez bj;
     int iIDJuez;
  
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
	     out.println("<h3>Dar de baja un juez</h3>");
    
	     String operacion = request.getParameter("operacion");
	    
	     if(operacion == null) { // El menú nos envia un parametro para indicar el inicio de una transaccion
	         iniciarBaja();  
	     }

	     else if(operacion.equals("validar")) {
	         validarJuez();
	     }

	     else if(operacion.equals("eliminar")) {
	         eliminarJuez();
	     }
	    
	     else if (operacion.equals("feedback")) {			     
	         desplegarFeedback();
	     }
  	 }
  
	 public void iniciarBaja() {
	     out.println("<p>Indique la matrícula del Juez a dar de baja</p>");
	     out.println("<form method=\"GET\" action=\"baja_juez\">");
	     out.println("<input type=\"hidden\" name=\"operacion\" value=\"validar\"/>");
	     out.println("<p> Matrícula  <input type=\"text\" name=\"Matrícula\" size=\"8\"></p>");;
	     out.println("<p><input type=\"submit\" value=\"Dar de baja\"></p>");
	     out.println("</form>");
	 
	     out.println("<form method=\"GET\" action=\"menu.html\">");
	     out.println("<p><input type=\"submit\" value=\"Cancelar\"></p>");
	     out.println("</form>");
	
	     out.println("</BODY>");
	     out.println("</HTML>");    
	 }
  
  	 public void validarJuez() {
	    bj = new bajaJuez();
	    //La funcion trim() elimina espacios antes y despues del valor
	    iIDJuez = Integer.parseInt(thisRequest.getParameter("Matrícula").trim());
	    boolean existe = bj.validarJuez(iIDJuez);
	    if (existe) {
	        eliminarJuez();
	    }
	    else {
	    	    out.println("<p>No existe juez con esa matrícula, intente de nuevo</p>");
              out.println("<p> </p>");
	         iniciarBaja();
	    }
     }
	 
      public void eliminarJuez() {
	     bj.eliminarJuez(iIDJuez);
		 desplegarFeedback();
	 }

	 public void desplegarFeedback() {
	     out.println("<p>El Juez ha sido dado de baja.</p>");
	     out.println("<p> </p>");
		out.println("<p>Presione el boton para terminar.</p>");
	     out.println("<form method=\"GET\" action=\"menu.html\">");
	     out.println("<p><input type=\"submit\" value=\"Terminar\"name=\"B1\"></p>");
	     out.println("</form>");
	     out.println("</BODY>");
	     out.println("</HTML>");   
	 }
 }
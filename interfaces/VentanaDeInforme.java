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

 public class VentanaDeInforme extends HttpServlet {	 	 
     HttpServletResponse thisResponse;
     HttpServletRequest thisRequest;
     PrintWriter out;
     calcularTiempoSuscripcion cts;
     int iIDPersona;
  
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
	     out.println("<h3>Consultar tiempo de suscripcion.</h3>");
    
	     String operacion = request.getParameter("operacion");
	    
	     if(operacion == null) { // El menú nos envia un parametro para indicar el inicio de una transaccion
	         iniciarConsulta();  
	     }
	    
	     else if(operacion.equals("consultar")) {
	         getTiempoSuscripcion();
	     }
  	 }
  
	 public void iniciarConsulta() {
	     out.println("<p>Complete los valores indicados.</p>");
	     out.println("<form method=\"GET\" action=\"informe\">");
	     out.println("<input type=\"hidden\" name=\"operacion\" value=\"getTiempoSuscripcion\"/>");
	     out.println("<p> IDPersona  <input type=\"int\" name=\"IDPersona\" size=\"8\"></p>");
		 out.println("<p><input type=\"submit\" value=\"consultar\"></p>");
	     out.println("</form>");
	     out.println("<form method=\"GET\" action=\"menu.html\">");
	     out.println("<p><input type=\"submit\" value=\"Cancelar\"></p>");
	     out.println("</form>");
	
	     out.println("</BODY>");
	     out.println("</HTML>");    
	 }
  
	 public void getTiempoSuscripcion() {
	     cts = new calcularTiempoSuscripcion();
		 Calendar cal = Calendar.getInstance();
		 Date dFechaIngreso = cal.getTime();
	     //La funcion trim() elimina espacios antes y despues del valor
	     iIDPersona = Integer.parseInt(thisRequest.getParameter("IDPersona").trim());
		 boolean existe = cts.validarPersona(iIDPersona);
		 if (existe) {
		 	long tiempo = cts.getTiempoSuscripcion(iIDPersona);
		 	out.println("<p>tiempo<p>");
		 }
		 else{
		 	out.println("<p>No se encuentra esa persona.</p>");
		 }
	 }
 }
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

 public class VentanaDePago extends HttpServlet {	 	 
     HttpServletResponse thisResponse;
     HttpServletRequest thisRequest;
     PrintWriter out;
     realizarPago rp;
     int iIDPersona;
     String sCuentaBancaria;
     int iAnios;
  
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
	     out.println("<h3>Realizar pago</h3>");
    
	     String operacion = request.getParameter("operacion");
	    
	     if(operacion == null) { // El menú nos envia un parametro para indicar el inicio de una transaccion
	         iniciarPagoSubscrion();  
	     }
	    
	     else if(operacion.equals("subscripcion")) {
	         realizarPagoSuscripcion();
	     }
	     else if(operacion.equals("renovacion")){
	     	realizarPagoRenovacion();
	     }
  	 }
  
	 public void iniciarPagoSubscrion() {
	     out.println("<p>Complete los valores indicados.</p>");
	     out.println("<form method=\"GET\" action=\"pago\">");
	     out.println("<input type=\"hidden\" name=\"operacion\" value=\"subscripcion\"/>");
	     out.println("<p>  IDPersona <input type=\"int\" name=\"IDPersona\" size=\"8\"></p>");
	     out.println("<p> Cuenta Bancaria <input type=\"text\" name=\"CuentaBancaria\" size=\"18\"></p>");
	     out.println("<p>  Anios <input type=\"int\" name=\"Anios\" size=\"8\"></p>");
	     out.println("<p><input type=\"submit\" value=\"Subscripcion\"></p>");
	     out.println("</form>");
	 
	     out.println("<form method=\"GET\" action=\"menu.html\">");
	     out.println("<p><input type=\"submit\" value=\"Cancelar\"></p>");
	     out.println("</form>");
	
	     out.println("</BODY>");
	     out.println("</HTML>");    
	 }

	 public void iniciarPagoRenovacion() {
	     out.println("<p>Complete los valores indicados.</p>");
	     out.println("<form method=\"GET\" action=\"pago\">");
	     out.println("<input type=\"hidden\" name=\"operacion\" value=\"renovacion\"/>");
	     out.println("<p>  IDPersona <input type=\"int\" name=\"IDPersona\" size=\"8\"></p>");
	     out.println("<p>  Anios <input type=\"int\" name=\"Anios\" size=\"8\"></p>");
	     out.println("<p><input type=\"submit\" value=\"Subscripcion\"></p>");
	     out.println("</form>");
	 
	     out.println("<form method=\"GET\" action=\"menu.html\">");
	     out.println("<p><input type=\"submit\" value=\"Cancelar\"></p>");
	     out.println("</form>");
	
	     out.println("</BODY>");
	     out.println("</HTML>");    
	 }
  
	 public void realizarPagoSuscripcion() {
	     rp = new realizarPago();
	     //La funcion trim() elimina espacios antes y despues del valor
		 iIDPersona = Integer.parseInt(thisRequest.getParameter("IDPersona").trim());
		 sCuentaBancaria = thisRequest.getParameter("CuentaBancaria").trim();
		 iAnios = Integer.parseInt(thisRequest.getParameter("Anios").trim());
		 boolean existe = rp.validarCliente(iIDPersona);
	     if (existe) {
	     	rp.realizarPagoSuscripcion(iIDPersona, sCuentaBancaria, iAnios);
	     }
	     else
	     {
	     	out.println("<p>El Cliente no existe.</p>");
	     }
	 }

	 public void realizarPagoRenovacion() {
	     rp = new realizarPago();
	     //La funcion trim() elimina espacios antes y despues del valor
		 iIDPersona = Integer.parseInt(thisRequest.getParameter("IDPersona").trim());;
		 iAnios = Integer.parseInt(thisRequest.getParameter("Anios").trim());
 		 boolean existe = rp.validarCliente(iIDPersona);
	     if (existe) {
	     	rp.realizarPagoRenovacion(iIDPersona, iAnios);
	     }
	     else
	     {
	     	out.println("<p>El Cliente no existe.</p>");
	     }
	 }

 }
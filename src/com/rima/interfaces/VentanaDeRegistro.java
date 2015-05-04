/*Author: Equipo 5
 *
 *Version: 1.0.0
 *
 */
 package com.rima.interfaces;
 import com.rima.controls.*;
 import javax.servlet.*;
 import javax.servlet.http.*;
 import java.io.*;
 import java.util.*;
 import java.util.Calendar;

 public class VentanaDeRegistro extends HttpServlet {	 	 
     HttpServletResponse thisResponse;
     HttpServletRequest thisRequest;
     PrintWriter out;
     altaCliente ac;
     String sNombre;
	 String sCorreo;
	 String sContrasena;
	 String sFechaNacimiento;
	 String sCuentaBancaria;
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
	     out.println("<h3>Dar de alta un cliente</h3>");
    
	     String operacion = request.getParameter("operacion");
	    
	     if(operacion == null) { // El menú nos envia un parametro para indicar el inicio de una transaccion
	         iniciarAlta();  
	     }
	    
	     else if(operacion.equals("agregar")) {
	         agregarCliente();
	     }
	    
	     else if (operacion.equals("feedback")) {			     
	         desplegarFeedback();
	     }
  	 }
  
	 public void iniciarAlta() {
	     out.println("<p>Complete los valores indicados.</p>");
	     out.println("<form method=\"GET\" action=\"agregarCliente\">");
	     out.println("<input type=\"hidden\" name=\"operacion\" value=\"agregar\"/>");
	     out.println("<p> Nombre  <input type=\"text\" name=\"Nombre\" size=\"20\"></p>");
		 out.println("<p> Correo  <input type=\"text\" name=\"Correo\" size=\"20\"></p>");
		 out.println("<p> Contrasena  <input type=\"text\" name=\"Contrasena\" size=\"15\"></p>");
		 out.println("<p> Fecha de Nacimiento (aaaa/mm/dd) <input type=\"text\" name=\"FechaNacimiento\" size=\"10\"></p>");
		 out.println("<p> Cuenta Bancaria <input type=\"text\" name=\"CuentaBancaria\" size=\"18\"></p>");
	     out.println("<p><input type=\"submit\" value=\"Agregar\"></p>");
	     out.println("</form>");
	 
	     out.println("<form method=\"GET\" action=\"menu.html\">");
	     out.println("<p><input type=\"submit\" value=\"Cancelar\"></p>");
	     out.println("</form>");
	
	     out.println("</BODY>");
	     out.println("</HTML>");    
	 }
  
	 public void agregarCliente() {
	     ac = new altaCliente();
		 Calendar cal = Calendar.getInstance();
	     //La funcion trim() elimina espacios antes y despues del valor
		 Date dFechaIngreso = cal.getTime();
		 sNombre = thisRequest.getParameter("Nombre");
		 sCorreo = thisRequest.getParameter("Correo").trim();
		 sContrasena = thisRequest.getParameter("Contrasena").trim();
		 sFechaNacimiento = thisRequest.getParameter("FechaNacimiento").trim();
		 sCuentaBancaria = thisRequest.getParameter("CuentaBancaria)").trim();
		 int day = Integer.parseInt(sFechaNacimiento.substring(8,9));
         int month = Integer.parseInt(sFechaNacimiento.substring(5,6));
         int year = Integer.parseInt(sFechaNacimiento.substring(0,3));
         cal.set(Calendar.DATE, day);
         cal.set(Calendar.MONTH, month);
         cal.set(Calendar.YEAR, year);
         Date dFechaNacimiento = cal.getTime();
	     ac.agregarCliente(sNombre, sCorreo, sContrasena, dFechaNacimiento, dFechaIngreso, dFechaVencimiento, true, sCuentaBancaria);
		 desplegarFeedback();
	 }

	 public void desplegarFeedback() {
	     out.println("<p>El Cliente ha sido agregado exitosamente.</p>");
	     out.println("<p>Presione el boton para terminar.</p>");
	     out.println("<form method=\"GET\" action=\"index.html\">");
	     out.println("<p><input type=\"submit\" value=\"Terminar\"name=\"B1\"></p>");
	     out.println("</form>");
	     out.println("</BODY>");
	     out.println("</HTML>");   
	 }
 }
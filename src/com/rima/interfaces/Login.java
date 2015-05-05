/*Author: Equipo 5
 *
 *Version: 1.0.0
 *
 */
 package com.rima.interfaces;
 import com.rima.controls.*;
 import com.rima.entities.Persona;
 import javax.servlet.*;
 import javax.servlet.http.*;
 import java.io.*;
 import java.util.*;
 import java.util.Calendar;

 public class Login extends HttpServlet {	 	 
     HttpServletResponse thisResponse;
     HttpServletRequest thisRequest;
     PrintWriter out;
     login login;
	 String sCorreo;
	 String sContrasena;
  
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
	     out.println("<h3>Ingrese su informacion para iniciar sesion.</h3>");
    
	     String operacion = request.getParameter("operacion");
	    
	     if(operacion == null) { // El menú nos envia un parametro para indicar el inicio de una transaccion
	         iniciarLogin();  
	     }
	    
	     else if(operacion.equals("validar")) {
	         validar();
	     }
	    
	     else if (operacion.equals("aceptar")) {			     
	         aceptar();
	     }
		 
		 else if (operacion.equals("rechazar")) {
			 rechazar();
		 }
  	 }
  
	 public void iniciarLogin() {
	     out.println("<form method=\"GET\" action=\"Login\">");
	     out.println("<input type=\"hidden\" name=\"operacion\" value=\"validar\"/>");
		 out.println("<p> Correo  <input type=\"text\" name=\"Correo\" size=\"20\"></p>");
		 out.println("<p> Contrasena  <input type=\"password\" name=\"Contrasena\" size=\"15\"></p>");
	     out.println("<p><input type=\"submit\" value=\"Login\"></p>");
	     out.println("</form>");
	 
	     out.println("<form method=\"GET\" action=\"index.html\">");
	     out.println("<p><input type=\"submit\" value=\"Cancelar\"></p>");
	     out.println("</form>");
	
	     out.println("</BODY>");
	     out.println("</HTML>");    
	 }
  
	 public void validar() {
	     login = new login();
	     //La funcion trim() elimina espacios antes y despues del valor
		 sCorreo = thisRequest.getParameter("Correo").trim();
		 sContrasena = thisRequest.getParameter("Contrasena").trim();
		 if (login.corroborarInformacion(sCorreo, sContrasena)) {
			 aceptar();
		 }
		 else {
			 rechazar();
		 }
	 }

	 public void aceptar() {
	     out.println("<p><a href=\"menu.html\"></a></p>");
	     out.println("</BODY>");
	     out.println("</HTML>");   
	 }
	 
	 public void rechazar() {
	     out.println("<p>La informacion introducida es errada.</p>");
	     out.println("<p>Presione el boton para intentar de nuevo.</p>");
	     out.println("<form method=\"GET\" action=\"index.html\">");
	     out.println("<p><input type=\"submit\" value=\"Regresar\"name=\"B1\"></p>");
	     out.println("</form>");
	     out.println("</BODY>");
	     out.println("</HTML>");   
	 }
 }
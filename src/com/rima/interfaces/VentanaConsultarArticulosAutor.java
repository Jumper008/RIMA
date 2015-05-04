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

 public class VentanaConsultarArticulosAutor extends HttpServlet {	 	 
     HttpServletResponse thisResponse;
     HttpServletRequest thisRequest;
     PrintWriter out;
     mostrarListaArticulosAutor mlaa;
     Vector vArticulo = new Vector(); 
     int iIDAutor;
  
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
	     out.println("<h3>Consulta de Artículos por Autor</h3>");
    
	     String operacion = request.getParameter("operacion");
	    
	     if(operacion == null || operacion.equals("iniciar")) { // El menú nos envia un parametro para indicar el inicio de una transaccion
	         iniciarConsulta();  
	     }
	    
	     else if(operacion.equals("validar")) {
	         validarAutor();
	     }

	      else if(operacion.equals("mostrarArticulos")) {
	         mostrarArticulos();
	     }
  	 }
  
	 public void iniciarConsulta() {
	     out.println("<p>Indique la matrícula del Autor</p>");
	     out.println("<form method=\"GET\" action=\"consultarArticulosAutor\">");
	     out.println("<input type=\"hidden\" name=\"operacion\" value=\"validar\"/>");
	     out.println("<p> Matrícula  <input type=\"text\" name=\"Matrícula\" size=\"8\"></p>");;
	     out.println("<p><input type=\"submit\" value=\"Consultar\"></p>");
	     out.println("</form>");
	 
	     out.println("<form method=\"GET\" action=\"menu.html\">");
	     out.println("<p><input type=\"submit\" value=\"Cancelar\"></p>");
	     out.println("</form>");
	
	     out.println("</BODY>");
	     out.println("</HTML>");    
	 }
  
	 public void validarAutor() {
	     mlaa = new mostrarListaArticulosAutor();
	     //La funcion trim() elimina espacios antes y despues del valor
		 iIDAutor = Integer.parseInt(thisRequest.getParameter("Matrícula").trim());
	     boolean existe = mlaa.validarAutor(iIDAutor);
		 if (existe) {
	        mostrarArticulos();
	    }
	    else {
	    	out.println("<p>No existe autor con esa matrícula, intente de nuevo</p>");
	        iniciarConsulta();
	    }
	 }

	 public void mostrarArticulos() {
		vArticulo = mLAA.obtenerListaArticulosAutor(iIDAutor);
		if (vArticulo.size() == 0)
			out.println("<h3>No hay articulos escritos por este autor</h3>");

		else {
			 
			for (int iI = 0; iI < vArticulo.size(); iI++) {
				out.println("<p>vArticulo.elementAt(iI)<p>");
			}
		}

		 out.println("<p>Consultar articulos por otro autor.</p>");
	     out.println("<form method=\"GET\" action=\"consultarArticulosAutor\">");
	     out.println("<input type=\"hidden\" name=\"operacion\" value=\"iniciar\"/>");
	     out.println("<p><input type=\"submit\" value=\"Consultar\"></p>");
	     out.println("</form>");

	     out.println("<p>Presione el boton para terminar.</p>");
	     out.println("<form method=\"GET\" action=\"index.html\">");
	     out.println("<p><input type=\"submit\" value=\"Terminar\"name=\"B1\"></p>");
	     out.println("</form>");
	     out.println("</BODY>");
	     out.println("</HTML>"); 
	 }
 }
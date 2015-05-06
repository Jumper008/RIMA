/*Author: Equipo 5
 *
 *Version: 1.0.0
 *
 */
 package interfaces;
 import controls.*;
 import entities.Cliente;
 import javax.servlet.*;
 import javax.servlet.http.*;
 import java.io.*;
 import java.util.*;

 public class VentanaDeListaDeArticulosPropios extends HttpServlet {	 	 
     HttpServletResponse thisResponse;
     HttpServletRequest thisRequest;
     PrintWriter out;
     verArticulosPropuestosPropios vapp;
     int iIDPersona;
     Vector<Articulo> vArticulos = new Vector<Articulo>(); 
  
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
	     out.println("<h3>Consultar artitulos propios</h3>");

	     String operacion = request.getParameter("operacion");

	     if (operacion == null) {// menu manda aprametro para inciiar transicion
	     	iniciarConsulta();
	     }
	     else if (operacion.equals("ver")) {
	     	verArticulosPropuestosPropios(); 
	     }    	      
  	 }

  	  public void iniciarConsulta() {
	     out.println("<p>Complete los valores indicados.</p>");
	     out.println("<form method=\"GET\" action=\"articulos_propuestos_propios\">");
	     out.println("<input type=\"hidden\" name=\"operacion\" value=\"ver\"/>");
		 out.println("<p> IDPersona <input type=\"int\" name=\"IDPersona\" size=\"8\"></p>");
	     out.println("<p><input type=\"submit\" value=\"Ver\"></p>");
	     out.println("</form>");
	 
	     out.println("<form method=\"GET\" action=\"menu.html\">");
	     out.println("<p><input type=\"submit\" value=\"Cancelar\"></p>");
	     out.println("</form>");
	
	     out.println("</BODY>");
	     out.println("</HTML>");    
	 }
  
	 public void verArticulosPropuestosPropios() {
	    vapp = new verArticulosPropuestosPropios();

	  	iIDPersona = Integer.parseInt(thisRequest.getParameter("IDPersona").trim());

		vArticulos = vapp.obtenerArticulosPropios(iIDPersona);
		if (vArticulos.size() == 0)
			out.println("<h3>No hay articulos de este autor.</h3>");

		else {
			out.println("<table width=\"75%\" border=\"0\">");
			for (int iI = 0; iI < vArticulos.size(); iI++) {
				out.println("<td align=\"center\">");
				out.println("<table width=\"99%\" border=\"4\">");
				out.println("<td align=\"center\">");
				out.println(vArticulos.at(iI).getsNombre() + " | " + vArticulos.at(iI).getsResumen());
				out.println("</td> ");
				out.println("</table> ");
				out.println("</td>");
			}
			out.println("</table>");
		}

		 out.println("<p>Presione el boton para regresar.</p>");
	     out.println("<form method=\"GET\" action=\"menu.html\">");
	     out.println("<p><input type=\"submit\" value=\"Regresar\"name=\"B1\"></p>");
	     out.println("</form>");
	     out.println("</BODY>");
	     out.println("</HTML>"); 	
	 }
 }
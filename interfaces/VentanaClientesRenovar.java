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

 public class VentanaClientesRenovar extends HttpServlet {	 	 
     HttpServletResponse thisResponse;
     HttpServletRequest thisRequest;
     PrintWriter out;
     mostrarClientesRenovar mcr;
     Vector<Cliente> vCliente = new Vector<Cliente>(); 
  
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
	     out.println("<h3>Clientes a renovar</h3>");
    
	     mostrarClientesRenovar(); 	      
  	 }
  
	 public void mostrarClientesRenovar() {
	    mcr = new mostrarClientesRenovar();

		vCliente = mcr.obtenerClientesRenovar();
		if (vCliente.size() == 0)
			out.println("<h3>No hay clientes a renovar.</h3>");

		else {
			out.println("<table width=\"75%\" border=\"0\">");
			for (int iI = 0; iI < vCliente.size(); iI++) {
				out.println("<td align=\"center\">");
				out.println("<table width=\"99%\" border=\"4\">");
				out.println("<td align=\"center\">");
				out.println(vCliente.get(iI).getsNombre() + " | " + vCliente.get(iI).getsCorreo());
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
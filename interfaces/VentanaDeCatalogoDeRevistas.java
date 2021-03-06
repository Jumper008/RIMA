/*Author: Equipo 5
 *
 *Version: 1.0.0
 *
 */
 package interfaces;
 import controls.*;
 import entities.Revista;
 import javax.servlet.*;
 import javax.servlet.http.*;
 import java.io.*;
 import java.util.*;
 import java.util.Date;
 import java.util.Calendar;

 public class VentanaDeCatalogoDeRevistas extends HttpServlet {	 	 
     HttpServletResponse thisResponse;
     HttpServletRequest thisRequest;
     PrintWriter out;
     mostrarListaRevistas mlr;
     Vector<Revista> vRevistas = new Vector<Revista>(); 
  
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
	     out.println("<h3>Catatalogo de Revistas</h3>");
    
	     mostrarListaRevistas(); 	      
  	 }
  
	 public void mostrarListaRevistas() {
	    mlr = new mostrarListaRevistas();

		vRevistas = mlr.obtenerListaRevistas();
		if (vRevistas.size() == 0)
			out.println("<h3>No hay revistas que mostrar.</h3>");

		else {
			out.println("<table width=\"75%\" border=\"0\">");
			for (int iI = 0; iI < vRevistas.size(); iI++) {
				
				
				String sFechaPublicacion = vRevistas.get(iI).getdFechaPublicacion();
//				Calendar cal = Calendar.getInstance(); 
//				cal.setTime(dFechaPublicacion);
//				int iday = cal.get(Calendar.DATE);
//				int imonth = cal.get(Calendar.MONTH);
//				int iyear = cal.get(Calendar.YEAR) + 1900;
//				String sFechaPublicacion = Integer.toString(iyear) + "/" + Integer.toString(imonth) + "/" + Integer.toString(iday);
				
				out.println("<td align=\"center\">");
				out.println("<table width=\"99%\" border=\"4\">");
				out.println("<td align=\"center\">");
				out.println(vRevistas.get(iI).getsNombre() + " | "
			 			+ sFechaPublicacion + " | " + vRevistas.get(iI).getiNumPaginas());
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
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

 public class VentanaSeleccionArticuloPublicacion extends HttpServlet {	 	 
     HttpServletResponse thisResponse;
     HttpServletRequest thisRequest;
     PrintWriter out;
     seleccionar se;
     int iIDArticulo;
     int iIDRevista;
  
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
	     out.println("<h3>Publicar articulo</h3>");
    
	     String operacion = request.getParameter("operacion");
	    
	     if(operacion == null) { // El menú nos envia un parametro para indicar el inicio de una transaccion
	         iniciarPublicacion();  
	     }
	    
	     else if(operacion.equals("publicar")) {
	         publicarEnRevista();
	     }
  	 }
  
	 public void iniciarPublicacion() {
	     out.println("<p>Ingrese el ID del articulo y el ID de la revista donde se publicara.</p>");
	     out.println("<form method=\"GET\" action=\"publicar_articulo\">");
	     out.println("<input type=\"hidden\" name=\"operacion\" value=\"publicar\"/>");
	     out.println("<p>  IDArticulo <input type=\"int\" name=\"IDArticulo\" size=\"8\"></p>");
	   	 out.println("<p>  IDRevista <input type=\"int\" name=\"IDRevista\" size=\"8\"></p>");
	     out.println("<p><input type=\"submit\" value=\"Publicar\"></p>");
	     out.println("</form>");
	 
	     out.println("<form method=\"GET\" action=\"menu.html\">");
	     out.println("<p><input type=\"submit\" value=\"Cancelar\"></p>");
	     out.println("</form>");
	
	     out.println("</BODY>");
	     out.println("</HTML>");    
	 }
  
	 public void publicarEnRevista() {
	     se = new seleccionar();
	     //La funcion trim() elimina espacios antes y despues del valor
		 iIDArticulo = Integer.parseInt(thisRequest.getParameter("IDArticulo").trim());
		 iIDRevista = Integer.parseInt(thisRequest.getParameter("IDRevista").trim());
		 boolean existe = se.validarArticulo(iIDArticulo);
	     if (existe) {
	     	boolean publicado = se.publicarEnRevista(iIDArticulo, iIDRevista);
	     	if (publicado) {
	     		out.println("<p>El articulo fue publicado con exito");
	     	}
	     	else{
	     		out.println("<p>El articulo no se pudo publicar");
	     	}
	     }
	     else
	     {
	     	out.println("<p>El ID de Articulo no existe.</p>");
	     }
	 }

 }
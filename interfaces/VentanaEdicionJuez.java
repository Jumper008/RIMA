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

 public class VentanaEdicionJuez extends HttpServlet {	 	 
     HttpServletResponse thisResponse;
     HttpServletRequest thisRequest;
     PrintWriter out;
     editarJuez ej;
     int iIDJuez;
     String sNombre;
	 String sCorreo;
	 String sContrasena;
	 String sFechaNacimiento;
  
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
	     out.println("<h3>Dar de alta un autor</h3>");
    
	     String operacion = request.getParameter("operacion");
	    
	     if(operacion == null) { // El menú nos envia un parametro para indicar el inicio de una transaccion
	         iniciarEdicion();  
	     }
	    
	     else if(operacion.equals("editar")) {
	         editarJuez();
	     }
  	 }
  
	 public void iniciarEdicion() {
	     out.println("<p>Complete los valores indicados.</p>");
	     out.println("<form method=\"GET\" action=\"edicion_juez\">");
	     out.println("<input type=\"hidden\" name=\"operacion\" value=\"editar\"/>");
	     out.println("<p> IDJuez  <input type=\"int\" name=\"IDJuez\" size=\"8\"></p>");
	     out.println("<p> Nombre  <input type=\"text\" name=\"Nombre\" size=\"20\"></p>");
		 out.println("<p> Correo  <input type=\"text\" name=\"Correo\" size=\"20\"></p>");
		 out.println("<p> Contrasena  <input type=\"text\" name=\"Contrasena\" size=\"15\"></p>");
		 out.println("<p> Fecha de Nacimiento (aaaa/mm/dd) <input type=\"text\" name=\"FechaNacimiento\" size=\"10\"></p>");
	     out.println("<p><input type=\"submit\" value=\"editar\"></p>");
	     out.println("</form>");
	 
	     out.println("<form method=\"GET\" action=\"menu.html\">");
	     out.println("<p><input type=\"submit\" value=\"Cancelar\"></p>");
	     out.println("</form>");
	
	     out.println("</BODY>");
	     out.println("</HTML>");    
	 }
  
	 public void editarJuez() {
	     ej = new editarJuez();
		 Calendar cal = Calendar.getInstance();
	     //La funcion trim() elimina espacios antes y despues del valor
		 sNombre = thisRequest.getParameter("Nombre");
		 sCorreo = thisRequest.getParameter("Correo").trim();
		 sContrasena = thisRequest.getParameter("Contrasena").trim();
		 iIDJuez = Integer.parseInt(thisRequest.getParameter("IDJuez").trim());
		 sFechaNacimiento = thisRequest.getParameter("FechaNacimiento").trim();
		 int day = Integer.parseInt(sFechaNacimiento.substring(8,10));
         int month = Integer.parseInt(sFechaNacimiento.substring(5,7));
         int year = Integer.parseInt(sFechaNacimiento.substring(0,4));
         cal.set(Calendar.DATE, day);
         cal.set(Calendar.MONTH, month);
         cal.set(Calendar.YEAR, year);
         Date dFechaNacimiento = cal.getTime();
	     boolean existe = ej.validarJuez(iIDJuez);
	     if (existe) {
	     	ej.updateJuezByJuez(iIDJuez, sNombre, sCorreo, sContrasena, dFechaNacimiento);
		 	out.println("<p>El Juez ha sido actualzaido con exito.</p>");
	     }
	     else
	     {
	     	out.println("<p>El Juez no existe.</p>");
	     }
		 out.println("<p> </p>");
		 out.println("<p>Presione el boton para terminar.</p>");
	     out.println("<form method=\"GET\" action=\"menu.html\">");
	     out.println("<p><input type=\"submit\" value=\"Terminar\"name=\"B1\"></p>");
	     out.println("</form>");
	     out.println("</BODY>");
	     out.println("</HTML>");

	 }
 }
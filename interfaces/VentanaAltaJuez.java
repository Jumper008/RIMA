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

 public class VentanaAltaJuez extends HttpServlet {	 	 
     HttpServletResponse thisResponse;
     HttpServletRequest thisRequest;
     PrintWriter out;
     altaJuez aj;
     String sNombre;
	 String sCorreo;
	 String sContrasena;
	 String sFechaNacimiento;
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
	     out.println("<h3>Dar de alta un juez</h3>");
    
	     String operacion = request.getParameter("operacion");
	    
	     if(operacion == null) { // El menú nos envia un parametro para indicar el inicio de una transaccion
	         iniciarAlta();  
	     }
	    
	     else if(operacion.equals("agregar")) {
	         agregarJuez();
	     }
	    
	     else if (operacion.equals("feedback")) {			     
	         desplegarFeedbackGood();
	     }
  	 }
  
	 public void iniciarAlta() {
	     out.println("<p>Complete los valores indicados.</p>");
	     out.println("<form method=\"GET\" action=\"alta_juez\">");
	     out.println("<input type=\"hidden\" name=\"operacion\" value=\"agregar\"/>");
	     out.println("<p> iIDPersona  <input type=\"integer\" name=\"iIDPersona\" size=\"8\"></p>");
		 //out.println("<p> Correo  <input type=\"text\" name=\"Correo\" size=\"20\"></p>");
		 //out.println("<p> Contrasena  <input type=\"password\" name=\"Contrasena\" size=\"15\"></p>");
		 //out.println("<p> Fecha de Nacimiento (aaaa/mm/dd) <input type=\"text\" name=\"FechaNacimiento\" size=\"10\"></p>");
	     out.println("<p><input type=\"submit\" value=\"Agregar\"></p>");
	     out.println("</form>");
	 
	     out.println("<form method=\"GET\" action=\"menu.html\">");
	     out.println("<p><input type=\"submit\" value=\"Cancelar\"></p>");
	     out.println("</form>");
	
	     out.println("</BODY>");
	     out.println("</HTML>");    
	 }
  
	 public void agregarJuez() {
	     aj = new altaJuez();
		 iIDPersona = Integer.parseInt(thisRequest.getParameter("iIDPersona").trim());
		 //Calendar cal = Calendar.getInstance();
	     //La funcion trim() elimina espacios antes y despues del valor
		 //Date dFechaIngreso = cal.getTime();
		 //sNombre = thisRequest.getParameter("Nombre").trim();
		// sCorreo = thisRequest.getParameter("Correo").trim();
		 //sContrasena = thisRequest.getParameter("Contrasena").trim();
		// sFechaNacimiento = thisRequest.getParameter("FechaNacimiento").trim();
		// int day = Integer.parseInt(sFechaNacimiento.substring(8,9));
        // int month = Integer.parseInt(sFechaNacimiento.substring(5,6));
       //  int year = Integer.parseInt(sFechaNacimiento.substring(0,3));
        // cal.set(Calendar.DATE, day);
        // cal.set(Calendar.MONTH, month);
        // cal.set(Calendar.YEAR, year);
         //Date dFechaNacimiento = cal.getTime();
         //long lFechaVencimiento = dFechaIngreso.getTime()/ (24 * 60 * 60 * 1000);
        // long lano = 31536000000l;
        // lFechaVencimiento = lFechaVencimiento + lano;
        // Date dFechaVencimiento = new Date(lFechaVencimiento);
	     System.out.println("Se trabajara con el ID: " + Integer.toString(iIDPersona));
		 boolean agregado = aj.agregarJuez(iIDPersona);
		 if(agregado) {
			 desplegarFeedbackGood();
		 }
		 else 
		 	 desplegarFeedbackBad();
		 
	 }

	 public void desplegarFeedbackGood() {
	     out.println("<p>El Juez ha sido agregado exitosamente.</p>");
	     out.println("<p> </p>");
		 out.println("<p>Presione el boton para terminar.</p>");
	     out.println("<form method=\"GET\" action=\"menu.html\">");
	     out.println("<p><input type=\"submit\" value=\"Terminar\"name=\"B1\"></p>");
	     out.println("</form>");
	     out.println("</BODY>");
	     out.println("</HTML>");   
	 }
	 
	  public void desplegarFeedbackBad() {
	     out.println("<p>El Juez NO se puedo agregar.</p>");
		 out.println("<p>Posiblemente no es un autor o el ID es invalido.</p>");
		 out.println("<p> </p>");
		 out.println("<p>Presione el boton para terminar.</p>");
	     out.println("<form method=\"GET\" action=\"menu.html\">");
	     out.println("<p><input type=\"submit\" value=\"Terminar\"name=\"B1\"></p>");
	     out.println("</form>");
	     out.println("</BODY>");
	     out.println("</HTML>");   
	 }
 }
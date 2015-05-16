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

 public class VentanaDePublicacion extends HttpServlet {	 	 
     HttpServletResponse thisResponse;
     HttpServletRequest thisRequest;
     PrintWriter out;
     altaRevista aa;
     int iNumPaginas;
  
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
	         iniciarAlta();  
	     }
	    
	     else if(operacion.equals("agregar")) {
	         agregarRevista();
	     }
	    
	     else if (operacion.equals("feedback")) {			     
	         desplegarFeedback();
	     }
  	 }
  
	 public void iniciarAlta() {
	     out.println("<p>Complete los valores indicados.</p>");
	     out.println("<form method=\"GET\" action=\"publicacion\">");
	     out.println("<input type=\"hidden\" name=\"operacion\" value=\"agregar\"/>");
	     out.println("<p> Numero Paginas  <input type=\"int\" name=\"NumPaginas\" size=\"34\"></p>");
       out.println("<p> Nombre  <input  name=\"Nombre\" size=\"34\"></p>");
	     out.println("<p><input type=\"submit\" value=\"Agregar\"></p>");
	     out.println("</form>");
	 
	     out.println("<form method=\"GET\" action=\"menu.html\">");
	     out.println("<p><input type=\"submit\" value=\"Cancelar\"></p>");
	     out.println("</form>");
	
	     out.println("</BODY>");
	     out.println("</HTML>");    
	 }
  
	 public void agregarRevista() {
	     aa = new altaRevista();
		 Calendar cal = Calendar.getInstance();
		 int iday = cal.get(Calendar.DATE);
		int imonth = cal.get(Calendar.MONTH);
		int iyear = cal.get(Calendar.YEAR);
        String sMonth;
        String sDay;
        if (imonth < 10) {
            sMonth = '0' + Integer.toString(imonth);
        } else {
            sMonth = Integer.toString(imonth);
        }
        if (iday <10) {
            sDay = '0' + Integer.toString(imonth);
        } else {
            sDay = Integer.toString(imonth);
        }
		String sDate = Integer.toString(iyear) + "/" + sMonth + "/" + sDay;
	     //La funcion trim() elimina espacios antes y despues del valor
		 iNumPaginas = Integer.parseInt(thisRequest.getParameter("NumPaginas").trim());
     String nombre = thisRequest.getParameter("Nombre").trim();
	   if (aa.agregarRevista(sDate, iNumPaginas, nombre, true)) {
      desplegarFeedback();
     } else {
      out.println("<p>Ha ocurrido un eror</p>");
     }
	 }

	 public void desplegarFeedback() {
	     out.println("<p>La revista ha sido publicada exitosamente.</p>");
	     out.println("<p>Presione el boton para terminar.</p>");
	     out.println("<form method=\"GET\" action=\"index.html\">");
	     out.println("<p><input type=\"submit\" value=\"Terminar\"name=\"B1\"></p>");
	     out.println("</form>");
	     out.println("</BODY>");
	     out.println("</HTML>");   
	 }
 }
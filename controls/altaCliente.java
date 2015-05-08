/*
 *Author: Equipo 5
 *
 *Version: 1.0.0
 *
 */
 package controls;
 
 import java.util.*;
 import java.io.*;
 import java.util.Date;
 import entities.*;
 
 public class altaCliente {
	 Cliente cliCliente;
	 
	 public altaCliente() {
		 cliCliente = new Cliente();
	 }
	 
	 public void agregarCliente(String sNombre, String sCorreo, String sContrasena, 
	 		Date dFechaNacimiento, Date dFechaIngreso, Date dFechaVencimiento, boolean bActivo, String sCuentaBancaria) {
		 int iIDPersona = cliCliente.generarID();
		 cliCliente.setiIDPersona(iIDPersona);
		 cliCliente.setsNombre(sNombre);
		 cliCliente.setsCorreo(sCorreo);
		 cliCliente.setsContrasena(sContrasena);
		 cliCliente.setdFechaNacimiento(dFechaNacimiento);
		 cliCliente.setdFechaIngreso(dFechaIngreso);
		 cliCliente.setdFechaVencimiento(dFechaVencimiento);
		 cliCliente.setbActivo(bActivo);
		 cliCliente.setsCuentaBancaria(sCuentaBancaria);
		 cliCliente.agregarCliente(cliCliente);
	 }
 }
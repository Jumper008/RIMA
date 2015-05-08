/*
 *Author: Equipo 5
 *
 *Version: 1.0.0
 *
 */
 package controls;
 
 import java.util.*;
 import java.io.*;
 import entities.*;
 
 public class mostrarClientesVigentes {
	 Cliente cliCliente;
	 
	 public mostrarClientesVigentes() {
		 cliCliente = new Cliente();
	 }
	 
	 public Vector<Cliente> obtenerClientesVigentes() {
		 return cliCliente.consultarClientesVigentes();
	 }
}
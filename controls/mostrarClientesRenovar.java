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
 
 public class mostrarClientesRenovar {
	 Cliente cliCliente;
	 
	 public mostrarClientesRenovar() {
		 cliCliente = new Cliente();
	 }
	 
	 public Vector<Cliente> obtenerClientesRenovar() {
		 return cliCliente.consultarClientesRenovar();
	 }
}
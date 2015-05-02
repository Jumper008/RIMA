/*
 *Author: Equipo 5
 *
 *Version: 1.0.0
 *
 */
 package com.rima.controls;
 
 import java.util.*;
 import java.io.*;
 import com.rima.entities.*;
 
 public class mostrarClientesVigentes {
	 Cliente cliCliente;
	 private transient Conexion conConexion;
	 
	 public mostrarClientesVigentes() {
		 conConexion = new Conexion();
		 cliCliente = new Cliente(conConexion);
	 }
	 
	 public Vector<Cliente> obtenerClientesVigentes() {
		 return cliCliente.consultarClientesRenovar();
	 }
}
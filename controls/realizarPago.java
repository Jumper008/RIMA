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
 
 public class realizarPago {
	 Cliente clCliente;
	 private transient Conexion conConexion;
	 
	 public realizarPago() {
		 conConexion = new Conexion();
		 clCliente = new Cliente(conConexion);
	 }
	 
	 public boolean validarCliente(int iIDPersona) {
		 return clCliente.corroborarExistencia(iIDPersona);
	 }
	 
	 public boolean realizarPagoSuscripcion(int iIDPersona, String sCuentaBancaria, int iAnos) {
		 return clCliente.realizarPagoSuscripcion(iIDPersona, sCuentaBancaria, iAnos);
	 }
	 
	 public boolean realizarPagoRenovacion(int iIDPersona, int iAnos) {
		 return clCliente.realizarPagoRenovacion(iIDPersona, iAnos);
	 }
 }
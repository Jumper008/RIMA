/*
 *Author: Equipo 5
 *
 *Version: 1.0.0
 *
 */
package com.rima.entities;

import java.util.*;
import java.sql.*;
import java.io.*;
import java.util.Date;
import java.util.Calendar;

public class Cliente {
	protected String sCuentaBancaria;

	public boolean realizarPagoRenovacion( int iIDPersona ) {
		return true;
	}
	
	public boolean pagarSuscripcion( int iIDPersona ) {
		return true;
	}
	
	public Vector<Cliente> consultarClientesRenovar() {
		Vector<Cliente> vClientes = new Vector<Cliente>();
		return vClientes;
	}
	
	public Vector<Cliente> consultarClientesVigentes() {
		Vector<Cliente> vClientes = new Vector<Cliente>();
		return vClientes;
	}
	
	public boolean agregarCliente( Cliente clCliente ) {
		return true;
	}
	
	public boolean editarCliente( Cliente clCliente ) {
		return true;
	}
	
	public Cliente consultarInformacion( int iIDPersona ) {
		Cliente clCliente = new Cliente();
		return clCliente;
	}

}	
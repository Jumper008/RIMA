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

public class Administrador {
	protected int iIDAdministrador;
	protected String sNombreAdmi;
	protected String sContrasena;
	protected String sCorreo;

	public boolean validarPago(){
		return true;

	}

	public boolean RegistrarAutorArticulo(){
		return true;
	}

	public void revisarVotacionPrevia(){
	}

	public int getArticulosVotados(){
		return iIDAdministrador;
	}

	public boolean DardeAltaRevista(){
		return true;
	}

	public boolean ActualizarInformacionArticulos(){
		return true;
	}

	public boolean ConsultarClientesRenovar(){
		return true;
	}

	public boolean ConsultarClientesVigentes(){
		return true;
	}

	public boolean agregarAutor(){
		return true;
	}

	public boolean submitInformacion(){
		return true;
	}

	public boolean revisarDuplicados(){
		return true;
	}

	public boolean agregarJuez(){
		return true;
	}

	public boolean desactivarJuez(){
		return true;
	}

	public boolean desactivarAdministrador(){
		return true;
	}

	public boolean agregarAdministrador(){
		return true;
	}

	public boolean mostrarInformacionActual(){
		return true;
	}

	public void submitNuevaInformacion(){

	}

	public void editarJuez(){

	}
}

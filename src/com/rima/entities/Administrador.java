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
	public boolean agregarAdministrador( Administrador administrador ) {
		return true;
	}

	public boolean mostrarInformacionActual() {
		return true;
	}

	public Administrador consultarInformacion( int iIDPersona ) {
		Administrador adAdministrador = new Administrador();
		return adAdministrador;
	}

	public boolean editarAdministrador( int iIDPersona ) {
		return true;
	}
}

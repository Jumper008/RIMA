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

public class Revista {
	protected int iIDRevista;
	protected Date dFechaPublicacion;
	protected int iNumPaginas;
	protected boolean bPublicada;

	public void mostarRevistasPublicadas() {
		
	}
	
	public boolean agregaraRevista( Revista reRevista) {
		return true;
	}
}

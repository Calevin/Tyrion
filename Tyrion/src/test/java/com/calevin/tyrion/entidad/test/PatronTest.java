package com.calevin.tyrion.entidad.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.calevin.tyrion.entidad.NodoObligatorioConstante;
import com.calevin.tyrion.entidad.NodoPatron;
import com.calevin.tyrion.entidad.Palabra;
import com.calevin.tyrion.entidad.Patron;
import com.calevin.tyrion.entidad.Posicion;

public class PatronTest {

	@Test
	public void evaluar_palabra_coincidente() {
		Patron p = new Patron(new NodoObligatorioConstante("palabra"));
		assertTrue(p.evaluar(new Palabra("palabra", new Posicion(1))));
	}
	
	@Test
	public void evaluar_palabra_no_coincidente() {
		Patron p = new Patron(new NodoObligatorioConstante("palabra"));
		assertTrue(!p.evaluar(new Palabra("otraPalabra", new Posicion(1))));
	}
}

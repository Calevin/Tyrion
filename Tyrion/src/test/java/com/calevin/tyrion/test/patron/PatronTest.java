package com.calevin.tyrion.test.patron;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.calevin.tyrion.patron.Patron;
import com.calevin.tyrion.patron.nodo.NodoPatron;
import com.calevin.tyrion.patron.nodo.obligatorio.ObligatorioConstante;
import com.calevin.tyrion.texto.Palabra;
import com.calevin.tyrion.texto.Posicion;

public class PatronTest {

	@Test
	public void evaluar_palabra_coincidente() {
		Patron p = new Patron(new ObligatorioConstante("palabra"));
		assertTrue(p.evaluar(new Palabra("palabra", new Posicion(1))));
	}
	
	@Test
	public void evaluar_palabra_no_coincidente() {
		Patron p = new Patron(new ObligatorioConstante("palabra"));
		assertTrue(!p.evaluar(new Palabra("otraPalabra", new Posicion(1))));
	}
}

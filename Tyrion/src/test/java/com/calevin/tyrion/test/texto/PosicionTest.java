package com.calevin.tyrion.test.texto;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.calevin.tyrion.texto.Posicion;

public class PosicionTest {
	@Test
	public void compareTo_misma_linea() {
		Posicion p1;
		p1 = new Posicion();
		p1.setLinea(1);
		p1.setColumna(1);
		
		Posicion p2 = new Posicion(2);
		Posicion p3 = new Posicion(1, 3);
		/*
		 * Compares this object with the specified object for order. 
		 * Returns a negative integer, zero, or a positive integer 
		 * as this object is less than, equal to, or greater 
		 * than the specified object. 
		 */
		assertTrue(1 == p2.compareTo(p1));
		assertTrue(-1 == p2.compareTo(p3));
		
	}
	
	@Test
	public void compareTo_misma_distinta() {
		Posicion p1 = new Posicion(1);
		Posicion p2 = new Posicion(2, 1);
		assertTrue(-1 == p1.compareTo(p2));
		assertTrue(1 == p2.compareTo(p1));
	}
}

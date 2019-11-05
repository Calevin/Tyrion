package com.calevin.tyrion.entidad.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.calevin.tyrion.entidad.NodoPatron;
import com.calevin.tyrion.entidad.Palabra;
import com.calevin.tyrion.entidad.Patron;

public class NodoPatronTest {

	@Test
	public void encadenar_ok() {
	
		NodoPatron uno = new NodoPatron("uno");
		NodoPatron dos = new NodoPatron("dos");
		
		NodoPatron encadenado = uno.encadenarSiguientePalabra("dos");
		
		assertTrue(encadenado.getValor().equalsIgnoreCase(dos.getValor()));
	}
	
	@Test
	public void anteriorSiExiste_ok() {
		NodoPatron uno = new NodoPatron("uno");
		
		NodoPatron encadenado = uno.encadenarSiguientePalabra("dos");
		
		assertTrue(encadenado.anteriorSiExiste().getValor().equalsIgnoreCase("uno"));
	}

	@Test
	public void componerPatron_ok() {
		NodoPatron uno = new NodoPatron("uno");
		Patron patronCompuesto = uno.encadenarSiguientePalabra("dos")
			.encadenarSiguientePalabra("tres")
			.encadenarSiguientePalabra("cuatro")
			.componerPatron();
		
		assertTrue(patronCompuesto.getLargoPatron()==4);
	}
	
	@Test
	public void tieneMismoValor_caso_afirmativa() {
		NodoPatron nodoPatron = new NodoPatron();
		Palabra p2 = new Palabra();
		
		assertTrue(nodoPatron.tieneMismoValor(p2));
		
		nodoPatron.setValor("test");
		p2.setValor("test");
		
		assertTrue(nodoPatron.tieneMismoValor(p2));
		
	}
	
	@Test
	public void tieneMismoValor_caso_negativo() {
		NodoPatron nodoPatron = new NodoPatron();
		Palabra p2 = new Palabra();
		
		p2.setValor("notest");
		
		assertTrue(!nodoPatron.tieneMismoValor(p2));
		
		nodoPatron.setValor("test");
		
		assertTrue(!nodoPatron.tieneMismoValor(p2));
	}
}

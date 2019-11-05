package com.calevin.tyrion.entidad.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.calevin.tyrion.entidad.NodoObligatorioConstante;
import com.calevin.tyrion.entidad.NodoPatron;
import com.calevin.tyrion.entidad.Palabra;
import com.calevin.tyrion.entidad.Patron;

public class NodoPatronTest {

	@Test
	public void encadenar_ok() {
	
		NodoObligatorioConstante uno = new NodoObligatorioConstante("uno");
		NodoObligatorioConstante dos = new NodoObligatorioConstante("dos");
		
		NodoPatron encadenado = uno.encadenarSiguiente(new NodoObligatorioConstante("dos"));
		
		assertTrue(encadenado.getValor().equalsIgnoreCase(dos.getValor()));
	}
	
	@Test
	public void anteriorSiExiste_ok() {
		NodoObligatorioConstante uno = new NodoObligatorioConstante("uno");
		
		NodoPatron encadenado = uno.encadenarSiguiente(new NodoObligatorioConstante("dos"));
		
		assertTrue(encadenado.anteriorSiExiste().getValor().equalsIgnoreCase("uno"));
	}

	@Test
	public void componerPatron_ok() {
		NodoObligatorioConstante uno = new NodoObligatorioConstante("uno");
		Patron patronCompuesto = uno.encadenarSiguiente(new NodoObligatorioConstante("dos"))
			.encadenarSiguiente(new NodoObligatorioConstante("tres"))
			.encadenarSiguiente(new NodoObligatorioConstante("cuatro"))
			.componerPatron();
		
		assertTrue(patronCompuesto.getLargoPatron()==4);
	}
	
	@Test
	public void tieneMismoValor_caso_afirmativa() {
		NodoPatron nodoPatron = new NodoObligatorioConstante(null);
		Palabra p2 = new Palabra();
		
		assertTrue(nodoPatron.tieneMismoValor(p2));
		
		nodoPatron.setValor("test");
		p2.setValor("test");
		
		assertTrue(nodoPatron.tieneMismoValor(p2));
		
	}
	
	@Test
	public void tieneMismoValor_caso_negativo() {
		NodoPatron nodoPatron = new NodoObligatorioConstante(null);
		Palabra p2 = new Palabra();
		
		p2.setValor("notest");
		
		assertTrue(!nodoPatron.tieneMismoValor(p2));
		
		nodoPatron.setValor("test");
		
		assertTrue(!nodoPatron.tieneMismoValor(p2));
	}
}

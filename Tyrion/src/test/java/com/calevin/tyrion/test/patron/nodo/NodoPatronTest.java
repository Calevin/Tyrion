package com.calevin.tyrion.test.patron.nodo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.calevin.tyrion.patron.Patron;
import com.calevin.tyrion.patron.nodo.NodoPatron;
import com.calevin.tyrion.patron.nodo.obligatorio.ObligatorioConstante;
import com.calevin.tyrion.patron.nodo.obligatorio.ObligatorioVariable;
import com.calevin.tyrion.texto.Palabra;

public class NodoPatronTest {

	@Test
	public void encadenar_ok() {
	
		ObligatorioConstante uno = new ObligatorioConstante("uno");
		ObligatorioConstante dos = new ObligatorioConstante("dos");
		
		NodoPatron encadenado = uno.encadenarSiguiente(new ObligatorioConstante("dos"));
		
		assertTrue(encadenado.getValor().equalsIgnoreCase(dos.getValor()));
	}
	
	@Test
	public void encadenar_nodos_obligatorios_variables_ok() {
		ObligatorioVariable uno = new ObligatorioVariable("[a]");
		ObligatorioVariable dos = new ObligatorioVariable("[b]");
		
		NodoPatron encadenado = uno.encadenarSiguiente(new ObligatorioVariable("[b]"));
		
		assertTrue(encadenado.getValor().equalsIgnoreCase(dos.getValor()));
		Palabra p = new Palabra();
		p.setValor("b");
		assertTrue(encadenado.tieneMismoValor(p) && dos.tieneMismoValor(p)); 
	}
	
	
	@Test
	public void anteriorSiExiste_ok() {
		ObligatorioConstante uno = new ObligatorioConstante("uno");
		
		NodoPatron encadenado = uno.encadenarSiguiente(new ObligatorioConstante("dos"));
		
		assertTrue(encadenado.anteriorSiExiste().getValor().equalsIgnoreCase("uno"));
	}

	@Test
	public void componerPatron_ok() {
		ObligatorioConstante uno = new ObligatorioConstante("uno");
		Patron patronCompuesto = uno.encadenarSiguiente(new ObligatorioConstante("dos"))
			.encadenarSiguiente(new ObligatorioConstante("tres"))
			.encadenarSiguiente(new ObligatorioConstante("cuatro"))
			.componerPatron();
		
		assertTrue(patronCompuesto.getLargoPatron()==4);
	}
		
	@Test
	public void tieneMismoValor_caso_afirmativa() {
		NodoPatron nodoPatron = new ObligatorioConstante(null);
		Palabra p2 = new Palabra();
				
		nodoPatron.setValor("test");
		p2.setValor("test");
		
		assertTrue(nodoPatron.tieneMismoValor(p2));
		
	}
	
	@Test
	public void tieneMismoValor_caso_negativo() {
		NodoPatron nodoPatron = new ObligatorioConstante(null);
		Palabra p2 = new Palabra();
		
		p2.setValor("notest");
		nodoPatron.setValor("test");
		
		assertTrue(!nodoPatron.tieneMismoValor(p2));
	}
}

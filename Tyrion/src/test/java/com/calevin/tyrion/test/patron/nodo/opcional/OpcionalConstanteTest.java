package com.calevin.tyrion.test.patron.nodo.opcional;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.calevin.tyrion.patron.nodo.obligatorio.ObligatorioConstante;
import com.calevin.tyrion.patron.nodo.opcional.OpcionalConstante;
import com.calevin.tyrion.texto.Palabra;

public class OpcionalConstanteTest {

	@Test
	public void cuando_tienen_el_mismo_valor() {
		OpcionalConstante opc = new OpcionalConstante("UNO");
		
		assertTrue(opc.tieneMismoValor(new Palabra("UNO")));
	}
	
	@Test
	public void cuando_el_siguiente_tiene_el_mismo_valor() {

		OpcionalConstante opc = new OpcionalConstante("UNO");
		opc.encadenarSiguiente(new ObligatorioConstante("DOS"));
		
		assertTrue(opc.tieneMismoValor(new Palabra("DOS")));
	}
	
	@Test
	public void cuando_el_siguiente_tiene_otro_valor() {

		OpcionalConstante opc = new OpcionalConstante("UNO");
		opc.encadenarSiguiente(new ObligatorioConstante("DOS"));
		
		assertTrue(!opc.tieneMismoValor(new Palabra("TRES")));
	}
}

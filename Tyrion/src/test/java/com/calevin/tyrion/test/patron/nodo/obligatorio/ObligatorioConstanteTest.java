package com.calevin.tyrion.test.patron.nodo.obligatorio;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.calevin.tyrion.patron.nodo.obligatorio.ObligatorioConstante;
import com.calevin.tyrion.texto.Palabra;

public class ObligatorioConstanteTest {

	@Test
	public void cuando_tienen_el_mismo_valor() {
		ObligatorioConstante oc = new ObligatorioConstante("CONSTANTE");
		
		assertTrue(oc.tieneMismoValor(new Palabra("CONSTANTE")));
	}
	
	@Test
	public void cuando_no_tienen_el_mismo_valor() {
		ObligatorioConstante oc = new ObligatorioConstante("CONSTANTE");
		
		assertTrue(!oc.tieneMismoValor(new Palabra("OTRACOSA")));
	}
}

package com.calevin.tyrion.test.patron.nodo;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.calevin.tyrion.patron.nodo.Opcional;
import com.calevin.tyrion.patron.nodo.obligatorio.ObligatorioConstante;
import com.calevin.tyrion.texto.Palabra;

public class OpcionalTest {

	@Test
	public void opcional_tiene_siguiente_para_evaluar() {
		OptTest o = new OptTest("uno");
		ObligatorioConstante ocTres = new ObligatorioConstante("tres");
		
		o.encadenarSiguiente(new ObligatorioConstante("dos"))
			.encadenarSiguiente(ocTres);
		
		assertTrue(o.getSiguienteNodoParaEvaluar().equals(ocTres));
	}
	
	@Test
	public void opcional_no_tiene_siguiente_para_evaluar() {
		OptTest o = new OptTest("uno");
		
		o.encadenarSiguiente(new ObligatorioConstante("dos"));
		assertNull(o.getSiguienteNodoParaEvaluar());
	}
	
	class OptTest extends Opcional {
		
		public OptTest(String valor) {
			super(valor);
		}

		@Override
		public boolean tieneMismoValor(Palabra other) {
			return true;
		}
	}
}

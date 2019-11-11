package com.calevin.tyrion.test.patron.nodo.opcional;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

import com.calevin.tyrion.patron.nodo.obligatorio.ObligatorioConstante;
import com.calevin.tyrion.patron.nodo.opcional.OpcionalVariable;
import com.calevin.tyrion.texto.Palabra;

public class OpcionalVariableTest {
	@Test
	public void cuando_tienen_el_mismo_valor() {
		Random rnd = new Random();
		int indicePrimerASCIImayus = 65;
		int cantidadASCIImayus = 25;
		
		OpcionalVariable variableLetraMayuscula = new OpcionalVariable("[A-Z]");
		
		String letraMayusculaRandom = String.valueOf((char)(indicePrimerASCIImayus + rnd.nextInt(cantidadASCIImayus)));
		
		System.out.println("LETRA: " + letraMayusculaRandom);
		
		assertTrue(variableLetraMayuscula.tieneMismoValor(new Palabra(letraMayusculaRandom)));
	}
	
	@Test
	public void cuando_el_siguiente_tiene_el_mismo_valor() {
		OpcionalVariable variableLetraMayuscula = new OpcionalVariable("[A-Z]");
		variableLetraMayuscula.encadenarSiguiente(new ObligatorioConstante("2"));
		
		assertTrue(variableLetraMayuscula.tieneMismoValor(new Palabra("2")));
	}
	
	@Test
	public void cuando_el_siguiente_tiene_otro_valor() {
		OpcionalVariable variableLetraMayuscula = new OpcionalVariable("[A-Z]");
		variableLetraMayuscula.encadenarSiguiente(new ObligatorioConstante("2"));
		
		assertTrue(!variableLetraMayuscula.tieneMismoValor(new Palabra("-")));
	}
}

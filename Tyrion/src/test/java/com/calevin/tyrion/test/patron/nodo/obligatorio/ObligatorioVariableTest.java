package com.calevin.tyrion.test.patron.nodo.obligatorio;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

import com.calevin.tyrion.patron.nodo.obligatorio.ObligatorioVariable;
import com.calevin.tyrion.texto.Palabra;

public class ObligatorioVariableTest {

	@Test
	public void cuando_tienen_el_mismo_valor() {
		
		ObligatorioVariable variableLetraMayuscula = new ObligatorioVariable("[A-Z]");
		
		String letraMayusculaRandom = generarLetraMayusculaRandom();
		
		System.out.println("LETRA: " + letraMayusculaRandom);
		
		assertTrue(variableLetraMayuscula.tieneMismoValor(new Palabra(letraMayusculaRandom)));
	}
	
	public static String generarLetraMayusculaRandom() {
		Random rnd = new Random();
		int indicePrimerASCIImayus = 65;
		int cantidadASCIImayus = 25;
		
		return String.valueOf((char)(indicePrimerASCIImayus + rnd.nextInt(cantidadASCIImayus)));
	}
}

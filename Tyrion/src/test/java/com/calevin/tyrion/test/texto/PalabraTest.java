package com.calevin.tyrion.test.texto;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.calevin.tyrion.texto.Palabra;
import com.calevin.tyrion.texto.Posicion;

public class PalabraTest {

	@Test
	public void toListaDePalabras_ok() {
		List<Palabra> listaAcomprobar = new ArrayList<Palabra>();
		Palabra palabra = new Palabra();
		palabra.setValor("primero");
		palabra.setPosicion(new Posicion(1, 0));
		listaAcomprobar.add(palabra);
		listaAcomprobar.add(new Palabra("segunda", new Posicion(1, 1)));
		listaAcomprobar.add(new Palabra("tercero", new Posicion(1, 2)));
		
		List<Palabra> lista = Palabra.toListaDePalabras("primero segunda tercero", 1);
		
		Assert.assertEquals(lista, listaAcomprobar);
		
	}
	

	@Test
	public void tieneMismoValor_caso_afirmativa() {
		Palabra p1 = new Palabra();
		Palabra p2 = new Palabra();
		
		assertTrue(p1.tieneMismoValor(p2));
		
		p1.setValor("test");
		p2.setValor("test");
		
		assertTrue(p1.tieneMismoValor(p2));
		
	}
	
	@Test
	public void tieneMismoValor_caso_negativo() {
		Palabra p1 = new Palabra();
		Palabra p2 = new Palabra();
		
		p2.setValor("notest");
		
		assertTrue(!p1.tieneMismoValor(p2));
		assertTrue(!p2.tieneMismoValor(p1));
		
		p1.setValor("test");
		
		assertTrue(!p1.tieneMismoValor(p2));
	}
}

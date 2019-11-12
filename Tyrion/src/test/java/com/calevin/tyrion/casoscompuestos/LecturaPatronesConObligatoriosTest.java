package com.calevin.tyrion.casoscompuestos;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.calevin.tyrion.core.Lector;
import com.calevin.tyrion.patron.Patron;
import com.calevin.tyrion.patron.PatronEncontrado;
import com.calevin.tyrion.patron.nodo.NodoPatron;
import com.calevin.tyrion.patron.nodo.obligatorio.ObligatorioConstante;
import com.calevin.tyrion.patron.nodo.obligatorio.ObligatorioVariable;
import static com.calevin.tyrion.test.patron.nodo.obligatorio.ObligatorioVariableTest.*;
import com.calevin.tyrion.texto.Palabra;
import com.calevin.tyrion.texto.Posicion;

public class LecturaPatronesConObligatoriosTest {
	private String regexLetraMayuscula = "[A-Z]";
	
	@Test
	public void evaluar_patron_tres_obligatorios_constante_ok() {
		Lector lector = new Lector("");
		NodoPatron unoDosTres = new ObligatorioConstante("uno")
				.encadenarSiguiente(new ObligatorioConstante("dos"))
				.encadenarSiguiente(new ObligatorioConstante("tres"));
		
		Patron patronUnoDosTres = unoDosTres.componerPatron();
		
		assertTrue(patronUnoDosTres.getLargoPatron()==3);
		
		lector.getPatrones().add(patronUnoDosTres);
		
		lector.setPalabras(Arrays.asList(
				new Palabra("uno", new Posicion(1))
				, new Palabra("dos", new Posicion(2))
				, new Palabra("tres", new Posicion(3))
				));
		
		lector.evaluarPatrones();
		
		PatronEncontrado patronUnoDosTresEncontrado = new PatronEncontrado(patronUnoDosTres, new Posicion(1, 1));
		
		assertTrue(lector.getPatronesEncontrados().get(0).equals(patronUnoDosTresEncontrado));
		
		lector.imprimirPatronesEncontrados();
	}
	
	@Test
	public void evaluar_patron_tres_obligatorios_variables_ok() {
		
		Lector lector = new Lector("");
		NodoPatron unoDosTres = new ObligatorioVariable(regexLetraMayuscula)
				.encadenarSiguiente(new ObligatorioVariable(regexLetraMayuscula))
				.encadenarSiguiente(new ObligatorioVariable(regexLetraMayuscula));
		
		Patron patronUnoDosTres = unoDosTres.componerPatron();
		
		assertTrue(patronUnoDosTres.getLargoPatron()==3);
		
		lector.getPatrones().add(patronUnoDosTres);

		lector.setPalabras(Arrays.asList(
				new Palabra(generarLetraMayusculaRandom(), new Posicion(1))
				, new Palabra(generarLetraMayusculaRandom(), new Posicion(2))
				, new Palabra(generarLetraMayusculaRandom(), new Posicion(3))
				));
		
		lector.evaluarPatrones();
		
		PatronEncontrado patronUnoDosTresEncontrado = new PatronEncontrado(patronUnoDosTres, new Posicion(1, 1));
		
		assertTrue(lector.getPatronesEncontrados().get(0).equals(patronUnoDosTresEncontrado));
		
		lector.imprimirPatronesEncontrados();
	}
	
	@Test
	public void evaluar_patron_obligatorios_constante_variable_constante() {
		Lector lector = new Lector("");
		NodoPatron unoAlgoTres = new ObligatorioConstante("uno")
				.encadenarSiguiente(new ObligatorioVariable(regexLetraMayuscula))
				.encadenarSiguiente(new ObligatorioConstante("tres"));
		
		Patron patronUnoAlgoTres = unoAlgoTres.componerPatron();
		
		assertTrue(patronUnoAlgoTres.getLargoPatron()==3);
		
		lector.getPatrones().add(patronUnoAlgoTres);
		
		lector.setPalabras(Arrays.asList(
				new Palabra("uno", new Posicion(1))
				, new Palabra(generarLetraMayusculaRandom(), new Posicion(2))
				, new Palabra("tres", new Posicion(3))
				));
		
		lector.evaluarPatrones();
		
		PatronEncontrado patronUnoAlgoTresEncontrado = new PatronEncontrado(patronUnoAlgoTres, new Posicion(1, 1));
		
		assertTrue(lector.getPatronesEncontrados().get(0).equals(patronUnoAlgoTresEncontrado));
		
		lector.imprimirPatronesEncontrados(); 
	}
	
	@Test
	public void evaluar_patron_obligatorios_variable_constante_variable() {
		Lector lector = new Lector("");
		NodoPatron algoDosAlgo = new ObligatorioVariable(regexLetraMayuscula)
				.encadenarSiguiente(new ObligatorioConstante("dos"))
				.encadenarSiguiente(new ObligatorioVariable(regexLetraMayuscula));
		
		Patron patronAlgoDosAlgo = algoDosAlgo.componerPatron();
		
		assertTrue(patronAlgoDosAlgo.getLargoPatron()==3);
		
		lector.getPatrones().add(patronAlgoDosAlgo);
		
		lector.setPalabras(Arrays.asList(
				new Palabra(generarLetraMayusculaRandom(), new Posicion(1))
				, new Palabra("dos", new Posicion(2))
				, new Palabra(generarLetraMayusculaRandom(), new Posicion(3))
				));
		
		lector.evaluarPatrones();

		PatronEncontrado patronAlgoDosAlgoEncontrado = new PatronEncontrado(patronAlgoDosAlgo, new Posicion(1, 1));
		
		assertTrue(lector.getPatronesEncontrados().get(0).equals(patronAlgoDosAlgoEncontrado));
		
		lector.imprimirPatronesEncontrados(); 
	}
}

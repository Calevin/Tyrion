package com.calevin.tyrion.casoscompuestos;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.calevin.tyrion.core.Lector;
import com.calevin.tyrion.patron.Patron;
import com.calevin.tyrion.patron.PatronEncontrado;
import com.calevin.tyrion.patron.nodo.NodoPatron;
import com.calevin.tyrion.patron.nodo.opcional.OpcionalConstante;
import com.calevin.tyrion.patron.nodo.opcional.OpcionalVariable;
import com.calevin.tyrion.texto.Palabra;
import com.calevin.tyrion.texto.Posicion;

import static com.calevin.tyrion.test.patron.nodo.obligatorio.ObligatorioVariableTest.*;

import java.util.Arrays;

public class LecturaPatronesConOpcionalTest {
	private String regexLetraMayuscula = "[A-Z]";

	@Test
	public void evaluar_patron_tres_opcionales_constante_ok() {
		Lector lector = new Lector("");
		NodoPatron unoDosTres = new OpcionalConstante("optuno")
				.encadenarSiguiente(new OpcionalConstante("optdos"))
				.encadenarSiguiente(new OpcionalConstante("opttres"));
		
		Patron patronUnoDosTres = unoDosTres.componerPatron();
		
		assertTrue(patronUnoDosTres.getLargoPatron()==3);
		
		lector.getPatrones().add(patronUnoDosTres);
		
		lector.setPalabras(Arrays.asList(
				new Palabra("optuno", new Posicion(1))
				, new Palabra("optdos", new Posicion(2))
				, new Palabra("opttres", new Posicion(3))
				));
		
		lector.evaluarPatrones();
		
		PatronEncontrado patronUnoDosTresEncontrado = new PatronEncontrado(patronUnoDosTres, new Posicion(1, 1));
		
		assertTrue(lector.getPatronesEncontrados().get(0).equals(patronUnoDosTresEncontrado));
		
		lector.imprimirPatronesEncontrados();
	}
	
	@Test
	public void evaluar_patron_tres_opcionales_variables_ok() {
		
		Lector lector = new Lector("");
		NodoPatron unoDosTres = new OpcionalVariable(regexLetraMayuscula)
				.encadenarSiguiente(new OpcionalVariable(regexLetraMayuscula))
				.encadenarSiguiente(new OpcionalVariable(regexLetraMayuscula));
		
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
	public void evaluar_patron_opcionales_constante_variable_constante() {
		Lector lector = new Lector("");
		NodoPatron unoAlgoTres = new OpcionalConstante("uno")
				.encadenarSiguiente(new OpcionalVariable(regexLetraMayuscula))
				.encadenarSiguiente(new OpcionalConstante("tres"));
		
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
	public void evaluar_patron_opcionales_variable_constante_variable() {
		Lector lector = new Lector("");
		NodoPatron algoDosAlgo = new OpcionalVariable(regexLetraMayuscula)
				.encadenarSiguiente(new OpcionalConstante("dos"))
				.encadenarSiguiente(new OpcionalVariable(regexLetraMayuscula));
		
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

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
import com.calevin.tyrion.texto.Texto;

import static com.calevin.tyrion.test.patron.nodo.obligatorio.ObligatorioVariableTest.*;

import java.util.Arrays;

public class LecturaPatronesConOpcionalTest {
	private String regexLetraMayuscula = "[A-Z]";

	@Test
	public void evaluar_patron_tres_opcionales_constante_ok() {
		Lector lector = new Lector("");
		Texto texto = new Texto();
		
		NodoPatron unoDosTres = new OpcionalConstante("optuno")
				.encadenarSiguiente(new OpcionalConstante("optdos"))
				.encadenarSiguiente(new OpcionalConstante("opttres"));
		
		Patron patronUnoDosTres = unoDosTres.componerPatron();
		
		assertTrue(patronUnoDosTres.getLargoPatron()==3);
		
		lector.getPatrones().add(patronUnoDosTres);
		
		Posicion posicionUnoUno = new Posicion(1, 1);
		Posicion posicionUnoDos = new Posicion(1, 2);
		Posicion posicionUnoTres = new Posicion(1, 3);
		
		texto.setPalabras(Arrays.asList(
				new Palabra("optuno", posicionUnoUno)
				, new Palabra("optdos", posicionUnoDos)
				, new Palabra("opttres", posicionUnoTres)
				));
		
		lector.setTexto(texto);
		lector.evaluarPatrones();
		
		PatronEncontrado patronUnoDosTresEncontrado 
			= new PatronEncontrado(patronUnoDosTres, posicionUnoUno, posicionUnoDos);
		
		assertTrue(lector.getPatronesEncontrados().get(0).equals(patronUnoDosTresEncontrado));
		
		lector.imprimirPatronesEncontrados();
	}
	
	@Test
	public void evaluar_patron_tres_opcionales_variables_ok() {
		Lector lector = new Lector("");
		Texto texto = new Texto();
		
		NodoPatron unoDosTres = new OpcionalVariable(regexLetraMayuscula)
				.encadenarSiguiente(new OpcionalVariable(regexLetraMayuscula))
				.encadenarSiguiente(new OpcionalVariable(regexLetraMayuscula));
		
		Patron patronUnoDosTres = unoDosTres.componerPatron();
		
		assertTrue(patronUnoDosTres.getLargoPatron()==3);
		
		lector.getPatrones().add(patronUnoDosTres);

		Posicion posicionUnoUno = new Posicion(1, 1);
		Posicion posicionUnoDos = new Posicion(1, 2);
		Posicion posicionUnoTres = new Posicion(1, 3);
		
		texto.setPalabras(Arrays.asList(
				new Palabra(generarLetraMayusculaRandom(), posicionUnoUno )
				, new Palabra(generarLetraMayusculaRandom(), posicionUnoDos)
				, new Palabra(generarLetraMayusculaRandom(), posicionUnoTres)
				));
		
		lector.setTexto(texto);
		lector.evaluarPatrones();

		PatronEncontrado patronUnoDosTresEncontrado 
			= new PatronEncontrado(patronUnoDosTres, posicionUnoUno, posicionUnoDos);
		
		assertTrue(lector.getPatronesEncontrados().get(0).equals(patronUnoDosTresEncontrado));
		
		lector.imprimirPatronesEncontrados();
	}
	
	@Test
	public void evaluar_patron_opcionales_constante_variable_constante() {
		Lector lector = new Lector("");
		Texto texto = new Texto();
		
		NodoPatron unoAlgoTres = new OpcionalConstante("uno")
				.encadenarSiguiente(new OpcionalVariable(regexLetraMayuscula))
				.encadenarSiguiente(new OpcionalConstante("tres"));
		
		Patron patronUnoAlgoTres = unoAlgoTres.componerPatron();
		
		assertTrue(patronUnoAlgoTres.getLargoPatron()==3);
		
		lector.getPatrones().add(patronUnoAlgoTres);
		
		Posicion posicionUnoUno = new Posicion(1, 1);
		Posicion posicionUnoDos = new Posicion(1, 2);
		Posicion posicionUnoTres = new Posicion(1, 3);
		
		texto.setPalabras(Arrays.asList(
				new Palabra("uno", posicionUnoUno)
				, new Palabra(generarLetraMayusculaRandom(), posicionUnoDos)
				, new Palabra("tres", posicionUnoTres)
				));
		
		lector.setTexto(texto);
		lector.evaluarPatrones();
		
		PatronEncontrado patronUnoAlgoTresEncontrado 
			= new PatronEncontrado(patronUnoAlgoTres, posicionUnoUno, posicionUnoDos);
		
		assertTrue(lector.getPatronesEncontrados().get(0).equals(patronUnoAlgoTresEncontrado));
		
		lector.imprimirPatronesEncontrados(); 
	}
	
	@Test
	public void evaluar_patron_opcionales_variable_constante_variable() {
		Lector lector = new Lector("");
		Texto texto = new Texto();
		
		NodoPatron algoDosAlgo = new OpcionalVariable(regexLetraMayuscula)
				.encadenarSiguiente(new OpcionalConstante("dos"))
				.encadenarSiguiente(new OpcionalVariable(regexLetraMayuscula));
		
		Patron patronAlgoDosAlgo = algoDosAlgo.componerPatron();
		
		assertTrue(patronAlgoDosAlgo.getLargoPatron()==3);
		
		lector.getPatrones().add(patronAlgoDosAlgo);
		
		Posicion posicionUnoUno = new Posicion(1, 1);
		Posicion posicionUnoDos = new Posicion(1, 2);
		Posicion posicionUnoTres = new Posicion(1, 3);
		
		texto.setPalabras(Arrays.asList(
				new Palabra(generarLetraMayusculaRandom(), posicionUnoUno)
				, new Palabra("dos", posicionUnoDos)
				, new Palabra(generarLetraMayusculaRandom(), posicionUnoTres)
				));
		
		lector.setTexto(texto);
		lector.evaluarPatrones();
		
		PatronEncontrado patronAlgoDosAlgoEncontrado 
			= new PatronEncontrado(patronAlgoDosAlgo, posicionUnoUno, posicionUnoDos);
		
		assertTrue(lector.getPatronesEncontrados().get(0).equals(patronAlgoDosAlgoEncontrado));
		
		lector.imprimirPatronesEncontrados(); 
	}	
}

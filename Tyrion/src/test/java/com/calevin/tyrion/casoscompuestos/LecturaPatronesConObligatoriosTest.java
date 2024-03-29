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
import com.calevin.tyrion.texto.Texto;

public class LecturaPatronesConObligatoriosTest {
	private String regexLetraMayuscula = "[A-Z]";
	
	@Test
	public void evaluar_patron_tres_obligatorios_constante_ok() {
		Lector lector = new Lector("");
		Texto texto = new Texto();
		
		NodoPatron unoDosTres = new ObligatorioConstante("uno")
				.encadenarSiguiente(new ObligatorioConstante("dos"))
				.encadenarSiguiente(new ObligatorioConstante("tres"));
		
		Patron patronUnoDosTres = unoDosTres.componerPatron();
		
		assertTrue(patronUnoDosTres.getLargoPatron()==3);
		
		lector.getPatrones().add(patronUnoDosTres);
		
		Posicion posicionUnoUno = new Posicion(1, 1);
		Posicion posicionUnoDos = new Posicion(1, 2);
		Posicion posicionUnoTres = new Posicion(1, 3);
		
		texto.setPalabras(Arrays.asList(
				new Palabra("uno", posicionUnoUno)
				, new Palabra("dos", posicionUnoDos)
				, new Palabra("tres", posicionUnoTres)
				));
		
		lector.setTexto(texto);
		lector.evaluarPatrones();
		
		PatronEncontrado patronUnoDosTresEncontrado 
			= new PatronEncontrado(patronUnoDosTres, posicionUnoUno, posicionUnoTres);
		
		assertTrue(lector.getPatronesEncontrados().get(0).equals(patronUnoDosTresEncontrado));
		
		lector.imprimirPatronesEncontrados();
	}
	
	@Test
	public void evaluar_patron_tres_obligatorios_variables_ok() {
		Lector lector = new Lector("");
		Texto texto = new Texto();
		
		NodoPatron unoDosTres = new ObligatorioVariable(regexLetraMayuscula)
				.encadenarSiguiente(new ObligatorioVariable(regexLetraMayuscula))
				.encadenarSiguiente(new ObligatorioVariable(regexLetraMayuscula));
		
		Patron patronUnoDosTres = unoDosTres.componerPatron();
		
		assertTrue(patronUnoDosTres.getLargoPatron()==3);
		
		lector.getPatrones().add(patronUnoDosTres);
		
		Posicion posicionUnoUno = new Posicion(1, 1);
		Posicion posicionUnoDos = new Posicion(1, 2);
		Posicion posicionUnoTres = new Posicion(1, 3);
		
		texto.setPalabras(Arrays.asList(
				new Palabra(generarLetraMayusculaRandom(), posicionUnoUno)
				, new Palabra(generarLetraMayusculaRandom(), posicionUnoDos)
				, new Palabra(generarLetraMayusculaRandom(), posicionUnoTres)
				));
		
		lector.setTexto(texto);
		lector.evaluarPatrones();
		
		PatronEncontrado patronUnoDosTresEncontrado 
			= new PatronEncontrado(patronUnoDosTres, posicionUnoUno, posicionUnoTres);
		
		assertTrue(lector.getPatronesEncontrados().get(0).equals(patronUnoDosTresEncontrado));
		
		lector.imprimirPatronesEncontrados();
	}
	
	@Test
	public void evaluar_patron_obligatorios_constante_variable_constante() {
		Lector lector = new Lector("");
		Texto texto = new Texto();
		
		NodoPatron unoAlgoTres = new ObligatorioConstante("uno")
				.encadenarSiguiente(new ObligatorioVariable(regexLetraMayuscula))
				.encadenarSiguiente(new ObligatorioConstante("tres"));
		
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
			= new PatronEncontrado(patronUnoAlgoTres, posicionUnoUno, posicionUnoTres);
		
		assertTrue(lector.getPatronesEncontrados().get(0).equals(patronUnoAlgoTresEncontrado));
		
		lector.imprimirPatronesEncontrados(); 
	}
	
	@Test
	public void evaluar_patron_obligatorios_variable_constante_variable() {
		Lector lector = new Lector("");
		Texto texto = new Texto();
		
		NodoPatron algoDosAlgo = new ObligatorioVariable(regexLetraMayuscula)
				.encadenarSiguiente(new ObligatorioConstante("dos"))
				.encadenarSiguiente(new ObligatorioVariable(regexLetraMayuscula));
		
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
			= new PatronEncontrado(patronAlgoDosAlgo, posicionUnoUno, posicionUnoTres);
		
		assertTrue(lector.getPatronesEncontrados().get(0).equals(patronAlgoDosAlgoEncontrado));
		
		lector.imprimirPatronesEncontrados(); 
	}
}

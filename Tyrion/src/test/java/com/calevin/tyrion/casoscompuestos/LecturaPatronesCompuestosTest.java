package com.calevin.tyrion.casoscompuestos;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.calevin.tyrion.core.Lector;
import com.calevin.tyrion.patron.Patron;
import com.calevin.tyrion.patron.PatronEncontrado;
import com.calevin.tyrion.patron.nodo.NodoPatron;
import com.calevin.tyrion.patron.nodo.obligatorio.ObligatorioConstante;
import com.calevin.tyrion.patron.nodo.opcional.OpcionalConstante;
import com.calevin.tyrion.texto.Palabra;
import com.calevin.tyrion.texto.Posicion;

public class LecturaPatronesCompuestosTest {
		
	@Test
	public void encontrando_patron_con_opcional() {
		Lector lector = new Lector("");
		
		NodoPatron unoQuizasComaDos = new ObligatorioConstante("uno")
				.encadenarSiguiente(new OpcionalConstante(","))
				.encadenarSiguiente(new ObligatorioConstante("dos"));
		
		Patron patronUnoQuizasComaDos = unoQuizasComaDos.componerPatron();
		
		assertTrue(patronUnoQuizasComaDos.getLargoPatron()==3);
		
		lector.getPatrones().add(patronUnoQuizasComaDos);
		
		lector.setPalabras(Arrays.asList(
				new Palabra("uno", new Posicion(1))
				, new Palabra(",", new Posicion(2))
				, new Palabra("dos", new Posicion(3))
				));
		
		lector.evaluarPatrones();

		PatronEncontrado patronUnoQuizasComaDosEncontrado = new PatronEncontrado(patronUnoQuizasComaDos, new Posicion(1, 1));
		
		assertTrue(lector.getPatronesEncontrados().get(0).equals(patronUnoQuizasComaDosEncontrado));

		System.out.println("Leyendo: " + lector.getPalabras());
		
		lector.imprimirPatronesEncontrados();
		
		System.out.println("Texto 2");

		lector.setPalabras(Arrays.asList(
				new Palabra("uno", new Posicion(1))
				, new Palabra("dos", new Posicion(2))
				));
		
		lector.evaluarPatrones();
		
		assertTrue(lector.getPatronesEncontrados().get(0).equals(patronUnoQuizasComaDosEncontrado));
		
		System.out.println("Leyendo: " + lector.getPalabras());
		
		lector.imprimirPatronesEncontrados();
	}
	
	@Test
	public void encontrando_dos_patrones_obligatorio_constante_mismo_inicio() {
		Lector lector = new Lector("");
		ObligatorioConstante unoDos = new ObligatorioConstante("uno");
		
		Patron patronUnoDos = unoDos.encadenarSiguiente(new ObligatorioConstante("dos")).componerPatron();
		
		Patron patronUnoDosTres = new ObligatorioConstante("uno")
				.encadenarSiguiente(new ObligatorioConstante("dos"))
				.encadenarSiguiente(new ObligatorioConstante("tres"))
				.componerPatron();
		
		List<Patron> patrones = new ArrayList<Patron>();
		
		patrones.add(patronUnoDosTres);
		patrones.add(patronUnoDos);
		
		lector.setPatrones(patrones);
		
		lector.setPalabras(Arrays.asList(
				new Palabra("uno", new Posicion(1))
				, new Palabra("dos", new Posicion(2))
				, new Palabra("tres", new Posicion(3))
				));

		lector.evaluarPatrones();
		Posicion unoUno = new Posicion(1, 1);
		PatronEncontrado patronUnoDosTresEncontrado = new PatronEncontrado(patronUnoDosTres, unoUno);
		PatronEncontrado patronUnoDosEncontrado = new PatronEncontrado(patronUnoDos, unoUno);
		
		assertTrue(lector.getPatronesEncontrados().contains(patronUnoDosTresEncontrado));
		assertTrue(lector.getPatronesEncontrados().contains(patronUnoDosEncontrado));
		
		System.out.println("Multiples patrones:");
		lector.imprimirPatronesEncontrados();
		System.out.println("Fin multiples patrones.");
	}
	
	@Test
	public void encontrando_dos_patrones_obligatorio_constante_distinto_inicio() {
		Lector lector = new Lector("");
		
		Patron patronUnoDos = new ObligatorioConstante("uno")
				.encadenarSiguiente(new ObligatorioConstante("dos"))
				.componerPatron();
		
		Patron patronDosTres = new ObligatorioConstante("dos")
				.encadenarSiguiente(new ObligatorioConstante("tres"))
				.componerPatron();
		
		List<Patron> patrones = new ArrayList<Patron>();
		
		patrones.add(patronUnoDos);
		patrones.add(patronDosTres);
		
		lector.setPatrones(patrones);
		
		lector.setPalabras(Arrays.asList(
				new Palabra("uno", new Posicion(1))
				, new Palabra("dos", new Posicion(2))
				, new Palabra("tres", new Posicion(3))
				));

		lector.evaluarPatrones();
		
		Posicion unoUno = new Posicion(1, 1);
		Posicion unoDos = new Posicion(1, 2);
		
		PatronEncontrado patronUnoDosTresEncontrado = new PatronEncontrado(patronUnoDos, unoUno);
		PatronEncontrado patronUnoDosEncontrado = new PatronEncontrado(patronDosTres, unoDos);
		
		assertTrue(lector.getPatronesEncontrados().get(0).equals(patronUnoDosTresEncontrado));
		assertTrue(lector.getPatronesEncontrados().get(1).equals(patronUnoDosEncontrado));
		
		System.out.println("Multiples patrones:");
		lector.imprimirPatronesEncontrados();
		System.out.println("Fin multiples patrones.");
	}
}

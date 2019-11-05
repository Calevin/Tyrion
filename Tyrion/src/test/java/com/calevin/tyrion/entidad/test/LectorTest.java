package com.calevin.tyrion.entidad.test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.calevin.tyrion.Lector;
import com.calevin.tyrion.entidad.NodoObligatorioConstante;
import com.calevin.tyrion.entidad.NodoPatron;
import com.calevin.tyrion.entidad.Palabra;
import com.calevin.tyrion.entidad.Patron;
import com.calevin.tyrion.entidad.Posicion;

public class LectorTest {


	@Test
	public void cargarArchivo_ok() throws IOException {
		Lector l = new Lector("D:\\GitHub\\Tyrion\\Tyrion\\src\\test\\resources\\test_un_patron.txt");
		l.cargarArchivo();
		
		l.getPalabras().equals(Arrays.asList(
				new Palabra("uno", new Posicion(1))
				, new Palabra("dos", new Posicion(2))
				, new Palabra("tres", new Posicion(3))
				));
	}
	
	@Test
	public void evaluarPatrones_ok() throws IOException {
		Lector lector = new Lector("");
		NodoPatron unoDosTres = new NodoObligatorioConstante("uno")
				.encadenarSiguiente(new NodoObligatorioConstante("dos"))
				.encadenarSiguiente(new NodoObligatorioConstante("tres"));
		
		Patron patronUnoDosTres = unoDosTres.componerPatron();
		
		assertTrue(patronUnoDosTres.getLargoPatron()==3);
		
		lector.getPatrones().add(patronUnoDosTres);
		
		lector.setPalabras(Arrays.asList(
				new Palabra("uno", new Posicion(1))
				, new Palabra("dos", new Posicion(2))
				, new Palabra("tres", new Posicion(3))
				));
		
		lector.evaluarPatrones();
		
		assertTrue(lector.getPatronesEncontrados().get(new Posicion(1, 3)).equals(patronUnoDosTres));
		System.out.println("un Patron:");
		lector.getPatronesEncontrados()
		.entrySet()
		.stream()
		.sorted((pe1, pe2) -> pe1.getKey().compareTo(pe2.getKey())) 
		.forEach(pe -> System.out.println(pe.getKey() + "\n" + pe.getValue() + "\n"));
		System.out.println("fin un Patron.");
	}

	@Test
	public void evaluarPatrones_multiples_patrones_ok() throws IOException {
		Lector lector = new Lector("");
		NodoObligatorioConstante unoDos = new NodoObligatorioConstante("uno");
		
		Patron patronUnoDos = unoDos.encadenarSiguiente(new NodoObligatorioConstante("dos")).componerPatron();
		
		Patron patronUnoDosTres = new NodoObligatorioConstante("uno")
				.encadenarSiguiente(new NodoObligatorioConstante("dos"))
				.encadenarSiguiente(new NodoObligatorioConstante("tres"))
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
		
		assertTrue(lector.getPatronesEncontrados().get(new Posicion(1, 3)).equals(patronUnoDosTres));
		assertTrue(lector.getPatronesEncontrados().get(new Posicion(1, 2)).equals(patronUnoDos));
		
		System.out.println("Multiples patrones:");
		lector.getPatronesEncontrados()
		.entrySet()
		.stream()
		.sorted((pe1, pe2) -> pe1.getKey().compareTo(pe2.getKey())) 
		.forEach(pe -> System.out.println(pe.getKey() + "\n" + pe.getValue() + "\n"));
		System.out.println("Fin multiples patrones.");
	}

	@Test(expected = IOException.class)
	public void cargarArchivo_io_Exception() throws IOException {
		Lector lector = new Lector("");
		lector.cargarArchivo();
		//assertThrows(IOException.class, () -> l.cargarArchivo());
	}
	
	@Test
	public void getPosicionInicialPatron_ok() {
		Lector lector = new Lector("");
		NodoPatron unoDos = new NodoObligatorioConstante("uno")
				.encadenarSiguiente(new NodoObligatorioConstante("dos"));
		
		Patron patronUnoDos = unoDos.componerPatron();
		
		Patron patronUnoDosTres = unoDos.encadenarSiguiente(new NodoObligatorioConstante("tres")).componerPatron();
		
		Patron patronDosTres = (new NodoObligatorioConstante("dos")
				.encadenarSiguiente(new NodoObligatorioConstante("tres")))
				.componerPatron();
		
		List<Patron> patrones = new ArrayList<Patron>();
		patrones.add(patronUnoDosTres);
		patrones.add(patronUnoDos);
		
		lector.setPatrones(patrones);
		Posicion lineaUnoColumnaUno = new Posicion(1);
		Posicion lineaUnoColumnaDos = new Posicion(2);
		Palabra dos = new Palabra("dos", lineaUnoColumnaDos);
		Palabra tres = new Palabra("tres", new Posicion(3)); 
		
		lector.setPalabras(Arrays.asList(
				new Palabra("uno", lineaUnoColumnaUno)
				, dos
				, tres
				));

		assertTrue(lector.getPosicionInicialPatron(dos, patronUnoDos).equals(lineaUnoColumnaUno)
				&& lector.getPosicionInicialPatron(tres, patronUnoDosTres).equals(lineaUnoColumnaUno)
				&& lector.getPosicionInicialPatron(tres, patronDosTres).equals(lineaUnoColumnaDos));
	}
}

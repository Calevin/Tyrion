package com.calevin.tyrion.test.core;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.calevin.tyrion.core.Lector;
import com.calevin.tyrion.patron.Patron;
import com.calevin.tyrion.patron.nodo.NodoPatron;
import com.calevin.tyrion.patron.nodo.obligatorio.ObligatorioConstante;
import com.calevin.tyrion.texto.Palabra;
import com.calevin.tyrion.texto.Posicion;
import com.calevin.tyrion.texto.Texto;

public class LectorTest {
	@Test
	public void cargarArchivo_ok() throws IOException {
		Lector l = new Lector("D:\\GitHub\\Tyrion\\Tyrion\\src\\test\\resources\\test_un_patron.txt");
		l.cargarArchivo();
		
		l.getTexto().getPalabras().equals(Arrays.asList(
				new Palabra("uno", new Posicion(1))
				, new Palabra("dos", new Posicion(2))
				, new Palabra("tres", new Posicion(3))
				));
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
		Texto texto = new Texto();
		
		NodoPatron unoDos = new ObligatorioConstante("uno")
				.encadenarSiguiente(new ObligatorioConstante("dos"));
		
		Patron patronUnoDos = unoDos.componerPatron();
		
		Patron patronUnoDosTres = unoDos.encadenarSiguiente(new ObligatorioConstante("tres")).componerPatron();
		
		Patron patronDosTres = (new ObligatorioConstante("dos")
				.encadenarSiguiente(new ObligatorioConstante("tres")))
				.componerPatron();
		
		List<Patron> patrones = new ArrayList<Patron>();
		patrones.add(patronUnoDosTres);
		patrones.add(patronUnoDos);
		
		lector.setPatrones(patrones);
		Posicion lineaUnoColumnaUno = new Posicion(1);
		Posicion lineaUnoColumnaDos = new Posicion(2);
		Palabra dos = new Palabra("dos", lineaUnoColumnaDos);
		Palabra tres = new Palabra("tres", new Posicion(3));
		
		texto.setPalabras(Arrays.asList(
				new Palabra("uno", lineaUnoColumnaUno)
				, dos
				, tres
				));
		
		lector.setTexto(texto);
		
		assertTrue(lector.getPosicionInicialPatron(dos, patronUnoDos).equals(lineaUnoColumnaUno)
				&& lector.getPosicionInicialPatron(tres, patronUnoDosTres).equals(lineaUnoColumnaUno)
				&& lector.getPosicionInicialPatron(tres, patronDosTres).equals(lineaUnoColumnaDos));
	}
}

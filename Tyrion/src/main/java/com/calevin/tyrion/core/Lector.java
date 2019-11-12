package com.calevin.tyrion.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.calevin.tyrion.patron.Patron;
import com.calevin.tyrion.patron.PatronEncontrado;
import com.calevin.tyrion.texto.Linea;
import com.calevin.tyrion.texto.Palabra;
import com.calevin.tyrion.texto.Posicion;

public class Lector {

	private List<PatronEncontrado> patronesEncontrados = new ArrayList<PatronEncontrado>();
	private List<Patron> patrones = new ArrayList<Patron>();
	private List<Palabra> palabras;
	private String archivo;
	
	public Lector(String archivo) {
		super();
		this.archivo = archivo;
	}

	public void cargarArchivo() throws IOException {
		AtomicInteger indiceLinea = new AtomicInteger(0);
		try (Stream<String> lineasDelArchivo = Files.lines(Paths.get(this.archivo))) {

			this.palabras = lineasDelArchivo
					.map(s -> {
							int linea = indiceLinea.incrementAndGet();
							return new Linea(s, linea);
					})
					.flatMap(l -> l.getPalabras().stream())
					.collect(Collectors.toList());

		} catch (IOException e) {
			throw e;
		}
	}

	public void evaluarPatrones() {
		this.palabras
			.stream()
			.forEach(palabra -> {
				this.patrones
					.stream()
					.forEach(patron -> this.comprobar(palabra, patron));
			});
	}
	
	public void comprobar(Palabra palabra, Patron patron) {
		if (patron.evaluar(palabra) 
				&& patron.patronEncontrado()) {
			
			this.patronesEncontrados.add(new PatronEncontrado(patron, patron.getPosicionInicial()));
			patron.resetPatron();
		}
	}
	
	public Posicion getPosicionInicialPatron(Palabra palabraFinal, Patron patron) {
		
		int indicePalabraFinal = 1+this.palabras.indexOf(palabraFinal);
		int indicePalabraInicial = indicePalabraFinal-patron.getLargoPatron();
		
		return this.palabras.get(indicePalabraInicial).getPosicion();
	}
	
	public void imprimirPatronesEncontrados() {
		System.out.println("********************************************************");
		System.out.println("Inicio Patrones Encontrados:");
		this.getPatronesEncontrados()
		.forEach(pe -> { 
				System.out.println(pe.getPosicionEncontrado()  + "\n" + pe.getPatron() + "\n");
			});
		System.out.println("fin Patrones Encontrados.");
		System.out.println("********************************************************");
	}
	
	public List<Palabra> getPalabras() {
		return palabras;
	}

	public void setPalabras(List<Palabra> palabras) {
		this.palabras = palabras;
	}
	
	public List<Patron> getPatrones() {
		return patrones;
	}

	public void setPatrones(List<Patron> patrones) {
		this.patrones = patrones;
	}
	public List<PatronEncontrado> getPatronesEncontrados() {
		return patronesEncontrados;
	}
}

package com.calevin.tyrion;

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

import com.calevin.tyrion.entidad.Linea;
import com.calevin.tyrion.entidad.Palabra;
import com.calevin.tyrion.entidad.Patron;
import com.calevin.tyrion.entidad.Posicion;

public class Lector {

	private static int numeroPalabra = 0;
	private Map<Posicion, Patron> patronesEncontrados = new HashMap<Posicion, Patron>();
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
			this.patronesEncontrados.put(palabra.getPosicion(), patron);
		}
	}
	
	public List<Palabra> getPalabras() {
		return palabras;
	}

	public void setPalabras(List<Palabra> palabras) {
		this.palabras = palabras;
	}
	
	public static int getNumeroPalabra() {
		return numeroPalabra;
	}
	public List<Patron> getPatrones() {
		return patrones;
	}

	public void setPatrones(List<Patron> patrones) {
		this.patrones = patrones;
	}
	public Map<Posicion, Patron> getPatronesEncontrados() {
		return patronesEncontrados;
	}

	public void setPatronesEncontrados(Map<Posicion, Patron> patronesEncontrados) {
		this.patronesEncontrados = patronesEncontrados;
	}
}

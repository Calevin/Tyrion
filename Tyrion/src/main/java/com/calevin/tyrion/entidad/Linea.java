package com.calevin.tyrion.entidad;

import java.util.List;

public class Linea {
	List<Palabra> palabras;
	int numeroLinea;
	
	public Linea(String oracion, int numeroLinea) {
		super();
		this.palabras = Palabra.toListaDePalabras(oracion, numeroLinea);
		this.numeroLinea = numeroLinea;
	}
	
	public List<Palabra> getPalabras() {
		return palabras;
	}
}

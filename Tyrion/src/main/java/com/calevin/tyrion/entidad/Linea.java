package com.calevin.tyrion.entidad;

import java.util.List;

public class Linea {
	List<Palabra> palabras;
	int numeroLinea;
	
	public Linea(List<Palabra> palabras , int numeroLinea) {
		super();
		this.palabras = palabras;
		this.numeroLinea = numeroLinea;
	}
	
	public Linea(String oracion, int numeroLinea) {
		super();
		this.palabras = Palabra.toListaDePalabras(oracion, numeroLinea);
		this.numeroLinea = numeroLinea;
	}

	public int getNumeroLinea() {
		return numeroLinea;
	}

	public void setNumeroLinea(int numeroLinea) {
		this.numeroLinea = numeroLinea;
	}

	public List<Palabra> getPalabras() {
		return palabras;
	}

	public void setPalabras(List<Palabra> palabras) {
		this.palabras = palabras;
	}

	@Override
	public String toString() {
		return "Linea [palabras=" + palabras + ", numeroLinea=" + numeroLinea + "]";
	}
}

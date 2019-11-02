package com.calevin.tyrion.entidad;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Palabra implements Comparable<Palabra> {
	private String valor;
	private Posicion posicion;

	public Palabra() {
		super();
	}

	public Palabra(String valor, Posicion posicion) {
		super();
		this.valor = valor;
		this.posicion = posicion;
	}

	public boolean tieneMismoValor(Palabra other) {
		if (this.valor == null) {
			if (other.getValor() != null)
				return false;
		} else if (!this.valor.equals(other.getValor()))
			return false;
		return true;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	@Override
	public int compareTo(Palabra o) {
		return this.getPosicion().compareTo(o.getPosicion());
	}

	@Override
	public String toString() {
		return "Palabra [valor=" + valor + ", posicion=" + posicion + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((posicion == null) ? 0 : posicion.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Palabra other = (Palabra) obj;
		if (posicion == null) {
			if (other.getPosicion() != null)
				return false;
		} else if (!posicion.equals(other.getPosicion()))
			return false;
		if (valor == null) {
			if (other.getValor() != null)
				return false;
		} else if (!valor.equals(other.getValor()))
			return false;
		return true;
	}

	public static List<Palabra> toListaDePalabras(String oracion, int indiceLinea) {
		AtomicInteger indicePosicion = new AtomicInteger(0);
		return Pattern.compile(" ")
				.splitAsStream(oracion)
				.map(s -> {
					return new Palabra(s, new Posicion(indiceLinea, indicePosicion.incrementAndGet()));
				}).collect(Collectors.toList());
	}
}

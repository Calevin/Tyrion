package com.calevin.tyrion.patron;

import com.calevin.tyrion.texto.Posicion;

public class PatronEncontrado {

	private Patron patron;
	private Posicion posicionInicio;
	private Posicion posicionFinal;
		
	public PatronEncontrado(Patron patron, Posicion posicionEncontrado, Posicion posicionFinal) {
		super();
		this.patron = patron;
		this.posicionInicio = posicionEncontrado;
		this.posicionFinal = posicionFinal;
	}

	public Patron getPatron() {
		return patron;
	}

	public void setPatron(Patron patron) {
		this.patron = patron;
	}

	public Posicion getPosicionInicio() {
		return posicionInicio;
	}

	public void setPosicionInicio(Posicion posicionEncontrado) {
		this.posicionInicio = posicionEncontrado;
	}

	public Posicion getPosicionFinal() {
		return posicionFinal;
	}

	public void setPosicionFinal(Posicion posicionFinal) {
		this.posicionFinal = posicionFinal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((patron == null) ? 0 : patron.hashCode());
		result = prime * result + ((posicionFinal == null) ? 0 : posicionFinal.hashCode());
		result = prime * result + ((posicionInicio == null) ? 0 : posicionInicio.hashCode());
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
		PatronEncontrado other = (PatronEncontrado) obj;
		if (patron == null) {
			if (other.patron != null)
				return false;
		} else if (!patron.equals(other.patron))
			return false;
		if (posicionFinal == null) {
			if (other.posicionFinal != null)
				return false;
		} else if (!posicionFinal.equals(other.posicionFinal))
			return false;
		if (posicionInicio == null) {
			if (other.posicionInicio != null)
				return false;
		} else if (!posicionInicio.equals(other.posicionInicio))
			return false;
		return true;
	}
}

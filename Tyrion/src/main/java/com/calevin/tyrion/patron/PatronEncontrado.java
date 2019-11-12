package com.calevin.tyrion.patron;

import com.calevin.tyrion.texto.Posicion;

public class PatronEncontrado {

	private Patron patron;
	private Posicion posicionEncontrado;
	
	public PatronEncontrado(Patron patron, Posicion posicionEncontrado) {
		super();
		this.patron = patron;
		this.posicionEncontrado = posicionEncontrado;
	}

	public Patron getPatron() {
		return patron;
	}

	public void setPatron(Patron patron) {
		this.patron = patron;
	}

	public Posicion getPosicionEncontrado() {
		return posicionEncontrado;
	}

	public void setPosicionEncontrado(Posicion posicionEncontrado) {
		this.posicionEncontrado = posicionEncontrado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((patron == null) ? 0 : patron.hashCode());
		result = prime * result + ((posicionEncontrado == null) ? 0 : posicionEncontrado.hashCode());
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
		if (posicionEncontrado == null) {
			if (other.posicionEncontrado != null)
				return false;
		} else if (!posicionEncontrado.equals(other.posicionEncontrado))
			return false;
		return true;
	}
}

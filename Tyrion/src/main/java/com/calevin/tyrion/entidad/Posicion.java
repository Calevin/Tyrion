package com.calevin.tyrion.entidad;

public class Posicion implements Comparable<Posicion> {
	private int linea;
	private int columna;

	public Posicion() {
		super();
	}

	public Posicion(int linea, int columna) {
		super();
		this.linea = linea;
		this.columna = columna;
	}

	public Posicion(int columna) {
		this.linea = 1;
		this.columna = columna;
	}

	public int getLinea() {
		return linea;
	}

	public void setLinea(int linea) {
		this.linea = linea;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	@Override
	public String toString() {
		return "Posicion [linea=" + linea + ", columna=" + columna + "]";
	}

	@Override
	public int compareTo(Posicion o) {
		int retorno = 0;
		if (this.getLinea() ==  o.getLinea()) {
			retorno = Integer.compare(this.getColumna(), o.getColumna());
		} else {
			retorno = Integer.compare(this.getLinea(), o.getLinea());
		}
		
		return retorno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + columna;
		result = prime * result + linea;
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
		Posicion other = (Posicion) obj;
		if (columna != other.getColumna())
			return false;
		if (linea != other.getLinea())
			return false;
		return true;
	}
}

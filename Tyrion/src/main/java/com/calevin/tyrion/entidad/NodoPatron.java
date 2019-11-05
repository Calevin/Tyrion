package com.calevin.tyrion.entidad;

public class NodoPatron {
	private String valor;
	private NodoPatron siguientePalabra;
	private NodoPatron anteriorPalabra;
	
	public NodoPatron() {
		super();
	}

	public NodoPatron(String valor) {
		super();
		this.valor = valor;
	}
	
	public boolean tieneMismoValor(Palabra other) {
		if (this.valor == null) {
			if (other.getValor() != null)
				return false;
		} else if (!this.valor.equals(other.getValor()))
			return false;
		return true;
	}

	public NodoPatron encadenarSiguientePalabra(String siguientePalabra) {
		this.siguientePalabra = new NodoPatron(siguientePalabra);
		this.siguientePalabra.setAnteriorPalabra(this);
		return this.siguientePalabra;
	}

	public NodoPatron anteriorSiExiste() {
		return this.getAnteriorPalabra() != null 
					? this.getAnteriorPalabra().anteriorSiExiste() : this;
	}
	
	public Patron componerPatron () {
		NodoPatron inicial = anteriorSiExiste();
		return new Patron(inicial);
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	
	public NodoPatron getSiguientePalabra() {
		return siguientePalabra;
	}

	public NodoPatron getAnteriorPalabra() {
		return anteriorPalabra;
	}

	public void setAnteriorPalabra(NodoPatron anteriorPalabra) {
		this.anteriorPalabra = anteriorPalabra;
	}
	
	@Override
	public String toString() {
		return "[valor=" + valor + (this.siguientePalabra!=null ? this.siguientePalabra.toString() : "") +  " ]";
	}
}

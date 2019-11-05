package com.calevin.tyrion.entidad;

public abstract class NodoPatron {
	private String valor;
	private NodoPatron siguienteNodo;
	private NodoPatron anteriorNodo;
	
	public NodoPatron() {
		super();
	}

	public NodoPatron(String valor) {
		super();
		this.valor = valor;
	}
	
	abstract public boolean tieneMismoValor(Palabra other);
		
	public NodoPatron encadenarSiguiente(NodoPatron siguiente) {
		this.setSiguienteNodo(siguiente);
		this.getSiguienteNodo().setAnteriorNodo(this);

		return this.getSiguienteNodo();
	}

	public NodoPatron anteriorSiExiste() {
		return this.getAnteriorNodo() != null ? this.getAnteriorNodo().anteriorSiExiste() : this;
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

	public NodoPatron getSiguienteNodo() {
		return siguienteNodo;
	}

	public void setSiguienteNodo(NodoPatron siguienteNodo) {
		this.siguienteNodo = siguienteNodo;
	}

	public NodoPatron getAnteriorNodo() {
		return anteriorNodo;
	}

	public void setAnteriorNodo(NodoPatron anteriorNodo) {
		this.anteriorNodo = anteriorNodo;
	}
	
	@Override
	public String toString() {
		return "[valor=" + valor + (this.siguienteNodo!=null ? this.siguienteNodo.toString() : "") +  "]";
	}
}

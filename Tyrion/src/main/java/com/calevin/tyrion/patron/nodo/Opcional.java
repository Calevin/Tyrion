package com.calevin.tyrion.patron.nodo;

public abstract class Opcional extends NodoPatron {
	
	public Opcional(String valor) {
		super(valor);
	}

	@Override
	public NodoPatron getSiguienteNodoParaEvaluar() {
		return this.getSiguienteNodo().getSiguienteNodo();
	}
}

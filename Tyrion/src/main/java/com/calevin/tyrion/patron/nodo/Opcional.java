package com.calevin.tyrion.patron.nodo;

public abstract class Opcional extends NodoPatron {

	@Override
	public NodoPatron getSiguienteNodoParaEvaluar() {
		return this.getSiguienteNodo().getSiguienteNodo();
	}
}

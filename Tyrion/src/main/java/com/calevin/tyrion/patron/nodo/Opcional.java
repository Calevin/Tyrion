package com.calevin.tyrion.patron.nodo;

import com.calevin.tyrion.texto.Palabra;

public abstract class Opcional extends NodoPatron {
	
	public Opcional(String valor) {
		super(valor);
	}

	@Override
	public NodoPatron getSiguienteNodoParaEvaluar() {
		return this.getSiguienteNodo() != null ? this.getSiguienteNodo().getSiguienteNodo() : null;
	}
	
	@Override
	public boolean tieneMismoValor(Palabra other) {
		if (this.getSiguienteNodo()!=null) {
			return this.getSiguienteNodo().tieneMismoValor(other);
		} else {
			return true;
		}
	}
}

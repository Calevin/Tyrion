package com.calevin.tyrion.patron.nodo.opcional;

import com.calevin.tyrion.patron.nodo.Opcional;
import com.calevin.tyrion.texto.Palabra;

public class OpcionalConstante extends Opcional {
	
	public OpcionalConstante(String valor) {
		super(valor);
	}
	
	@Override
	public boolean tieneMismoValor(Palabra other) {
		
		if(this.getValor().equals(other.getValor())) {
			return true;
		} else {
			return this.getSiguienteNodo().tieneMismoValor(other);
		}
	}
}

package com.calevin.tyrion.entidad;

public class NodoOpcionalConstante extends NodoPatron {

	public NodoOpcionalConstante(String valor) {
		this.setValor(valor);
	}
	
	@Override
	public boolean tieneMismoValor(Palabra other) {
		
		if(this.getValor().equals(other.getValor())) {
			return true;
		} else {
			return this.getSiguienteNodo().tieneMismoValor(other);
		}
	}
	
	@Override
	public NodoPatron getSiguienteNodoParaEvaluar() {
		return this.getSiguienteNodo().getSiguienteNodo();
	}
}

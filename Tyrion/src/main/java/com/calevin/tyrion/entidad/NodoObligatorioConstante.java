package com.calevin.tyrion.entidad;

public class NodoObligatorioConstante extends NodoPatron {

	public NodoObligatorioConstante(String valor) {
		this.setValor(valor);
	}

	@Override
	public boolean tieneMismoValor(Palabra other) {
		if (this.getValor() == null) {
			if (other.getValor() != null)
				return false;
		} else if (!this.getValor().equals(other.getValor()))
			return false;
		return true;
	}
}

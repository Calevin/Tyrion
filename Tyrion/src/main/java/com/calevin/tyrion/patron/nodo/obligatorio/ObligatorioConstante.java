package com.calevin.tyrion.patron.nodo.obligatorio;

import com.calevin.tyrion.patron.nodo.NodoPatron;
import com.calevin.tyrion.texto.Palabra;

public class ObligatorioConstante extends NodoPatron {
	
	public ObligatorioConstante(String valor) {
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

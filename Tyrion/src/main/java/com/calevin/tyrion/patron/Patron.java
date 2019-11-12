package com.calevin.tyrion.patron;

import com.calevin.tyrion.patron.nodo.NodoPatron;
import com.calevin.tyrion.texto.Palabra;

public class Patron {
	final private NodoPatron patronCompuesto;
	private NodoPatron nodoActual;
	private int largoPatron = -1;
	
	public Patron(NodoPatron palabrasNodo) {
		super();
		this.patronCompuesto = palabrasNodo;
		this.nodoActual = patronCompuesto;
		this.setLargoPatron(1);
	}	
	
	public Patron(NodoPatron palabrasNodo, int largoPatron) {
		super();
		this.patronCompuesto = palabrasNodo;
		this.nodoActual = patronCompuesto;
		this.setLargoPatron(largoPatron);
	}
	
	public boolean evaluar(Palabra p) {
		boolean reicidente=false;
		return evaluar(p, reicidente);
	}
	
	private boolean evaluar(Palabra palabraAevaluar, boolean reicidente) {
		boolean resultadoEvaluacion = false;
		
		if (nodoActual.tieneMismoValor(palabraAevaluar)) {
			nodoActual = nodoActual.getSiguienteNodoParaEvaluar();
			resultadoEvaluacion = true;
		} else {
			this.nodoActual = patronCompuesto;
			if (!reicidente) {
				reicidente=true;
				resultadoEvaluacion = this.evaluar(palabraAevaluar,reicidente);
			}
		}
		
		return resultadoEvaluacion;
	}
	
	public boolean patronEncontrado() {
		if(nodoActual==null) {
			this.nodoActual=this.patronCompuesto;
			return true;
		} else {
			return false;
		}
	}
	
	public int getLargoPatron() {
		return largoPatron;
	}

	public void setLargoPatron(int largoPatron) {
		this.largoPatron = largoPatron;
	}

	@Override
	public String toString() {
		return "Patron [ patron=" + patronCompuesto.toString() + "]";
	}
}

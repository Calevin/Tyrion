package com.calevin.tyrion.patron;

import com.calevin.tyrion.patron.nodo.NodoPatron;
import com.calevin.tyrion.texto.Palabra;
import com.calevin.tyrion.texto.Posicion;

public class Patron {
	final private NodoPatron patronCompuesto;
	private NodoPatron nodoActual;
	private int largoPatron = -1;
	private Posicion posicionInicial;
	
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
			if(nodoActual.equals(patronCompuesto)) {
				this.posicionInicial = palabraAevaluar.getPosicion();
			}
			nodoActual = nodoActual.getSiguienteNodoParaEvaluar();
			resultadoEvaluacion = true;
		} else {
			this.resetPatron();
			
			if (!reicidente) {
				reicidente=true;
				resultadoEvaluacion = this.evaluar(palabraAevaluar,reicidente);
			}
		}
		
		return resultadoEvaluacion;
	}
	
	public boolean patronEncontrado() {
		return nodoActual==null;
	}
	
	public void resetPatron() {
		this.nodoActual = this.patronCompuesto;
		this.posicionInicial = null;
	}
	
	public int getLargoPatron() {
		return largoPatron;
	}

	public void setLargoPatron(int largoPatron) {
		this.largoPatron = largoPatron;
	}

	public Posicion getPosicionInicial() {
		return posicionInicial;
	}
	
	@Override
	public String toString() {
		return "Patron [ patron=" + patronCompuesto.toString() + "]";
	}
}

package com.calevin.tyrion.entidad;

public class Patron {
	final private NodoPatron patronCompuesto;
	private NodoPatron nodoActual;
	private int largoPatron = -1;
	
	public Patron(NodoPatron palabrasNodo) {
		super();
		this.patronCompuesto = palabrasNodo;
		this.nodoActual = patronCompuesto;
		this.largoPatron = getLargoPatron();
	}
	
	public boolean evaluar(Palabra p) {
		boolean reicidente=false;
		return evaluar(p, reicidente);
	}
	
	private boolean evaluar(Palabra palabraAevaluar, boolean reicidente) {
		boolean resultadoEvaluacion = false;
		
		if (nodoActual.tieneMismoValor(palabraAevaluar)) {
			nodoActual = nodoActual.getSiguienteNodo();
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
	/*
	public boolean comprobrarPatron(List<Palabra> palabrasAcomprobar) {
		int limitePatron = patron.size();
		
		for(int i = 0; i < limitePatron; i++){
			if(!palabrasAcomprobar.get(i).getValor()
					.equals(patron.get(i).getValor())) {
				return false;
			}
		}
		
		return true;
	}*/
	
	@Override
	public String toString() {
		//patronList.stream().map(s -> s.toString() + "\n").forEach(System.out::println);
		return "Patron [ patron=" + patronCompuesto.toString() + "]";
	}
	
	public int getLargoPatron() {
		if (this.largoPatron==-1) {
			this.largoPatron = acumularNumeroPalabras(patronCompuesto, 0);
		}
		
		return this.largoPatron; 
	}
	
	private int acumularNumeroPalabras (NodoPatron p, int i) {
		i++;
		return (p.getSiguienteNodo()!=null ? acumularNumeroPalabras(p.getSiguienteNodo(), i) : i);
	}
	
	/*
	private String toStringNodoPatron(NodoPatron n) {
		return n.toString() + n.getSiguientePalabra()!=null ? toStringNodoPatron(n) : "";
	}*/
}

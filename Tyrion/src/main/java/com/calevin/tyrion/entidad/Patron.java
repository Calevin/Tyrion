package com.calevin.tyrion.entidad;

import java.util.List;
import java.util.stream.Collectors;

public class Patron {
	private List<Palabra> patron;
	private int indiceInterno = 0;
	
	public Patron(String patron) {
		super();
		int lineaPorDefecto = 0;
		this.patron = Palabra.toListaDePalabras(patron, lineaPorDefecto);
	}

	public boolean evaluar(Palabra p) {
		boolean reicidente=false;
		return evaluar(p, reicidente);
	}
	
	private boolean evaluar(Palabra palabraAevaluar, boolean reicidente) {
		boolean resultadoEvaluacion = false;
		Palabra palabraDelPatronEsperada = this.patron.get(this.indiceInterno);
		
		if (palabraDelPatronEsperada.tieneMismoValor(palabraAevaluar)) {
			this.indiceInterno++;
			resultadoEvaluacion = true;
		} else {
			this.indiceInterno=0; //Se resetea
			if (!reicidente) {
				reicidente=true;
				resultadoEvaluacion = this.evaluar(palabraAevaluar,reicidente);
			}
		}
		
		return resultadoEvaluacion;
	}
	
	public boolean patronEncontrado() {
		if (this.indiceInterno == this.patron.size()) {
			this.indiceInterno = 0;
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
		return "Patron [ patron=" + patron.stream()
					.map(s -> s.getValor().toString())
					.collect(Collectors.joining()) + "]";
	}
	
}

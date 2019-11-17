package com.calevin.tyrion.texto;

import java.util.List;
import java.util.stream.Collectors;

import com.calevin.tyrion.patron.PatronEncontrado;

public class Texto {
	private List<Palabra> palabras;
	private List<Linea> lineasDelTexto;
	
	public Texto() {
		super();
	}
	
	public Texto(List<Linea> lineasDelTexto) {
		super();
		this.lineasDelTexto = lineasDelTexto;
		this.palabras = this.listaDePalabrasToLineasDelTexto();
	}
	
	public List<Palabra> listaDePalabrasToLineasDelTexto() {

		return this.lineasDelTexto
				.stream()
				.flatMap(l -> l.getPalabras().stream())
				.collect(Collectors.toList());
	}	
	
	public List<Palabra> obtenerRangoDePalabras(PatronEncontrado patronEncontrado) {
		int indicePalabra = this.convertirPosicionEnIndicePalabra(patronEncontrado.getPosicionInicio());
		int palabras = this.cantidadDePalabrasEntreDosPosiciones(patronEncontrado);
		
		return this.palabras.subList(indicePalabra, indicePalabra+palabras);
	}
	
	public List<Palabra> obtenerRangoDePalabras(Posicion posicionInicial, int palabras) {
		
		int indicePalabra = this.convertirPosicionEnIndicePalabra(posicionInicial);
		return this.palabras.subList(indicePalabra+1, indicePalabra+palabras);
	}
	
	public int cantidadDePalabrasEntreDosPosiciones(PatronEncontrado patronEncontrado) {
		return this.cantidadDePalabrasEntreDosPosiciones(
				patronEncontrado.getPosicionFinal(),
				patronEncontrado.getPosicionInicio());
	}
	
	public int cantidadDePalabrasEntreDosPosiciones(Posicion posicion1, Posicion posicion2) {
		int cantidad = 0;
		
		if(posicion2.getLinea()>posicion1.getLinea()) {
			throw new IllegalArgumentException("posicion1 esta por delante de posicion 2");
		}
				
		if(posicion1.getLinea()==posicion2.getLinea()) {
			cantidad = posicion1.getColumna()-posicion2.getColumna();	
			return cantidad;
		} else {
			
			int lineaRestada = posicion1.getLinea(); 
			while (--lineaRestada > posicion2.getLinea()) {
				cantidad+=this.lineasDelTexto.get(lineaRestada).getPalabras().size();
			}
			
			Linea anteriorLinea = this.lineasDelTexto.get(lineaRestada);
			
			cantidad += posicion1.getColumna() + (anteriorLinea.getPalabras().size()-posicion2.getColumna());
		}
		
		return cantidad;
	}
	
	public Palabra obtenerPalabraEnPosicionSumada(Posicion posicionInicial, int posicionesAsumar) {
		Linea lineaInicial = this.lineasDelTexto.get(posicionInicial.getLinea());
		
		int largoLinea = lineaInicial.getPalabras().size();
		
		int posicionSumada = posicionInicial.getColumna()+posicionesAsumar;
		
		if (posicionSumada>=largoLinea) {
			int posicionExcedida = posicionSumada-largoLinea;
			Linea siguienteLinea = this.lineasDelTexto.get(posicionInicial.getLinea()+1);
			
			if(posicionExcedida >= siguienteLinea.getPalabras().size()) {
				posicionInicial.setLinea(posicionInicial.getLinea()+1);
				return obtenerPalabraEnPosicionSumada(posicionInicial, posicionExcedida);
			}
			
			return siguienteLinea.getPalabras().get(posicionExcedida);
		} else {
			return lineaInicial.getPalabras().get(posicionSumada);
		}
	}
	
	public Palabra obtenerPalabraEnPosicionRestada(Posicion posicionInicial, int posicionesArestar) {
		int limiteInicioDeLinea = 0;
		
		int posicionRestada = posicionInicial.getColumna()-posicionesArestar;
		
		if (posicionRestada < limiteInicioDeLinea) {
			Linea anteriorLinea = this.lineasDelTexto.get(posicionInicial.getLinea()-1);
			int posicionExcedida = anteriorLinea.getPalabras().size()+posicionRestada;
			
			if(posicionExcedida < limiteInicioDeLinea) {
				posicionInicial.setLinea(posicionInicial.getLinea()-1);
				
				return obtenerPalabraEnPosicionRestada(posicionInicial, Math.abs(posicionExcedida));
			}
			
			return anteriorLinea.getPalabras().get(posicionExcedida);
		} else {
			
			Linea lineaInicial = this.lineasDelTexto.get(posicionInicial.getLinea());
			
			return lineaInicial.getPalabras().get(posicionRestada);
		}
	}
	
	public int convertirPosicionEnIndicePalabra(Posicion posicion) {
		int linea = posicion.getLinea();
		int columna = posicion.getColumna();
		int respuesta = 0;
		
		for (int i = 0; i < linea; i++) {
			respuesta+= this.getLineasDelTexto().get(i).getPalabras().size();
		}
		
		return respuesta+columna;
	}
	
	public List<Palabra> getPalabras() {
		return palabras;
	}
	public void setPalabras(List<Palabra> palabras) {
		this.palabras = palabras;
	}
	public List<Linea> getLineasDelTexto() {
		return lineasDelTexto;
	}
	public void setLineasDelTexto(List<Linea> lineasDelTexto) {
		this.lineasDelTexto = lineasDelTexto;
	}
}

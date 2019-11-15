package com.calevin.tyrion.texto;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Texto {
	private Map<Posicion, Palabra> mapPalabras;
	private List<Palabra> palabras;
	private List<Linea> lineasDelTexto;
	
	public Texto() {
		super();
	}
	
	public Texto(List<Linea> lineasDelTexto) {
		super();
		this.lineasDelTexto = lineasDelTexto;
		this.palabras = this.listaDePalabrasToLineasDelTexto();
		this.setMapPalabras(this.mapDePalabrasToLineasDelTexto());
	}
	
	public List<Palabra> listaDePalabrasToLineasDelTexto() {

		return this.lineasDelTexto
				.stream()
				.flatMap(l -> l.getPalabras().stream())
				.collect(Collectors.toList());
	}
	
	public TreeMap<Posicion, Palabra> mapDePalabrasToLineasDelTexto() {

		return this.palabras
				.stream()
				.sorted()
				.collect(Collectors.toMap(Palabra::getPosicion, Function.identity(), (o1, o2) -> o1, TreeMap::new));		
	}
	
	public List<Palabra> obtenerRangoDePalabras(Posicion posicionInicial, int palabras) {
		//TODO
		return null;
	}
	
	public Palabra obtenerPalabraEnPosicionSumada(Posicion posicionInicial, int posicionesAsumar) {
		Linea lineaInicial = this.lineasDelTexto.get(posicionInicial.getLinea());
		Linea siguienteLinea = this.lineasDelTexto.get(posicionInicial.getLinea()+1);
		
		int largoLinea = lineaInicial.getPalabras().size();
		
		int posicionSumada = posicionInicial.getColumna()+posicionesAsumar;
		
		if (posicionSumada>=largoLinea) {
			int posicionExcedida = posicionSumada-largoLinea;
			
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
		Linea lineaInicial = this.lineasDelTexto.get(posicionInicial.getLinea());
		Linea anteriorLinea = this.lineasDelTexto.get(posicionInicial.getLinea()-1);
		
		int limiteInicioDeLinea = 0;
		
		int posicionRestada = posicionInicial.getColumna()-posicionesArestar;
		
		if (posicionRestada< limiteInicioDeLinea) {
			int posicionExcedida = anteriorLinea.getPalabras().size()+posicionRestada;
			
			if(posicionExcedida < limiteInicioDeLinea) {
				posicionInicial.setLinea(posicionInicial.getLinea()-1);
				
				return obtenerPalabraEnPosicionRestada(posicionInicial, Math.abs(posicionExcedida));
			}
			
			return anteriorLinea.getPalabras().get(posicionExcedida);
		} else {
			return lineaInicial.getPalabras().get(posicionRestada);
		}
	}
	
	public Map<Posicion, Palabra> getMapPalabras() {
		return mapPalabras;
	}

	public void setMapPalabras(Map<Posicion, Palabra> mapPalabras) {
		this.mapPalabras = mapPalabras;
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

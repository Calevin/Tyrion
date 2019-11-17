package com.calevin.tyrion.test.texto;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.calevin.tyrion.patron.Patron;
import com.calevin.tyrion.patron.PatronEncontrado;
import com.calevin.tyrion.patron.nodo.obligatorio.ObligatorioConstante;
import com.calevin.tyrion.texto.Linea;
import com.calevin.tyrion.texto.Palabra;
import com.calevin.tyrion.texto.Posicion;
import com.calevin.tyrion.texto.Texto;

public class TextoTest {

	@Test
	public void obtener_lista_palabras_desde_las_lineas_del_texto() {
		//Given:
		Texto t = new Texto();
		
		List<Linea> lineas = Arrays.asList(
				new Linea("uno dos tres", 0)
				, new Linea("cuatro cinco seis", 1)
				, new Linea("siente ocho nueve", 2)
				);
		
		List<Palabra> palabras = Arrays.asList(
				new Palabra("uno", new Posicion(0, 0))
				, new Palabra("dos", new Posicion(0, 1))
				, new Palabra("tres", new Posicion(0, 2))
				, new Palabra("cuatro", new Posicion(1, 0))
				, new Palabra("cinco", new Posicion(1, 1))
				, new Palabra("seis", new Posicion(1, 2))
				, new Palabra("siente", new Posicion(2, 0))
				, new Palabra("ocho", new Posicion(2, 1))
				, new Palabra("nueve", new Posicion(2, 2))
				);
		
		//When
		t.setLineasDelTexto(lineas);

		//Then
		assertTrue(t.listaDePalabrasToLineasDelTexto().equals(palabras));
	}
	
	@Test
	public void obtener_palabra_en_posicion_sumada_misma_linea() {
		//Given:
		Texto t = new Texto();
		
		List<Linea> lineas = Arrays.asList(
				new Linea("uno dos tres", 0)
				, new Linea("cuatro cinco seis", 1)
				, new Linea("siente ocho nueve", 2)
				);
		
		Posicion posicionInicial = new Posicion(1, 1);
		int posicionesAsumar = 1;
		
		//When
		t.setLineasDelTexto(lineas);
		
		Palabra palabraObtenida = t.obtenerPalabraEnPosicionSumada(posicionInicial, posicionesAsumar);
		
		//Then
		assertTrue(palabraObtenida.equals(new Palabra("seis", new Posicion(1, 2))));
	}
	
	@Test
	public void obtener_palabra_en_posicion_sumada_siguiente_linea() {
		//Given:
		Texto t = new Texto();
		
		List<Linea> lineas = Arrays.asList(
				new Linea("uno dos tres", 0)
				, new Linea("cuatro cinco seis", 1)
				, new Linea("siente ocho nueve", 2)
				);
		
		Posicion posicionInicial = new Posicion(1, 1);
		int posicionesAsumar = 2;
		
		//When
		t.setLineasDelTexto(lineas);
		
		Palabra palabraObtenida = t.obtenerPalabraEnPosicionSumada(posicionInicial, posicionesAsumar);
		//Then
		assertTrue(palabraObtenida.equals(new Palabra("siente", new Posicion(2, 0))));
	}
	
	@Test
	public void obtener_palabra_en_posicion_sumada_siguiente_siguiente_linea() {
		//Given:
		Texto t = new Texto();
		
		List<Linea> lineas = Arrays.asList(
				new Linea("uno", 0)
				, new Linea("dos tres", 1)
				, new Linea("cuatro cinco seis", 2)
				);
		
		Posicion posicionInicial = new Posicion(0, 0);
		int posicionesAsumar = 3;
		
		//When
		t.setLineasDelTexto(lineas);
		
		Palabra palabraObtenida = t.obtenerPalabraEnPosicionSumada(posicionInicial, posicionesAsumar);
		
		//Then
		assertTrue(palabraObtenida.equals(new Palabra("cuatro", new Posicion(2, 0))));
	}
	
	@Test
	public void obtener_palabra_en_posicion_restada_misma_linea() {
		//Given:
		Texto t = new Texto();
		
		List<Linea> lineas = Arrays.asList(
				new Linea("uno dos tres", 0)
				, new Linea("cuatro cinco seis", 1)
				, new Linea("siente ocho nueve", 2)
				);
		
		Posicion posicionInicial = new Posicion(1, 1);
		int posicionesArestar = 1;
		
		//When
		t.setLineasDelTexto(lineas);
		
		Palabra palabraObtenida = t.obtenerPalabraEnPosicionRestada(posicionInicial, posicionesArestar);
		
		//Then
		assertTrue(palabraObtenida.equals(new Palabra("cuatro", new Posicion(1, 0))));
	}
	
	@Test
	public void obtener_palabra_en_posicion_restada_anterior_linea() {
		//Given:
		Texto t = new Texto();
		
		List<Linea> lineas = Arrays.asList(
				new Linea("uno dos tres", 0)
				, new Linea("cuatro cinco seis", 1)
				, new Linea("siente ocho nueve", 2)
				);
		
		Posicion posicionInicial = new Posicion(1, 0);
		int posicionesArestar = 1;
		
		//When
		t.setLineasDelTexto(lineas);
		
		Palabra palabraObtenida = t.obtenerPalabraEnPosicionRestada(posicionInicial, posicionesArestar);
		
		//Then
		assertTrue(palabraObtenida.equals(new Palabra("tres", new Posicion(0, 2))));
	}
	
	@Test
	public void obtener_palabra_en_posicion_restada_anterior_anterior_linea() {
		//Given:
		Texto t = new Texto();
		
		List<Linea> lineas = Arrays.asList(
				new Linea("uno", 0)
				, new Linea("dos tres", 1)
				, new Linea("cuatro cinco seis", 2)
				);
		
		Posicion posicionInicial = new Posicion(2, 0);
		int posicionesArestar = 3;
		
		//When
		t.setLineasDelTexto(lineas);
		
		Palabra palabraObtenida = t.obtenerPalabraEnPosicionRestada(posicionInicial, posicionesArestar);
		
		//Then
		assertTrue(palabraObtenida.equals(new Palabra("uno", new Posicion(0, 0))));
	}
	
	@Test 
	public void convertir_posicion_en_indice_palabra_ok(){
		//Given:
		Texto t = new Texto();
		
		List<Linea> lineas = Arrays.asList(
				new Linea("uno", 0)
				, new Linea("dos tres", 1)
				, new Linea("cuatro cinco seis", 2)
				);
		
		Posicion posicionDeUno = new Posicion(0, 0);
		Posicion posicionDeTres = new Posicion(1, 1);
		Posicion posicionDeSeis = new Posicion(2, 2);
		
		//When
		t.setLineasDelTexto(lineas);
		List<Palabra> palabras = t.listaDePalabrasToLineasDelTexto();
		
		int indiceParaUno = t.convertirPosicionEnIndicePalabra(posicionDeUno); 
		int indiceParaTres = t.convertirPosicionEnIndicePalabra(posicionDeTres);
		int indiceParaSeis =  t.convertirPosicionEnIndicePalabra(posicionDeSeis);
		
		//Then
		assertTrue(indiceParaUno == 0);
		Palabra palabraEnindiceParaUno = palabras.get(indiceParaUno);
		assertTrue(palabraEnindiceParaUno.equals(new Palabra("uno", new Posicion(0, 0))));
		
		assertTrue(indiceParaTres == 2);
		Palabra palabraEnindiceParaTres = palabras.get(indiceParaTres);
		assertTrue(palabraEnindiceParaTres.equals(new Palabra("tres", new Posicion(1, 1))));
		
		assertTrue(indiceParaSeis == 5);
		Palabra palabraEnindiceParaSeis = palabras.get(indiceParaSeis);
		assertTrue(palabraEnindiceParaSeis.equals(new Palabra("seis", new Posicion(2, 2))));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void cantidad_de_palabras_entre_dos_posiciones_excepcion() {
		//Given:
		Texto t = new Texto();
		
		Posicion posicionCeroCero = new Posicion(0, 0);
		Posicion posicionUnoUno = new Posicion(1, 1);
		
		//Then
		t.cantidadDePalabrasEntreDosPosiciones(posicionCeroCero, posicionUnoUno);
	}
	
	@Test
	public void cantidad_de_palabras_entre_dos_posiciones_misma_linea() {
		//Given:
		Texto t = new Texto();
		
		Posicion posicionUnoCinco = new Posicion(1, 5);
		Posicion posicionUnoTres = new Posicion(1, 3);
		
		//When
		int resultadoResta = t.cantidadDePalabrasEntreDosPosiciones(posicionUnoCinco, posicionUnoTres);
		
		//Then
		assertTrue(resultadoResta == 2);
	}
	
	@Test
	public void cantidad_de_palabras_entre_dos_posiciones_linea_anterior() {
		//Given:
		Texto t = new Texto();
		
		List<Linea> lineas = Arrays.asList(
				new Linea("uno", 0)
				, new Linea("dos tres", 1)
				, new Linea("cuatro cinco seis", 2)
				);
		
		Posicion posicionDeSeis = new Posicion(2, 2);
		Posicion posicionDeTres = new Posicion(1, 1);
		
		//When
		t.setLineasDelTexto(lineas);
		
		int resultadoResta = t.cantidadDePalabrasEntreDosPosiciones(posicionDeSeis, posicionDeTres);
		
		//Then
		assertTrue(resultadoResta == 3);
	}
	
	@Test
	public void cantidad_de_palabras_entre_dos_posiciones_linea_anterior_anterior() {
		//Given:
		Texto t = new Texto();
		
		List<Linea> lineas = Arrays.asList(
				new Linea("uno", 0)
				, new Linea("dos tres", 1)
				, new Linea("cuatro cinco seis", 2)
				);
		
		Posicion posicionDeSeis = new Posicion(2, 2);
		Posicion posicionDeUno = new Posicion(0, 0);
		
		//When
		t.setLineasDelTexto(lineas);
		
		
		int resultadoResta = t.cantidadDePalabrasEntreDosPosiciones(posicionDeSeis, posicionDeUno);
		
		//Then
		assertTrue(resultadoResta == 5);
	}
	
	@Test
	public void obtener_rango_de_palabras_by_posicion_inicial() {
		//Given:
		Texto t = new Texto();
		
		List<Linea> lineas = Arrays.asList(
				new Linea("uno", 0)
				, new Linea("dos tres", 1)
				, new Linea("cuatro cinco seis", 2)
				);
		
		Posicion posicionInicial = new Posicion(1, 2);
		
		//When
		t.setLineasDelTexto(lineas);
		t = new Texto(lineas);
		
		List<Palabra> rangoPalabras = t.obtenerRangoDePalabras(posicionInicial, 2);
		
		//Then
		rangoPalabras.equals(Arrays.asList(
				new Linea("tres", 1)
				, new Linea("cuatro cinco seis", 2)
				));
	}
	
	@Test
	public void obtener_rango_de_palabras_by_patron_encontrado() {
		//Given:
		Texto t = new Texto();
		
		List<Linea> lineas = Arrays.asList(
				new Linea("uno", 0)
				, new Linea("dos tres", 1)
				, new Linea("cuatro cinco seis", 2)
				);
		
		Patron patronUnoDos = new ObligatorioConstante("tres")
				.encadenarSiguiente(new ObligatorioConstante("cuatro"))
				.encadenarSiguiente(new ObligatorioConstante("cinco"))
				.encadenarSiguiente(new ObligatorioConstante("seis"))
				.componerPatron();
		
		Posicion posicionInicial = new Posicion(1, 1);
		Posicion posicionFinal = new Posicion(2, 2);
		
		PatronEncontrado patronTresCuatroCincoSeisEncontrado 
			= new PatronEncontrado(patronUnoDos, posicionInicial, posicionFinal);
		
		//When
		t.setLineasDelTexto(lineas);
		t = new Texto(lineas);
		
		List<Palabra> rangoPalabras = t.obtenerRangoDePalabras(patronTresCuatroCincoSeisEncontrado);

		//Then
		rangoPalabras.equals(Arrays.asList(
				new Linea("tres", 1)
				, new Linea("cuatro cinco seis", 2)
				));		
	}
}

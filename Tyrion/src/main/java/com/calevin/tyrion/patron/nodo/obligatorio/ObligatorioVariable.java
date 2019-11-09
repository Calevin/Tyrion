package com.calevin.tyrion.patron.nodo.obligatorio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.calevin.tyrion.patron.nodo.NodoPatron;
import com.calevin.tyrion.texto.Palabra;

public class ObligatorioVariable extends NodoPatron {
	Pattern pattern;
	
	public ObligatorioVariable(String pattern) {
		super(pattern);
		this.pattern = Pattern.compile(pattern);
	}
	
	@Override
	public boolean tieneMismoValor(Palabra other) {
		Matcher matcher = this.pattern.matcher(other.getValor());
		return matcher.find();
	}
}

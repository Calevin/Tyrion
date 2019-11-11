package com.calevin.tyrion.patron.nodo.opcional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.calevin.tyrion.patron.nodo.Opcional;
import com.calevin.tyrion.texto.Palabra;

public class OpcionalVariable extends Opcional{
	Pattern pattern;
	
	public OpcionalVariable(String pattern) {
		super(pattern);
		this.pattern = Pattern.compile(pattern);
	}
	
	@Override
	public boolean tieneMismoValor(Palabra other) {
		Matcher matcher = this.pattern.matcher(other.getValor());
		if (matcher.find()) {
			return true;
		} else {
			return this.getSiguienteNodo().tieneMismoValor(other);
		}
	}
}

package com.calevin.tyrion.entidad;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NodoObligatorioVariable extends NodoPatron {
	Pattern pattern;
		
	public NodoObligatorioVariable(String pattern) {
		super(pattern);
		this.pattern = Pattern.compile(pattern);
	}
	
	@Override
	public boolean tieneMismoValor(Palabra other) {
		Matcher matcher = this.pattern.matcher(other.getValor());
		return matcher.find();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pattern == null) ? 0 : pattern.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NodoObligatorioVariable other = (NodoObligatorioVariable) obj;
		if (pattern == null) {
			if (other.pattern != null)
				return false;
		} else if (!pattern.equals(other.pattern))
			return false;
		return true;
	}

}

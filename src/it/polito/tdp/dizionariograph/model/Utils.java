package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.List;

public class Utils {
	
	public static List<String> getParoleUguali(List<String> parole, String parola, int numLettere) {
		List<String> paroleUguali = new ArrayList<>();
		for(String s : parole) {
			if(getSingolaLettera(parola, s))
				paroleUguali.add(s);
		}
		
		return paroleUguali;
	}

	private static boolean getSingolaLettera(String prima, String seconda) {
		int distanza = 1;
		
		if(prima.length() != seconda.length())
			throw new RuntimeException("Le due parole hanno una lunghezza diversa.");
		for(int i=0; i<prima.length(); i++) {
			if(prima.charAt(i) != seconda.charAt(i))
				distanza --;
		}
		
		if(distanza == 0)
			return true;
		else
			return false;
	}

}

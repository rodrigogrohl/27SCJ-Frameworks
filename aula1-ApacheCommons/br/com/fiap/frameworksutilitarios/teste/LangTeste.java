package br.com.fiap.frameworksutilitarios.teste;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang.time.DateUtils;

public class LangTeste {

	public static void main(String[] args) throws Exception {
		
		// array
		String carros[] = new String[] { "Gol", "Corsa", "Montana", "Civic", "Sandero", "Polo" };
		ArrayUtils.indexOf(carros, "Corsa");
		System.out.println("Procurando Civic: \nEncontrado na posição " + ArrayUtils.indexOf(carros, "Civic") + " do array.");
		System.out.println("Carros: " + ArrayUtils.toString(carros));

		// data
		System.out.println("Data com arredondamento de hora: " + DateUtils.round(new Date(), Calendar.HOUR));
		System.out.println("Data com dia truncado: " + DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));

		
		//String Utils
		String string = "Teste do String Utils.";

		System.out.println("caracter \"e\" countMatches: " + StringUtils.countMatches(string, "e"));
		System.out.println("chop: " + StringUtils.chop(string));
		System.out.println("1251 isNumeric: " + StringUtils.isNumeric("1251"));
		System.out.println("aOI is Alpha: " + StringUtils.isAlpha("aOI"));

		System.out.println("10 leftPad 5: " + StringUtils.leftPad("10", 5, "0"));
		System.out.println("10 rightPad 3: " + StringUtils.rightPad("11", 3, "0"));
		
		//validate
		//String nome = null;
		String nome = "teste";
		Validate.notNull(nome, "Nome não pode ser nulo.");
	}
}

package br.com.fiap.frameworksutilitarios.teste;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.validator.UrlValidator;
import org.apache.commons.validator.routines.BigDecimalValidator;
import org.apache.commons.validator.routines.CurrencyValidator;
import org.apache.commons.validator.routines.DateValidator;

public class Validator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// MOEDA
		BigDecimalValidator validator = CurrencyValidator.getInstance();
		BigDecimal fooAmount = validator.validate("12,500.00", Locale.US);
		System.out.println(fooAmount);
		
		
		// DATA
		DateValidator dateValidator = DateValidator.getInstance();
		Date data = new Date("2010/04/11");
		// verifica igualdade do mes
		int compare = dateValidator.compareMonths(data, new Date(), null);
		System.out.println(compare);

		// verifica igualdade do mes
		compare = dateValidator.compareQuarters(data, new Date(), null);
		System.out.println(compare);

		// verifica igualdade do ano
		compare = dateValidator.compareYears(data, new Date(), null);
		System.out.println(compare);

		
		// URL
		String[] schemes = { "http", "https" };
		UrlValidator urlValidator = new UrlValidator(schemes);
		if (urlValidator.isValid("ftp://fiap.com.br/")) {
			System.out.println("url valida");
		} else {
			System.out.println("url invalida");
		}

		if (urlValidator.isValid("http://wwws.fiap.com.br/")) {
			System.out.println("url valida");
		} else {
			System.out.println("url invalida");
		}
	}
}

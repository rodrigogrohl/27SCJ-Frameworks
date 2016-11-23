package br.com.fiap.frameworksutilitarios.teste;

import org.apache.commons.math.util.MathUtils;

public class MathTeste {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("Fatorial: " + MathUtils.factorial(10));
		System.out.println("Arredondamento: " + MathUtils.round(100.5222, 2));
		System.out.println("Sinal: " + MathUtils.sign(-3));
	}
}

package br.com.fiap.frameworksutilitarios.teste;

import org.apache.commons.configuration.PropertiesConfiguration;

public class ConfigurationTeste {

	public static void main(String[] args) throws Exception {

		PropertiesConfiguration config = new PropertiesConfiguration(
				"properties/fiap.properties");

		System.out.println("Telefone: " + config.getString("telefone"));
		System.out.println("Endereco: " + config.getList("endereco").get(0));
		System.out.println("Numero: " + config.getList("endereco").get(1));
	}
}

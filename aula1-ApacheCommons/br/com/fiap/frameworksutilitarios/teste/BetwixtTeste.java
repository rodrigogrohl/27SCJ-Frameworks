package br.com.fiap.frameworksutilitarios.teste;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.betwixt.io.BeanWriter;

import br.com.fiap.frameworksutilitarios.common.Colaborador;
import br.com.fiap.frameworksutilitarios.common.Empresa;

public class BetwixtTeste {

	public static void main(String[] args) throws Exception {
		Empresa empresa = new Empresa();
		empresa.setCodigo(9);
		empresa.setNome("FIAP");
		
		Colaborador colaborador = new Colaborador();
		colaborador.setCodigo(120);
		colaborador.setNome("Marcio da Silva");
		colaborador.setEmpresa(empresa);
		
		Colaborador colaborador2 = new Colaborador();
		colaborador2.setCodigo(130);
		colaborador2.setNome("Marcos Souza");

		List<Colaborador> colaboradores = new ArrayList();
		colaboradores.add(colaborador);
		colaboradores.add(colaborador2);
		//empresa.setColaboradores(colaboradores);
		
		
		// geracao de XML
		StringWriter outputWriter = new StringWriter();
		BeanWriter beanWriter = new BeanWriter(outputWriter);
		beanWriter.enablePrettyPrint();
		beanWriter.write("colaborador", colaborador);
		//beanWriter.write("empresa", empresa);
		System.out.println(outputWriter.toString());
	}

}

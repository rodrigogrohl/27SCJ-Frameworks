package br.com.fiap.frameworksutilitarios.teste;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;

import br.com.fiap.frameworksutilitarios.common.Colaborador;
import br.com.fiap.frameworksutilitarios.common.Empresa;

public class BeanUtilsTeste {

	public static void main(String[] args) throws Exception {
		Empresa empresa = new Empresa();
		empresa.setCodigo(9);
		empresa.setNome("FIAP");

		Colaborador colaborador1 = new Colaborador();
		colaborador1.setCodigo(120);
		colaborador1.setNome("Marcio da Silva Dias");
		colaborador1.setEmpresa(empresa);

		Colaborador colaborador2 = new Colaborador();
		//Colaborador colaborador2 = colaborador1;
		BeanUtils.copyProperties(colaborador2, colaborador1);
		System.out.println("Teste BeanUtils.copyProperties: \n"
				+ colaborador2.getNome());

		colaborador1.setNome("Novo nome");

		System.out.println("\n\nTeste BeanUtils.describe:");
		Map map = BeanUtils.describe(colaborador2);
		Set atributos = map.keySet();
		for (Iterator iter = atributos.iterator(); iter.hasNext();) {
			String element = (String) iter.next();
			System.out.println(element + " --> " + map.get(element));
		}

	}

}

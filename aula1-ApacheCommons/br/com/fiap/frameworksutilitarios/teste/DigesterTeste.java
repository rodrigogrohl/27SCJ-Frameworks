package br.com.fiap.frameworksutilitarios.teste;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.digester.Digester;

import br.com.fiap.frameworksutilitarios.common.Autor;
import br.com.fiap.frameworksutilitarios.common.Livro;


public class DigesterTeste {

	public static void main(String[] args) throws Exception {

		Digester digester = new Digester();
		digester.addObjectCreate("livro", Livro.class);
		digester.addSetProperties("livro","isbn","isbn");
		digester.addBeanPropertySetter("livro/titulo","titulo");
		digester.addBeanPropertySetter("livro/datapublicacao","dataPublicacao");
		digester.addObjectCreate("livro/autores", ArrayList.class);
		digester.addObjectCreate("livro/autores/autor",Autor.class);
		digester.addSetNext("livro/autores/autor","add");
		digester.addSetProperties("livro/autores/autor","id","id");
		digester.addBeanPropertySetter("livro/autores/autor","nome");
		digester.addSetNext("livro/autores","setAutores");
		
		Livro livro = (Livro) digester.parse(new File("livro.xml"));
		
		System.out.println(livro.getIsbn());
		System.out.println(livro.getDataPublicacao());
		System.out.println(livro.getTitulo());
		System.out.println(livro.getAutores().size());
		Iterator iterator = livro.getAutores().iterator();
		while (iterator.hasNext()) {
			Autor autor = (Autor) iterator.next();
			System.out.println(autor.getId() + " " + autor.getNome());
		}
	}
}

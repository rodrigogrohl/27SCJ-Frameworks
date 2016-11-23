package br.com.fiap.frameworksutilitarios.teste;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.com.fiap.frameworksutilitarios.common.Autor;
import br.com.fiap.frameworksutilitarios.common.Livro;

public class DOMParserTeste {

	public static void main(String[] args) throws Exception {
		
		Livro livro = new Livro();
		
		DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		//Document document = documentBuilder.parse(new InputSource(new StringReader("<xml version...")));
		Document document = documentBuilder.parse("livro.xml");

		Element root = document.getDocumentElement();
		livro.setIsbn(root.getAttribute("isbn"));
		
		NodeList titulos = document.getElementsByTagName("titulo");
		livro.setTitulo(titulos.item(0).getFirstChild().getNodeValue());
		
		NodeList dataPublicacao = document.getElementsByTagName("datapublicacao");
		livro.setDataPublicacao(dataPublicacao.item(0).getFirstChild().getNodeValue());
		
		List autores = new ArrayList();
		for (int contador = 0; contador < document.getElementsByTagName("autor").getLength(); contador++) {
			Autor autor = new Autor();
			autor.setNome(document.getElementsByTagName("autor").item(contador).getFirstChild().getNodeValue());
			autor.setId(document.getElementsByTagName("autor").item(contador).getAttributes().item(0).getNodeValue());
			autores.add(autor);
		}
		livro.setAutores(autores);
		
		
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

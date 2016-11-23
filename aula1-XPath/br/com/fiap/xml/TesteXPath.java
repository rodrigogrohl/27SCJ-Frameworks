package br.com.fiap.xml;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jaxen.BaseXPath;
import org.jaxen.dom.DOMXPath;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TesteXPath {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		Element element = null;
		BaseXPath base = null;

		DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		Document document = documentBuilder.parse(new File("notasFiscais.xml"));

		//elemento root - report
		element = document.getDocumentElement();
		
		base = new DOMXPath("data/file/cliente/nome");
		System.out.println(base.stringValueOf(element));

		//contagem ocorrencias da tag nome
		base = new DOMXPath("count(/report/data/file/cliente/nome)");
		System.out.println(base.numberValueOf(document).intValue());
		//contagem ocorrencias da tag nome
		base = new DOMXPath("data/file/cliente/nome");
		System.out.println(((List)base.evaluate(element)).size());

		base = new DOMXPath("data/file/notasfiscaisservico/nota[1]/valor");
		System.out.println("Valor Nota 00012: " + base.stringValueOf(element));
		
		base = new DOMXPath("count(/report/data/file/notasfiscaisservico/nota)");
		Number n = base.numberValueOf(document);
		for (int i = 1; i <= n.intValue(); i++) {
			base = new DOMXPath("data/file/notasfiscaisservico/nota["+ i + "]/valor");
			System.out.println("Valor nota: " + base.stringValueOf(element));
		}

	}

}

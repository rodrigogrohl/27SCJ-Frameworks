package br.com.fiap.frameworksutilitarios.common;

import java.util.Collection;

public class Livro {

	private String isbn;
	private String titulo;
	private Collection autores;
	private String dataPublicacao;
	
	public Collection getAutores() {
		return autores;
	}
	public void setAutores(Collection autores) {
		this.autores = autores;
	}
	public String getDataPublicacao() {
		return dataPublicacao;
	}
	public void setDataPublicacao(String dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	
}

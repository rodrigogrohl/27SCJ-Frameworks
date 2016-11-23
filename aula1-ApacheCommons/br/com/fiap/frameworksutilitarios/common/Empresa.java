package br.com.fiap.frameworksutilitarios.common;

import java.util.List;

public class Empresa {
	private int codigo;
	private String nome;
	private List<Colaborador> colaboradores;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Colaborador> getColaboradores() {
		return colaboradores;
	}
	public void setColaboradores(List<Colaborador> colaboradores) {
		this.colaboradores = colaboradores;
	}

	
}

package com.altec.bsbr.app.blank.dto;

public class Pessoa {


	private String nrRegistro;
	private String nome;
	private String sbNome;
	private String idade;
	private String nrCpf;
	private String dtNasc;
	private String nrTel;
	private String nrCel;
	private String email;
	
	
	public Pessoa(String nrRegistro
					, String nome
					, String sbNome
					, String idade
					, String nrCpf
					, String dtNasc
					, String nrTel
					, String nrCel
					, String email) {
		
		this.nrRegistro = nrRegistro;
		this.nome = nome;
		this.sbNome = sbNome;
		this.idade = idade;
		this.nrCpf = nrCpf;
		this.dtNasc = dtNasc;
		this.nrTel = nrTel;
		this.nrCel = nrCel;
		this.email = email;
		
	}
	
	public String getNrRegistro() {
		return nrRegistro;
	}
	public void setNrRegistro(String nrRegistro) {
		this.nrRegistro = nrRegistro;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSbNome() {
		return sbNome;
	}
	public void setSbNome(String sbNome) {
		this.sbNome = sbNome;
	}
	public String getIdade() {
		return idade;
	}
	public void setIdade(String idade) {
		this.idade = idade;
	}
	public String getNrCpf() {
		return nrCpf;
	}
	public void setNrCpf(String nrCpf) {
		this.nrCpf = nrCpf;
	}
	public String getDtNasc() {
		return dtNasc;
	}
	public void setDtNasc(String dtNasc) {
		this.dtNasc = dtNasc;
	}
	public String getNrTel() {
		return nrTel;
	}
	public void setNrTel(String nrTel) {
		this.nrTel = nrTel;
	}
	public String getNrCel() {
		return nrCel;
	}
	public void setNrCel(String nrCel) {
		this.nrCel = nrCel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}

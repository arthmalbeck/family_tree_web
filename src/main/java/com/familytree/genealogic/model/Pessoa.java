package com.familytree.genealogic.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonIgnoreProperties()
public class Pessoa implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne()
	private Pessoa pai;
	
	@ManyToOne()
	private Pessoa mae;
	
	@ManyToMany	
	private List<Pessoa> irmaos;

	private String nome;
	
	@OneToOne
	private Certidao certidaoNascimento;
	
	@OneToOne
	private Certidao certidaoObito;
	
	private String profissao;
	private String escolaridade;
	private String titulosEspeciais;
	private String historiaFamilia;
	private String origemNome;
	private String origemSobrenome;
	private String outrasInformacoes;
	private String sexo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Pessoa getPai() {
		return pai;
	}
	public void setPai(Pessoa pai) {
		this.pai = pai;
	}
	public Pessoa getMae() {
		return mae;
	}
	public void setMae(Pessoa mae) {
		this.mae = mae;
	}
	public List<Pessoa> getIrmaos() {
		return irmaos;
	}
	public void setIrmaos(List<Pessoa> irmaos) {
		this.irmaos = irmaos;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Certidao getCertidaoNascimento() {
		return certidaoNascimento;
	}
	public void setCertidaoNascimento(Certidao certidaoNascimento) {
		this.certidaoNascimento = certidaoNascimento;
	}
	public Certidao getCertidaoObito() {
		return certidaoObito;
	}
	public void setCertidaoObito(Certidao certidaoObito) {
		this.certidaoObito = certidaoObito;
	}
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	public String getEscolaridade() {
		return escolaridade;
	}
	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}
	public String getTitulosEspeciais() {
		return titulosEspeciais;
	}
	public void setTitulosEspeciais(String titulosEspeciais) {
		this.titulosEspeciais = titulosEspeciais;
	}
	public String getHistoriaFamilia() {
		return historiaFamilia;
	}
	public void setHistoriaFamilia(String historiaFamilia) {
		this.historiaFamilia = historiaFamilia;
	}
	public String getOrigemNome() {
		return origemNome;
	}
	public void setOrigemNome(String origemNome) {
		this.origemNome = origemNome;
	}
	public String getOrigemSobrenome() {
		return origemSobrenome;
	}
	public void setOrigemSobrenome(String origemSobrenome) {
		this.origemSobrenome = origemSobrenome;
	}
	public String getOutrasInformacoes() {
		return outrasInformacoes;
	}
	public void setOutrasInformacoes(String outrasInformacoes) {
		this.outrasInformacoes = outrasInformacoes;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	
	
}

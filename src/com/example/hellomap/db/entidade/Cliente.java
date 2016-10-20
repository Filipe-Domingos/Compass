package com.example.hellomap.db.entidade;

import java.io.Serializable;
import java.sql.Date;

public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	private int _id;	
	private int codigo;
	private String nome;
	private Double latitude;
	private Double longitude;
	private String rua;
	private String numero;
	private String bairro;
	private String cidade;
	private Date dAlter;
	private Date dCadas;

	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
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
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public Date getdAlter() {
		return dAlter;
	}
	public void setdAlter(Date dAlter) {
		this.dAlter = dAlter;
	}
	public Date getdCadas() {
		return dCadas;
	}
	public void setdCadas(Date dCadas) {
		this.dCadas = dCadas;
	}	
}

package com.example.hellomap.db.entidade;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class Rota implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int _id;	
	private int codigo;
	private Date dataInicial;
	private Date dataFinal;
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();	
	
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
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public ArrayList<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}
}


package com.example.hellomap.db.entidade;

import java.io.Serializable;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hellomap.db.dao.Cliente_Dao;
import com.example.hellomap.db.util.Statics;


public class ListaCliente implements Serializable {
	private static final long serialVersionUID = 2L;
	private ArrayList<Cliente> lista = new ArrayList<Cliente>();
	private Context context;
	
	public ListaCliente(Context context) {
		this.context = context;
		load();
	}

	public ArrayList<Cliente> getListaClientes() {		
		return lista;
	}
	
	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.lista = listaClientes;
	}

	public boolean persisteLista(){
		Cliente_Dao cliDao = new Cliente_Dao(context);

		for (Cliente cliente: lista) {
			cliDao.insere(cliente);
		}
	
		return true;
	}
	
	private void load(){
		Cliente cliente = new Cliente();
		cliente.setCodigo(1);
		cliente.setNome("EVERTO FÁBIO DA SILVA MACHADO");
		cliente.setLatitude(-12.23129391);
		cliente.setLongitude(-3.0001122);
		
		this.lista.add(cliente);
	}
	
	public ArrayList<Cliente> getClientesAtualizados() {
		SQLiteDatabase db;
		Cliente cliente = new Cliente();
		
		
		db = Statics.getReadableDatabase(context);
		Cursor alteracao = db.rawQuery("select codigo,latitude,longitude from cliente where dalter>dcadas", null);
		
	    while (alteracao.moveToNext()) {
	    	cliente.setCodigo(0);
	    }
		
		return null;
	}
}

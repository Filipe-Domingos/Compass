package com.example.hellomap.db.entidade;

import java.io.Serializable;
import java.util.ArrayList;

import android.content.Context;

import com.example.hellomap.db.dao.RotaDao;

public class ListaRota implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Rota> lista = new ArrayList<Rota>();
	private Context context;
	
	public ListaRota(Context context) {
		this.context = context;
	}
	
	public ArrayList<Rota> getLista() {
		return lista;
	}
	
	public void setLista(ArrayList<Rota> lista) {
		this.lista = lista;
	}
	
	public boolean persisteLista(){
		RotaDao rotaDao = new RotaDao(context);
		
		for (Rota rota: lista) {
			rotaDao.insere(rota);
		}
		
		return true;
	}
}

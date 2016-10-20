package com.example.hellomap.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hellomap.db.entidade.Cliente;
import com.example.hellomap.db.util.Statics;

public class Cliente_Dao {	
	private SQLiteDatabase dataBase;
	
	public Cliente_Dao(Context context){
		dataBase = Statics.getWritableDatabase(context);
	}
	
	public boolean insere(Cliente cliente) {
		String sql;
		sql = "select 1 from cliente where codigo="+cliente.getCodigo();
		
		Cursor registros = dataBase.rawQuery(sql, null);
		
		if (registros.getCount()>0)
			return false;
		
		ContentValues registro = new ContentValues();

		registro.put("codigo", cliente.getCodigo());
		registro.put("nome", cliente.getNome());
		registro.put("latitude", cliente.getLatitude());
		registro.put("longitude", cliente.getLongitude());
		registro.put("rua", cliente.getRua());
		registro.put("numero", cliente.getNumero());
		registro.put("bairro", cliente.getBairro());
		registro.put("cidade", cliente.getCidade());
		
		long result = dataBase.insert("cliente", null, registro);
		
		return (result>0);
	}

	public boolean atualizaLocalizacao(Cliente cliente) {
		ContentValues registro = new ContentValues();

		registro.put("latitude", cliente.getLatitude());
		registro.put("longitude", cliente.getLongitude());

		int result = dataBase.update("cliente", registro, "_id="+cliente.get_id(), null);

		return result > 0;
	}
}

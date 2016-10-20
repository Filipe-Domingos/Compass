package com.example.hellomap.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hellomap.db.entidade.Cliente;
import com.example.hellomap.db.entidade.Rota;
import com.example.hellomap.db.util.Statics;

public class RotaDao {
	private SQLiteDatabase dataBase;

	public RotaDao(Context context) {
		dataBase = Statics.getWritableDatabase(context);
	}
	
	public boolean insere(Rota rota) {
		String sql;
		sql = "select 1 from rota where codigo="+rota.getCodigo();
		
		Cursor registros = dataBase.rawQuery(sql, null);
		
		if (registros.getCount()>0)
			return false;
		
		ContentValues registro = new ContentValues();
		
		registro.put("codigo", rota.getCodigo());
		registro.put("datainicial", Statics.getDateParser().format(rota.getDataInicial()));
		registro.put("datafinal", Statics.getDateParser().format(rota.getDataFinal()));
		
		long result = dataBase.insert("rota", null, registro);
		
		for (Cliente cliente: rota.getClientes()) {
			registro.clear();
			registro.put("rota", rota.getCodigo());
			registro.put("codigo", cliente.getCodigo());
			
			result = dataBase.insert("clienterota", null, registro);
		}
		
		return (result>0);		
	}

}

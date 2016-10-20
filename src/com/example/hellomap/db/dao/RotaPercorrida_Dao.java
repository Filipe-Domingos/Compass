package com.example.hellomap.db.dao;

import com.example.hellomap.db.util.Statics;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class RotaPercorrida_Dao {
	private int id;
	private String data;
	private String hora;
	private Double latitude;
	private Double longitude;
	private SQLiteDatabase dataBase;
	
	public RotaPercorrida_Dao(Context context) {
		dataBase = Statics.getWritableDatabase(context);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
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
	
	public boolean insere(){
		ContentValues registro = new ContentValues();
		
		registro.put("data", data);
		registro.put("hora", hora);
		registro.put("latitude", latitude);
		registro.put("longitude", longitude);
		
		long result = dataBase.insert("rotapercorida", null, registro);

		return (result>0);		
	}
}

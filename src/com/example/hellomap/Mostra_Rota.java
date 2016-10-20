package com.example.hellomap;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.hellomap.geo.GeoCoordinate;
import com.example.hellomap.db.factory.DbHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Mostra_Rota extends FragmentActivity {
	private GoogleMap mMap;
	private int _id;
	private SQLiteDatabase db;
	private String dataInicial;
	private String dataFinal;
	private GeoCoordinate ponto;	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		MarkerOptions marker;
		LatLng posicao;
		String endereco;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostra_rota);

		mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapRota)).getMap();

		Intent intent = getIntent();
		_id = intent.getIntExtra("_id", 0);
	
		db = new DbHelper(this).getReadableDatabase();
		String sql = "select cliente.latitude,cliente.longitude,cliente.rua,";
		sql += "cliente.numero,cliente.nome,rota.datainicial,rota.datafinal FROM rota JOIN ";
		sql += " clienterota ON rota.codigo=clienterota.rota JOIN ";
		sql += "cliente on cliente.codigo=clienterota.codigo ";
		sql += "where clienterota.rota="+_id;
		
		//Cursor rota = db.rawQuery("select * from clienterota JOIN cliente on cliente.codigo=clienterota.codigo where clienterota.rota="+_id, null);
		Cursor rota = db.rawQuery(sql, null);
		
		posicao = null;

		while (rota.moveToNext()) {
			dataInicial = rota.getString(5);
			dataFinal = rota.getString(6);

			posicao = new LatLng(rota.getDouble(rota.getColumnIndex("latitude")), rota.getDouble(rota.getColumnIndex("longitude")));
			endereco = rota.getString(rota.getColumnIndex("rua"))+","+rota.getString(rota.getColumnIndex("numero"));
			
			ponto = new GeoCoordinate(posicao.latitude, posicao.longitude);

			marker = new MarkerOptions();
			
			if (passouAqui(ponto))
				marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
			
			marker.title("Nome: "+rota.getString(rota.getColumnIndex("nome")));
			marker.snippet("Endereço: "+endereco);
			marker.position(posicao);

			mMap.addMarker(marker);		
		}

		if (rota.getCount()>0)
			mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posicao, 10));
	}
	
	public boolean passouAqui(GeoCoordinate ponto) {
		String sql = "SELECT latitude,longitude from rotapercorida where ";
		sql += " data between '";
		sql += dataInicial+"' and '"+dataFinal+"' and ";
		sql += " abs(latitude-("+ponto.getLatitude()+"))<0.5";		
		sql += " and abs(longitude-("+ponto.getLongitude()+"))<0.5";
		
		Cursor percorrido = db.rawQuery(sql, null);
		
		while (percorrido.moveToNext()) {
			double distancia = new GeoCoordinate(percorrido.getDouble(0), percorrido.getDouble(1)).distanceInKm(ponto);
			distancia = distancia*1000.0; //Converque quilometros em metros
			distancia = Math.round(distancia); //Aredonda para aparar as arestas (centimetros)
			if (distancia<100) 
				return true;
		}
		
		return false;
	}
	
}

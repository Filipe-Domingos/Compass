package com.example.hellomap;

import com.example.hellomap.db.factory.DbHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.support.v4.app.FragmentActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;


public class Cliente_Mapa_Activity extends FragmentActivity {
	private GoogleMap mMap;
	private int _id;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		LatLng posicao;
		String endereco;
		MarkerOptions marker;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cliente_mapa);

		mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapCliente)).getMap();
		
		Intent intent = getIntent();
		_id = intent.getIntExtra("_id", 0);
		
		db = new DbHelper(this).getReadableDatabase();
		Cursor cliente = db.rawQuery("select * from cliente where cliente._id="+_id, null);

		if (cliente.moveToFirst()) {
			posicao = new LatLng(cliente.getDouble(cliente.getColumnIndex("latitude")), cliente.getDouble(cliente.getColumnIndex("longitude")));
			mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posicao, 10));

			endereco = cliente.getString(cliente.getColumnIndex("rua"))+","+cliente.getString(cliente.getColumnIndex("numero"));

			marker = new MarkerOptions();
			marker.title("Nome: "+cliente.getString(cliente.getColumnIndex("nome")));
			marker.snippet("Endereço: "+endereco);
			marker.position(posicao);

			mMap.addMarker(marker);			
		}
	}
}

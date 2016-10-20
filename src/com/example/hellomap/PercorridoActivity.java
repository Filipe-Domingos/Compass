package com.example.hellomap;

import java.util.ArrayList;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.hellomap.db.factory.DbHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class PercorridoActivity extends FragmentActivity {
	private GoogleMap mMap;
	private SQLiteDatabase db;
	private String data;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		MarkerOptions marker;
		LatLng posicao;
		String hora;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_percorrido);

		mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapPercorrido)).getMap();

		Intent intent = getIntent();
		data = intent.getStringExtra("data");

		db = new DbHelper(this).getReadableDatabase();
		Cursor rota = db.rawQuery("select * from rotapercorida where data='"+data+"' order by data,hora", null);

		if (rota.moveToFirst()) {

			posicao = new LatLng(rota.getDouble(rota.getColumnIndex("latitude")), rota.getDouble(rota.getColumnIndex("longitude")));
			mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posicao, 15));

			hora = rota.getString(rota.getColumnIndex("hora"));
			
			PolylineOptions polylineOptions = new PolylineOptions();
			ArrayList<LatLng> points = new ArrayList<LatLng>();
			points.add(posicao);
			
            polylineOptions.color(Color.RED);
            polylineOptions.width(5);

			marker = new MarkerOptions();
			marker.title(rota.getString(rota.getColumnIndex("data")));
			marker.snippet(hora);
			marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
			marker.anchor(0.5f, 0.5f);
			marker.position(posicao);

			mMap.addMarker(marker);            

			while (rota.moveToNext()) {
				posicao = new LatLng(rota.getDouble(rota.getColumnIndex("latitude")), rota.getDouble(rota.getColumnIndex("longitude")));
				hora = rota.getString(rota.getColumnIndex("hora"));
				points.add(posicao);
			
				marker = new MarkerOptions();
				marker.title(rota.getString(rota.getColumnIndex("data")));
				marker.snippet(hora);
				marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
				marker.position(posicao);

				//mMap.addMarker(marker);
			}
			
			mMap.addMarker(marker);
			polylineOptions.addAll(points);
			mMap.addPolyline(polylineOptions);
		}
	}
}

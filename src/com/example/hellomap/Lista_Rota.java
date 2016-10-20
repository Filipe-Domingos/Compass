package com.example.hellomap;

import com.example.hellomap.db.factory.DbHelper;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class Lista_Rota extends ListActivity {
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//testeInicial();

		db = new DbHelper(this).getReadableDatabase();		
		montaListView();

		registerForContextMenu(this.getListView());
	}		

	private void montaListView() {
		Cursor registros = db.rawQuery("select _id,datainicial,datafinal from rota", null);

		String[] campos = {"_id","datainicial", "datafinal"};
		int camposTela[] = {R.id.tvCodigo,R.id.tvDtaInicio,R.id.tvDtaFinal};

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.lista_rotas_campos, registros, campos, camposTela); 

		this.setListAdapter(adapter);		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(this, Mostra_Rota.class);

		int _id = Integer.parseInt(((TextView) v.findViewById(R.id.tvCodigo)).getText().toString());

		intent.putExtra("_id", _id);
		startActivity(intent);

		super.onListItemClick(l, v, position, id);
	}
/*
	private void testeInicial(){
		try {
			Geocoder geocoder = new Geocoder(this);
			
			List<Address> geoResults = geocoder.getFromLocationName("Rua Helena J. Zonta, 26, Marrecas, Paraná", 1);
			
			if (geoResults.size()>0) {
				Address addr = geoResults.get(0);
//				myLocation.setLatitude(addr.getLatitude());
//				myLocation.setLongitude(addr.getLongitude());
				Toast.makeText(this, String.valueOf(addr.getLatitude()), Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
*/
}

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
import android.widget.Toast;

public class Lista_Cliente extends ListActivity {
	SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		//db = Statics.getReadableDatabase(getApplicationContext());
		db = new DbHelper(getApplicationContext()).getReadableDatabase();
		montaListView();
		
		registerForContextMenu(this.getListView());
	}		
	
	private void montaListView() {
		Cursor registros = db.rawQuery("select * from cliente", null);
		
		String[] campos = {"_id","nome","cidade","bairro", "rua", "numero"};
		int camposTela[] = {R.id.tvCodigo,R.id.tvDescricao,R.id.tvLabel03,R.id.tvLabel04,R.id.tvLabel05,R.id.tvLabel06};
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.lista_clientes_campos, registros, campos, camposTela); 
		
		this.setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(this, Cliente_Mapa_Activity.class);
		
		int _id = Integer.parseInt(((TextView) v.findViewById(R.id.tvCodigo)).getText().toString());

		intent.putExtra("_id", _id);
		startActivity(intent);
	}	
}

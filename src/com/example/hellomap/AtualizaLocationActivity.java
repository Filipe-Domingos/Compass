package com.example.hellomap;

import java.util.List;

import com.example.hellomap.db.factory.DbHelper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;

public class AtualizaLocationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_atualiza_location);
	}

	public void btnAtualizaOnClick(View v) {
		atualizaTodoMundo();
		return;
	}

	private void atualizaTodoMundo() {
		try {
			SQLiteDatabase db = new DbHelper(getApplicationContext()).getReadableDatabase();
			Cursor registros = db.rawQuery("select rua,numero,bairro,cidade,codigo from cliente", null);
			Geocoder geocoder = new Geocoder(this);
			String endereco;
			
			ProgressDialog progress = ProgressDialog.show(this, "dialog title",
				    "dialog message", true);

			while (registros.moveToNext()) {
				endereco = registros.getString(0)+", "+
						registros.getString(1)+", "+
						registros.getString(2)+", "+
						registros.getString(3);
				
				List<Address> geoResults = geocoder.getFromLocationName(endereco, 1);

				if (geoResults.size()>0) {
					Address addr = geoResults.get(0);
					ContentValues values = new ContentValues();
					
					values.put("latitude", addr.getLatitude());
					values.put("longitude", addr.getLongitude());
					
					db.update("cliente", values, "codigo="+registros.getString(4), null);
				}
				
			}
			
			progress.dismiss();
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}		
		
		finish();
	}
}

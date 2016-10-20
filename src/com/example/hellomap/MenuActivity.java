package com.example.hellomap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MenuActivity extends Activity {	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		//Statics.InicializaContext(getApplicationContext());
	}

	public void btnMapaOnClick(View v) {
		if (verificaGps()) {
			chamaMapa();
		}
	}
	
	public void btnListClienOnClick(View v) {
		Intent intent = new Intent(this, Lista_Cliente.class);
		startActivity(intent);
	}
	
	public void btnListRotaOnClick(View v) {
		Intent intent = new Intent(this, Lista_Rota.class);
		startActivity(intent);
	}
	
	public void btnSincroOnClick(View v) {
		Intent intent = new Intent(this, SinconizaActivity.class);
		startActivity(intent);
	}
	
	public void btnPercorridoOnClick(View v) {
		Intent intent = new Intent(this, Lista_RotaPercorrida.class);		
		startActivity(intent);
	}
	
	public void btnAtualizaOnClick(View v) {
		Intent intent = new Intent(this, AtualizaLocationActivity.class);		
		startActivity(intent);
	}	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		return super.onOptionsItemSelected(item);
	}
	
	private boolean verificaGps() {
		boolean result = true;
		String provider = Settings.Secure.getString(getContentResolver(),   
				Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

		if ((provider==null) || (provider.length()==0) || (provider.equals("network"))) {
			Intent intent  = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			startActivityForResult(intent, 1);
			result = false;
		}
		
		return result;
	}	
	
	private void chamaMapa() {
	    Intent intent = new Intent(this, Mapa_Activity.class);
	    startActivity(intent);		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode==1) {
			chamaMapa();
		}
	}	
}

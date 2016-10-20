package com.example.hellomap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import com.google.gson.Gson;

public class SinconizaActivity extends Activity {
	private CheckBox chkCliente;
	private CheckBox chkRotas;
	private CheckBox chkEnviaLoc;
	private CheckBox chkEnviaRot;
	ProgressDialog progress;
	Gson g = new Gson();
	String json = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_importacao);

		chkCliente = (CheckBox) findViewById(R.id.chkCliente);
		chkRotas = (CheckBox) findViewById(R.id.chkRotas);
		chkEnviaLoc = (CheckBox) findViewById(R.id.chkEnviaLoc);
		chkEnviaRot = (CheckBox) findViewById(R.id.chkEnviaRot);
	}

	public void btnSincroOnClick(View v) {
		if (chkCliente.isChecked()) {
			SincClientes sinc = new SincClientes(this, progress);
			sinc.execute();

			chkCliente.setChecked(false);
		}

		if (chkRotas.isChecked()) {
			SincRotas sinc = new SincRotas(this, progress);
			sinc.execute();

			chkRotas.setChecked(false);
		}

		if (chkEnviaLoc.isChecked()) {

			chkEnviaLoc.setChecked(false);
		}

		if (chkEnviaRot.isChecked()) {

			chkEnviaLoc.setChecked(false);
		}
	}
}
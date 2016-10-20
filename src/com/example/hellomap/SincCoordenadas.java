package com.example.hellomap;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class SincCoordenadas extends AsyncTask<String, Integer, String>  {
	
	Context context;
	ProgressDialog progresso;

	public SincCoordenadas(Context context,ProgressDialog progresso) {
		this.context = context;			
		this.progresso = progresso; 
	}	

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}

}

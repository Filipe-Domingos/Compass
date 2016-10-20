package com.example.hellomap;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import com.example.hellomap.db.entidade.ListaCliente;
import com.example.hellomap.db.util.Statics;
import com.google.gson.Gson;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

class SincClientes extends AsyncTask<String, Integer, String> {
	Context context;
	ProgressDialog progresso;

	public SincClientes(Context context,ProgressDialog progresso) {
		this.context = context;			
		this.progresso = progresso; 
	}

	@Override
	protected String doInBackground(String... params) {
		try {
			Socket servidor = new Socket(Statics.getHost(), Statics.getPort()); //Pode gerar exceções;
			DataInputStream in = new DataInputStream(servidor.getInputStream());
			DataOutputStream out = new DataOutputStream(servidor.getOutputStream());
			
			out.writeUTF("GETCLIEN");
			
			out.flush();
			
			final String resposta = in.readUTF(); //Esta linha é bloqueante
			
			in.close();
			out.close();
			servidor.close();
			
			return resposta;
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	protected void onPreExecute() {
		progresso = new ProgressDialog(context);
		progresso.setMessage("Acessando Socket");
		progresso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progresso.setMax(6);			
		progresso.setCancelable(false);
		progresso.show();
	}

	@Override
	protected void onPostExecute(String result) {			
		Gson g = new Gson();
	 	ListaCliente lista = g.fromJson(result, ListaCliente.class);
	 	
	 	lista.persisteLista();
		
		progresso.dismiss();
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		progresso.setProgress(values[0]);
	}
}
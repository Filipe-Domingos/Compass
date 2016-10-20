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

public class Lista_RotaPercorrida extends ListActivity {
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		db = new DbHelper(this).getReadableDatabase();		
		montaListView();
		
//		db.execSQL("update cliente set longitude=-53.0694609957 where codigo=708");
		
/*		String[] rota2 = alimentaRota2();
		
		for (String s:rota2) {			
			db.execSQL(s);
		}*/	
		
		registerForContextMenu(this.getListView());
	}
	
	private void montaListView() {
		Cursor registros = db.rawQuery("select max(_id) as _id,data,min(hora) as inicio,max(hora) as final from rotapercorida group by data", null);
		
		String[] campos = {"_id","data","inicio","final"};
		int camposTela[] = {R.id.tvCodigo,R.id.tvData,R.id.tvHoraFinal,R.id.tvHoraInicio};
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.lista_percorrido_campos, registros, campos, camposTela); 
		
		this.setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(this, PercorridoActivity.class);
		
		String data = ((TextView) v.findViewById(R.id.tvData)).getText().toString();

		intent.putExtra("data", data);
		startActivity(intent);
	}
	
	private String[] alimentaRota2() {
		String[] sql = new String[7];

		//"update rota set datainicial='2014-11-05' where codigo=4"
		//"update cliente set longitude=-53.0694609957 where codigo=708"
		
		//-53.0694609957
		sql[0] = "INSERT INTO rota(codigo,datainicial,datafinal) values (4,'2014-11-10', '2014-11-15')";
		
		sql[1] = " INSERT INTO cliente (codigo,nome,latitude,longitude, rua, bairro, numero, cidade) values (709,'EVERTO FABIO S MACHADO', -26.0735585354, -53.0594609957,'OTAVIANO TEIXEIRA SANTOS ','ALVORADA','140','FRANCISCO BELTRAO')";                                
		sql[2] = " INSERT INTO cliente (codigo,nome,latitude,longitude, rua, bairro, numero, cidade) values (708,'JAQUELINE BAGGIO', -26.0735585354, -53.0594609957,'RUA SAO PAULO ','CENTRO','1200','FRANCISCO BELTRAO')";                                 
		sql[3] = " INSERT INTO cliente (codigo,nome,latitude,longitude, rua, bairro, numero, cidade) values (707,'JECA TATU', -26.0785585354,-53.0594609957,'RUA PORTO ALEGRE ','CENTO','40','FRANCISCO BELTRAO')";
		
		sql[4] = "INSERT INTO clienterota(codigo,rota) values (709, 4)";
		sql[5] = "INSERT INTO clienterota(codigo,rota) values (708, 4)";
		sql[6] = "INSERT INTO clienterota(codigo,rota) values (707, 4)";		
		
		return sql;
	}
}

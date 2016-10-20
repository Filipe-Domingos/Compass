package com.example.hellomap.db.factory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
	private static final String NOME_BD = "compass.db";
    private static final int VERSAO_BD = 1;    
    //private final Context contexto;    
	
	public DbHelper(Context context) {
		super(context, NOME_BD, null, VERSAO_BD);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub	
	}
}

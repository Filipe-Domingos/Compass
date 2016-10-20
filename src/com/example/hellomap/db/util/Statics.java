package com.example.hellomap.db.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.hellomap.db.factory.DbHelper;

public class Statics {
	private static DbHelper conexao;
	private static final String HOST = "192.168.0.100";
	private static final int PORT = 4444;	
	private static SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-ddTHH:mm:ss.sss");
	//protected static Context context;

	//public static void InicializaContext(Context ctx) {
	     //setContext(ctx);
	//}

//	private static void setContext(Context ctx) {
	     //context = ctx;
	//}

	//public static Context getAppContext() {
	     //return Statics.context;
	//}
	
//	public Context getContext() {
		//return context;
	//}

	public static SQLiteDatabase getWritableDatabase(Context context) {
		if (conexao==null) {
			conexao = new DbHelper(context);
		}
		
		return conexao.getWritableDatabase();
	}
	
	public static SQLiteDatabase getReadableDatabase(Context context) {
		if (conexao==null) {
			conexao = new DbHelper(context);
		}
		
		return conexao.getReadableDatabase();		
	}
	
	public static String getHost(){
		return HOST;
	}
	
	public static int getPort() {
		return PORT;
	}
	
	public static SimpleDateFormat getDateParser() {
		return dateParser;
	}
	
	public static String getData() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 		
		return sdf.format(new Date());		
	}
	
	public static String getHora() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); 		
		return sdf.format(new Date());
	}	
}

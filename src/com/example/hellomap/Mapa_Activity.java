package com.example.hellomap;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.hellomap.db.factory.DbHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class Mapa_Activity extends FragmentActivity implements LocationListener {
	private GoogleMap mMap;
	private static LocationManager lm;
	private Location location;
	private SQLiteDatabase dataBase;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_minha_localiz);

		if (mMap != null) {
			return;
		}

		dataBase = new DbHelper(getApplicationContext()).getWritableDatabase();

		mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

		if (mMap == null) {
			return;
		}

		mMap.setMyLocationEnabled(true);

		lm = (LocationManager) this.getSystemService( Context.LOCATION_SERVICE );		
		lm.requestLocationUpdates( LocationManager.GPS_PROVIDER, 2, 20, (LocationListener) this );

		getLocation();
		if (location!=null)
			mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 10));
	}

	@Override
	protected void onResume() {
		super.onResume();
		//setUpMapIfNeeded();
	}

	@Override
	public void onLocationChanged(Location location) {
		if (location.getAccuracy()<20) {

			mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 15));

			ContentValues registro = new ContentValues();

			registro.put("data", getData());
			registro.put("hora", getHora());
			registro.put("latitude", location.getLatitude());
			registro.put("longitude", location.getLongitude());

			//Grava a localização no banco de dados
			dataBase.insert("rotapercorida", null, registro);		
		}
		Toast.makeText(this, "Latitude: "+Double.toString(location.getLatitude())+" longitude: "+Double.toString(location.getLongitude())+" Acuracy: "+Double.toString(location.getAccuracy()), Toast.LENGTH_LONG).show();		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	public static String getData() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 		
		return sdf.format(new Date());		
	}

	public static String getHora() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); 		
		return sdf.format(new Date());
	}	

	public Location getLocation() {
		boolean isGPSEnabled;
		boolean isNetworkEnabled;
		LocationManager locationManager;

		try {
			locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

			// getting GPS status
			isGPSEnabled = locationManager
					.isProviderEnabled(LocationManager.GPS_PROVIDER);

			// getting network status
			isNetworkEnabled = locationManager
					.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

			if (!isGPSEnabled && !isNetworkEnabled) {
				// no network provider is enabled
			} else {
				if (isNetworkEnabled) {
					locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,20,20, this);

					if (locationManager != null) 
						location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
				}

				if (isGPSEnabled) {
					if (location == null) {
						locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,20,20, this);

						if (locationManager != null) 
							location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return location;
	}
}

package com.example.android6969.listatimes;

import android.location.Geocoder;

import com.google.android.gms.identity.intents.Address;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

/**
 * Created by android6969 on 02/09/17.
 */

public class MapaFragment extends SupportMapFragment {
    @Override
    public void onResume() {
        super.onResume();
        AlunoDAO dao = new AlunoDAO(getContext());
        final List<Aluno> alunos = dao.getLista();
        dao.close();

        getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                for (Aluno a: alunos) {
                    String rua = a.getEndereco();
                    Geocoder geo = new Geocoder(getContext());
                    try {
                        List<android.location.Address> ruas = geo.getFromLocationName(rua, 1);
                        android.location.Address address = ruas.get(0);
                        Double lat = address.getLatitude();
                        Double log = address.getLongitude();
                        LatLng latLNG = new LatLng(lat, log);
                        MarkerOptions marker = new MarkerOptions();
                        marker.position(latLNG);
                        marker.title(a.getNome());
                        marker.snippet(a.getEndereco());
                        googleMap.addMarker(marker);
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLNG, 14));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }
}

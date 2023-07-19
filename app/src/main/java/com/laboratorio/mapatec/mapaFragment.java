package com.laboratorio.mapatec;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.data.geojson.GeoJsonFeature;
import com.google.maps.android.data.geojson.GeoJsonLayer;
import com.google.maps.android.data.geojson.GeoJsonPolygon;
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class mapaFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;
    private EditText searchEditText;

    private GeoJsonLayer geoJsonLayer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mapa, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext());
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    googleMap.addMarker(new MarkerOptions().position(latLng).title("Mi posición"));
                }
            }
        };

        searchEditText = rootView.findViewById(R.id.search_edittext);
        searchEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String searchText = searchEditText.getText().toString().trim();
                if (!searchText.isEmpty()) {
                    searchLocation(searchText);
                    return true;
                }
            }
            return false;
        });

        Button highlightButton = rootView.findViewById(R.id.highlight_button);
        highlightButton.setOnClickListener(v -> {
            String searchText = searchEditText.getText().toString().trim();
            if (!searchText.isEmpty()) {
                highlightLocation(searchText);
            }
        });

        Button centerButton = rootView.findViewById(R.id.center_button);
        centerButton.setOnClickListener(v -> {
            centerMapOnCurrentLocation();
        });

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        try {
            InputStream inputStream = getResources().openRawResource(R.raw.geofences);
            geoJsonLayer = new GeoJsonLayer(googleMap, new JSONObject(convertStreamToString(inputStream)));

            // Personaliza el estilo de tus polígonos si lo deseas
            GeoJsonPolygonStyle polygonStyle = geoJsonLayer.getDefaultPolygonStyle();
            polygonStyle.setStrokeColor(Color.RED);
            polygonStyle.setStrokeWidth(2);

            geoJsonLayer.addLayerToMap();

            // Ajusta la vista del mapa para mostrar el objeto GeoJsonLayer
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (GeoJsonFeature feature : geoJsonLayer.getFeatures()) {
                if (feature.getGeometry() instanceof GeoJsonPolygon) {
                    GeoJsonPolygon polygon = (GeoJsonPolygon) feature.getGeometry();
                    for (LatLng latLng : polygon.getOuterBoundaryCoordinates()) {
                        builder.include(latLng);
                    }
                }
            }
            LatLngBounds bounds = builder.build();
            googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));

            // Puedes realizar otras operaciones con los objetos GeoJsonFeature del layer si lo deseas
            for (GeoJsonFeature feature : geoJsonLayer.getFeatures()) {
                // Realiza operaciones con los features
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(requireContext(), "Error al cargar el archivo GeoJSON", Toast.LENGTH_SHORT).show();
        } catch (Exception ecp) {
            ecp.printStackTrace();
        }
    }

    private String convertStreamToString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }

    private void searchLocation(String locationName) {
        GeoJsonFeature resultFeature = null;
        for (GeoJsonFeature feature : geoJsonLayer.getFeatures()) {
            String description = feature.getProperty("description");
            if (description != null && description.equalsIgnoreCase(locationName)) {
                resultFeature = feature;
                break;
            }
        }

        if (resultFeature != null) {
            geoJsonLayer.removeLayerFromMap();

            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            if (resultFeature.getGeometry() instanceof GeoJsonPolygon) {
                GeoJsonPolygon polygon = (GeoJsonPolygon) resultFeature.getGeometry();
                for (LatLng latLng : polygon.getOuterBoundaryCoordinates()) {
                    builder.include(latLng);
                }
            }
            LatLngBounds bounds = builder.build();
            googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));

            CircleOptions circleOptions = new CircleOptions()
                    .center(bounds.getCenter())
                    .radius(100) // Cambiar el valor del radio según tus necesidades
                    .strokeColor(Color.BLUE)
                    .fillColor(Color.parseColor("#220000FF")); // Color semi-transparente para el relleno
            googleMap.addCircle(circleOptions);
        } else {
            Toast.makeText(requireContext(), "No se encontró la ubicación", Toast.LENGTH_SHORT).show();
        }
    }



    private void highlightLocation(String locationName) {
        GeoJsonFeature resultFeature = null;
        for (GeoJsonFeature feature : geoJsonLayer.getFeatures()) {
            String description = feature.getProperty("description");
            if (description != null && description.equalsIgnoreCase(locationName)) {
                resultFeature = feature;
                break;
            }
        }

        if (resultFeature != null) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            if (resultFeature.getGeometry() instanceof GeoJsonPolygon) {
                GeoJsonPolygon polygon = (GeoJsonPolygon) resultFeature.getGeometry();
                for (LatLng latLng : polygon.getOuterBoundaryCoordinates()) {
                    builder.include(latLng);
                }
            }
            LatLngBounds bounds = builder.build();
            googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));

            CircleOptions circleOptions = new CircleOptions()
                    .center(bounds.getCenter())
                    .radius(100) // Cambiar el valor del radio según tus necesidades
                    .strokeColor(Color.BLUE)
                    .fillColor(Color.parseColor("#220000FF")); // Color semi-transparente para el relleno
            googleMap.addCircle(circleOptions);
        } else {
            Toast.makeText(requireContext(), "No se encontró la ubicación", Toast.LENGTH_SHORT).show();
        }
    }

    private void centerMapOnCurrentLocation() {
        if (googleMap != null) {
            if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
                    if (location != null) {
                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f));
                    }
                });
            }
        }
    }
}

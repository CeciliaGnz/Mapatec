package com.laboratorio.mapatec;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;

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
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
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

public class MapaFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private FusedLocationProviderClient fusedLocationClient;
    private EditText searchEditText;
    private GeoJsonLayer geoJsonLayer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mapa, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext());

        searchEditText = rootView.findViewById(R.id.search_edittext);
        searchEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) { // Escucha la acción IME_ACTION_SEARCH
                String searchText = searchEditText.getText().toString().trim();
                if (!searchText.isEmpty()) {
                    getLocationFromAddress(searchText);
                    return true;
                }
            }
            return false;
        });

        Button centerButton = rootView.findViewById(R.id.center_button);
        centerButton.setOnClickListener(v -> centerMapOnCurrentLocation());

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        try {
            InputStream inputStream = getResources().openRawResource(R.raw.mapat);
            geoJsonLayer = new GeoJsonLayer(googleMap, new JSONObject(convertStreamToString(inputStream)));

            // Personalize the polygon style if desired
            GeoJsonPolygonStyle polygonStyle = geoJsonLayer.getDefaultPolygonStyle();
            polygonStyle.setStrokeColor(Color.RED);
            polygonStyle.setStrokeWidth(2);

            // Set a click listener for markers
            googleMap.setOnMarkerClickListener(marker -> {
                String markerTitle = marker.getTitle();
                if (markerTitle != null) {
                    // Show a toast with the marker's title
                    Toast.makeText(requireContext(), markerTitle, Toast.LENGTH_SHORT).show();
                }
                return true;
            });

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

    private void getLocationFromAddress(String query) {
        // Convert the query to lowercase for case-insensitive comparison
        String searchQuery = query.toLowerCase();

        boolean foundMatchingLocation = false;
        for (GeoJsonFeature feature : geoJsonLayer.getFeatures()) {
            // Get the "valor" property from the GeoJSON feature
            String featureValor = feature.getProperty("valor");

            // Check if the "valor" property matches the search query
            if (featureValor != null && featureValor.toLowerCase().equals(searchQuery)) {
                foundMatchingLocation = true;

                LatLng featureLatLng = (LatLng) feature.getGeometry().getGeometryObject();
                String featureDescription = feature.getProperty("description");

                googleMap.clear(); // Clear any previous marker
                Marker marker = googleMap.addMarker(new MarkerOptions()
                        .position(featureLatLng)
                        .title(featureDescription)
                        .snippet("Valor: " + featureValor));

                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(featureLatLng, 15f));
                break; // Exit the loop when a match is found
            }
        }

        if (!foundMatchingLocation) {
            // If no match is found, show a marker at the location from the address
            Geocoder geocoder = new Geocoder(requireContext());
            List<Address> addressList;
            try {
                addressList = geocoder.getFromLocationName(query, 1);
                if (addressList != null && !addressList.isEmpty()) {
                    Address resultAddress = addressList.get(0);
                    double latitude = resultAddress.getLatitude();
                    double longitude = resultAddress.getLongitude();
                    LatLng latLng = new LatLng(latitude, longitude);

                    googleMap.clear(); // Clear any previous marker
                    Marker marker = googleMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Ubicación"));

                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f));
                } else {
                    Toast.makeText(requireContext(), "No se encontró la ubicación", Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(requireContext(), "Error al obtener la ubicación", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void centerMapOnCurrentLocation() {
        if (googleMap != null) {
            if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
                    if (location != null) {
                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                        Marker marker = googleMap.addMarker(new MarkerOptions().position(latLng).title("Mi posición"));
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f));
                    }
                });
            }
        }
    }
}

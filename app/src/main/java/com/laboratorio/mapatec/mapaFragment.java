package com.laboratorio.mapatec;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.indooratlas.android.sdk.IALocation;
import com.indooratlas.android.sdk.IALocationListener;
import com.indooratlas.android.sdk.IALocationManager;
import com.indooratlas.android.sdk.IALocationRequest;
import com.indooratlas.android.sdk.IARegion;
import com.indooratlas.android.sdk.IARegion.Listener;

public class mapaFragment extends Fragment implements IALocationListener, Listener {

    private IALocationManager mLocationManager;
    private TextView mLog;
    private ScrollView mScrollView;
    private long mRequestStartTime;

    private static final String FASTEST_INTERVAL = "fastestInterval";
    private static final String SHORTEST_DISPLACEMENT = "shortestDisplacement";

    private long mFastestInterval = -1L;
    private float mShortestDisplacement = -1f;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mFastestInterval = savedInstanceState.getLong(FASTEST_INTERVAL);
            mShortestDisplacement = savedInstanceState.getFloat(SHORTEST_DISPLACEMENT);
        }

        // Inicializar el administrador de ubicación de IndoorAtlas
        mLocationManager = IALocationManager.create(requireContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mapa, container, false);

        // Configurar vistas y otras inicializaciones

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mLocationManager.requestLocationUpdates(IALocationRequest.create(), this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mLocationManager.removeLocationUpdates(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationManager.destroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(FASTEST_INTERVAL, mFastestInterval);
        outState.putFloat(SHORTEST_DISPLACEMENT, mShortestDisplacement);
    }

    // Implementar métodos de IALocationListener y Listener según sea necesario

    @Override
    public void onLocationChanged(IALocation iaLocation) {
        // Lógica para manejar las actualizaciones de ubicación
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // Lógica para manejar los cambios de estado de ubicación
    }

    @Override
    public void onEnterRegion(IARegion iaRegion) {
        // Lógica para manejar la entrada a una región de IndoorAtlas
    }

    @Override
    public void onExitRegion(IARegion iaRegion) {
        // Lógica para manejar la salida de una región de IndoorAtlas
    }
}

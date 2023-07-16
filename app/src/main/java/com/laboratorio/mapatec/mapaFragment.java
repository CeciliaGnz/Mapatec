package com.laboratorio.mapatec;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import com.indooratlas.android.sdk.IALocationManager;
import com.indooratlas.android.sdk.IALocationListener;
import com.indooratlas.android.sdk.IALocation;
import com.indooratlas.android.sdk.IALocationRequest;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link mapaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class mapaFragment extends Fragment {

    private IALocationManager mIALocationManager;

    public mapaFragment() {
        // Required empty public constructor
    }

    public static mapaFragment newInstance() {
        return new mapaFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Configurar cualquier parámetro o argumento necesario

        // Inicializar IALocationManager
        mIALocationManager = IALocationManager.create(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mapa, container, false);
        // Inflar el diseño del fragmento y realizar cualquier configuración adicional

        // Configurar IALocationListener para recibir actualizaciones de ubicación
        IALocationListener mIALocationListener = new IALocationListener() {
            @Override
            public void onLocationChanged(IALocation iaLocation) {
                // Lógica para manejar las actualizaciones de ubicación
                double latitude = iaLocation.getLatitude();
                double longitude = iaLocation.getLongitude();
                // ...
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                // Lógica para manejar los cambios de estado de ubicación
            }
        };

        // Solicitar actualizaciones de ubicación
        mIALocationManager.requestLocationUpdates(IALocationRequest.create(), mIALocationListener);

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Detener las actualizaciones de ubicación y liberar recursos
        mIALocationManager.destroy();
    }
}
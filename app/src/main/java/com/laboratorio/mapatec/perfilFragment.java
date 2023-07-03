package com.laboratorio.mapatec;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.View.OnClickListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link perfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class perfilFragment extends Fragment {
    Button btn_back, btn_login;
    EditText ced, pass;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public perfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment perfilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static perfilFragment newInstance(String param1, String param2) {
        perfilFragment fragment = new perfilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        return view;}

   //METODO PARA EL BOTTON DE REGRESAR QUE TE LLEVE A UN FRAGMENTO EN ESPECIFICO EN ESTA CASO INICIO
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        btn_back = view.findViewById(R.id.button_back);
        ced=view.findViewById(R.id.ced_adm);
        pass=view.findViewById(R.id.pass_adm);
        btn_login = view.findViewById(R.id.login);

        btn_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager =getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);

                transaction.replace(R.id.frame_layout, inicioFragment.newInstance("", ""));

                transaction.commit();

                BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);

                // Establecer el ítem seleccionado correspondiente al inicio
                bottomNavigationView.setSelectedItemId(R.id.inicio);
            }
        });

        //bd
        btn_login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String cedula = ced.getText().toString();
                String password = pass.getText().toString();

                if (validateLogin(cedula, password)) {
                    showResetPasswordDialog();
                } else {
                    Toast.makeText(getActivity(), "Credenciales inválidas", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean validateLogin(String cedula, String password) {
        // Obtener una instancia de la base de datos
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        // Obtener el nombre de las columnas
        String cedulaColumn = databaseHelper.getCedulaColumnName();
        String passwordColumn = databaseHelper.getPasswordColumnName();
        String tableName = databaseHelper.getTableName();

        // Definir las columnas que deseas obtener de la consulta
        String[] projection = {passwordColumn};

        // Definir la cláusula WHERE para filtrar por cédula
        String selection = cedulaColumn + " = ?";
        String[] selectionArgs = {cedula};

        // Realizar la consulta a la base de datos
        Cursor cursor = db.query(
                tableName,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        boolean isValid = false;

        if (cursor.moveToFirst()) {
            // Obtener la contraseña almacenada en la base de datos
            String storedPassword = cursor.getString(cursor.getColumnIndexOrThrow(passwordColumn));

            // Comparar la contraseña ingresada con la contraseña almacenada
            if (password.equals(storedPassword)) {
                isValid = true;
            }
        }

        // Cerrar el cursor y la conexión con la base de datos
        cursor.close();
        db.close();

        return isValid;
    }


    private void showResetPasswordDialog() {
        // Implementar la lógica para mostrar el diálogo de restablecimiento de contraseña aquí
    }

}
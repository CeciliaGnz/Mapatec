package com.laboratorio.mapatec;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.laboratorio.mapatec.Evento;

import java.util.ArrayList;

public class EventoListAdapter extends ArrayAdapter<Evento> {
    private Activity context;
    private ArrayList<Evento> listaEventos;

    public EventoListAdapter(Activity context, ArrayList<Evento> listaEventos) {
        super(context, R.layout.item_event, listaEventos);
        this.context = context;
        this.listaEventos = listaEventos;
    }

    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {
        View vi = view;
        if (vi == null) {
            LayoutInflater li = context.getLayoutInflater();
            vi = li.inflate(R.layout.item_event, null);
        }

        Evento evento = listaEventos.get(posicion);

        TextView textViewEventoInfo = vi.findViewById(R.id.textViewEventoInfo);

        // Concatenar los detalles del evento en una sola cadena de texto
        String eventoInfo = "Título: " + evento.getTitulo() + "\n"
                + "Fecha y hora: " + evento.getFechaHora() + "\n"
                + "Lugar: " + evento.getLugar() + "\n"
                + "Descripción: " + evento.getDescripcion();

        // Establecer la cadena de texto en el TextView
        textViewEventoInfo.setText(eventoInfo);

        return vi;
    }
}


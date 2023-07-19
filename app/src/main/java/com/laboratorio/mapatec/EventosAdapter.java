package com.laboratorio.mapatec;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EventosAdapter extends RecyclerView.Adapter<EventosAdapter.EventoViewHolder> {

    private List<Evento> eventosList;

    public EventosAdapter(List<Evento> eventosList) {
        this.eventosList = eventosList;
    }

    @NonNull
    @Override
    public EventoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_evento, parent, false);
        return new EventoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventoViewHolder holder, int position) {
        Evento evento = eventosList.get(position);
        holder.txtIdEvento.setText("ID del evento: " + evento.getId());
        holder.txtTitulo.setText("Título: " + evento.getTitulo());
        // Agrega otros campos de información del evento si es necesario
    }

    @Override
    public int getItemCount() {
        return eventosList.size();
    }

    static class EventoViewHolder extends RecyclerView.ViewHolder {
        TextView txtIdEvento;
        TextView txtTitulo;
        // Agrega otros TextViews para mostrar más información del evento si es necesario

        EventoViewHolder(View itemView) {
            super(itemView);
            txtIdEvento = itemView.findViewById(R.id.view_id);
            txtTitulo = itemView.findViewById(R.id.view_title);
            // Inicializa otros TextViews si es necesario
        }
    }


}

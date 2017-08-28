package com.example.diegoapp.login;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * Created by diego on 27/08/2017.
 */

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.EventoViewHolder> implements ItemClickListener{

    private List<Evento> items;
    private Context context;

    public EventoAdapter(List<Evento> items, Context context){

        this.items= items;
        this.context=context;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public EventoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list, viewGroup, false);
        return new EventoViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(EventoViewHolder holder, int position) {
        holder.evento.setText(items.get(position).getNombreEvento());
        holder.fecha.setText(items.get(position).getFechaEvento());
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    public static class EventoViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        // Campos respectivos de un item
        public TextView evento;
        public TextView fecha;

        public ItemClickListener listener;

        public EventoViewHolder(View v, ItemClickListener listener) {
            super(v);
            evento = (TextView) v.findViewById(R.id.Evento);
            fecha = (TextView) v.findViewById(R.id.fecha);

            this.listener = listener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAdapterPosition());
        }
    }
}


interface ItemClickListener {
    void onItemClick(View view, int position);
}

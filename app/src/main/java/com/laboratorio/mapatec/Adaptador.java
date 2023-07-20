package com.laboratorio.mapatec;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    ArrayList<Evento> lista;
    daoEvento dao;
    Evento v;
    Activity a;

    public Adaptador(ArrayList<Evento> lista, Activity a, daoEvento dao){
        this.lista=lista;
        this.a=a;
        this.dao=dao;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Evento getItem(int i) {
        v=lista.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}

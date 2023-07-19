package com.laboratorio.mapatec;

public class Evento {
    private String id;
    private String titulo;

    public Evento(String id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}

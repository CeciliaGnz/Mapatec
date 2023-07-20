package com.laboratorio.mapatec;

public class Evento {
    int id;
    String Titulo;
    String FechaHora;
    String Lugar;
    String Descripcion;


    public Evento(String titulo, String fechaHora, String lugar, String descripcion) {
        Titulo = titulo;
        FechaHora = fechaHora;
        Lugar = lugar;
        Descripcion = descripcion;
    }

    public Evento(int id, String titulo, String fechaHora, String lugar, String descripcion) {
        this.id = id;
        Titulo = titulo;
        FechaHora = fechaHora;
        Lugar = lugar;
        Descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getFechaHora() {
        return FechaHora;
    }

    public void setFechaHora(String fechaHora) {
        FechaHora = fechaHora;
    }

    public String getLugar() {
        return Lugar;
    }

    public void setLugar(String lugar) {
        Lugar = lugar;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}

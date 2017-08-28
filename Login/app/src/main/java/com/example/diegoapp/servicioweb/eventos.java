package com.example.diegoapp.servicioweb;

/**
 * Created by diego on 28/08/2017.
 */

public class eventos {
    int idEvento;
    String FechaEvento;
    String nombreEvento;

    public eventos(int idEvento, String fechaEvento, String nombreEvento) {
        this.idEvento = idEvento;
        this.FechaEvento = fechaEvento;
        this.nombreEvento = nombreEvento;
    }

    public eventos(String fechaEvento, String nombreEvento) {
        FechaEvento = fechaEvento;
        this.nombreEvento = nombreEvento;
    }

    public eventos(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public eventos() {
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getFechaEvento() {
        return FechaEvento;
    }

    public void setFechaEvento(String fechaEvento) {
        FechaEvento = fechaEvento;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public String toString() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }
}
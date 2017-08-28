package com.example.diegoapp.login;

import java.sql.Date;

/**
 * Created by diego on 26/08/2017.
 */

public class Evento {
    int idEvento;
    String FechaEvento;
    String nombreEvento;

    public Evento(int idEvento, String fechaEvento, String nombreEvento) {
        this.idEvento = idEvento;
        FechaEvento = fechaEvento;
        this.nombreEvento = nombreEvento;
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

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }
}

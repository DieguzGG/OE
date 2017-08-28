package com.example.diegoapp.login;

/**
 * Created by diego on 26/08/2017.
 */

public class Asistente {
    int idAsistente;
    int Matricula;
    String Nombre;
    String aPaterno;
    String aMaterno;
    int PE;
    String qrcode;
    String qrfile;
    String correo;

    public int getIdAsistente() {
        return idAsistente;
    }

    public void setIdAsistente(int idAsistente) {
        this.idAsistente = idAsistente;
    }

    public int getMatricula() {
        return Matricula;
    }

    public void setMatricula(int matricula) {
        Matricula = matricula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getaPaterno() {
        return aPaterno;
    }

    public void setaPaterno(String aPaterno) {
        this.aPaterno = aPaterno;
    }

    public String getaMaterno() {
        return aMaterno;
    }

    public void setaMaterno(String aMaterno) {
        this.aMaterno = aMaterno;
    }

    public int getPE() {
        return PE;
    }

    public void setPE(int PE) {
        this.PE = PE;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getQrfile() {
        return qrfile;
    }

    public void setQrfile(String qrfile) {
        this.qrfile = qrfile;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Asistente(int idAsistente, int matricula, String nombre, String aPaterno, String aMaterno, int PE, String qrcode, String qrfile, String correo) {

        this.idAsistente = idAsistente;
        Matricula = matricula;
        Nombre = nombre;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.PE = PE;
        this.qrcode = qrcode;
        this.qrfile = qrfile;
        this.correo = correo;
    }
}

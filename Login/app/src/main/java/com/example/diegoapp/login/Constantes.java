package com.example.diegoapp.login;

/**
 * Created by diego on 26/08/2017.
 */
/**
 * Clase que contiene los códigos usados en "I Wish" para
 * mantener la integridad en las interacciones entre actividades
 * y fragmentos
 */
public class Constantes {
    /**
     * Transición Home -> Detalle
     */
    public static final int CODIGO_DETALLE = 100;

    /**
     * Transición Detalle -> Actualización
     */
    public static final int CODIGO_ACTUALIZACION = 101;

    /**
     * Puerto que utilizas para la conexión.
     * Dejalo en blanco si no has configurado esta carácteristica.
     */
    private static final String PUERTO_HOST = "";

    /**
     * Dirección IP de genymotion o AVD
     */
    private static final String IP = "http://orientacioneducativa.000webhostapp.com";
    /**
     * URLs del Web Service
     */
    public static final String LOGIN = IP + PUERTO_HOST + "/obtenerAdmin.php";
    public static final String ASISTENTE = IP + PUERTO_HOST + "/obtenerAsistente.php";
    public static final String ASISTENCIA = IP + PUERTO_HOST + "insertarAsistencia.php";
    public static final String EVENTOS = IP + PUERTO_HOST + "obtenerEventos.php";

    /**
     * Clave para el valor extra que representa al identificador de una meta
     */
    public static final String EXTRA_ID = "IDEXTRA";

}
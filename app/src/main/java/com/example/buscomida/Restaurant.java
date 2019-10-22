package com.example.buscomida;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Restaurant {

    @SerializedName("NombreRestaurante")
    private String nombreRestaurante;
    @SerializedName("LatRestaurante")
    private String latRestaurante;
    @SerializedName("LogRestaurante")
    private String logRestaurante;
    @SerializedName("EspecialidadRestaurante")
    private String especialidadRestaurante;
    @SerializedName("NombreCategoria")
    private String nombreCategoria;
    @SerializedName("DireccionRestaurante")
    private String direccionRestaurante;

    public Restaurant(String nombreRestaurante, String latRestaurante, String logRestaurante, String especialidadRestaurante, String nombreCategoria, String direccionRestaurante) {
        this.nombreRestaurante = nombreRestaurante;
        this.latRestaurante = latRestaurante;
        this.logRestaurante = logRestaurante;
        this.especialidadRestaurante = especialidadRestaurante;
        this.nombreCategoria = nombreCategoria;
        this.direccionRestaurante = direccionRestaurante;
    }

    public String getNombreRestaurante() {
        return nombreRestaurante;
    }

    public void setNombreRestaurante(String nombreRestaurante) {
        this.nombreRestaurante = nombreRestaurante;
    }

    public String getLatRestaurante() {
        return latRestaurante;
    }

    public void setLatRestaurante(String latRestaurante) {
        this.latRestaurante = latRestaurante;
    }

    public String getLogRestaurante() {
        return logRestaurante;
    }

    public void setLogRestaurante(String logRestaurante) {
        this.logRestaurante = logRestaurante;
    }

    public String getEspecialidadRestaurante() {
        return especialidadRestaurante;
    }

    public void setEspecialidadRestaurante(String especialidadRestaurante) {
        this.especialidadRestaurante = especialidadRestaurante;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDireccionRestaurante() {
        return direccionRestaurante;
    }

    public void setDireccionRestaurante(String direccionRestaurante) {
        this.direccionRestaurante = direccionRestaurante;
    }

}


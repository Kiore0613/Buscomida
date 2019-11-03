package com.example.buscomida.apiFiles;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Restaurant implements Parcelable {

    @SerializedName("NombreRestaurante")
    private String nombreRestaurante;
    @SerializedName("LatRestaurante")
    private String latRestaurante;
    @SerializedName("LogRestaurante")
    private String logRestaurante;
    @SerializedName("EspecialidadRestaurante")
    private String especialidadRestaurante;
    @SerializedName("Categoria")
    private String nombreCategoria;
    @SerializedName("DireccionRestaurante")
    private String direccionRestaurante;

    //Endpoint Category
    @SerializedName("IdCateria")
    private String idCategoria;
    @SerializedName("NombreCateria")
    private String categoriaNombre;

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    public Restaurant(String nombreRestaurante, String latRestaurante, String logRestaurante, String especialidadRestaurante, String nombreCategoria, String direccionRestaurante, String idCategoria, String categoriaNombre) {
        this.nombreRestaurante = nombreRestaurante;
        this.latRestaurante = latRestaurante;
        this.logRestaurante = logRestaurante;
        this.especialidadRestaurante = especialidadRestaurante;
        this.nombreCategoria = nombreCategoria;
        this.direccionRestaurante = direccionRestaurante;
        this.idCategoria = idCategoria;
        this.categoriaNombre = categoriaNombre;
    }

    protected Restaurant(Parcel in) {
        nombreRestaurante = in.readString();
        latRestaurante = in.readString();
        logRestaurante = in.readString();
        especialidadRestaurante = in.readString();
        nombreCategoria = in.readString();
        direccionRestaurante = in.readString();
        idCategoria = in.readString();
        categoriaNombre = in.readString();
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombreRestaurante);
        parcel.writeString(latRestaurante);
        parcel.writeString(logRestaurante);
        parcel.writeString(especialidadRestaurante);
        parcel.writeString(nombreCategoria);
        parcel.writeString(direccionRestaurante);
        parcel.writeString(idCategoria);
        parcel.writeString(nombreCategoria);
    }
}


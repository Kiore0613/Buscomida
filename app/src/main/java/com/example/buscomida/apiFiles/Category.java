package com.example.buscomida.apiFiles;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Category implements Parcelable {

    public Category(String idCategoria, String categoriaNombre) {
        this.idCategoria = idCategoria;
        this.categoriaNombre = categoriaNombre;
    }

    //Endpoint Category
    @SerializedName("IdCateria")
    private String idCategoria;
    @SerializedName("NombreCateria")
    private String categoriaNombre;

    protected Category(Parcel in) {
        idCategoria = in.readString();
        categoriaNombre = in.readString();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idCategoria);
        parcel.writeString(categoriaNombre);
    }
}

package com.example.buscomida.apiFiles;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface BuscomidaApi {

    @GET("/restaurantes")
    Call <List<Restaurant>> getRestaurantes();

    @GET("/restaurantes/{NombreRestaurante}")
    Call <List<Restaurant>> getRestaurantes(@Path("NombreRestaurante") String nombreRestaurante);

    @GET("/categorias")
    Call <List<Restaurant>> getCategorias();

    @GET("/restaurantes/categoria/{IdCateria}")
    Call <List<Restaurant>> getCategoriaId();
}

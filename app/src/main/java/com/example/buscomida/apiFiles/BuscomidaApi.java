package com.example.buscomida.apiFiles;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface BuscomidaApi {

    @GET("/restaurantes")
    Call <List<Restaurant>> getRestaurantes();

    @GET("/restaurantes/{NombreRestaurante}")
    Call <List<Restaurant>> getRestaurantes(@Path("NombreRestaurante") String nombreRestaurante);

    @GET("/categorias")
    Call <List<Category>> getCategorias();

    @GET("/restaurantes/categoria/{IdCateria}")
    Call <List<Restaurant>> getRestauranteById(@Path("IdCateria") String nombreCategoria);

    // post para el login
    @FormUrlEncoded
    @POST("/login")
    Call <List<User>> checkUser(@Field("user")String user, @Field("password") String password );
}

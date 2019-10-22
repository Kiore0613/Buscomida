package com.example.buscomida;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface BuscomidaApi {

    @GET("/restaurantes")
    Call <List<Restaurant>> getRestaurantes();

    //@GET("restaurantes")
    //    Call <Restaurant> getRestaurantes(@Query("name")String nombreRestaurante,
    //                                      @Query("LatRestaurante")String latRestaurante,
    //                                      @Query("LogRestaurante")String logRestaurante,
    //                                      @Query("EspecialidadRestaurante")String especialidadRestaurante,
    //                                      @Query("DireccionRestaurante")String direccionRestaurante);


}

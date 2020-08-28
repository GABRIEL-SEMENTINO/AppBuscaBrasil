package com.example.buscabrasil;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;



public interface EstadoService {

    @GET("/api/v1/localidades/estados")
    Call<List<Estado>> findAll();
}

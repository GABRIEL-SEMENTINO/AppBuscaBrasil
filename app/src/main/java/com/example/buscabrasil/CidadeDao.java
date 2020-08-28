package com.example.buscabrasil;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface CidadeDao {

    @Query("SELECT * FROM cidades u")
    List<Cidade>cidades();

    @Insert
    void insertAll(List<Cidade> cidades);
}

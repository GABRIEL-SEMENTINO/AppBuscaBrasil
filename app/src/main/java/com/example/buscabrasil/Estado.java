package com.example.buscabrasil;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Nome do Estado")
public class Estado {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String nome;


    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}

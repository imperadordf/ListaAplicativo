package com.example.listaaplicativo.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Personagem implements Serializable {
    private String nome;
    private String altura;
    private String nascimento;
    private int id=0;

    //Construtor do Personagem
    public Personagem(String nome, String altura,String nascimento)
    {
        //Dando valor as minhas variavels privadas
        this.nome=nome;
        this.altura=altura;
        this.nascimento=nascimento;
    }

    public Personagem(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    @NonNull
    @Override
    public String toString(){return nome;}

    public void setId(int id){this.id=id;}

    public int getId(){return id;}

    public boolean IdValide(){return id>0;}
}
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

    //Pegando o Nome do Personagem
    public String getNome() {
        return nome;
    }
//Dando o Nome para o Personagem
    public void setNome(String nome) {
        this.nome = nome;
    }
//Pegando a altura
    public String getAltura() {
        return altura;
    }
//Dando a altura
    public void setAltura(String altura) {
        this.altura = altura;
    }
//Pegando o Nascimento
    public String getNascimento() {
        return nascimento;
    }
//Dando o Nascimento
    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    @NonNull
    @Override
    public String toString(){return nome;}
//Dando o ID do personagem de indeficação, para reconhecer o qual personagem representa
    public void setId(int id){this.id=id;}
//Pegando o ID dele, para saber qual é o personagem
    public int getId(){return id;}
//O id é valido ? ele sera valido quando for maior que Zero
    public boolean IdValide(){return id>0;}
}

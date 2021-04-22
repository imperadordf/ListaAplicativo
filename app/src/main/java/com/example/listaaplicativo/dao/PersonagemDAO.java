package com.example.listaaplicativo.dao;

import com.example.listaaplicativo.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {
    //duas variaves Static, uma contem a lista de Personagens e outra um contador de quantos tem..
    private final static List<Personagem> personagens = new ArrayList<>();
    private static int contadorDeID= 1;

    public void salvar(Personagem personagemSalvo){
        personagemSalvo.setId(contadorDeID);
        personagens.add(personagemSalvo);
        contadorDeID++;
    }

    public void edita(Personagem personagem)
    {
        Personagem personagemEscolhido = buscaPersonagemId(personagem);
        if(personagemEscolhido!=null){
            int posicaoDoPersonagem = personagens.indexOf(personagemEscolhido);
            personagens.set(posicaoDoPersonagem,personagem);
        }
    }

    public  Personagem buscaPersonagemId(Personagem personagem)
    {
        for (Personagem p: personagens) {
            if(p.getId()==personagem.getId()){
                return p;
            }
        }
        return  null;
    }

    public List<Personagem> todos(){
        return new ArrayList<>(personagens);
    }

    public void remove(Personagem personagem){
        Personagem personagemDevolvido = buscaPersonagemId(personagem);
        if(personagemDevolvido!=null){
            personagens.remove(personagemDevolvido);
        }
    }

}

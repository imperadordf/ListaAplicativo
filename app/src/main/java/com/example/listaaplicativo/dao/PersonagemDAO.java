package com.example.listaaplicativo.dao;

import com.example.listaaplicativo.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {
    //duas variaves Static, uma contem a lista de Personagens e outra um contador de quantos tem..
    private final static List<Personagem> personagens = new ArrayList<>();
    private static int contadorDeID= 1;

    //Metodo que salva o Personagem que recebe no metodo
    public void salvar(Personagem personagemSalvo){
        //Salvo o Personagem e dou o ID que ele esta no momento
        personagemSalvo.setId(contadorDeID);
        //Adiciono ele na Lista
        personagens.add(personagemSalvo);
        //Aumento o ID para o proximo Personagem, para cada personagem ter um ID diferente.
        contadorDeID++;
    }

    public void edita(Personagem personagem)
    {
        //Crio um Personagem escolhido e ele vai buscar o Personagem pelo ID e referencia esse personagemEscolhido
        Personagem personagemEscolhido = buscaPersonagemId(personagem);
        //Se o Personagem for diferente de Nulo, eu coloco o Personagem na Posição dele e mando o personagem
        if(personagemEscolhido!=null){
            int posicaoDoPersonagem = personagens.indexOf(personagemEscolhido);
            personagens.set(posicaoDoPersonagem,personagem);
        }
    }
    //Procuro o Personagem pelo o ID Dele, verifico se na lista de personagem, existe algum personagem com o ID dele, caso exista, returno ele
    public  Personagem buscaPersonagemId(Personagem personagem)
    {
        for (Personagem p: personagens) {
            if(p.getId()==personagem.getId()){
                return p;
            }
        }
        return  null;
    }

    //um Metodo que Return a lista de Personagens que eu tenho
    public List<Personagem> todos(){
        return new ArrayList<>(personagens);
    }

    //Removo o Personagem, buscando ele pelo ID e removendo  ele da Lista
    public void remove(Personagem personagem){
        Personagem personagemDevolvido = buscaPersonagemId(personagem);
        if(personagemDevolvido!=null){
            personagens.remove(personagemDevolvido);
        }
    }

}

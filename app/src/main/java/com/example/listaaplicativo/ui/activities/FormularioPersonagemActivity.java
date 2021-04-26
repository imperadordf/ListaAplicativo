package com.example.listaaplicativo.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.listaaplicativo.R;
import com.example.listaaplicativo.dao.PersonagemDAO;
import com.example.listaaplicativo.model.Personagem;

import static com.example.listaaplicativo.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class FormularioPersonagemActivity extends AppCompatActivity {
    //String para caso for editar ou novo Personagem
    private static  final String TITUTLO_APPBAR_EDITA_PERSONAGEM ="EDITAR PERSONAGENS";
    private static  final String TITUTLO_APPBAR_NOVO_PERSONAGEM ="NOVO PERSONAGEM";

    //Edit text para o Nome, altura e Nascimento, declarando eles para ser usado nos metodos
    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;

    //Criando um PersonagemDAO, que ele tem a lista e configura os personagens
    private final PersonagemDAO dao = new PersonagemDAO();
    //Uma classe Personagem, que vai ser criado ou editado
    private Personagem personagem;

    //Seria o Start do Android Studio a onde tudo começa, o Main que seria a criação de tudo
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setando o contexto que esse Script teria a logica do Layou activity_Formulario_personagem
        setContentView(R.layout.activity_formulario_personagem);
        //Inicializa o campos
        inicializeCampos();
        configuraBotaoSalvar();
        carregarPersonagem();

        //Estou dando os valores inicias e configurando tudo para o Usuario
    }

    private void carregarPersonagem() {
        //Metodo que verifica se existe um Personagem salvo, caso seja um personagem Editado
        Intent dados = getIntent();
        if(dados.hasExtra(CHAVE_PERSONAGEM)){
            //Caso seja um Personagem para Edita,eu mudo o titulo do aplicativo no topo.
            setTitle(TITUTLO_APPBAR_EDITA_PERSONAGEM);
            //Pego a referencia do Personagem usando Serializable
            personagem = (Personagem) dados.getSerializableExtra(CHAVE_PERSONAGEM);
            //Chamo um metodo que pega os dados do Usuario e coloca na tela do aplicativo
            preencherCampos();
        }else{
            setTitle(TITUTLO_APPBAR_NOVO_PERSONAGEM);
            personagem = new Personagem();
        }
    }

    private void preencherCampos() {
        //Estou dando os valores no campo para digitar, pegando os dados e colocando visualmente.
        campoNome.setText(personagem.getNome());
        campoAltura.setText(personagem.getAltura());
        campoNascimento.setText(personagem.getNascimento());
    }

    private void configuraBotaoSalvar() {
        //Configuro o botão de Salvar e dou a Referencia
        Button botaoEnviar = findViewById(R.id.botaoEnviar);
        // estou dando a logica que ação ele vai tomar ao clickar
        botaoEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            //Se for Clickando ele chama o FinalizaFormulario
            public void onClick(View v) {
                FinalizaFormulario();
            }
        });
    }
   //
    private void FinalizaFormulario() {
        //Chamo o Metodo que seta os valores do meu Personagem
        preencherPersonagem();
        //Verifico se estou editando ou Criando um personagem
        if(personagem.IdValide()){
            dao.edita(personagem);
        }else{
            dao.salvar(personagem);
        }
        //Finalizo a criação do Personagem e volto para tela inicial
        finish();
    }

    private void preencherPersonagem() {
        //Estou pegando as referencia dos nomes inseridos pelo Usuario.
      String nome = campoNome.getText().toString();
      String altura = campoAltura.getText().toString();
      String nascimento = campoAltura.getText().toString();
      //Estou mandando para a minha classe Personagem para criar o Personagem, dando Nome, altura e Nascimento.
      personagem.setNome(nome);
      personagem.setAltura(altura);
      personagem.setNascimento(nascimento);
    }

    private void inicializeCampos() {
        //dou valores para os meus EditTEXT/ a referencia de cada- POO
        campoNome = findViewById(R.id.editTextName);
        campoAltura = findViewById(R.id.editTextAltura);
        campoNascimento = findViewById(R.id.editTextNascimento);
    }

}
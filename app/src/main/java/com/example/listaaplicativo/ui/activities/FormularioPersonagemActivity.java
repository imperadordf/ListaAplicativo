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

    private static  final String TITUTLO_APPBAR_EDITA_PERSONAGEM ="EDITAR PERSONAGENS";
    private static  final String TITUTLO_APPBAR_NOVO_PERSONAGEM ="NOVO PERSONAGEM";

    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;

    private final PersonagemDAO dao = new PersonagemDAO();
    private Personagem personagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);
        inicializeCampos();
        configuraBotaoSalvar();
        carregarPersonagem();
    }

    private void carregarPersonagem() {
        Intent dados = getIntent();
        if(dados.hasExtra(CHAVE_PERSONAGEM)){
            setTitle(TITUTLO_APPBAR_EDITA_PERSONAGEM);
            personagem = (Personagem) dados.getSerializableExtra(CHAVE_PERSONAGEM);
            preencherCampos();
        }else{
            setTitle(TITUTLO_APPBAR_NOVO_PERSONAGEM);
            personagem = new Personagem();
        }
    }

    private void preencherCampos() {
        campoNome.setText(personagem.getNome());
        campoAltura.setText(personagem.getAltura());
        campoNascimento.setText(personagem.getNascimento());
    }

    private void configuraBotaoSalvar() {
        Button botaoEnviar = findViewById(R.id.botaoEnviar);
        botaoEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FinalizaFormulario();
            }
        });
    }

    private void FinalizaFormulario() {
        preencherPersonagem();
        if(personagem.IdValide()){
            dao.edita(personagem);
        }else{
            dao.salvar(personagem);
        }
        finish();
    }

    private void preencherPersonagem() {
      String nome = campoNome.getText().toString();
      String altura = campoAltura.getText().toString();
      String nascimento = campoAltura.getText().toString();

      personagem.setNome(nome);
      personagem.setAltura(altura);
      personagem.setNascimento(nascimento);
    }

    private void inicializeCampos() {
        campoNome = findViewById(R.id.editTextName);
        campoAltura = findViewById(R.id.editTextAltura);
        campoNascimento = findViewById(R.id.editTextNascimento);
    }

}
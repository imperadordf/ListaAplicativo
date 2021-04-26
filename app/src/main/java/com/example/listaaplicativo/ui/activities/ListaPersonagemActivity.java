package com.example.listaaplicativo.ui.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listaaplicativo.R;
import com.example.listaaplicativo.dao.PersonagemDAO;
import com.example.listaaplicativo.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.listaaplicativo.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class ListaPersonagemActivity extends AppCompatActivity {

    public static final String  TITULO_APPBAR = "LISTA DE PERSONAGENS";
    private final PersonagemDAO dao = new PersonagemDAO();
    private ArrayAdapter<Personagem> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        setTitle(TITULO_APPBAR);
        configuraFabNovoPersonagem();
        configuraLista();
       // List<String> personagem = new ArrayList<>(Arrays.asList("Alex","Ken","Ryu"));

       // ListView listadePersonagens = findViewById(R.id.activity_main_lista_personagem);
        //listadePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,personagem));

      /* TextView primeiroPersonagem = findViewById((R.id.textView));
        TextView segundoPersonagem = findViewById((R.id.textView2));
          TextView terceiroPersonagem = findViewById((R.id.textView3));

          primeiroPersonagem.setText(personagem.get(0));
          segundoPersonagem.setText(personagem.get(1));
          terceiroPersonagem.setText(personagem.get(2));
        */

    }

    private void configuraFabNovoPersonagem() {
        //Botão de Adicionar um Personagem
        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.iconeAdd);
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreFormularioSalva();
            }
        });
    }
//Abre o Formulario Salvar e referenciando o
    private void abreFormularioSalva() {
        startActivity(new Intent(this, FormularioPersonagemActivity.class));
    }
//Recarrega o os personagem
    protected  void onResume(){
        super.onResume();
        atualizaPersonagem();
    }
//Atualiza o Personagem
    private void atualizaPersonagem() {
        adapter.clear();
        adapter.addAll(dao.todos());
    }
  //Remove o Personagem
    private void remove(Personagem personagem){
        dao.remove(personagem);
        adapter.remove(personagem);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_personagem_menu,menu);
    }
//O menu para Remover um Personagem
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        //Pegando o Item
        int itemId = item.getItemId();
        if(itemId==R.id.activity_lista_personagem_menu_remover){
             new AlertDialog.Builder(this)
                     .setTitle("Removendo Personagem")
                     .setMessage("Deseja remover")
                     .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             //Metodo ao clickar em Remove e escolhe Sim
                          AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                          Personagem personagemEscolhido = adapter.getItem(menuInfo.position);
                          remove(personagemEscolhido);
                         }
                     })
                     .setNegativeButton("Não",null) //"não", ele apenas faz nada
                     .show();
        }

        return super.onContextItemSelected(item);
    }
//Configura a lista
    private void configuraLista() {
        ListView listaDePersonagens = findViewById(R.id.activity_main_lista_personagem);
        listaDePersonagens(listaDePersonagens);
        ConfiguraItemPorClique(listaDePersonagens);
        registerForContextMenu(listaDePersonagens);
    }
 //
    private void ConfiguraItemPorClique(ListView listaDePersonagens) {
        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Personagem personagemEscolhido = (Personagem) adapterView.getItemAtPosition(posicao);
                abreFormularioModoEditar(personagemEscolhido);
            }
        });
    }
//Abre o Formulario para o Personagem poder editar, e vai para o Layout de Formulario levando as informações desse Personagem que vai editar
    private void abreFormularioModoEditar(Personagem personagem) {
        Intent vaiParaFormulario = new Intent(this,FormularioPersonagemActivity.class);
        vaiParaFormulario.putExtra(CHAVE_PERSONAGEM,  personagem);
        startActivity(vaiParaFormulario);
    }

//criar um listView da lista do Personagem, dando cada Personagem para lista
    private  void listaDePersonagens(ListView listaDePersonagens){
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        listaDePersonagens.setAdapter(adapter);
    }
}

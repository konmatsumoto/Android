package com.example.android6969.listatimes;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class ListaTimes extends AppCompatActivity {

    private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_times);
        Permissao.fazPermissao(this);
        lista = (ListView) findViewById(R.id.lista);
        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Aluno aluno = (Aluno) lista.getItemAtPosition(posicao);
                Intent edicao = new Intent(ListaTimes.this, FormularioActivity.class);
                edicao.putExtra("chave", aluno);
                startActivity(edicao);
            }
        });
        //final ListView lista = (ListView) findViewById(R.id.lista);
        //String[] times = {"Corinthians", "Palmeiras", "São Paulo", "Santos"};
        //ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, times);
        //lista.setAdapter(adapter);
        /*
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long id) {
                String time = (String) lista.getItemAtPosition(pos);
                Toast.makeText(ListaTimes.this, "pos: "+pos, Toast.LENGTH_LONG).show();
                return true;
            }
        });
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String time = (String) lista.getItemAtPosition(i);
                Toast.makeText(ListaTimes.this, time, Toast.LENGTH_SHORT).show();
            }
        });
    */
        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.btnadd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intencao = new Intent(ListaTimes.this, FormularioActivity.class);
                startActivity(intencao);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();

        AlunoDAO dao = new AlunoDAO(this);
        ListaAlunosAdapter adapter = new ListaAlunosAdapter(dao.getLista());
        lista.setAdapter(adapter);
        dao.close();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        int pos = info.position;
        final Aluno aluno = (Aluno) lista.getItemAtPosition(pos);

        MenuItem ligar = menu.add("Ligar");
        Intent intentLigar = new Intent(Intent.ACTION_CALL);
        intentLigar.setData(Uri.parse("tel:"+aluno.getTelefone()));
        ligar.setIntent(intentLigar);

        MenuItem mandarsms = menu.add("Enviar SMS");
        Intent intentsms = new Intent(Intent.ACTION_VIEW);
        intentsms.setData(Uri.parse("sms:" + aluno.getTelefone()));
        mandarsms.setIntent(intentsms);

        MenuItem vaiMapa = menu.add("Verificar Endereço");
        Intent intentMapa = new Intent(Intent.ACTION_VIEW);
        intentMapa.setData(Uri.parse("geo:0,0?z=14&q=" + aluno.getEndereco()));
        vaiMapa.setIntent(intentMapa);

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                new AlertDialog.Builder(ListaTimes.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Deletar")
                        .setMessage("Deseja realmente deletar?")
                        .setPositiveButton("Sim",
                                new DialogInterface.OnClickListener(){
                                    public void onClick(DialogInterface dialog, int which) {
                                        AlunoDAO dao = new AlunoDAO(ListaTimes.this);
                                        dao.exclui(aluno);
                                        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(ListaTimes.this, android.R.layout.simple_list_item_1, dao.getLista());
                                        lista.setAdapter(adapter);
                                        dao.close();

                                    }
                                })
                        .setNegativeButton("Não", null).show();
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_alunos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_enviar_notas:
                AlunoDAO dao = new AlunoDAO(this);
                List<Aluno> alunos = dao.getLista();
                dao.close();

                String json = new AlunoConverter().toJSON(alunos);
                EnviaAlunosTask task = new EnviaAlunosTask(json, ListaTimes.this);
                task.execute();
        }
        return super.onOptionsItemSelected(item);
    }
}

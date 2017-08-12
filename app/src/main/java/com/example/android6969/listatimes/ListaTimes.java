package com.example.android6969.listatimes;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ListaTimes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_times);
        final ListView lista = (ListView) findViewById(R.id.lista);
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
        ListView lista = (ListView) findViewById(R.id.lista);
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.getLista();
        dao.close();
        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
        lista.setAdapter(adapter);
    }
}

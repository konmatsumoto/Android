package com.example.android6969.listatimes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.MenuItemHoverListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FormularioActivity extends AppCompatActivity {
    FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.helper = new FormularioHelper(this);
        /*
        Button salvar = (Button) findViewById(R.id.formulario_botao);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.formulario_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Aluno aluno = helper.getAluno();
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:
                if(helper.validaCampos()){
                    AlunoDAO dao = new AlunoDAO(this);
                    dao.insere(aluno);
                    dao.close();
                    Toast.makeText(FormularioActivity.this, "Aluno inserido: "+aluno.getNome(), Toast.LENGTH_LONG).show();
                    finish();
                    return true;
                }
                else return false;

            case android.R.id.home:
                finish();
                return true;
        }
        return  true;
    }

}

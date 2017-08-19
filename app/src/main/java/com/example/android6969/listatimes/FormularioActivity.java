package com.example.android6969.listatimes;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.MenuItemHoverListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class FormularioActivity extends AppCompatActivity {
    FormularioHelper helper;
    private String localArquivoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.helper = new FormularioHelper(this);
        Intent intent = getIntent();
        if(intent.hasExtra("chave")){
            Aluno aluno = (Aluno) intent.getSerializableExtra("chave");
            helper.preencheFormulario(aluno);
        }
        /*
        Button salvar = (Button) findViewById(R.id.formulario_botao);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        */

        FloatingActionButton foto = helper.getFotoButton();
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                localArquivoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                Uri caminho = Uri.fromFile(new File(localArquivoFoto));
                camera.putExtra(MediaStore.EXTRA_OUTPUT, caminho);
                startActivityForResult(camera, 123);
            }
        });
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
                    if(aluno.getId() == null){
                        dao.insere(aluno);
                        Toast.makeText(FormularioActivity.this, "Aluno inserido: "+aluno.getNome(), Toast.LENGTH_LONG).show();
                    } else {
                        dao.altera(aluno);
                    }
                    dao.close();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==123) {
            if(resultCode == Activity.RESULT_OK){
                helper.carregaImagem(this.localArquivoFoto);
            }
            else{
                this.localArquivoFoto = null;
            }
        }
    }
}

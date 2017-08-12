package com.example.android6969.listatimes;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import android.widget.RatingBar;

/**
 * Created by android6969 on 12/08/17.
 */

public class FormularioHelper {

    private Aluno aluno;
    private EditText nome;
    private EditText endereco;
    private EditText telefone;
    private EditText email;
    private RatingBar nota;
    private TextInputLayout campoNome;

    public FormularioHelper(FormularioActivity act) {
        //this.aluno = new Aluno();

        this.nome = (EditText) act.findViewById(R.id.formulario_nome);
        this.endereco = (EditText) act.findViewById(R.id.formulario_endereco);
        this.telefone = (EditText) act.findViewById(R.id.formulario_telefone);
        this.email = (EditText) act.findViewById(R.id.formulario_email);
        this.nota = (RatingBar) act.findViewById(R.id.formulario_ratingbar);
        this.campoNome = (TextInputLayout) act.findViewById(R.id.til);
    }

    public Aluno getAluno(){
        Aluno aluno = new Aluno();
        aluno.setNome(nome.getText().toString());
        aluno.setEndereco(endereco.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setEmail(email.getText().toString());
        aluno.setNota((double) nota.getRating());
        return aluno;
    }

    public boolean validaCampos(){
        boolean vazio = nome.getText().toString().isEmpty();
        if(vazio){
            campoNome.setError("Campo não pode ficar em branco!");
            return false;
        }
        return true;
    }
}

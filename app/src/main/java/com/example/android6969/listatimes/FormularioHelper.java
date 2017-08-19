package com.example.android6969.listatimes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    private ImageView foto;
    private FloatingActionButton fotoButton;

    public FormularioHelper(FormularioActivity act) {
        this.aluno = new Aluno();

        this.nome = (EditText) act.findViewById(R.id.formulario_nome);
        this.endereco = (EditText) act.findViewById(R.id.formulario_endereco);
        this.telefone = (EditText) act.findViewById(R.id.formulario_telefone);
        this.email = (EditText) act.findViewById(R.id.formulario_email);
        this.nota = (RatingBar) act.findViewById(R.id.formulario_ratingbar);
        this.campoNome = (TextInputLayout) act.findViewById(R.id.til);
        this.foto = (ImageView) act.findViewById(R.id.formulario_foto);
        this.fotoButton = (FloatingActionButton) act.findViewById(R.id.formulario_foto_button);
    }

    public FloatingActionButton getFotoButton(){
        return fotoButton;
    }

    public Aluno getAluno(){

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

    public void preencheFormulario(Aluno aluno) {
        nome.setText(aluno.getNome());
        endereco.setText(aluno.getEndereco());
        telefone.setText(aluno.getTelefone());
        email.setText(aluno.getEmail());
        nota.setRating(aluno.getNota().floatValue());
        this.aluno = aluno;
    }

    public void carregaImagem(String localArquivoFoto){
        Bitmap imagemFoto = BitmapFactory.decodeFile(localArquivoFoto);
        Bitmap imagemFotoReduzida = Bitmap.createScaledBitmap(imagemFoto, imagemFoto.getWidth(), 300, true);
        foto.setImageBitmap(imagemFotoReduzida);
        foto.setTag(localArquivoFoto);
        foto.setScaleType(ImageView.ScaleType.FIT_XY);
    }
}

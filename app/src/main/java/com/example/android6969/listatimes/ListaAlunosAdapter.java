package com.example.android6969.listatimes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by android6969 on 26/08/17.
 */

public class ListaAlunosAdapter extends BaseAdapter {
    private final List<Aluno> alunos;
    //Construtor
    public ListaAlunosAdapter(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public int getCount(){
        return alunos.size();
    }

    @Override
    public Object getItem(int pos) {
        return alunos.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return alunos.get(pos).getId();
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        Bitmap bm;
        Aluno aluno = alunos.get(pos);
        View linha;
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            linha = inflater.inflate(R.layout.item, parent, false);
        } else {
            linha = convertView;
        }
        TextView nome = (TextView) linha.findViewById(R.id.item_nome);
        nome.setText(aluno.getNome());
        TextView telefone = (TextView) linha.findViewById(R.id.item_telefone);
        telefone.setText(aluno.getTelefone());

        ImageView foto = (ImageView) linha.findViewById(R.id.item_foto);

        if(aluno.getCaminhoFoto() != null) {
            bm = BitmapFactory.decodeFile(aluno.getCaminhoFoto());
        } else {
            bm = BitmapFactory.decodeResource(foto.getResources(),R.drawable.ic_no_image);
        }
        bm = Bitmap.createScaledBitmap(bm, 100, 100, true);

        foto.setImageBitmap(bm);
        return linha;
    }

}

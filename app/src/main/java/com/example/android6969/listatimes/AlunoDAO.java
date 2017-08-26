package com.example.android6969.listatimes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android6969 on 12/08/17.
 */

public class AlunoDAO extends SQLiteOpenHelper {
    public AlunoDAO(Context context){
        super(context, "Caelum", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "create table Aluno(" +
                "id INTEGER primary key," +
                "nome text not null," +
                "endereco text," +
                "telefone text," +
                "email text," +
                "nota Real);";
        db.execSQL(sql);
    }

    public  void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova){
        switch (versaoAntiga){
            case 2:
                String sql = "ALTER TABLE Aluno add column caminhoFoto text;";
                db.execSQL(sql);
        }
    }

    public void insere(Aluno aluno){
        ContentValues valores = new ContentValues();
        valores.put("nome", aluno.getNome());
        valores.put("endereco", aluno.getEndereco());
        valores.put("telefone", aluno.getTelefone());
        valores.put("email", aluno.getEmail());
        valores.put("nota", aluno.getNota());
        valores.put("caminhoFoto", aluno.getCaminhoFoto());
        getWritableDatabase().insert("Aluno", null, valores);
    }

    public List<Aluno> getLista(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Aluno;", null);
        List<Aluno> alunos = new ArrayList<Aluno>();
        while (cursor.moveToNext()){
            Aluno a = new Aluno();
            a.setId(cursor.getLong(cursor.getColumnIndex("id")));
            a.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            a.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            a.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            a.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            a.setNota(cursor.getDouble(cursor.getColumnIndex("nota")));
            a.setCaminhoFoto(cursor.getString(cursor.getColumnIndex("caminhoFoto")));
            alunos.add(a);
        }
        cursor.close();
        return alunos;
    }

    public void altera(Aluno aluno){
        ContentValues valores = new ContentValues();
        valores.put("nome", aluno.getNome());
        valores.put("endereco", aluno.getEndereco());
        valores.put("telefone", aluno.getTelefone());
        valores.put("email", aluno.getEmail());
        valores.put("nota", aluno.getNota());
        valores.put("caminhoFoto", aluno.getCaminhoFoto());
        String[] clausula = {aluno.getId().toString()};
        getWritableDatabase().update("Aluno", valores, "id=?", clausula);
    }

    public void exclui(Aluno aluno){
        getWritableDatabase().delete("Aluno", "id=?", new String[] {aluno.getId().toString()});
    }

    public  boolean isAluno(String telefone) {
        String[] parametros = {telefone};
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT telefone FROM Aluno WHERE telefone = ?", parametros);
        int total = rawQuery.getCount();
        rawQuery.close();
        return total > 0;
    }
}

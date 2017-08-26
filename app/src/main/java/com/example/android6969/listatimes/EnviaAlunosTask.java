package com.example.android6969.listatimes;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by android6969 on 26/08/17.
 */

public class EnviaAlunosTask extends AsyncTask<Object, Object, String> {
    private String json;
    private Context cxt;
    ProgressDialog progress;

    public EnviaAlunosTask(String json, Context cxt) {
        this.json = json;
        this.cxt = cxt;
    }

    @Override
    protected String doInBackground(Object... objects) {
        WebClient client = new WebClient();
        String media = null;
        try {
            media = client.post(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return media;
    }

    @Override
    protected void onPostExecute(String result) {
        progress.dismiss();
        super.onPostExecute(result);
        Toast.makeText(cxt, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress = ProgressDialog.show(cxt, "Aguarde...","Envio de dados para a web", true, true);
    }
}

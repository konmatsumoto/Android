package com.example.android6969.listatimes;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by android6969 on 26/08/17.
 */

public class WebClient {
    public String post(String json) throws IOException{
        OkHttpClient client = new OkHttpClient();
        MediaType tipoDoArquivo = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(tipoDoArquivo,json);
        Request req = new Request.Builder().post(body).url("https://www.caelum.com.br/mobile").build();
        Response resp = client.newCall(req).execute();
        return resp.body().string();
    }
}

package com.example.android6969.listatimes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by android6969 on 26/08/17.
 */

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent data) {
        Toast.makeText(context, "Chegou um SMS!!!", Toast.LENGTH_LONG).show();
        Bundle bundle = data.getExtras();
        Object[] mensagens = (Object[]) bundle.get("pdus");
        byte[] mensagem = (byte[]) mensagens[0];
        String formato = (String) bundle.get("format");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            SmsMessage sms = SmsMessage.createFromPdu(mensagem, formato);
        }
        MediaPlayer mp = MediaPlayer.create(context, R.raw.msg);
        mp.start();
    }
}

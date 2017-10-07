package com.example.yasirbilici.android_ses_ile_yazi_yazdirma;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public EditText txt;
    public Intent intent;
    public static final int request_code_voice = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //imagebutton ve edittext tanımlandı
        ImageButton button = (ImageButton)findViewById(R.id.button);
        txt = (EditText)findViewById(R.id.txt);
        //edittext gizlendi.
        txt.setEnabled(false);
        // image button a tıklama olayı
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //ses'i tanımak için intent'i oluşturduk
                intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

                try{
                  //activity'i başlattık
                    startActivityForResult(intent, request_code_voice);
                }catch(ActivityNotFoundException e)
                {
                    Toast.makeText(MainActivity.this, "cihazınız bu uygulamayı desteklemiyor!", Toast.LENGTH_SHORT).show();

                }

    }
    });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case request_code_voice: {

                if (resultCode == RESULT_OK && data != null)
                {
                    //intent dolu oldugunda konuşmayı listenin içersine aldık
                    ArrayList<String> speech = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //sonucu edittext'e aktardık
                    txt.setText(speech.get(0));
                }
                break;
            }

        }
    }

}
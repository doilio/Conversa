package com.example.android.conversa;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.android.conversa.MainActivity.MSG;

public class Resposta extends AppCompatActivity {


    /**
     * MSG1 para conseguirmos recuperar a mensagem na outra classe
     */
    public static final String MSG1 = "2";

    /**
     * Inicializacao das Views
     */
    TextView mMensagemRecebida1;
    EditText mMensagemDeRetorno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resposta);

        /**
         * Referencia para as Views
         */
        mMensagemRecebida1 = findViewById(R.id.mensagem_recebida1);
        mMensagemDeRetorno = findViewById(R.id.mensagem1);

        /**
         * Tratamos o Intent recebido da MainActivity, pondo o valor recebido na TextView.
         */
        Intent intent = getIntent();
        String mensagem = intent.getStringExtra(MSG);
        mMensagemRecebida1.setText(mensagem);




    }

    public void responderMensagem(View view) {
        //Ao Clickarmos Send, respondemos o Intent da MainActivity passando o RESULT_OK e o intent contendo a mensagem de resposta
        Intent returnIntent = new Intent();
        String mensagem1 = mMensagemDeRetorno.getText().toString();
        returnIntent.putExtra(MSG1, mensagem1);
        setResult(RESULT_OK,returnIntent);
        finish();

    }

   /* @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mMensagemRecebida1.getVisibility()==View.VISIBLE){
            outState.putString("outState_resposta", mMensagemRecebida1.getText().toString());
        }
    }*/
}

package com.example.android.conversa;

import android.content.Intent;
import android.opengl.Visibility;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.android.conversa.Resposta.MSG1;

public class MainActivity extends AppCompatActivity {

    /**
     * Request para a activity, e MSG para conseguirmos recuperar a mensagem na outra classe
     */
    public static final String MSG = "1";
    public static final int REQUEST = 1;

    /**
     * Inicializacao das Views
     */
    EditText mEditTextMessage;
    TextView mTitulo;
    TextView mMensagemRecebida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Referencia para as Views
         */
        mEditTextMessage = findViewById(R.id.mensagem);
        mTitulo = findViewById(R.id.titulo);
        mMensagemRecebida = findViewById(R.id.mensagem_recebida);

        //Persistir os dados caso Tenha algum dado no instanceState
        if (savedInstanceState!= null){
            mMensagemRecebida.setText(savedInstanceState.getString("outState_resposta"));
            mTitulo.setVisibility(View.VISIBLE);
            mMensagemRecebida.setVisibility(View.VISIBLE);
        }



    }

    /**
     * Metodo chamado ao fazermos o click do Butao send.
     * Permite com que possamos enviar a mensagem que foi escrita nesta activity para a outra.
     */
    public void enviarMensagem(View view) {
        Intent intent = new Intent(this, Resposta.class);
        String mensagem = mEditTextMessage.getText().toString();
        intent.putExtra(MSG, mensagem);
        startActivityForResult(intent,REQUEST);
        mEditTextMessage.setText("");
    }

    /**
     *@param requestCode ajuda-nos a verificar para que tipo de request estamos a responder
     *@param resultCode ajuda-nos a verificar se houve falhas.
     *@param data ajuda-nos a pegar os dados de volta.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST) {
            //Se o resultado tiver sido bem sucedido.
            if (resultCode == RESULT_OK) {
                //Pegamos os dados de Resposta.class, e tornamos visiveis o titulo e a mensagem recebida.
                String resposta = data.getStringExtra(MSG1);
                mTitulo.setVisibility(View.VISIBLE);
                mMensagemRecebida.setText(resposta);
                mMensagemRecebida.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mTitulo.getVisibility()==View.VISIBLE){
            outState.putString("outState_resposta",mMensagemRecebida.getText().toString());
        }
    }
}

package com.example.jogodavelha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements EventoJogo {

    private TextView txtPontuacaoJogador1;
    private TextView txtPontuacaoJogador2;

    private TextView txtJogador;

    private Jogo jogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        this.txtPontuacaoJogador1 = this.findViewById(R.id.txtPontuacaoJogador1);
        this.txtPontuacaoJogador2 = this.findViewById(R.id.txtPontuacaoJogador2);
        this.txtJogador = this.findViewById(R.id.txtJogador);

        this.jogo = new Jogo(this);
        this.jogo.iniciarJogo();
    }

    public void clickReiniciar(View view){
        this.txtJogador.setText("Vez do desumilde!");
    }

    public void clickJogar(View view){

    }


    @Override
    public void onJogadorAlterado(Jogador jogador){
        this.txtJogador.setText("Vez do: "+jogador);
    }




}
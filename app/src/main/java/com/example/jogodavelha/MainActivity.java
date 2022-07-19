package com.example.jogodavelha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements JogoDaVelhaEventos {

    private JogoDaVelha jogo;

    private TextView txtPontuacaoJogador1;
    private TextView txtPontuacaoJogador2;
    private TextView txtJogadorAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.txtPontuacaoJogador1 = findViewById(R.id.txtPontuacaoJogador1);
        this.txtPontuacaoJogador2 = findViewById(R.id.txtPontuacaoJogador2);
        this.txtJogadorAtual = findViewById(R.id.txtJogador);

        this.reiniciarJogo();
    }

    public void reiniciarJogo(){
        jogo = new JogoDaVelha(this);
        jogo.iniciar();
    }

    private void limparTabuleiro(){
        for(int i=0; i<9 ; i++){
            int btnId = getResources().getIdentifier("btn_"+i, "id", this.getPackageName());
            Button btn = findViewById(btnId);
            btn.setText("");
        }
    }

    public void reiniciarClick(View view){
        reiniciarJogo();
    }

    public void jogarClick(View view){
        try {
            int posicao = Integer.parseInt(view.getTag().toString());
            jogo.jogar(posicao);
        } catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public void onPontuacaoAtualizada(){
        this.txtPontuacaoJogador1.setText(String.valueOf(this.jogo.getPontuacaoJogador1()));
        this.txtPontuacaoJogador2.setText(String.valueOf(this.jogo.getPontuacaoJogador2()));
    }

    @Override
    public void onJogadaRealizada(int posicao, Jogador jogador) {
        int btnId = getResources().getIdentifier("btn_"+posicao, "id", this.getPackageName());
        Button btn = findViewById(btnId);
        btn.setText(jogador == Jogador.JOGADOR1?"X":"O");
    }

    @Override
    public void onPartidaIniciada() {
        limparTabuleiro();
    }

    @Override
    public void onEmpate() {
        Toast.makeText(this, "Empatou", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onJogadorAlterado(Jogador novoJogador) {
        this.txtJogadorAtual.setText("Vez de " + (novoJogador==Jogador.JOGADOR1?"X":"O"));
    }

    @Override
    public void onJogadorGanhou(Jogador jogador) {
        Toast.makeText(this, (jogador==Jogador.JOGADOR1?"X":"O")+" Ganhou!", Toast.LENGTH_SHORT)
                .show();
    }
}
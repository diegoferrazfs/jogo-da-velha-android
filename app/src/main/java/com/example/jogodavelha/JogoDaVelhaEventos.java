package com.example.jogodavelha;

public interface JogoDaVelhaEventos {

    void onJogadorAlterado(Jogador novoJogador);
    void onJogadorGanhou(Jogador novoJogador);
    void onPontuacaoAtualizada();
    void onJogadaRealizada(int posicao, Jogador jogador);
    void onPartidaIniciada();
    void onEmpate();
}

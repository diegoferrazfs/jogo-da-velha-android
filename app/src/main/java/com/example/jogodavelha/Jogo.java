package com.example.jogodavelha;

public class Jogo {
    private Jogador jogadorAtual;

    private Jogador[] tabuleiro;

    private EventoJogo evento;

    public Jogo(EventoJogo evento) {
        this.evento = evento;
    }

    public void iniciarJogo(){
        this.tabuleiro = new Jogador[9];
        this.alterarJogador();
    }

    public void jogar(int i){

        this.alterarJogador();
    }

    public void alterarJogador(){
        this.jogadorAtual = jogadorAtual == Jogador.JOGADOR_O?
                Jogador.JOGADOR_X:Jogador.JOGADOR_O;
    }

    public Jogador getJogadorAtual() {
        return jogadorAtual;
    }
}

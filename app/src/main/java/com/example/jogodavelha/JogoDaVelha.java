package com.example.jogodavelha;

public class JogoDaVelha {

    private Jogador jogadorAtual;

    private int pontuacaoJogador1;
    private int pontuacaoJogador2;

    private Jogador[] tabuleiro;

    private final int[][] posicoesParaGanhar = {
            {0,1,2},
            {3,4,5},
            {6,7,8},

            {0,3,6},
            {1,4,7},
            {2,5,8},

            {0,4,8},
            {2,4,6}
    };

    private JogoDaVelhaEventos eventos;

    public JogoDaVelha(JogoDaVelhaEventos eventos) {
        this.eventos = eventos;
    }

    public int getPontuacaoJogador1() {
        return pontuacaoJogador1;
    }

    public int getPontuacaoJogador2() {
        return pontuacaoJogador2;
    }

    public void iniciarPartida(){
        this.tabuleiro = new Jogador[9];
        this.alternarJogadorAtual();
        this.eventos.onPartidaIniciada();
    }

    private boolean podeJogar(int posicao){
        return tabuleiro[posicao] == null;
    }

    private void pontuarJogador(Jogador jogador){
        if(jogador==Jogador.JOGADOR1)
            this.pontuacaoJogador1++;
        else
            this.pontuacaoJogador2++;

        this.eventos.onPontuacaoAtualizada();
    }

    private boolean isVitoria(){
        for (int[] posicaoGanhar : this.posicoesParaGanhar){
            if(this.tabuleiro[posicaoGanhar[0]] == this.tabuleiro[posicaoGanhar[1]] &&
                    this.tabuleiro[posicaoGanhar[0]] == this.tabuleiro[posicaoGanhar[2]]
                    && this.tabuleiro[posicaoGanhar[0]]!=null) {
                return true;
            }
        }
        return false;
    }

    private boolean isEmpate(){
        for (Jogador jogador : tabuleiro){
            if (jogador == null){
                return false;
            }
        }
        return true;
    }

    public void jogar(int posicao){
        if(!podeJogar(posicao)){
            throw new RuntimeException("Jogada Inválida");
        }

        this.tabuleiro[posicao] = this.jogadorAtual;
        this.eventos.onJogadaRealizada(posicao, this.jogadorAtual);

        if(isVitoria()){
            this.pontuarJogador(this.jogadorAtual);
            this.eventos.onJogadorGanhou(this.jogadorAtual);
            this.iniciarPartida();
        } else if (isEmpate()){
            this.eventos.onEmpate();
            this.iniciarPartida();
        } else {
            alternarJogadorAtual();
        }
    }

    public void alternarJogadorAtual(){
        this.jogadorAtual = this.jogadorAtual == Jogador.JOGADOR1 ? Jogador.JOGADOR2 : Jogador.JOGADOR1;
        this.eventos.onJogadorAlterado(this.jogadorAtual);
    }
    public void iniciar(){
        this.iniciarPartida();
    }
}

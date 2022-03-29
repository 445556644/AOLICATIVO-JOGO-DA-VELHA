    package br.senai.sp.cotia.appjogodavelha.fragment;

    import android.graphics.Color;
    import android.os.Bundle;

    import androidx.annotation.NonNull;
    import androidx.fragment.app.Fragment;

    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;
    import android.widget.Toast;

    import java.util.Arrays;
    import java.util.Random;

    import br.senai.sp.cotia.appjogodavelha.R;
    import br.senai.sp.cotia.appjogodavelha.databinding.FragmentJogoBinding;

    public class fragmentJogo extends Fragment {

        // vetor para agrupar os botoes
        private Button[] botoes;

        // variavel que representa o tabuleiro
        private String[][] tabuleiro;
        // variavel que representa os simbolos
        private String simbJog1, simbJog2, simbolo;

        // variavel que conta o numero de jogadas
        private int numJog = 0;
        // variavel random que sorteia quem começa
        private Random random;
        // VARIAVEL QUE ACESSA OS ELEMENTOS NA VIEW
        private @NonNull
        FragmentJogoBinding biding;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            // INSTANCIANDO O BIDING
            biding = FragmentJogoBinding.inflate(inflater, container, false);

            // agrupando os botoes no vetor

            // instanciando o vetor
            botoes = new Button[9];
            botoes[0] = biding.bt00;
            botoes[1] = biding.bt01;
            botoes[2] = biding.bt02;
            botoes[3] = biding.bt10;
            botoes[4] = biding.bt11;
            botoes[5] = biding.bt12;
            botoes[6] = biding.bt20;
            botoes[7] = biding.bt21;
            botoes[8] = biding.bt22;

            // associa o listener aos botoes

            for (Button bts : botoes) {

                bts.setOnClickListener(listenerBotoes);
            }

            // instanciando o tabuleiro
            tabuleiro = new String[3][3];

            // setando ""
            for (String[] vetor : tabuleiro){

                // setando "" em todos os button
                Arrays.fill(vetor, "");
            }

            random = new Random();

            simbJog1 = "X";
            simbJog2 = "O";

            // metodo que sorteia quem começa ...
            sorteia();
            atualizaVez();

            return biding.getRoot();
        }

        // metodo que sorteia
        private void sorteia() {

            // caso o random gere um valor verdadeiro jogador 1 começa (valor verdadeiro gerado automaticamente)

            // metodo boolean que gera um valor verdadeiro e falso "random.nextBoolean()"
            if (random.nextBoolean()) {

                simbolo = simbJog1;
                // caso o random gere um valor false jogador 2 começa
            } else {

                simbolo = simbJog2;
            }
        }

        // metodo que verifica se vendeu nas linhas


        // metodo que verifica as posições possiveis para vencer.
        private boolean venceu() {

            for (int i = 0; i < 3; i++) {

                if (tabuleiro[i][0].equals(simbolo) && tabuleiro[i][1].equals(simbolo) && tabuleiro[i][2].equals(simbolo)) {

                    return true;
                }

                // verificar s vencdeu na coluna

                if (tabuleiro[0][i].equals(simbolo) && tabuleiro[1][i].equals(simbolo) && tabuleiro[2][i].equals(simbolo)) {

                    return true;
                }

            }
            if (tabuleiro[0][0].equals(simbolo) && tabuleiro[1][1].equals(simbolo) && tabuleiro[2][2].equals(simbolo)){

                return  true;
            }

            if (tabuleiro[0][2].equals(simbolo) && tabuleiro[1][1].equals(simbolo) && tabuleiro[2][0].equals(simbolo)){

                return  true;
            }


            return false;

        }


        // metodo que atualiza a vez
        private void atualizaVez(){

            // verifica de quem é a vez e "acende o placar do jogador da vez"

            if (simbolo.equals(simbJog1)){

                biding.linearUm.setBackgroundColor(getResources().getColor(R.color.blue));
                biding.linearDois.setBackgroundColor(getResources().getColor(R.color.white));
            }else {

                biding.linearUm.setBackgroundColor(getResources().getColor(R.color.white));
                biding.linearDois.setBackgroundColor(getResources().getColor(R.color.blue));

            }

        }

        private void resetar() {

            // USANDO O FOR PARA PERCORRER TODOS OS BOTOES DO TABULEIRO
            for (String[] vetor : tabuleiro){

                // setando "" em todos os button
                Arrays.fill(vetor, "");
            }

            // USANDO O FOR PARA PERCORRER TODOS OS BOTOES DO TABULEIRO ASSIM O ASSOCIANDO A VARIAVEL BTN QUE POSSUI OS "METODOS QUE RESETAM..."
            for (Button btn : botoes){

                // LIMPANDO O TEXTO
                btn.setText("");
                // SETANDO A COR "ORIGINAL DO BTN"
                btn.setBackgroundColor(getResources().getColor(R.color.azulClaro));
                // FAZENDO COM QUE O BOTAO SEJA CLICAVEL
                btn.setClickable(true);

            }
            // sorteia
            sorteia();
            // atualiza a vez
            atualizaVez();
            // zerando o numero de jogadas
            numJog =0;

        }



        //faz com que ele veja qual botao foi selecionado
        private View.OnClickListener listenerBotoes = botaoP -> {

            //incrementa numero de jogadas
            numJog++;

            String nomeBotao = getContext().getResources().getResourceName(botaoP.getId());

            // extraindo os 2 ultimos caracteres do nomeBotao
            String posicao = nomeBotao.substring(nomeBotao.length()-2);

            // extrai a posicao em linha e coluna

            // charAT pega o caracter que esta na posição
            int linha = Character.getNumericValue(posicao.charAt(0));
            int coluna = Character.getNumericValue(posicao.charAt(1));

            // marca no tabuleiro o simbolo que foi jogado
            tabuleiro[linha][coluna] = simbolo;
            // faz um casting de view pra button para ter acesso a propeiedade setText...

            Button botao  = (Button) botaoP;
            // trocar o simbolo do botao que foi clicado
            ((Button) botaoP).setText(simbolo);
            // desabilita o botao
            botao.setClickable(false);
            // e muda a cor do btn apos ser desabilitadp
            botao.setBackgroundColor(Color.WHITE);

            // VERIFICANDO SE O JOGADOR VENCEU
            if (numJog >= 5 && venceu()){

                //exibe um toast informando que o jogador venceu
                Toast.makeText(getContext(),R.string.venceu, Toast.LENGTH_SHORT).show();

                // resetando
                resetar();
                // VERIFICANDO SE O NUMERO DE JOGADAS É IGUAL A 9 = VELHA
            }else if(numJog == 9) {

                // DEU VELHA (SOBE A MSG DE VELHA )
                Toast.makeText(getContext(),R.string.velha, Toast.LENGTH_SHORT).show();
                // CASO DE VELHA RESETA OS CAMPOS E AS CORES
                resetar();

            }else{


                // invertendo a vez
                simbolo = simbolo.equals(simbJog1) ? simbJog2 : simbJog1;

                // atualiza a vez de quem esta jogando
                atualizaVez();

            }


        };
    }
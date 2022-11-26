package view;

import prova.Partida;
import prova.Selecao;

public class Gui {
    public static void main(String[] args) {
        //Teste Inserir
        /*
        Partida partida1 = new Partida();
        partida1.setId(7);
        Selecao selecao1 = partida1.getSelecao1();
        Selecao selecao2 = partida1.getSelecao2();
        selecao1.setPais("Laos");
        selecao2.setPais("Iraque");
        selecao1.setQtdeTitulos(0);
        selecao2.setQtdeTitulos(0);
        partida1.setSelecao1(selecao1);
        partida1.setSelecao2(selecao2);
        partida1.setPlacar(2, 2);

        System.out.println(partida1.inserir());
        */

        //Teste Excluir
        /*
        Partida partida2 = new Partida();
        partida2.setId(4);
        System.out.println(partida2.excluir());
         */

        //Teste Listar
        //Listagem 1:

        Partida partida3 = new Partida();
        partida3.listar(3);


    }
}

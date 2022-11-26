package prova;

import seila.Padrao;
import services.BD;

import java.sql.SQLException;

public class Partida extends Selecao implements Padrao{
    private String sql;
    private BD bd;

    private int id;
    private Selecao selecao1;
    private Selecao selecao2;
    private int gols1;
    private int gols2;

    public Partida() {
        bd = new BD();
        //Classes abstratas não podem ser instanciadas de maneira normal, mas podem ser "instanciadas" através do construtor
        //Se não passar pelo construtor na hora que for usar o setPais e setQtdeTitulos fala que a seleção é nula e não consegue atribuir valor
        //Era isso que estava dando problema no meu na prova, só faltou passar pelo construtor
        this.selecao1 = new Selecao() {};
        this.selecao2 = new Selecao() {};
    }

    public Partida(int id, int gols1, int gols2) {
        this.id = id;
        this.selecao1 = new Selecao() {};
        this.selecao2 = new Selecao() {};
        this.gols1 = gols1;
        this.gols2 = gols2;
        bd = new BD();
    }

    @Override
    public Boolean inserir() {
        Boolean isSuccessful;
        sql = "INSERT INTO partida VALUES(?, ?, ?, ?, ?)";
        try{
            bd.getConnection();
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, id);
            bd.st.setString(2, selecao1.getPais());
            bd.st.setString(3, selecao2.getPais());
            bd.st.setInt(4, gols1);
            bd.st.setInt(5, gols2);
            bd.st.executeUpdate();
            isSuccessful = true;
        } catch (Exception e){
            System.out.println("ERRO! DETALHES: " + e.toString());
            isSuccessful = false;
        }
        finally {
            bd.close();
        }
        return isSuccessful;
    }

    @Override
    public Boolean excluir() {
        Boolean isSuccessfull;
        sql = "DELETE FROM partida WHERE id = ?";
        try{
            bd.getConnection();
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, id);
            bd.st.executeUpdate();
            isSuccessfull = true;
        } catch (SQLException e) {
            System.out.println("ERRO! DETALHES: " + e.getMessage());
            isSuccessfull = false;
        }
        finally {
            bd.close();
        }
        return isSuccessfull;
    }

    //Seta o placar da partida
    public void setPlacar(int gols1, int gols2){
        if (gols1 >= 0 && gols2 >= 0){
            this.gols1 = gols1;
            this.gols2 = gols2;
        }
        else {
            System.out.println("Informe apenas valores positivos para gols");
        }
    }

    public void listar(int i){
        switch (i){
            //Seleciona todos as partidas
            case 1:
                sql = "SELECT * FROM partida";
                try{
                    bd.getConnection();
                    bd.st = bd.con.prepareStatement(sql);
                    //Armazena os valores obtidos via select no bd.rs = bancoDeDados.Result, ou seja os resultados obditos
                    bd.rs = bd.st.executeQuery();

                    //bd.rs.next() retorna um true caso exista um próximo registro
                    //enquanto existir um próximo registro o "loop" continuará
                    while (bd.rs.next()){
                        Partida partida = new Partida();
                        partida.setId(bd.rs.getInt("id"));
                        Selecao selecao1 = partida.getSelecao1();
                        Selecao selecao2 = partida.getSelecao2();
                        selecao1.setPais(bd.rs.getString("selecao1"));
                        selecao2.setPais(bd.rs.getString("selecao2"));
                        partida.setPlacar(bd.rs.getInt("gols1"), bd.rs.getInt("gols2"));
                        System.out.println(partida);
                    }
                } catch (SQLException e) {
                    System.out.println("Erro ao listar todas as partidas! Detalhes: " + e.getMessage());
                } finally {
                  bd.close();
                }
                break;

            //Seleciona o vencedor de cada partida
            case 2:
                sql = "SELECT * FROM partida";
                try{
                    bd.getConnection();
                    bd.st = bd.con.prepareStatement(sql);
                    bd.rs = bd.st.executeQuery();
                    while (bd.rs.next()){
                        if (bd.rs.getInt("gols1") > bd.rs.getInt("gols2")) {
                            System.out.println(bd.rs.getString("selecao1"));
                        }
                        else if (bd.rs.getInt("gols1") < bd.rs.getInt("gols2")) {
                            System.out.println(bd.rs.getString("selecao2"));
                        }
                    }
                } catch (SQLException e) {
                    System.out.println("Erro ao listar todas as partidas! Detalhes: " + e.getMessage());
                } finally {
                    bd.close();
                }
                break;

            //Seleciona as partidas empatadas
            case 3:
                sql = "SELECT * FROM partida where gols1 = gols2";
                try{
                    bd.getConnection();
                    bd.st = bd.con.prepareStatement(sql);
                    bd.rs = bd.st.executeQuery();
                    while (bd.rs.next()){
                        Partida partida = new Partida();
                        partida.setId(bd.rs.getInt("id"));
                        Selecao selecao1 = partida.getSelecao1();
                        Selecao selecao2 = partida.getSelecao2();
                        selecao1.setPais(bd.rs.getString("selecao1"));
                        selecao2.setPais(bd.rs.getString("selecao2"));
                        partida.setPlacar(bd.rs.getInt("gols1"), bd.rs.getInt("gols2"));
                        System.out.println(partida);
                    }
                } catch (SQLException e) {
                    System.out.println("Erro ao listar todas as partidas! Detalhes: " + e.getMessage());
                } finally {
                    bd.close();
                }
                break;
            default:
                System.out.println("Tipo de listagem inválido!");
                break;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Selecao getSelecao1() {
        return selecao1;
    }

    public void setSelecao1(Selecao selecao1) {
        this.selecao1 = selecao1;
    }

    public Selecao getSelecao2() {
        return selecao2;
    }

    public void setSelecao2(Selecao selecao2) {
        this.selecao2 = selecao2;
    }

    public int getGols1() {
        return gols1;
    }

    public int getGols2() {
        return gols2;
    }

    @Override
    public String toString() {
        return "Partida{" +
                "id=" + id +
                ", selecao1=" + selecao1.pais +
                ", selecao2=" + selecao2.pais +
                ", gols1=" + gols1 +
                ", gols2=" + gols2 +
                '}';
    }
}

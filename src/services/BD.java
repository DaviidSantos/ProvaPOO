package services;

import java.sql.*;

public class BD {
    public Connection con = null;

    // executa instruções SQL
    public PreparedStatement st = null;

    // recebe o resultado de uma query (select)
    public ResultSet rs = null;

    private final String DRIVER = "org.postgresql.Driver";
    private final String BANCO = "prova";
    private final String URL = "jdbc:postgresql://localhost:5432/"+BANCO;
    private final String LOGIN = "postgres";
    private final String SENHA = "banco123";
    /**
     * Realiza a conexão ao banco de dados
     * @return - true em caso de sucesso, ou false caso contrário
     */
    public boolean getConnection() {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, LOGIN, SENHA);
            System.out.println("Conectou...");
            return true;
        }
        catch(SQLException erro) {
            System.out.println("Erro de conexão "+erro);
            return false;
        }
        catch(ClassNotFoundException erro) {System.out.println("Driver não encontrado!");
            return false;
        }
    }

    public void close() {
        try {
            if(rs!=null) rs.close();
        }
        catch(SQLException erro) {}

        try {
            if(st!=null) st.close();
        }
        catch(SQLException erro) {}

        try {
            if(con!=null) {
                con.close();
                System.out.println("Desconectou...");
            }}
        catch(SQLException erro) {}
    }


    public static void main(String[] args) {
        BD bd = new BD();
        bd.getConnection();
        bd.close();

    }
}

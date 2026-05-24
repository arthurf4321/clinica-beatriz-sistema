package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/clinica_beatriz?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {

            String mensagem = "Erro ao conectar com o banco de dados.";

            if (e.getMessage().contains("Access denied")) {
                mensagem = "Usuário ou senha do banco inválidos.";
            } else if (e.getMessage().contains("Unknown database")) {
                mensagem = "Banco de dados não encontrado.";
            } else if (e.getMessage().contains("Communications link failure")) {
                mensagem = "Não foi possível acessar o MySQL. Verifique se o serviço está ativo.";
            }

            throw new RuntimeException(mensagem, e);
        }
    }
}
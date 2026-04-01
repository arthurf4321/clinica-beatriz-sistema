import java.sql.Connection;
import connection.ConnectionFactory;

public class Main {

    public static void main(String[] args) {

        try (Connection conn = ConnectionFactory.getConnection()) {

            if (conn != null) {
                System.out.println("✅ Conectado com sucesso ao banco!");
            }

        } catch (Exception e) {
            System.out.println("❌ Erro ao conectar");
            e.printStackTrace();
        }
    }
}
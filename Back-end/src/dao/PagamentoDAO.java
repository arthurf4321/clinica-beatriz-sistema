package dao;

import connection.ConnectionFactory;
import model.Pagamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class PagamentoDAO {

    public void criarPagamento(Pagamento pagamento) {

        String sql = "INSERT INTO pagamentos (valor, forma_pagamento, status, data_pagamento, recepcionista_id) Values (?,?,?,?)";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setDouble(1, pagamento.getValor());
            stmt.setString(2, pagamento.getFormaPagamento().name());
            stmt.setString(3, pagamento.getStatusPagamento().name());
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(pagamento.getDataPagamento()));
            stmt.setInt(5, pagamento.getRecepcionistaId());

            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar pagamento: " + e.getMessage(), e);
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Erro ao fechar recursos: " + e.getMessage());
        }
    }
}

package dao;

import connection.ConnectionFactory;
import model.Pagamento;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public List<Pagamento> listarPagamentos() {
        String sql = "SELECT * FROM pagamentos ORDER BY id";
        List<Pagamento> pagamentos = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                pagamentos.add(mapearResultSetParaPagamento(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar pagamentos: " + e.getMessage(), e);

        } finally {
            closeResources(conn, stmt, rs);
        }

        return pagamentos;
    }


    private Pagamento mapearResultSetParaPagamento(ResultSet rs) throws SQLException {
        Pagamento pagamento = new Pagamento();

        pagamento.setId(rs.getInt("id"));
        pagamento.setValor(rs.getDouble("valor"));

        pagamento.setFormaPagamento(
                Pagamento.FormaPagamento.valueOf(
                        rs.getString("forma_pagamento").toUpperCase()
                )
        );

        pagamento.setStatusPagamento(
                Pagamento.StatusPagamento.valueOf(
                        rs.getString("status").toUpperCase()
                )
        );

        pagamento.setDataPagamento(
                rs.getTimestamp("data_pagamento") != null
                        ? rs.getTimestamp("data_pagamento").toLocalDateTime()
                        : null
        );

        return pagamento;
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

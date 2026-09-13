package dao;

import connection.ConnectionFactory;
import model.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public void salvar(Paciente paciente) {
        String sql = "INSERT INTO pacientes (nome, cpf, data_nascimento, telefone, email) VALUES (?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setDate(3, java.sql.Date.valueOf(paciente.getDataNascimento()));
            stmt.setString(4, paciente.getTelefone());
            stmt.setString(5, paciente.getEmail());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar paciente: " + e.getMessage(), e);
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    public List<Paciente> listar() {
        String sql = "SELECT * FROM pacientes ORDER BY nome";
        List<Paciente> pacientes = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                pacientes.add(mapearResultSetParaPaciente(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar pacientes: " + e.getMessage(), e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return pacientes;
    }

    public void atualizar(Paciente paciente) {
        String sql = "UPDATE pacientes SET nome = ?, cpf = ?, data_nascimento = ?, telefone = ?, email = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setDate(3, java.sql.Date.valueOf(paciente.getDataNascimento()));
            stmt.setString(4, paciente.getTelefone());
            stmt.setString(5, paciente.getEmail());
            stmt.setInt(6, paciente.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new RuntimeException("Nenhum paciente encontrado com ID: " + paciente.getId());
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar paciente: " + e.getMessage(), e);
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM pacientes WHERE id = ?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new RuntimeException("Nenhum paciente encontrado com ID: " + id);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar paciente: " + e.getMessage(), e);
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    private Paciente mapearResultSetParaPaciente(ResultSet rs) throws SQLException {
        Paciente paciente = new Paciente();
        paciente.setId(rs.getInt("id"));
        paciente.setNome(rs.getString("nome"));
        paciente.setCpf(rs.getString("cpf"));

        if (rs.getDate("data_nascimento") != null) {
            paciente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
        }

        paciente.setTelefone(rs.getString("telefone"));
        paciente.setEmail(rs.getString("email"));

        return paciente;
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
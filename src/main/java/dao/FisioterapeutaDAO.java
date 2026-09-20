package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionFactory;
import model.Fisioterapeuta;


public class FisioterapeutaDAO {

	public void criarFisioterapeuta(Fisioterapeuta fisioterapeuta) {

		String sql = "INSERT INTO fisioterapeutas  (nome, cpf, telefone, email, especialidade ) VALUES (?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, fisioterapeuta.getNome());
			stmt.setString(2, fisioterapeuta.getCpf());
			stmt.setString(3, fisioterapeuta.getTelefone());
			stmt.setString(4, fisioterapeuta.getEmail());
			stmt.setString(5, fisioterapeuta.getTipoFisioterapeuta().name());

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar Fisioterapeuta" + e.getMessage(), e);

		} finally {
			closeResources(conn, stmt, null);
		}
	}
	
	
	public List<Fisioterapeuta> listarFsioterapeuta() {

		String sql = "SELECT * FROM fisioterapeutas WHERE ativo = true ORDER BY nome";

	    List<Fisioterapeuta> fisioterapeutas = new ArrayList<>();

	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {

	        conn = ConnectionFactory.getConnection();
	        stmt = conn.prepareStatement(sql);
	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            fisioterapeutas.add(mapearResultSetParaFisioterapeuta(rs));
	        }

	    } catch (SQLException e) {

	        throw new RuntimeException(
	            "Erro ao listar fisioterapeutas: " + e.getMessage(), e
	        );

	    } finally {

	        closeResources(conn, stmt, rs);
	    }

	    return fisioterapeutas;
	}
	
	
	


	    public Fisioterapeuta buscarFisioterapeutasPorId(int id) {
	        String sql = "SELECT * FROM fisioterapeutas WHERE id = ?";

	        Connection conn = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;

	        try {
	            conn = ConnectionFactory.getConnection();
	            stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, id);
	            rs = stmt.executeQuery();

	            if (rs.next()) {
	                return mapearResultSetParaFisioterapeuta(rs);
	            }

	            return null;

	        } catch (SQLException e) {
	            throw new RuntimeException("Erro ao buscar fisioterapeuta por ID: " + e.getMessage(), e);
	        } finally {
	            closeResources(conn, stmt, rs);
	        }
	    }

	    public Fisioterapeuta buscarPorEmail(String email) {
	        String sql = "SELECT * FROM fisioterapeutas WHERE email = ?";

	        Connection conn = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;

	        try {
	            conn = ConnectionFactory.getConnection();
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, email);
	            rs = stmt.executeQuery();

	            if (rs.next()) {
	                return mapearResultSetParaFisioterapeuta(rs);
	            }

	            return null;

	        } catch (SQLException e) {
	            throw new RuntimeException("Erro ao buscar  por email: " + e.getMessage(), e);
	        } finally {
	            closeResources(conn, stmt, rs);
	        }
	    }
	
	
	
	

	public void atualizarFisioterapeuta(Fisioterapeuta fisioterapeuta) {

		String sql = "UPDATE fisioterapeutas SET nome = ?, cpf = ?, telefone = ?, email = ?, especialidade = ? WHERE id = ?";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, fisioterapeuta.getNome());
			stmt.setString(2, fisioterapeuta.getCpf());
			stmt.setString(3, fisioterapeuta.getTelefone());
			stmt.setString(4, fisioterapeuta.getEmail());
			stmt.setString(5, fisioterapeuta.getTipoFisioterapeuta().name());
			stmt.setInt(6, fisioterapeuta.getId());

			int linhasAfetadas = stmt.executeUpdate();

			if (linhasAfetadas == 0) {
				throw new RuntimeException("Nenhum fisioterapeuta encontrado com ID: " + fisioterapeuta.getId());
			}

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar fisioterapeuta: " + e.getMessage(), e);
		} finally {
			closeResources(conn, stmt, null);
		}
	}

	public void deletarFisioterapeuta(int id) {

		String sql = "UPDATE fisioterapeutas SET ativo = false WHERE id = ?";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			int linhasAfetadas = stmt.executeUpdate();

			if (linhasAfetadas == 0) {
				throw new RuntimeException("Nenhum  fisioterapeuta com ID: " + id);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao deletar fisioterapeuta: " + e.getMessage(), e);
		} finally {
			closeResources(conn, stmt, null);
		}

	}
	
	
	private Fisioterapeuta mapearResultSetParaFisioterapeuta(ResultSet rs) throws SQLException {

	    Fisioterapeuta fisioterapeuta = new Fisioterapeuta();

	    fisioterapeuta.setId(rs.getInt("id"));
	    fisioterapeuta.setNome(rs.getString("nome"));
	    fisioterapeuta.setCpf(rs.getString("cpf"));
	    fisioterapeuta.setTelefone(rs.getString("telefone"));
	    fisioterapeuta.setEmail(rs.getString("email"));

	    fisioterapeuta.setTipoFisioterapeuta(
	        Fisioterapeuta.Especialidade.valueOf(
	            rs.getString("especialidade").toUpperCase()
	        )
	    );

	    return fisioterapeuta;
	}

	
	
	
	

	private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			System.out.println("Erro ao fechar recursos: " + e.getMessage());
		}
	}
}

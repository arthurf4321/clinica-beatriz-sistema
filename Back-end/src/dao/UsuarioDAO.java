package dao;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import connection.ConnectionFactory;

public class UsuarioDAO {

    // Methods
    public void criarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, email, senha, tipo_usuario, fisioterapeuta_id, ativo, data_criacao) VALUES (?,?,?,?,?,?,?)";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTipoUsuario().name());
            stmt.setObject(5, usuario.getFisioterapeutaId());
            stmt.setBoolean(6, usuario.isAtivo());
            stmt.setObject(7, LocalDateTime.now());
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar usuário: " + e.getMessage(), e);
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    public List<Usuario> listarUsuarios() {
        String sql = "SELECT * FROM usuario ORDER BY nome";
        List<Usuario> usuarios = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                usuarios.add(mapearResultSetParaUsuario(rs));
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuários: " + e.getMessage(), e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        
        return usuarios;
    }

    public Usuario buscarUsuarioPorId(int id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapearResultSetParaUsuario(rs);
            }
            
            return null;
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário por ID: " + e.getMessage(), e);
        } finally {
            closeResources(conn, stmt, rs);
        }
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        String sql = "SELECT * FROM usuario WHERE email = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapearResultSetParaUsuario(rs);
            }
            
            return null;
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário por email: " + e.getMessage(), e);
        } finally {
            closeResources(conn, stmt, rs);
        }
    }

    public void atualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, tipo_usuario = ?, fisioterapeuta_id = ?, ativo = ? WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTipoUsuario().name());
            stmt.setObject(5, usuario.getFisioterapeutaId());
            stmt.setBoolean(6, usuario.isAtivo());
            stmt.setInt(7, usuario.getId());
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuário: " + e.getMessage(), e);
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    public void deletarUsuario(int id) {
        String sql = "UPDATE usuario SET ativo = false WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar usuário: " + e.getMessage(), e);
        } finally {
            closeResources(conn, stmt, null);
        }
    }
    
    private Usuario mapearResultSetParaUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setNome(rs.getString("nome"));
        usuario.setEmail(rs.getString("email"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setTipoUsuario(Usuario.TipoUsuario.valueOf(rs.getString("tipo_usuario")));
        
        int fisioterapeutaId = rs.getInt("fisioterapeuta_id");
        usuario.setFisioterapeutaId(rs.wasNull() ? null : fisioterapeutaId);
        
        usuario.setAtivo(rs.getBoolean("ativo"));
        
        LocalDateTime dataCriacao = rs.getTimestamp("data_criacao") != null 
            ? rs.getTimestamp("data_criacao").toLocalDateTime() : null;
        usuario.setDataCriacao(dataCriacao);
        
        return usuario;
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

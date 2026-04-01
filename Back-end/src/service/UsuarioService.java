package service;

import model.Usuario;
import dao.UsuarioDAO;
import java.util.List;

public class UsuarioService {
    
    private UsuarioDAO usuarioDAO;
    
    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }
    
    public void criarUsuario(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            System.out.println("Erro: Nome é obrigatório");
            return;
        }
        
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            System.out.println("Erro: Email é obrigatório");
            return;
        }
        
        if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
            System.out.println("Erro: Senha é obrigatória");
            return;
        }
        
        usuarioDAO.criarUsuario(usuario);
        System.out.println("Usuário criado com sucesso!");
    }
    
    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listarUsuarios();
    }
    
    public Usuario buscarUsuarioPorId(int id) {
        if (id <= 0) {
            System.out.println("Erro: ID inválido");
            return null;
        }
        
        return usuarioDAO.buscarUsuarioPorId(id);
    }
    
    public Usuario buscarUsuarioPorEmail(String email) {
        if (email == null || email.isEmpty()) {
            System.out.println("Erro: Email é obrigatório");
            return null;
        }
        
        return usuarioDAO.buscarUsuarioPorEmail(email);
    }
    
    public void atualizarUsuario(Usuario usuario) {
        if (usuario.getId() <= 0) {
            System.out.println("Erro: ID inválido para atualização");
            return;
        }
        
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            System.out.println("Erro: Nome é obrigatório");
            return;
        }
        
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            System.out.println("Erro: Email é obrigatório");
            return;
        }
        
        usuarioDAO.atualizarUsuario(usuario);
        System.out.println("Usuário atualizado com sucesso!");
    }
    
    public void deletarUsuario(int id) {
        if (id <= 0) {
            System.out.println("Erro: ID inválido");
            return;
        }
        
        usuarioDAO.deletarUsuario(id);
        System.out.println("Usuário deletado com sucesso!");
    }
}

package controller;

import model.Usuario;
import service.UsuarioService;
import java.util.List;

public class UsuarioController {
    
    private UsuarioService usuarioService;
    
    public UsuarioController() {
        this.usuarioService = new UsuarioService();
    }
    
    public void cadastrarUsuario(String nome, String email, String senha, String tipoUsuario) {
        try {
            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senha);
            usuario.setTipoUsuario(Usuario.TipoUsuario.valueOf(tipoUsuario));
            usuario.setAtivo(true);
            
            usuarioService.criarUsuario(usuario);
            
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    public void listarTodosUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário encontrado.");
            return;
        }
        
        System.out.println("=== USUÁRIOS CADASTRADOS ===");
        for (Usuario usuario : usuarios) {
            System.out.println("ID: " + usuario.getId());
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("Tipo: " + usuario.getTipoUsuario());
            System.out.println("---------------------");
        }
    }
    
    public Usuario buscarUsuario(int id) {
        return usuarioService.buscarUsuarioPorId(id);
    }
    
    public void atualizarUsuario(int id, String nome, String email, String senha, String tipoUsuario) {
        try {
            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senha);
            usuario.setTipoUsuario(Usuario.TipoUsuario.valueOf(tipoUsuario));
            
            usuarioService.atualizarUsuario(usuario);
            
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    public void removerUsuario(int id) {
        usuarioService.deletarUsuario(id);
    }
}

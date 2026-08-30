package controller;

import model.Usuario;
import service.UsuarioService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/usuarios")
public class UsuarioController extends HttpServlet {

    private UsuarioService usuarioService = new UsuarioService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String idParam = request.getParameter("id");

        if (idParam != null && !idParam.isEmpty()) {
            int id = Integer.parseInt(idParam);
            Usuario usuario = usuarioService.buscarUsuarioPorId(id);

            if (usuario == null) {
                out.println("Usuário não encontrado");
                return;
            }

            out.println(usuario.getId() + " - " + usuario.getNome() + " - "
                    + usuario.getEmail() + " - " + usuario.getTipoUsuario());

        } else {
            List<Usuario> usuarios = usuarioService.listarUsuarios();

            if (usuarios.isEmpty()) {
                out.println("Nenhum usuário encontrado.");
                return;
            }

            for (Usuario usuario : usuarios) {
                out.println(usuario.getId() + " - " + usuario.getNome() + " - "
                        + usuario.getEmail() + " - " + usuario.getTipoUsuario());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            Usuario usuario = new Usuario();
            usuario.setNome(request.getParameter("nome"));
            usuario.setEmail(request.getParameter("email"));
            usuario.setSenha(request.getParameter("senha"));
            usuario.setTipoUsuario(
                    Usuario.TipoUsuario.valueOf(
                            request.getParameter("tipoUsuario").toUpperCase()
                    )
            );
            usuario.setAtivo(true);

            usuarioService.criarUsuario(usuario);

            out.println("Usuário cadastrado com sucesso");

        } catch (Exception e) {
            out.println("Erro: " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            Usuario usuario = new Usuario();
            usuario.setId(Integer.parseInt(request.getParameter("id")));
            usuario.setNome(request.getParameter("nome"));
            usuario.setEmail(request.getParameter("email"));
            usuario.setSenha(request.getParameter("senha"));
            usuario.setTipoUsuario(
                    Usuario.TipoUsuario.valueOf(
                            request.getParameter("tipoUsuario").toUpperCase()
                    )
            );
            usuario.setAtivo(true);

            usuarioService.atualizarUsuario(usuario);

            out.println("Usuário atualizado com sucesso");

        } catch (Exception e) {
            out.println("Erro: " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));
        usuarioService.deletarUsuario(id);

        out.println("Usuário removido com sucesso");
    }
}
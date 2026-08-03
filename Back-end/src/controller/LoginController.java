package controller;

import dao.UsuarioDAO;
import model.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String email = request.getParameter("email");
        String senha = request.getParameter("senha");


        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuarioValidado = dao.validarLogin(email, senha);

        if (usuarioValidado != null) {



            HttpSession sessao = request.getSession();
            sessao.setAttribute("usuarioLogado", usuarioValidado);



            request.getRequestDispatcher("html/index.html").forward(request, response);
            return;

        } else {

            request.setAttribute("mensagemErro", "E-mail ou senha incorretos!");
            request.getRequestDispatcher("src/sistema/html/login.html").forward(request, response);
        }
    }
}

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("URI: " + request.getRequestURI());
        System.out.println("URL: " + request.getRequestURL());
        System.out.println("====================================");
        System.out.println("ENTROU NO SERVLET LOGIN");

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        System.out.println("Email recebido: " + email);
        System.out.println("Senha recebida: " + senha);

        UsuarioDAO dao = new UsuarioDAO();

        Usuario usuarioValidado = dao.validarLogin(email, senha);

        System.out.println("Resultado da consulta: " + usuarioValidado);

        if (usuarioValidado != null) {

            System.out.println("LOGIN OK");

            HttpSession sessao = request.getSession();
            sessao.setAttribute("usuarioLogado", usuarioValidado);

            System.out.println("Redirecionando para Navbar...");

            response.sendRedirect(
                    request.getContextPath() + "/sistema/NavBar/html/navbar.html"
            );

        } else {

            System.out.println("LOGIN INVÁLIDO");

            response.sendRedirect(
                    request.getContextPath() + "/sistema/Login/html/login.html"
            );
        }

        System.out.println("====================================");
    }
}
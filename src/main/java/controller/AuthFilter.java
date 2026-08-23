package controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/sistema/*")

public class AuthFilter implements Filter {

	@Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        String contexto = req.getContextPath();

        System.out.println("========== AUTH FILTER ==========");
        System.out.println("URI: " + uri);
        System.out.println("Contexto: " + contexto);

    
        if (uri.startsWith(contexto + "/sistema/Login/")) {
            chain.doFilter(request, response);
            return;
        }

        
        HttpSession session = req.getSession(false);

        boolean logado = session != null
                && session.getAttribute("usuarioLogado") != null;

        System.out.println("Sessão: " + session);
        System.out.println("Logado: " + logado);

     
        if (logado) {
            chain.doFilter(request, response);
            return;
        }

      
        resp.sendRedirect(
                contexto + "/sistema/Login/html/login.html"
        );
    }
}
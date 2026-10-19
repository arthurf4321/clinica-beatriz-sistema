package controller;

import model.Fisioterapeuta;
import service.FisioterapeutaService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/fisioterapeutas")
public class FisioterapeutaController extends HttpServlet {

    private final FisioterapeutaService fisioterapeutaService = new FisioterapeutaService();

    private String formatar(Fisioterapeuta f) {
        return f.getId() + " - " + f.getNome() + " - " + f.getCpf() + " - "
                + f.getTelefone() + " - " + f.getEmail() + " - " + f.getTipoFisioterapeuta();
    }

    private Fisioterapeuta.Especialidade parseEspecialidade(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("Especialidade é obrigatória.");
        }
        try {
            return Fisioterapeuta.Especialidade.valueOf(valor.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Especialidade inválida.");
        }
    }

    private int parseId(String idParam) {
        if (idParam == null || idParam.trim().isEmpty()) {
            throw new IllegalArgumentException("ID é obrigatório.");
        }
        try {
            return Integer.parseInt(idParam.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID inválido.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String idParam = request.getParameter("id");

        try {
            if (idParam != null && !idParam.trim().isEmpty()) {

                Fisioterapeuta f = fisioterapeutaService.buscarFisioterapeutaPorId(parseId(idParam));

                if (f == null) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.println("Fisioterapeuta não encontrado");
                    return;
                }
                out.println(formatar(f));

            } else {
                List<Fisioterapeuta> lista = fisioterapeutaService.listarFisioterapeutas();

                if (lista.isEmpty()) {
                    out.println("Nenhum fisioterapeuta encontrado");
                    return;
                }
                for (Fisioterapeuta f : lista) {
                    out.println(formatar(f));
                }
            }

        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("Erro: " + e.getMessage());

        } catch (RuntimeException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            getServletContext().log("Erro ao consultar fisioterapeutas", e);
            out.println("Erro interno ao consultar. Confira o console do Tomcat.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            Fisioterapeuta f = new Fisioterapeuta();
            f.setNome(request.getParameter("nome"));
            f.setCpf(request.getParameter("cpf"));
            f.setTelefone(request.getParameter("telefone"));
            f.setEmail(request.getParameter("email"));
            f.setTipoFisioterapeuta(parseEspecialidade(request.getParameter("especialidade")));

            fisioterapeutaService.criarFisioterapeuta(f);

            response.setStatus(HttpServletResponse.SC_CREATED);
            out.println("Fisioterapeuta criado com sucesso!");

        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("Erro: " + e.getMessage());

        } catch (RuntimeException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            getServletContext().log("Erro ao cadastrar fisioterapeuta", e);
            out.println("Erro interno ao cadastrar. Confira o console do Tomcat.");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            int id = parseId(request.getParameter("id"));

            Fisioterapeuta f = fisioterapeutaService.buscarFisioterapeutaPorId(id);

            if (f == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.println("Fisioterapeuta não encontrado");
                return;
            }

            String nome = request.getParameter("nome");
            String cpf = request.getParameter("cpf");
            String telefone = request.getParameter("telefone");
            String email = request.getParameter("email");
            String especialidade = request.getParameter("especialidade");

            if (nome != null) f.setNome(nome);
            if (cpf != null) f.setCpf(cpf);
            if (telefone != null) f.setTelefone(telefone);
            if (email != null) f.setEmail(email);
            if (especialidade != null) f.setTipoFisioterapeuta(parseEspecialidade(especialidade));

            fisioterapeutaService.atualizarFisioterapeuta(f);

            out.println("Fisioterapeuta atualizado com sucesso!");

        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("Erro: " + e.getMessage());

        } catch (RuntimeException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            getServletContext().log("Erro ao atualizar fisioterapeuta", e);
            out.println("Erro interno ao atualizar. Confira o console do Tomcat.");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            int id = parseId(request.getParameter("id"));

            if (fisioterapeutaService.buscarFisioterapeutaPorId(id) == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.println("Fisioterapeuta não encontrado");
                return;
            }

            fisioterapeutaService.deletarFisioterapeuta(id);

            out.println("Fisioterapeuta excluído com sucesso!");

        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("Erro: " + e.getMessage());

        } catch (RuntimeException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            getServletContext().log("Erro ao excluir fisioterapeuta", e);
            out.println("Erro interno ao excluir. Confira o console do Tomcat.");
        }
    }
}
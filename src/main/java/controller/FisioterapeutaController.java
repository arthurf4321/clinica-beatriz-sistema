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

	private FisioterapeutaService fisioterapeutaService =
            new FisioterapeutaService(); 	



	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");

	    PrintWriter out = response.getWriter();

	    String idParam = request.getParameter("id");

	    if (idParam != null && !idParam.isEmpty()) {

	        int id = Integer.parseInt(idParam);

	        Fisioterapeuta fisioterapeuta =
	                fisioterapeutaService.buscarFisioterapeutaPorId(id);

	        if (fisioterapeuta == null) {
	            out.println("Fisioterapeuta não encontrado");
	            return;
	        }

	        out.println(
	            fisioterapeuta.getId() + " - " +
	            fisioterapeuta.getNome() + " - " +
	            fisioterapeuta.getCpf() + " - " +
	            fisioterapeuta.getTelefone() + " - " +
	            fisioterapeuta.getEmail() + " - " +
	            fisioterapeuta.getTipoFisioterapeuta()
	        );

	    } else {

	        List<Fisioterapeuta> fisioterapeutas =
	                fisioterapeutaService.listarFisioterapeutas();

	        if (fisioterapeutas.isEmpty()) {
	            out.println("Nenhum fisioterapeuta encontrado");
	            return;
	        }

	        for (Fisioterapeuta f : fisioterapeutas) {

	            out.println(
	                f.getId() + " - " +
	                f.getNome() + " - " +
	                f.getCpf() + " - " +
	                f.getTelefone() + " - " +
	                f.getEmail() + " - " +
	                f.getTipoFisioterapeuta()
	            );
	        }
	    }
	}
	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        response.setContentType("text/plain");
	        response.setCharacterEncoding("UTF-8");
	        PrintWriter out = response.getWriter();

	        Fisioterapeuta fisioterapeuta = new Fisioterapeuta();

	        fisioterapeuta.setNome(request.getParameter("nome"));
	        fisioterapeuta.setCpf(request.getParameter("cpf"));
	        fisioterapeuta.setTelefone(request.getParameter("telefone"));
	        fisioterapeuta.setEmail(request.getParameter("email"));

	        fisioterapeuta.setTipoFisioterapeuta(
	                Fisioterapeuta.Especialidade.valueOf(
	                        request.getParameter("especialidade").toUpperCase()
	                )
	        );

	        fisioterapeutaService.criarFisioterapeuta(fisioterapeuta);

	        out.println("Requisição de criação processada");
	    }

	    @Override
	    protected void doPut(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        response.setContentType("text/plain");
	        response.setCharacterEncoding("UTF-8");
	        PrintWriter out = response.getWriter();

	        int id = Integer.parseInt(request.getParameter("id"));

	        Fisioterapeuta fisioterapeuta =
	                fisioterapeutaService.buscarFisioterapeutaPorId(id);

	        if (fisioterapeuta == null) {
	            out.println("Fisioterapeuta não encontrado");
	            return;
	        }

	        fisioterapeuta.setNome(request.getParameter("nome"));
	        fisioterapeuta.setCpf(request.getParameter("cpf"));
	        fisioterapeuta.setTelefone(request.getParameter("telefone"));
	        fisioterapeuta.setEmail(request.getParameter("email"));

	        fisioterapeuta.setTipoFisioterapeuta(
	                Fisioterapeuta.Especialidade.valueOf(
	                        request.getParameter("especialidade").toUpperCase()
	                )
	        );

	        fisioterapeutaService.atualizarFisioterapeuta(fisioterapeuta);

	        out.println("Requisição de atualização processada");
	    }

	    @Override
	    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        response.setContentType("text/plain");
	        response.setCharacterEncoding("UTF-8");
	        PrintWriter out = response.getWriter();

	        int id = Integer.parseInt(request.getParameter("id"));

	        fisioterapeutaService.deletarFisioterapeuta(id);

	        out.println("Requisição de exclusão processada");
	    }
	}

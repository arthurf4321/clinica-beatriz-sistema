package controller;

import model.Pagamento;
import service.PagamentoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/pagamentos")
public class PagamentoController extends HttpServlet {

    private PagamentoService pagamentoService = new PagamentoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String idParam = request.getParameter("id");

        if (idParam != null && !idParam.isEmpty()) {
            int id = Integer.parseInt(idParam);
            Pagamento pagamento = pagamentoService.buscarPagamentoPorID(id);

            if (pagamento == null) {
                out.println("Pagamento não encontrado");
                return;
            }

            out.println(pagamento.getId() + " - " + pagamento.getValor() + " - "
                    + pagamento.getFormaPagamento() + " - " + pagamento.getStatusPagamento());

        } else {
            List<Pagamento> pagamentos = pagamentoService.listarPagamentos();
            for (Pagamento p : pagamentos) {
                out.println(p.getId() + " - " + p.getValor() + " - "
                        + p.getFormaPagamento() + " - " + p.getStatusPagamento());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        Pagamento pagamento = new Pagamento();
        pagamento.setValor(Double.parseDouble(request.getParameter("valor")));
        pagamento.setFormaPagamento(
                Pagamento.FormaPagamento.valueOf(request.getParameter("formaPagamento").toUpperCase())
        );
        pagamento.setStatusPagamento(Pagamento.StatusPagamento.PENDENTE);
        pagamento.setDataPagamento(LocalDateTime.now());
        pagamento.setAvaliacaoId(Integer.parseInt(request.getParameter("avaliacaoId")));
        pagamento.setRecepcionistaId(Integer.parseInt(request.getParameter("recepcionistaId")));

        pagamentoService.criarPagamento(pagamento);

        out.println("Requisição de criação processada");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));
        String status = request.getParameter("status");

        pagamentoService.atualizarStatusPagamento(id, status);

        out.println("Requisição de atualização processada");
    }
}
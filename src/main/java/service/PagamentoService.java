package service;

import dao.PagamentoDAO;
import model.Pagamento;
import java.util.List;

public class PagamentoService {

    private PagamentoDAO pagamentoDAO;

    public PagamentoService() { this.pagamentoDAO = new PagamentoDAO(); }

    public void criarPagamento(Pagamento pagamento) {

        if(pagamento == null || pagamento.getValor() <= 0 )  {
            System.out.println("Erro: Valor do pagamento é obrigatório");
            return;
        }

        if(pagamento.getAvaliacaoId() <= 0) {
            System.out.println("ERRO: Pagemente precisa pertencer a uma avaliacao");
            return;
        }

        if (pagamento.getFormaPagamento() == null) {
            System.out.println("ERRO: Forma de pagamento é obrigatória");
            return;
        }

        pagamentoDAO.criarPagamento(pagamento);
        System.out.println("Pagamento criado com sucesso!!");
    }

    public List<Pagamento> listarPagamentos() {
        return pagamentoDAO.listarPagamentos();
    }

    public Pagamento buscarPagamentoPorID(int id) {
        if (id <= 0) {
            System.out.println("Erro: Esse Id nao foi encontrado!!");
            return null;
        }

        return pagamentoDAO.buscarPagamentoPorId(id);
    }

    public void atualizarStatusPagamento(int id, String status) {

        if (id <= 0) {
            System.out.println("Erro: ID do pagamento inválido");
            return;
        }

        if (status == null || status.isEmpty()) {
            System.out.println("Erro: Status do pagamento é obrigatório");
            return;
        }

        try {
            Pagamento.StatusPagamento novoStatus =
                    Pagamento.StatusPagamento.valueOf(status.toUpperCase());

            pagamentoDAO.atualizarStatusPagamento(id, novoStatus.name());

            System.out.println("Status do pagamento atualizado com sucesso!");

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: Status de pagamento inválido!");
        }
    }
}
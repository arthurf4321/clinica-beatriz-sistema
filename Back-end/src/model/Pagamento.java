package model;
import java.time.LocalDateTime;

public class Pagamento {
    private int id;
    private int avaliacaoId;
    private int recepcionistaId;
    private double valor;
    private FormaPagamento formaPagamento;
    private StatusPagamento statusPagamento;
    private LocalDateTime dataPagamento;

    // Enums
    public enum StatusPagamento {
        pendente,
        pago,
        cancelado
    }

    public enum FormaPagamento {
        DINHEIRO,
        CARTAO,
        PIX,
        TRANSFERENCIA
    }

    // Gets and Sets
    public int getAvaliacaoId() { return avaliacaoId; }
    private int getrecepcionistaId() { return recepcionistaId; }
    public int getId() { return  id; }
    public void setValor( double valor ) { this.valor = valor;}
    public double getValor() { return valor; }
    public FormaPagamento getFormaPagamento() { return formaPagamento ;}
    public void setFormaPagamento( FormaPagamento formaPagamento ) { this.formaPagamento = formaPagamento; }
    public StatusPagamento getStatusPagamento() { return statusPagamento; }
    public void setStatusPagamento( StatusPagamento statusPagamento ) { this.statusPagamento = statusPagamento; }
    public LocalDateTime getDataPagamento() { return dataPagamento; }
    public void setDataPagamento( LocalDateTime dataPagamento ) { this.dataPagamento = dataPagamento; }
}
package model;
import java.time.LocalDateTime;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private TipoUsuario tipoUsuario;
    private Integer fisioterapeutaId;
    private boolean ativo;
    private LocalDateTime dataCriacao;

    // Enums
    public enum TipoUsuario {
        ADMINISTRADOR,
        FISIOTERAPEUTA,
        RECEPCIONISTA
    }

    // Construct
    public Usuario(int id, String nome, String email, String senha, TipoUsuario tipoUsuario, Integer fisioterapeutaId ,boolean ativo, LocalDateTime dataCriacao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.fisioterapeutaId = fisioterapeutaId;
        this.ativo = ativo;
        this.dataCriacao = dataCriacao;
    }
    public Usuario() {}

    // Gets and Sets
    public int getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public TipoUsuario getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(TipoUsuario tipoUsuario) { this.tipoUsuario = tipoUsuario; }
    public Integer getFisioterapeutaId() { return fisioterapeutaId; }
    public void setFisioterapeutaId(Integer fisioterapeutaId) { this.fisioterapeutaId = fisioterapeutaId; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
}
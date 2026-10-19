package service;

import java.util.List;

import dao.FisioterapeutaDAO;
import model.Fisioterapeuta;

public class FisioterapeutaService {

    private FisioterapeutaDAO fisioterapeutaDAO;

    public FisioterapeutaService() {
        this.fisioterapeutaDAO = new FisioterapeutaDAO();
    }

    public void criarFisioterapeuta(Fisioterapeuta fisioterapeuta) {
        validarFisioterapeuta(fisioterapeuta);
        fisioterapeutaDAO.criarFisioterapeuta(fisioterapeuta);
    }

    public List<Fisioterapeuta> listarFisioterapeutas() {
        return fisioterapeutaDAO.listarFsioterapeuta();
    }

    public Fisioterapeuta buscarFisioterapeutaPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        return fisioterapeutaDAO.buscarFisioterapeutasPorId(id);
    }

    public Fisioterapeuta buscarPorEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email é obrigatório.");
        }
        return fisioterapeutaDAO.buscarPorEmail(email.trim());
    }

    public void atualizarFisioterapeuta(Fisioterapeuta fisioterapeuta) {
        if (fisioterapeuta != null && fisioterapeuta.getId() <= 0) {
            throw new IllegalArgumentException("ID inválido para atualização.");
        }
        validarFisioterapeuta(fisioterapeuta);
        fisioterapeutaDAO.atualizarFisioterapeuta(fisioterapeuta);
    }

    public void deletarFisioterapeuta(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido para exclusão.");
        }
        fisioterapeutaDAO.deletarFisioterapeuta(id);
    }

    private void validarFisioterapeuta(Fisioterapeuta f) {
        if (f == null) {
            throw new IllegalArgumentException("Os dados do fisioterapeuta são obrigatórios.");
        }
        if (f.getNome() == null || f.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
        if (f.getCpf() == null || f.getCpf().trim().isEmpty()) {
            throw new IllegalArgumentException("CPF é obrigatório.");
        }
        if (f.getEmail() == null || f.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email é obrigatório.");
        }
        if (f.getTipoFisioterapeuta() == null) {
            throw new IllegalArgumentException("Especialidade é obrigatória.");
        }
    }
}
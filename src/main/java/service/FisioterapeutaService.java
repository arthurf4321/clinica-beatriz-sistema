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

          
            if (fisioterapeuta.getNome() == null || fisioterapeuta.getNome().isEmpty()) {
                System.out.println("Erro: Nome é obrigatório");
                return;
            }

            if (fisioterapeuta.getCpf() == null || fisioterapeuta.getCpf().isEmpty()) {
                System.out.println("Erro: CPF é obrigatório");
                return;
            }

            if (fisioterapeuta.getEmail() == null || fisioterapeuta.getEmail().isEmpty()) {
                System.out.println("Erro: Email é obrigatório");
                return;
            }

            if (fisioterapeuta.getTipoFisioterapeuta() == null) {
                System.out.println("Erro: Especialidade é obrigatória");
                return;
            }

            fisioterapeutaDAO.criarFisioterapeuta(fisioterapeuta);

            System.out.println("Fisioterapeuta criado com sucesso!");
        }

       
        public List<Fisioterapeuta> listarFisioterapeutas() {

            return fisioterapeutaDAO.listarFsioterapeuta();
        }

       
        public Fisioterapeuta buscarFisioterapeutaPorId(int id) {

            if (id <= 0) {
                System.out.println("Erro: ID inválido");
                return null;
            }

            return fisioterapeutaDAO.buscarFisioterapeutasPorId(id);
        }

      
        public Fisioterapeuta buscarPorEmail(String email) {

            if (email == null || email.isEmpty()) {
                System.out.println("Erro: Email é obrigatório");
                return null;
            }

            return fisioterapeutaDAO.buscarPorEmail(email);
        }

      
        public void atualizarFisioterapeuta(Fisioterapeuta fisioterapeuta) {

            if (fisioterapeuta == null) {
                System.out.println("Erro: Fisioterapeuta inválido");
                return;
            }

            if (fisioterapeuta.getId() <= 0) {
                System.out.println("Erro: ID inválido para atualização");
                return;
            }

            if (fisioterapeuta.getNome() == null || fisioterapeuta.getNome().isEmpty()) {
                System.out.println("Erro: Nome é obrigatório");
                return;
            }

            if (fisioterapeuta.getCpf() == null || fisioterapeuta.getCpf().isEmpty()) {
                System.out.println("Erro: CPF é obrigatório");
                return;
            }

            if (fisioterapeuta.getEmail() == null || fisioterapeuta.getEmail().isEmpty()) {
                System.out.println("Erro: Email é obrigatório");
                return;
            }

            if (fisioterapeuta.getTipoFisioterapeuta() == null) {
                System.out.println("Erro: Especialidade é obrigatória");
                return;
            }

            fisioterapeutaDAO.atualizarFisioterapeuta(fisioterapeuta);

            System.out.println("Fisioterapeuta atualizado com sucesso!");
        }

        
        public void deletarFisioterapeuta(int id) {

            if (id <= 0) {
                System.out.println("Erro: ID inválido");
                return;
            }

            fisioterapeutaDAO.deletarFisioterapeuta(id);

            System.out.println("Fisioterapeuta deletado com sucesso!");
        }
    }
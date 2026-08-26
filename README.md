# Sistema de Gestão — Clínica Beatriz
 
![Banner do Projeto](https://placehold.co/1200x300/2d6a4f/ffffff?text=Sistema+de+Gest%C3%A3o+-+Cl%C3%ADnica+Beatriz)
 
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow?style=for-the-badge)
![License](https://img.shields.io/badge/license-MIT-blue?style=for-the-badge)
 
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)
![Trello](https://img.shields.io/badge/Trello-0052CC?style=for-the-badge&logo=trello&logoColor=white)
 
Sistema desenvolvido para auxiliar no gerenciamento administrativo e clínico de uma clínica de fisioterapia. O sistema permitirá o controle de pacientes, profissionais, avaliações clínicas, sessões de atendimento, evoluções de tratamento e registros de pagamentos.
 
> 🎓 **Projeto acadêmico** — desenvolvido com foco em aprendizado prático de desenvolvimento de software, aplicando conceitos de engenharia de software em um cenário real.
 
## Índice
 
- [Objetivo do Projeto](#objetivo-do-projeto)
- [Status do Projeto](#status-do-projeto)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Funcionalidades do Sistema](#funcionalidades-do-sistema)
- [Fluxo do Sistema](#fluxo-do-sistema)
- [Como Executar](#como-executar)
- [Prints do Sistema](#prints-do-sistema)
- [Equipe de Desenvolvimento](#equipe-de-desenvolvimento)
- [Licença](#licença)
## Objetivo do Projeto
 
Este projeto possui dois objetivos principais:
 
- Desenvolver um sistema funcional para a clínica
- Servir como ambiente de aprendizado prático em desenvolvimento de software
## Status do Projeto
 
🚧 **Em desenvolvimento**
 
O projeto está sendo construído em etapas:
 
1. **MVP (fase atual)** — versão com as funcionalidades básicas para validar o fluxo completo do sistema (cadastro de pacientes, avaliações, sessões e evolução clínica), implementada em **Java puro** com JDBC.
2. **Migração para Spring Boot** — após a conclusão do MVP, o backend será migrado de Java puro para **Spring Boot**, trazendo uma arquitetura mais robusta e escalável.
## Tecnologias Utilizadas
 
**Backend**
- Java
- JDBC
**Banco de Dados**
- MySQL
**Frontend**
- HTML
- CSS
- JavaScript
**Controle de Versão**
- Git
- GitHub
**Gestão de Tarefas**
- Trello
## Estrutura do Projeto
 
```
src
 ├─ model
 ├─ dao
 ├─ service
 ├─ controller
 └─ connection
 
web
 ├─ site
 └─ sistema
```
 
## Funcionalidades do Sistema
 
- Cadastro de pacientes
- Cadastro de fisioterapeutas
- Registro de avaliações clínicas
- Controle de sessões de atendimento
- Registro de evolução clínica
- Controle de pagamentos
## Fluxo do Sistema
 
```
Paciente
   ↓
Avaliação Inicial
   ↓
Sessões de Atendimento
   ↓
Evolução Clínica
```
 
## Como Executar
 
> ⚠️ Seção em construção — os passos abaixo são uma ilustracao e serão atualizados com os comandos reais assim que o MVP estiver pronto para execução.
 
### Pré-requisitos
 
- [ ] JDK (versão a definir)
- [ ] MySQL instalado e em execução
- [ ] Git
### Passos
 
```bash
# 1. Clonar o repositório
git clone <url-do-repositorio>
 
# 2. Configurar o banco de dados
# (instruções de configuração do MySQL a definir)
 
# 3. Compilar o projeto
# (comando de build a definir)
 
# 4. Executar a aplicação
# (comando de execução a definir)
```
 
## Prints do Sistema
 
> ⚠️ As imagens abaixo são Ilustrativas.
 
<table>
  <tr>
    <td align="center">
      <img src="https://placehold.co/400x250/1b4332/ffffff?text=Tela+de+Pacientes" width="100%"/>
      <br />
      <sub>Cadastro de Pacientes</sub>
    </td>
    <td align="center">
      <img src="https://placehold.co/400x250/1b4332/ffffff?text=Avaliação+Clínica" width="100%"/>
      <br />
      <sub>Avaliação Clínica</sub>
    </td>
    <td align="center">
      <img src="https://placehold.co/400x250/1b4332/ffffff?text=Sessões" width="100%"/>
      <br />
      <sub>Sessões de Atendimento</sub>
    </td>
  </tr>
</table>
## Equipe de Desenvolvimento
 
Projeto desenvolvido por uma equipe de três desenvolvedores com foco em aprendizado colaborativo e aplicação prática de conceitos de engenharia de software.
 
### 👥 Contribuidores
 
<table>
  <tr>
    <td align="center">
      <a href="https://github.com/arthurf4321">
        <img src="https://github.com/arthurf4321.png" width="100px;" alt=""/>
        <br />
        <sub><b>Arthur Felipe</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/Paulo9260">
        <img src="https://github.com/Paulo9260.png" width="100px;" alt=""/>
        <br />
        <sub><b>Paulo Vitor</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/marcosalves43">
        <img src="https://github.com/marcosalves43.png" width="100px;" alt=""/>
        <br />
        <sub><b>Marcos Alvez</b></sub>
      </a>
    </td>
  </tr>
</table>
## Licença
 
Este projeto está licenciado sob a licença **MIT**. Consulte o arquivo `LICENSE` para mais detalhes.
 

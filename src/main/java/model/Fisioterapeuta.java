package model;

public class Fisioterapeuta {
	

		private int id;
		private String nome;
		private String cpf;
		private String telefone;
		private String email;
		private Especialidade especialidade;
		
		
		
		//Enums
		public enum Especialidade{ 
			ORTOPEDIA,
			NEUROLOGIA,
			ESPORTIVA,
			PEDIATRIA,
			GERIATRIA,
			RESPIRATORIA
		}
		
		  // Construct
		
		public Fisioterapeuta (int id, String nome, String cpf, String telefone, String email, Especialidade tipoFisioterapeuta) {
			this.id = id;
			this.nome = nome;
			this.cpf =cpf;
			this.telefone = telefone;
			this.email = email;
			this.especialidade = tipoFisioterapeuta;
		}
		
		public Fisioterapeuta() {};
		
		 // Gets and Sets
		public int getId() {return id;}
		public void setId(int id) {this.id = id;}
		public String getNome() {return nome;}
		public void setNome(String nome) {this.nome = nome;}
		public String getCpf() {return cpf;}
		public void setCpf(String cpf) {this.cpf = cpf;}
		public String getTelefone() {return telefone;}
		public void setTelefone(String telefone) {this.telefone = telefone;}
		public String getEmail() {return email;}
		public void setEmail(String email) {this.email = email;}
		public Especialidade getTipoFisioterapeuta() {return especialidade;}
		public void setTipoFisioterapeuta(Especialidade tipoFisioterapeuta) {this.especialidade = tipoFisioterapeuta;}
	}



package model.entidade;

import java.util.Arrays;
import java.util.Objects;

public class Funcionario extends Pessoa {
	
	private String email;
	private String senha;
	private boolean privilegio;
	private String cargo;
	
	public Funcionario(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}
	
	public Funcionario(
			String cpf, 
			String rg, 
			String nome, 
			Endereco endereco,
			String nascimento, 
			String email,
			String senha,
			boolean privilegio, 
			String cargo)
	{
		super(cpf, rg, nome, endereco, nascimento);
		this.email = email;
		this.senha = senha;
		this.privilegio = privilegio;
		this.cargo = cargo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean getPrivilegio() {
		return privilegio;
	}

	public void setPrivilegio(boolean privilegio) {
		this.privilegio = privilegio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(email, senha);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		return Objects.equals(email, other.email) && Objects.equals(senha, other.senha);
	}

	@Override
	public String toString() {
		
		String format = """
				CPF: %s
				RG: %s
				Nome: %s
				Endereço: %s
				Nascimento: %s
				Email: %s
				Privilégio: %s
				Cargo: %s
				Telefones: %s""";
		
		format = String.format(format, 
				getCpf(), 
				getRg(), 
				getNome(), 
				getEndereco().fullEndereco(),
				getNascimento(),
				getEmail(),
				getPrivilegio(),
				getCargo(),
				(getTelefone() == null ? "[]" : Arrays.toString(getTelefone().telefone())));
		
		return format;
	}
}

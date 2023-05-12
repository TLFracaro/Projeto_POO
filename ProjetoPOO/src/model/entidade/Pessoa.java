package model.entidade;

import java.util.Arrays;
import java.util.Objects;

public abstract class Pessoa {
	
	private String cpf; 
	private String rg;
	private String nome;
	private Endereco endereco;
	private String nascimento;
	private Telefone telefone;
	
	public Pessoa() {
		
	}
	
	public Pessoa(
			String cpf, 
			String rg,
			String nome,
			Endereco endereco,
			String nascimento
			) 
	{
		this.cpf = cpf;
		this.rg = rg;
		this.nome = nome;
		this.nascimento = nascimento;
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, rg);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(rg, other.rg);
	}

	@Override
	public String toString() {
		return "Nome: " + nome
				+ "\nCPF: " + cpf
				+ "\nRG: " + rg
				+ "\nNascimento: " + nascimento
				+ "\nTelefone: " + Arrays.toString(telefone.telefone())
				+ "\nEndereco: " + endereco.fullEndereco();
	}
}

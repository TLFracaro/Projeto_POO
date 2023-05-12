package model.service;

import model.entidade.Endereco;
import model.entidade.Funcionario;
import model.entidade.Telefone;

public class Main {

	public static void main(String[] args) {
		
		Endereco endereco = new Endereco(
				"00000000", 
				"Rua Algo", 
				"Bairro Algo", 
				"São Paulo", 
				"SP", 
				100, 
				"Brasil");
		
		Funcionario funcionario = new Funcionario("00000000000", 
				"000000000", 
				"Admin 123", 
				endereco, 
				"1960-12-12", 
				"admin@admin.com", 
				"admin", true, 
				"Analista de Sistemas");
		
		String[] telefones = {"00000000000", "11111111111"};
		
		Telefone telefone = new Telefone(telefones);
		
		funcionario.setTelefone(telefone);
		
		FuncionarioService.insert(funcionario, endereco, telefones);
		
		Funcionario funcionarioRetornado = FuncionarioService.select(new Funcionario("admin@admin.com", "admin"));
		
		System.out.println(funcionarioRetornado);
	}

}

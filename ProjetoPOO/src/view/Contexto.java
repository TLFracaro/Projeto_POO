package view;

import model.entidade.Funcionario;

public class Contexto {
	
	private Funcionario funcionario;
	public static Contexto contexto = new Contexto();
	
	public Contexto(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public Contexto() {
		
	}
}

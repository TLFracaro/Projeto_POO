package model.service;

import model.dao.FuncionarioDAO;
import model.entidade.Funcionario;

public class FuncionarioService {
	
	private static FuncionarioDAO db = new FuncionarioDAO(); 

	public static Funcionario select(Funcionario funcionario) {
		db.conectar();
		funcionario = db.select(funcionario);
		db.desconectar();
		return funcionario;
	}
	
//	public static ArrayList<Funcionario> selectAll() {
//		db.conectar();
//		Collection<Funcionario> colecao = db.selectAll();
//		db.desconectar();
//		ArrayList<Funcionario> funcionarios = (ArrayList<Funcionario>) colecao;
//		return funcionarios;
//	}
	
	public static boolean insert(Object...args) {
		db.conectar();
		boolean state = db.insert(args);
		db.desconectar();
		return state;
	}
	
//	public static boolean update(Funcionario funcionario) {
//		db.conectar();
//		boolean state = db.update(funcionario);
//		db.desconectar();
//		return state;
//	}
//	
//	public static boolean delete(Funcionario funcionario) {
//		db.conectar();
//		boolean state = db.delete(funcionario);
//		db.desconectar();
//		return state;
//	}
}

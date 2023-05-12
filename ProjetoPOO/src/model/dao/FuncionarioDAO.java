package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.entidade.Endereco;
import model.entidade.Funcionario;
import model.entidade.Telefone;

@SuppressWarnings("unchecked")
public final class FuncionarioDAO implements DAO {
	
	private Connection con;
	private Statement stm;
	private ResultSet result;

	private final String servidor = "jdbc:mysql://localhost:3306/holerite";
	private final String usuario = "root";
	private final String senha = "";
	private final  String driver = "com.mysql.cj.jdbc.Driver";

	@Override
	public boolean conectar() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(servidor, usuario, senha);
			stm = con.createStatement();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return conectado();
		
	}

	@Override
	public boolean desconectar() {
		try {
			this.con.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return conectado();
		
	}

	@Override
	public boolean conectado() {
		return con != null;
	}

	@Override
	public <T> T select(T t) {
		try {
			
			
			Funcionario input = (Funcionario) t;
			
			String query = """
					SELECT P.*, F.email, F.senha, F.privilegio, F.cargo, E.logradouro, E.bairro, E.cidade, E.uf, E.numero, E.pais
					FROM (Pessoa P, Cep C)
					INNER JOIN (Funcionario F, Endereco E)
					ON P.cpf = F.cpf
					AND P.cep = C.cep;""";
			
			result = stm.executeQuery(query);
			
			Telefone telefone = null;
			String telefones = "";
			
			Funcionario funcionario = null;
			
			while (result.next()) {
				
				String email = result.getString("email"), senha = result.getString("senha");
				
				if (input.getEmail().equals(email) && input.getSenha().equals(senha)) {
					
					Endereco endereco = new Endereco(
							result.getString("cep"),
							result.getString("logradouro"), 
							result.getString("bairro"),
							result.getString("cidade"), 
							result.getString("uf"), 
							result.getInt("numero"),
							result.getString("pais"));
					
					funcionario = new Funcionario(
							result.getString("cpf"),
							result.getString("rg"),
							result.getString("nome"),
							endereco,
							result.getString("nascimento"),
							result.getString("email"),
							result.getString("senha"),
							result.getBoolean("privilegio"),
							result.getString("cargo"));
				}
			}
			
			if (funcionario == null) {
				return null;
			}
			
			query = """
					SELECT Telefone.cpf, telefone FROM Pessoa, Telefone
					WHERE Pessoa.cpf = Telefone.cpf;""";
			
			result = stm.executeQuery(query);
			
			
			while (result.next()) {
				if (funcionario.getCpf().equals(result.getString("cpf"))) {
					telefones += result.getString("telefone") + ";";
				}
			}
			
			if (telefones.length() >= 11) {
				telefone = new Telefone(telefones.split(";"));
				funcionario.setTelefone(telefone);
			}
			
			return funcionario != null ? (T) funcionario : null;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public List<Funcionario> selectAll() {
		try {
			String query = """
					SELECT P.*, F.email, F.senha, F.privilegio, F.cargo, E.logradouro, E.bairro, E.cidade, E.uf, E.numero, E.pais
					FROM (Pessoa P, Cep C)
					INNER JOIN (Funcionario F, Endereco E)
					ON P.cpf = F.cpf
					AND P.cep = C.cep;""";
			
			result = stm.executeQuery(query);
			
			ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
			
			while (result.next()) {
				
				Endereco endereco = new Endereco(
						result.getString("cep"),
						result.getString("logradouro"), 
						result.getString("bairro"),
						result.getString("cidade"), 
						result.getString("uf"), 
						result.getInt("numero"),
						result.getString("pais"));
				
				funcionarios.add(new Funcionario(
						result.getString("cpf"),
						result.getString("rg"),
						result.getString("nome"),
						endereco,
						result.getString("nascimento"),
						result.getString("email"),
						result.getString("senha"),
						result.getBoolean("privilegio"),
						result.getString("cargo")));
			}
			
			query = """
					SELECT Telefone.cpf, telefone FROM Pessoa, Telefone
					WHERE Pessoa.cpf = Telefone.cpf;""";
			
			result = stm.executeQuery(query);
			
			String relatives = "";
			String telefones = "";
			while (result.next()) {
				relatives += result.getString("cpf") + ";";
				telefones += result.getString("telefone") + ";";
			}
			
			String[] cpfs = relatives.split(";");
			String[] phone = telefones.split(";");
			String temp = "";
			for (int i = 0; i < cpfs.length - 1; i++) {
				if (cpfs[i].equals(cpfs[i+1])) {
					temp += phone[i] + ";" + phone[i+1] + ";";
				}
				for (Funcionario funcionario : funcionarios) {
					if (funcionario.getCpf().equals(cpfs[i])) {
						funcionario.setTelefone(new Telefone(temp.split(";")));
					}
				}
			}
			return funcionarios != null ? funcionarios : null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insert(Object...args) {
		try {
			
			if (args == null || args.length != 3) {
				throw new Exception("Os argumentos passados não são válidos!");
			}
			
			Funcionario funcionario = null;
			Telefone telefone = null;
			Endereco endereco = null;
			
			int count = 0;
			for (Object obj : args) {
				if (obj instanceof Funcionario) {
					funcionario = (Funcionario) obj;
					args[count] = funcionario;
					count++;
				}
				else if (obj instanceof Telefone) {
					telefone = (Telefone) obj;
					args[count] = telefone;
					count++;
				}
				else if (obj instanceof Endereco) {
					endereco = (Endereco) obj;
					args[count] = endereco;
					count++;
				}
			}
			
			for (Object obj : args) {
				if (obj == null) {
					return false;
				}
			}
			
			String query = """
					INSERT INTO Pessoa VALUES ("%s", "%s", "%s", "%s", "%s");
					INSERT INTO Endereco VALUES ("%s", "%s", "%s", "%s", "%s", %d, "%s");
					INSERT INTO Funcionario VALUES ("%s", "%s", "%s", "%s", %b, "%s");
					INSERT INTO Cep VALUES ("%s", "%s", "%s");""";
			
			String[] queries = query.split(";");
			
			queries[0] = String.format(queries[0], 
					funcionario.getCpf(), 
					funcionario.getRg(), 
					funcionario.getNome(), 
					endereco.cep(), 
					funcionario.getNascimento());
			
			queries[1] = String.format(queries[1], 
					endereco.cep(), 
					endereco.logradouro(), 
					endereco.bairro(), 
					endereco.cidade(), 
					endereco.uf(), 
					endereco.numero(), 
					endereco.pais());
			
			queries[2] = String.format(queries[2], 
					funcionario.getCpf(), 
					funcionario.getRg(), 
					funcionario.getEmail(), 
					funcionario.getSenha(), 
					funcionario.getPrivilegio(), 
					funcionario.getCargo());
			
			queries[3] = String.format(queries[3], 
					funcionario.getCpf(), 
					funcionario.getRg(), 
					endereco.cep());
			
			
			for (String index : queries) {
				stm.execute(index);
			}
			
			for (String telefones : telefone.telefone()) {
				query = """
						INSERT INTO Telefone VALUES ("%s", "%s", "%s");""";
				
				query = String.format(query, 
						funcionario.getCpf(), 
						funcionario.getRg(), 
						telefones);
				
				stm.execute(query);
			}
			
			return true;
		}
		catch (SQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(null, "Estes documentos já possuem registro");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Object...args) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean delete(Object...args) {
		// TODO Auto-generated method stub
		return false;
	}
	
}

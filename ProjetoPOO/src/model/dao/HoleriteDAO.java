package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public final class HoleriteDAO implements DAO {

	private Connection con;
	private Statement stm;
	private ResultSet result;

	private final String servidor = "jdbc:mysql://localhost:3306/bdprojetopoo";
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
	public Object select(Object...args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Object...args) {
		// TODO Auto-generated method stub
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

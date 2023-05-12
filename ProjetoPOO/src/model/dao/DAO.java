package model.dao;

import java.util.List;

public sealed interface DAO permits FuncionarioDAO, HoleriteDAO {
	
	public boolean conectar();
	
	public boolean desconectar();
	
	public boolean conectado();
	
	public <T> T select(T t);
	
	public <T> List<T> selectAll();
	
	public boolean insert(Object...args);
	
	public boolean update(Object...args);
	
	public boolean delete(Object...args);
}

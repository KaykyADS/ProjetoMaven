package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Carro;

public interface ICrudDao<T> {
	public void inserir(Carro c) throws SQLException, ClassNotFoundException;
    public void atualizar(Carro c) throws SQLException, ClassNotFoundException;
    public void excluir(Carro c) throws SQLException, ClassNotFoundException;
    public T buscar(Carro c) throws SQLException, ClassNotFoundException;
    public List<Carro> listar() throws SQLException, ClassNotFoundException;
}

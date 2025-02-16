package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Carro;

public class CarroDao implements ICrudDao<Carro> {

	private GenericDao gDao;
	
	public CarroDao(GenericDao gDao) {
		this.gDao = gDao;
	}
	
	
	@Override
	public void inserir(Carro c) throws SQLException, ClassNotFoundException {
		System.out.println("Foi");
		Connection cn = gDao.getConnection();
		String sql = "INSERT INTO carros(placa, marca, modelo, ano, cor) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps = cn.prepareStatement(sql);
		ps.setString(1, c.getPlaca());
		System.out.println("Inserido placa: " + c.getPlaca());
		ps.setString(2, c.getMarca());
		System.out.println("Inserido marca: " + c.getMarca());
		ps.setString(3, c.getModelo());
		System.out.println("Inserido modelo: " + c.getModelo());
		ps.setInt(4, c.getAno());
		System.out.println("Inserido ano: " + c.getAno());
		ps.setString(5, c.getCor());
		System.out.println("Inserido cor: " + c.getCor());
		ps.executeUpdate();
		ps.close();
		cn.close();
	}

	@Override
	public void atualizar(Carro c) throws SQLException, ClassNotFoundException {
		Connection cn = gDao.getConnection();
		String sql = "UPDATE carros SET marca = ?, modelo = ?, ano = ?, cor = ? WHERE placa = ?";
		PreparedStatement ps = cn.prepareStatement(sql);
		ps.setString(1, c.getMarca());
		ps.setString(2, c.getModelo());
		ps.setInt(3, c.getAno());
		ps.setString(4, c.getCor());
		ps.setString(5, c.getPlaca());
		ps.executeUpdate();
		ps.close();
		cn.close();
	}

	@Override
	public void excluir(Carro c) throws SQLException, ClassNotFoundException {
		Connection cn = gDao.getConnection();
		String sql = "DELETE carros WHERE placa = ?";
		PreparedStatement ps = cn.prepareStatement(sql);
		ps.setString(1, c.getPlaca());
		ps.executeUpdate();
		ps.close();
		cn.close();
	}

	@Override
	public Carro buscar(Carro c) throws SQLException, ClassNotFoundException {
		Connection cn = gDao.getConnection();
		String sql = "SELECT placa, marca, modelo, ano, cor FROM carros WHERE placa = ?";
		PreparedStatement ps = cn.prepareStatement(sql);
		ps.setString(1, c.getPlaca());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			c.setPlaca(rs.getString("placa"));
			c.setMarca(rs.getString("marca"));
			c.setModelo(rs.getString("modelo"));
			c.setAno(rs.getInt("ano"));
			c.setCor(rs.getString("cor"));
			System.out.println("A placa buscada: " + c.getPlaca());
		}
		rs.close();
		ps.close();
		cn.close();		
		return c;
	}

	@Override
	public List<Carro> listar() throws SQLException, ClassNotFoundException {
	    System.out.println("Foi listar");
	    List<Carro> carros = new ArrayList<Carro>();
	    Connection cn = gDao.getConnection();
	    String sql = "SELECT placa, marca, modelo, ano, cor FROM carros";
	    PreparedStatement ps = cn.prepareStatement(sql);
	    ResultSet rs = ps.executeQuery();
	    
	    while (rs.next()) {
	        Carro c = new Carro();
	        c.setPlaca(rs.getString("placa"));
	        c.setMarca(rs.getString("marca"));
	        c.setModelo(rs.getString("modelo"));
	        c.setAno(rs.getInt("ano"));
	        c.setCor(rs.getString("cor"));
	        
	        carros.add(c);
	    }
	    
	    rs.close();
	    ps.close();
	    cn.close();
	    
	    return carros;
	}
}
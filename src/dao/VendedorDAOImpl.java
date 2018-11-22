package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidade.Vendedor;

public class VendedorDAOImpl implements VendedorDAO {

	ConnectionSingleton conSing = ConnectionSingleton.getInstancy();
	Connection con = conSing.getcon();
			
	@Override
	public void adicionar(Vendedor v) throws GenericDAOException {
		String sql = "INSERT INTO vendedor (matricula, nome, salario, nivelExperiencia) "
				+ "VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, v.getMatricula());
			pstmt.setString(2, v.getNome());
			pstmt.setFloat(3, v.getSalario());
			pstmt.setString(4, v.getNivelExperiencia());
			pstmt.executeUpdate();
			pstmt.close();			
		} catch (SQLException e) {
			throw new GenericDAOException( e );
		}
	}

	@Override
	public List<Vendedor> pesquisarPorNome(String nome) throws GenericDAOException {
		List<Vendedor> lista = new ArrayList<>();
		String sql = "SELECT * FROM vendedor WHERE nome like ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + nome + "%");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Vendedor v = new Vendedor();
				v.setMatricula(rs.getInt("matricula"));
				v.setNome(rs.getString("nome"));
				v.setSalario(rs.getFloat("salario"));
				v.setNivelExperiencia(rs.getString("nivelExperiencia"));
				lista.add( v );
			}
		} catch (SQLException e) {
			throw new GenericDAOException( e );
		}
		return lista;
	}

	@Override
	public void remover(int matricula) throws GenericDAOException {
		String sql = "DELETE FROM vendedor WHERE matricula = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, matricula);
			pstmt.executeUpdate();
			pstmt.close();			
		} catch (SQLException e) {
			throw new GenericDAOException( e );
		}
		
	}

	@Override
	public Vendedor pesquisarPorMatricula(int matricula) throws GenericDAOException {
		Vendedor v = new Vendedor();
		String sql = "SELECT * FROM vendedor WHERE matricula = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, matricula);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				v.setMatricula(rs.getInt("matricula"));
				v.setNome(rs.getString("nome"));
				v.setSalario(rs.getFloat("salario"));
				v.setNivelExperiencia(rs.getString("nivelExperiencia"));
			}
		} catch (SQLException e) {
			throw new GenericDAOException( e );
		}
		return v;
	}

	@Override
	public void salvar(int matricula, Vendedor v) throws GenericDAOException {
		String sql = "UPDATE vendedor SET nome = ?, salario = ?, nivelExperiencia = ? "
				+ "WHERE matricula = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, v.getNome());
			pstmt.setFloat(2, v.getSalario());
			pstmt.setString(3, v.getNivelExperiencia());
			pstmt.setInt(4, v.getMatricula());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			throw new GenericDAOException( e );
		}
	}
	
}

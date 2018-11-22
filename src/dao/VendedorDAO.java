package dao;

import java.util.List;

import entidade.Vendedor;

public interface VendedorDAO {
	public void adicionar(Vendedor v) throws GenericDAOException;
	public List<Vendedor> pesquisarPorNome(String nome) throws GenericDAOException;
	public void remover(int matricula) throws GenericDAOException;
	public Vendedor pesquisarPorMatricula(int matricula) throws GenericDAOException;
	public void salvar(int matricula, Vendedor v) throws GenericDAOException;
}

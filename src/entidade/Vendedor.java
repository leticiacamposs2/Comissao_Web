package entidade;

import java.io.Serializable;

public class Vendedor implements Serializable  {
	private static final long serialVersionUID = 1985700417488988006L;
	
	private int matricula;
	private String nome = "";
	private float salario;
	private String nivelExperiencia = "";
	
	public int getMatricula() {
		return matricula;
	}
	
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public float getSalario() {
		return salario;
	}
	
	public void setSalario(float salario) {
		this.salario = salario;
	}
	
	public String getNivelExperiencia() {
		return nivelExperiencia;
	}
	
	public void setNivelExperiencia(String nivelExperiencia) {
		this.nivelExperiencia = nivelExperiencia;
	}
	
	@Override
	public String toString() {
		StringBuffer s = new StringBuffer();
		s.append("Matricula:");
		s.append(this.getMatricula());
		s.append("\tNome:");
		s.append(this.getNome());
		s.append("\tSalário:");
		s.append(this.getSalario());
		s.append("\tNível de Experiência:");
		s.append(this.getNivelExperiencia());
		return s.toString();
	}
}
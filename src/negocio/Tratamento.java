package negocio;

import java.util.ArrayList;

public class Tratamento {

	private String nome;
	private ArrayList<Funcionario> especialistas;
	
	public Tratamento(String nome){
		especialistas = new ArrayList<Funcionario>();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Funcionario> getEspecialistas() {
		return this.especialistas;
	}

	public void setEspecialistas(ArrayList<Funcionario> especialistas) {
		this.especialistas = especialistas;
	}
	
	@Override
	public String toString() {
		return this.nome + this.especialistas;
	}

}

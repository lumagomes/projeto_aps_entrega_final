package negocio;

public class Funcionario {

	private Pessoa pessoa;
	private Cargo cargo;

	public Funcionario(Pessoa pessoa, Cargo cargo) {
		this.pessoa = pessoa;
		this.cargo = cargo;
	}

	public Pessoa getPessoa(){
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa){
		this.pessoa = pessoa;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	public String toString(){
		return this.pessoa.getNome();
	}

}

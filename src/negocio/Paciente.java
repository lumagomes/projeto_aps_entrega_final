package negocio;

public class Paciente {
	
	private Pessoa pessoa;
	private Plano plano;
	
	public Paciente(Pessoa pessoa, Plano plano){
		this.pessoa = pessoa;
		this.plano = plano;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}		
}

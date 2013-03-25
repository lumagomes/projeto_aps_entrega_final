package gerenciadores;

import java.util.ArrayList;
import java.util.List;

import negocio.Cargo;
import negocio.Funcionario;
import excecao.Excecao;

public class GerenciadorFuncionario {

	private ArrayList<Funcionario> funcionarios;
	private GerenciadorPessoa gp;

	public GerenciadorFuncionario(){
		funcionarios = new ArrayList<Funcionario>();
		gp = new GerenciadorPessoa();
	}

	public ArrayList<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public boolean addFuncionario(Funcionario f){
		boolean retorno = false;
		this.validateCargo(f);
		if(!gp.validarCamposBranco(f.getPessoa().getNome(), f.getPessoa().getCpf())){
			gp.validar(f.getPessoa());
			gp.validateData(f.getPessoa());
			if(!this.verificarExistenciaCpf(f.getPessoa().getCpf())){
				funcionarios.add(f);
				retorno = true;
			}
		}
		return retorno;
	}

	public boolean editarFuncionario(Funcionario antigo, Funcionario novo) {
		boolean retorno = false;
		int indiceFuncAntigo = this.getFuncionarios().indexOf(antigo);
		if(!gp.validarCamposBranco(novo.getPessoa().getNome(), novo.getPessoa().getCpf())){
			gp.validar(novo.getPessoa());
			gp.validateData(novo.getPessoa());
			if (indiceFuncAntigo == -1 || this.verificarExistenciaCpf(novo.getPessoa().getCpf()))
				throw new Excecao(Excecao.CPF_EXISTENTE);
			this.getFuncionarios().set(indiceFuncAntigo, novo);
			retorno = true;

		}
		return retorno;
	}

	public boolean editarCargoFuncionario(Funcionario func, Cargo c) throws Excecao {
		boolean retorno = false;		
		for (Funcionario f : funcionarios) {
			if(c != null){
				if(f.equals(func)){
					f.setCargo(c);
					retorno = true;
				}
				else throw new Excecao(Excecao.FUNCIONARIO_INEXISTENTE);
			}			
		}
		return retorno;
	}

	public boolean removerFuncionario(Funcionario f) {
		int numeroDeRegistrosAntes = funcionarios.size();
		funcionarios.remove(f);
		if (funcionarios.size() == numeroDeRegistrosAntes) {
			throw new Excecao(Excecao.FUNCIONARIO_INEXISTENTE);
		} else 
			return true;
	}

	public Funcionario buscarPeloCpf(String cpf) {
		Funcionario func = null;
		for (Funcionario f : funcionarios) {
			if(f.getPessoa().getCpf().equals(cpf))
				func = f;
		}
		return func;
	}

	public List<Funcionario> buscarPeloNome(String nome) {
		List<Funcionario> lista = new ArrayList<Funcionario>();
		for (Funcionario f : funcionarios) {
			if(f.getPessoa().getNome().contains(nome))
				lista.add(f);
		}
		return lista;
	}
	
	public List<Funcionario> buscarPeloCargo(Cargo cargo){
		List<Funcionario> lista = new ArrayList<Funcionario>();
		for (Funcionario f : funcionarios) {
			if(f.getCargo().equals(cargo))
				lista.add(f);
		}
		return lista;
	}

	public boolean verificarExistenciaCpf(String cpf){
		for(Funcionario f : funcionarios){
			if(f.getPessoa().getCpf().equals(cpf)) return true;
		}	
		return false;		
	}

	private void validateCargo(Funcionario f) throws Excecao{
		if(f.getCargo() == null){
			throw new Excecao(Excecao.CARGO_INEXISTENTE); 
		}

	}

}

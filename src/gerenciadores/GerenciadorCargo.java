package gerenciadores;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import excecao.Excecao;

import negocio.*;


public class GerenciadorCargo {
	private List<Cargo> cargos;  

	public GerenciadorCargo(){
		cargos = new ArrayList<Cargo>();
	}

	public List<Cargo> getCargos() {
		return cargos;
	}
	public boolean addCargo(Cargo c) {
		boolean retorno = false;
		this.validate(c);
		if (!this.verficarExistencia(c.getFuncao(), c.getTipo())) {
			if (!this.validarCamposBranco(c.getFuncao(), c.getTipo(), c.getSalarioPorHora(),
					c.getCargaHoraria())) {
				if (c.getTipo() == TipoDoCargo.Efetivo) {
					c.setSalarioPorHora(c.getSalarioPorHora());
				} else {
					c.setSalarioPorHora(c.getSalarioPorHora() * (-0.2));
				}
				cargos.add(c);
				retorno = true;
			}
		}
		return retorno;
	}

	public boolean editarCargo(Cargo antigo, Cargo novo) throws Excecao {
		int indiceDoCargoAntigo = this.getCargos().indexOf(antigo);
		this.validate(novo);
		if (indiceDoCargoAntigo == -1 || this.verficarExistencia(novo.getFuncao(), novo.getTipo()))
			throw new Excecao(Excecao.CARGO_INEXISTENTE);
		if (this.validarCamposBranco(novo.getFuncao(), novo.getTipo(), novo.getSalarioPorHora(), novo.getCargaHoraria()))
			throw new Excecao(Excecao.CARGO_FUNCAO_VALOR_INVALIDO);
		this.getCargos().set(indiceDoCargoAntigo, novo);
		return true;
	}

	public List<Cargo> buscarCargoPelaFuncao(String funcao) {
		List<Cargo> retorno = new ArrayList<Cargo>();
		for (Cargo c : this.getCargos()) {
			if (c.getFuncao().equalsIgnoreCase(funcao)) {
				retorno.add(c);
			}
		}
		return retorno;
	}
	
	public List<Cargo> buscarCargoPeloTipo(TipoDoCargo tipo) {
		List<Cargo> retorno = new ArrayList<Cargo>();
		for (Cargo c : this.getCargos()) {
			if (c.getTipo().equals(tipo)) {
				retorno.add(c);
			}
		}
		return retorno;
	}
	
	public boolean removerCargo(Cargo cargoParaRemover) throws Excecao {
		int numeroDeRegistrosAntes = getCargos().size();
		getCargos().remove(cargoParaRemover);

		if (getCargos().size() == numeroDeRegistrosAntes) {
			throw new Excecao(Excecao.CARGO_INEXISTENTE);
		} else
			return true;
	}
	
	public Cargo buscaAvancada(String funcao, TipoDoCargo tipo){
		Cargo c = null;
		for (Cargo cc : this.cargos) {
			if (cc.getFuncao().equals(funcao) && cc.getTipo() == tipo) {
				c = cc;
			}
		}
		return c;
	}
	

	public boolean verficarExistencia(String funcao, TipoDoCargo tipo){
		for(Cargo c:cargos){
			if((c.getFuncao().equalsIgnoreCase(funcao)) && (c.getTipo() == tipo)) return true;
		}
		return false;		
	}

	public boolean validarCamposBranco(String funcao, TipoDoCargo tipo, double salario,
			int cargaHoraria) {
		if(funcao.isEmpty() || tipo == null || salario < 1.0 || cargaHoraria < 1){
			return true;
		} else{
			return false;
		}
	}
	
	public void validate(Cargo c) throws Excecao{
		Pattern padrao = Pattern.compile("[A-Za-z\\s0-9]+");
		Matcher pesquisa = padrao.matcher(c.getFuncao());
		
		if(!pesquisa.matches())
			throw new Excecao(Excecao.CARGO_FUNCAO_VALOR_INVALIDO);
	}

}

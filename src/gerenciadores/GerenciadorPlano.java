package gerenciadores;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import negocio.Plano;
import excecao.Excecao;

public class GerenciadorPlano {

	private ArrayList<Plano> planos;
	
	public GerenciadorPlano() {
		planos = new ArrayList<Plano>();
	}

	public ArrayList<Plano> getPlanos() {
		return planos;
	}

	public boolean addPlano(Plano plano) {
		this.validarExistencia(plano);
		if (!this.validarCamposEmBranco(plano.getNome(), plano.getValor(),
				plano.getCodigo())) {
			this.validate(plano);
			planos.add(plano);
			return true;
		}
		return false;
	}

	public boolean editarPlano(Plano antigo, Plano novo) {
		boolean retorno = false;
		int indiceAntigo = this.getPlanos().indexOf(antigo);
		if (!this.validarCamposEmBranco(novo.getNome(), novo.getValor(),
				novo.getCodigo())) {
			this.validate(novo);
			if (indiceAntigo == -1) {
				this.validarExistencia(novo);
			}
			this.getPlanos().set(indiceAntigo, novo);
			retorno = true;
		}
		return retorno;
	}

	public boolean removerPlano(Plano plano) {
		int numeroDeRegistrosAntes = planos.size();
		planos.remove(plano);
		if (planos.size() == numeroDeRegistrosAntes) {
			throw new Excecao(Excecao.PLANO_INEXISTENTE);
		} else
			return true;
	}

	public Plano buscarPlanoPeloCodigo(String cod) {
		Plano retorno = null;
		for (Plano p : planos) {
			if (p.getCodigo().equals(cod)) {
				retorno = p;
			}
		}
		return retorno;
	}

	public Plano buscarPlanoPeloNome(String nome) {
		Plano retorno = null;
		for (Plano p : planos) {
			if (p.getNome().equalsIgnoreCase(nome)) {
				retorno = p;
			}
		}
		return retorno;
	}

	public boolean verficarExistencia(String cod) {
		for (Plano p : planos) {
			if (p.getCodigo().equals(cod))
				return true;
		}
		return false;
	}

	public boolean validarCamposEmBranco(String nome, double valor, String cod) {
		if (nome.isEmpty() || valor < 1.0 || cod.isEmpty())
			return true;
		else
			return false;
	}

	public void validarExistencia(Plano plano) throws Excecao {
		for (Plano p : planos) {
			if (p.getNome().equalsIgnoreCase(plano.getNome())
					|| p.getCodigo().equalsIgnoreCase(plano.getCodigo())) {
				throw new Excecao(Excecao.NOME_OU_CODIGO_EXISTENTE);
			}
		}
	}

	public void validate(Plano p) throws Excecao {
		if (p.getCodigo().length() > 8) {
			throw new Excecao(Excecao.CODIGO_EXCEDEU_LIMITE);
		}

		Pattern padrao = Pattern.compile("[0-9]+");
		Matcher pesquisa = padrao.matcher(p.getCodigo());

		if (!pesquisa.matches())
			throw new Excecao(Excecao.CODIGO_EXCEDEU_LIMITE);
	}

}

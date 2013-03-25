package gerenciadores;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import negocio.Pessoa;
import excecao.Excecao;

public class GerenciadorPessoa {

	public void validar(Pessoa p) throws Excecao {
		Pattern padrao = Pattern.compile("[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}");
		Matcher pesquisa = padrao.matcher(p.getCpf());
		if (!pesquisa.matches()) {
			throw new Excecao(Excecao.CPF_INVALIDO);
		}

		Pattern padrao2 = Pattern.compile("[0-9]{4}-[0-9]{4}");
		pesquisa = padrao2.matcher(p.getTelefone());
		if (!pesquisa.matches()) {
			throw new Excecao(Excecao.TELEFONE_INVALIDO);
		}
	}

	public void validateData(Pessoa p) throws Excecao {
		if (p.getDataNascimento().after(new Date())) {
			throw new Excecao(Excecao.DATA_INVALIDA);
		}
	}

	public boolean validarCamposBranco(String nome, String cpf) {
		if (nome.isEmpty() || cpf.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

}

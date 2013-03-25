package testes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import negocio.Endereco;
import negocio.Estados;
import negocio.Pessoa;

public class AuxiliarFuncPac {
	
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	
	//pessoa com cep invalido
	public Pessoa pessoaComCepInValido() throws ParseException{
		Endereco e = new Endereco("Rua", "bairro", Estados.PARAIBA, "aaaaa-aaa", "11");
		Pessoa p = new Pessoa("Fulano", "111.222.333-01", (Date)format.parse("01/02/1984") ,"9999-9999", e);
		return p;
	}
	
	//pessoa com numero invalido
	public Pessoa pessoaComNumeroInvalido() throws ParseException{
		Endereco e = new Endereco("Rua", "bairro", Estados.PARAIBA, "11.111-111", "///");
		Pessoa p = new Pessoa("Fulano", "111.222.333-08", (Date)format.parse("01/02/1984") ,"9999-9999", e);
		return p;
	}
	
	//pessoa com endereco sem numero
	public Pessoa pessoaComEnderecoSemNumero() throws ParseException{
		Endereco e = new Endereco("Rua", "bairro", Estados.PARAIBA, "11.111-111", "S/N");
		Pessoa p = new Pessoa("Fulano", "111.222.333-18", (Date)format.parse("01/02/1984") ,"9999-9999", e);
		return p;
	}
	
	//pessoa com bairro inválido
	public Pessoa pessoaComBairroInvalido() throws ParseException{
		Endereco e = new Endereco("Rua", "!@#$%¨&*", Estados.PARAIBA, "11.111-111", "S/N");
		Pessoa p = new Pessoa("Fulano", "111.222.333-18", (Date)format.parse("01/02/1984") ,"9999-9999", e);
		return p;
	}
	
	//pessoa com rua inválida
	public Pessoa pessoaComRuaInvalida() throws ParseException{
		Endereco e = new Endereco("!@#$%", "Bairro", Estados.PARAIBA, "11.111-111", "S/N");
		Pessoa p = new Pessoa("Fulano", "111.222.333-18", (Date)format.parse("01/02/1984") ,"9999-9999", e);
		return p;
	}
	
	public Pessoa pessoaComCpfInvalido() throws ParseException{
		Endereco e = new Endereco("Rua", "bairro", Estados.PARAIBA, "11.111-111", "11");
		Pessoa p = new Pessoa("Fulano", "aaa.aaa.aaa-aa", (Date)format.parse("01/02/1984"),"9999-9999", e);
		return p;
	}
	
	public Pessoa pessoaComTelefoneInvalido() throws ParseException{
		Endereco e = new Endereco("Rua", "bairro", Estados.PARAIBA, "11.111-111", "11");
		Pessoa p = new Pessoa("Fulano", "111.111.111-11", (Date)format.parse("01/02/1984") ,"aaaa-aaaa", e);
		return p;
	}
	
	public Pessoa pessoaComDataNascInvalida() throws ParseException{
		Endereco e = new Endereco("Rua", "bairro", Estados.PARAIBA, "11.111-111", "11");
		Pessoa p = new Pessoa("Fulano", "111.111.111-33", (Date)format.parse("01/01/2014") ,"2222-2222", e);
		return p;
	}
	
	public Pessoa pessoaComCpfJaExistente() throws ParseException{
		Endereco e = new Endereco("Rua", "bairro", Estados.PARAIBA, "11.222-333", "11");
		Pessoa p = new Pessoa("Fulano Dois", "111.222.333-00", (Date)format.parse("01/02/1984") ,"9999-9999", e);
		return p;
	}
	
	public Pessoa pessoaComCamposObrigatoriosVazios() throws ParseException{
		Endereco e = new Endereco("Rua", "bairro", Estados.PARAIBA, "11.222-333", "11");
		Pessoa p = new Pessoa("", "", (Date)format.parse("01/02/1984") ,"9999-9999", e);
		return p;
	}

}

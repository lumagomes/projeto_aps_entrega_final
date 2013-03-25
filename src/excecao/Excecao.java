package excecao;

public class Excecao extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String CARGO_INEXISTENTE = "cargo inexistente";
	public static final String CARGO_JA_EXISTENTE = "cargo ja existente";
	public static final String CARGO_FUNCAO_VALOR_INVALIDO = "funcao com valor invalido";
	
	public static final String ENDERECO_CEP_INVALIDO = "endereco cep invalido";
	public static final String ENDERECO_NUMERO_INVALIDO = "endereco numero invalido";
	public static final String ENDERECO_RUA_INVALIDO = "endereco rua invalido";
	public static final String ENDERECO_BAIRRO_INVALIDO = "endereco bairro invalido";
	
	public static final String CPF_INVALIDO = "CPF inválido!";
	public static final String TELEFONE_INVALIDO = "Telefone Inválido";
	public static final String CPF_EXISTENTE = "CPF ja existe";
	public static final String DATA_INVALIDA = "Data de Nascimento Invalida";
	public static final String FUNCIONARIO_INEXISTENTE = "Funcionario inexistente";
	public static final String PACIENTE_INEXISTENTE = "Paciente não existe";

	public static final String NOME_OU_CODIGO_EXISTENTE = "Nome ou Código ja existem.";
	public static final String CODIGO_EXCEDEU_LIMITE = "Codigo excedeu o limite maximo";
	public static final String PLANO_INEXISTENTE = "Plano inexistente";

	public Excecao(String msg) {
		super(msg);
	}
}

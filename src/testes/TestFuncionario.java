package testes;

import excecao.Excecao;
import gerenciadores.ClinicaFachada;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;
import negocio.Cargo;
import negocio.Endereco;
import negocio.Estados;
import negocio.Funcionario;
import negocio.Paciente;
import negocio.Pessoa;
import negocio.Plano;
import negocio.TipoDoCargo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestFuncionario {
	
	ClinicaFachada clinica;
	Funcionario funcionario;
	Cargo cargo;
	Endereco endereco;
	Pessoa pessoa;
	AuxiliarFuncPac aux;
	
	@Before
	public void instanciarFachada(){
		aux = new AuxiliarFuncPac();
		clinica = new ClinicaFachada();
		cargo = new Cargo("Cargo 1", TipoDoCargo.Efetivo, 1000, 8);
		endereco = new Endereco("Rua", "bairro", Estados.PARAIBA, "55.999-999", "11");
		pessoa = new Pessoa("Fulano", "111.222.333-00", new Date(),"9999-9999", endereco);
		funcionario = new Funcionario(pessoa, cargo);
		clinica.addFuncionario(funcionario);
	}
	
	@After
	public void limparCargos(){
		clinica.zerarDados();
	}
	
	@Test
	public void addFuncionario(){
		Assert.assertEquals(funcionario, clinica.listarFuncionarios().get(0));
	}
	
	@Test(expected = Excecao.class)
	public void addFuncionarioSemCargo(){
		Funcionario f = new Funcionario(pessoa, null);
		clinica.addFuncionario(f);
	}
	
	@Test
	public void addFuncionarioExistente(){
		Funcionario f = new Funcionario(pessoa, cargo);
		Assert.assertFalse(clinica.addFuncionario(f));
	}
	
	@Test
	public void addFuncionarioQueJaEhPaciente(){
		Pessoa p = new Pessoa("Cicrano", "111.222.333-44", new Date(),"9999-9999", endereco);
		Plano plano = new Plano("Plano 1", 60.0, "123");
		Paciente pa = new Paciente(p, plano);
		clinica.addPaciente(pa);
		Funcionario f = new Funcionario(pa.getPessoa(), cargo);
		Assert.assertTrue(clinica.addFuncionario(f));
	}
	
	@Test(expected = Excecao.class)
	public void addFuncionarioCepInvalido() throws ParseException{
		Funcionario f = new Funcionario(aux.pessoaComCepInValido(), cargo);
		clinica.addFuncionario(f);
	}
	
	@Test(expected = Excecao.class)
	public void addFuncionarioNumeroInvalido() throws ParseException{
		Funcionario f = new Funcionario(aux.pessoaComNumeroInvalido(), cargo);
		clinica.addFuncionario(f);
	}
	
	@Test
	public void addFuncionarioEnderecoSemNumero() throws ParseException{
		Funcionario f = new Funcionario(aux.pessoaComEnderecoSemNumero(), cargo);
		Assert.assertTrue(clinica.addFuncionario(f));
	}
	
	@Test(expected = Excecao.class)
	public void addCampoBairroInvalido() throws ParseException{
		Funcionario f = new Funcionario(aux.pessoaComBairroInvalido(), cargo);
		clinica.addFuncionario(f);
	}
	
	@Test(expected = Excecao.class)
	public void addCampoRuaInvalido() throws ParseException{
		Funcionario f = new Funcionario(aux.pessoaComRuaInvalida(), cargo);
		clinica.addFuncionario(f);
	}
	
	@Test(expected = Excecao.class)
	public void addFuncionarioCpfInvalido() throws ParseException{
		Funcionario f = new Funcionario(aux.pessoaComCpfInvalido(), cargo);
		clinica.addFuncionario(f);
	}
	
	@Test(expected = Excecao.class)
	public void addFuncionarioTelefoneInvalido() throws ParseException{
		Funcionario f = new Funcionario(aux.pessoaComTelefoneInvalido(), cargo);
		clinica.addFuncionario(f);
	}
	
	@Test(expected = Excecao.class)
	public void addFuncionarioDataNascimentoInvalida() throws ParseException{
		Funcionario f = new Funcionario(aux.pessoaComDataNascInvalida(), cargo);
		clinica.addFuncionario(f);
	}
	
	@Test
	public void addFuncionarioCpfExistente() throws ParseException{
		Funcionario f = new Funcionario(aux.pessoaComCpfJaExistente(), cargo);
		Assert.assertFalse(clinica.addFuncionario(f));
	}
	
	@Test
	public void addFuncionarioComCamposObrigatoriosVazios() throws ParseException{
		Funcionario f = new Funcionario(aux.pessoaComCamposObrigatoriosVazios(), cargo);
		Assert.assertFalse(clinica.addFuncionario(f));
	}
	
	@Test
	public void buscarFuncionarioPeloCpf(){
		Assert.assertEquals(funcionario, clinica.buscarFuncionarioPeloCpf("111.222.333-00"));
	}
	
	@Test
	public void buscarFuncionarioInexistente(){
		Assert.assertNull(clinica.buscarFuncionarioPeloCpf("111.222.333-44"));
	}
	
	@Test
	public void buscarFuncionarioPeloNome(){
		Assert.assertEquals(funcionario, clinica.buscarFuncionarioPeloNome("Fulano").get(0));
	}
	
	@Test
	public void buscarPeloNomeFuncionarioInexistente(){
		Assert.assertEquals(0, clinica.buscarFuncionarioPeloNome("Cicrano").size());
		
	}

	@Test
	public void buscarMaisDeUmFuncionarioExistente(){
		Pessoa p = new Pessoa("Fulano", "111.222.333-44", new Date(),"9999-9999", endereco);
		Funcionario f = new Funcionario(p, cargo);
		clinica.addFuncionario(f);
		List<Funcionario> teste = new ArrayList<Funcionario>();
		teste.add(funcionario);
		teste.add(f);
		Assert.assertEquals(teste, clinica.buscarFuncionarioPeloNome("Fulano"));
	}
	
	@Test
	public void buscarFuncionarioPeloCargo(){
		Assert.assertEquals(funcionario, clinica.buscarFuncionarioPeloCargo(cargo).get(0));
	}
	
	@Test
	public void buscarFuncionariosComMesmoCargo(){
		Pessoa p = new Pessoa("Fulano", "111.222.333-44", new Date(),"9999-9999", endereco);
		Funcionario f = new Funcionario(p, cargo);
		clinica.addFuncionario(f);
		List<Funcionario> teste = new ArrayList<Funcionario>();
		teste.add(funcionario);
		teste.add(f);
		Assert.assertEquals(teste, clinica.buscarFuncionarioPeloCargo(cargo));
	}
	
	@Test
	public void buscarFuncionarioPorCargoInexistente(){
		Cargo c = new Cargo("Cargo 2", TipoDoCargo.Estagiário, 800.0, 6);
		Assert.assertEquals(0, clinica.buscarFuncionarioPeloCargo(c).size());
	}
	
	@Test
	public void editarFuncionario() {
		Endereco e = new Endereco("Rua", "bairro", Estados.PARAIBA, "11.111-111", "11");
		Pessoa p = new Pessoa("Fulano", "111.111.111-11", new Date() ,"9999-9999", e);
		Funcionario f = new Funcionario(p, cargo);
		Assert.assertTrue(clinica.editarFuncionario(funcionario, f));
		Assert.assertEquals(f, clinica.listarFuncionarios().get(0));
	}
	
	@Test
	public void editarCargoDoFuncionario(){
		Cargo c = new Cargo("Cargo 2", TipoDoCargo.Estagiário, 800.0, 6);
		clinica.addCargo(c);
		Assert.assertTrue(clinica.editarCargoFuncionario(funcionario, c));
		Assert.assertEquals(funcionario.getCargo(), c);
	}
	
	@Test (expected = Excecao.class)
	public void editarCargoFuncionarioInexistente(){
		Funcionario f = new Funcionario(pessoa, cargo);
		Cargo c = new Cargo("Cargo 2", TipoDoCargo.Estagiário, 800.0, 6);
		clinica.addCargo(c);
		clinica.editarCargoFuncionario(f, c);
	}
	
	@Test
	public void editarFuncionarioParaCargoNulo(){
		Assert.assertFalse(clinica.editarCargoFuncionario(funcionario, null));
		
	}
	
	@Test(expected  = Excecao.class)
	public void editarFuncionarioComCpfInvalido() throws ParseException{
		Funcionario f = new Funcionario(aux.pessoaComCpfInvalido(), cargo);
		clinica.editarFuncionario(funcionario,f);
	}
	
	@Test(expected  = Excecao.class)
	public void editarFuncionarioComCpfExistente() throws ParseException{
		Funcionario f = new Funcionario(aux.pessoaComCpfJaExistente(), cargo);
		clinica.editarFuncionario(funcionario,f);
	}
	
	@Test(expected = Excecao.class)
	public void editarFuncionarioEnderecoNumeroComLetra() throws ParseException{
		Funcionario f = new Funcionario(aux.pessoaComNumeroInvalido(), cargo);
		clinica.editarFuncionario(funcionario,f);
	}
	
	@Test(expected  = Excecao.class)
	public void editarFuncionarioComTelefoneInvalido() throws ParseException{
		Funcionario f = new Funcionario(aux.pessoaComTelefoneInvalido(), cargo);
		clinica.editarFuncionario(funcionario,f);
	}
	
	@Test(expected  = Excecao.class)
	public void editarFuncionarioComDataNascimentoMaiorQueAnoAtual() throws ParseException{
		Funcionario f = new Funcionario(aux.pessoaComDataNascInvalida(), cargo);
		clinica.editarFuncionario(funcionario,f);
	}
	
	@Test
	public void editarFuncionarioComCamposObrigatoriosVazios() throws ParseException{
		Funcionario f = new Funcionario(aux.pessoaComCamposObrigatoriosVazios(), cargo);
		Assert.assertFalse(clinica.editarFuncionario(funcionario,f));
	}

	
	@Test
	public void removerFuncionario(){
		Assert.assertEquals(1, clinica.listarFuncionarios().size());
		Assert.assertTrue(clinica.removerFuncionario(funcionario));
		Assert.assertEquals(0, clinica.listarFuncionarios().size());
	}
	
	@Test (expected = Excecao.class)
	public void removerFuncionarioInexistente(){
		Funcionario f = new Funcionario(pessoa, cargo);
		clinica.removerFuncionario(f);
	}
	
}

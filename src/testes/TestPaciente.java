package testes;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import excecao.Excecao;

import gerenciadores.ClinicaFachada;
import negocio.Cargo;
import negocio.Endereco;
import negocio.Estados;
import negocio.Funcionario;
import negocio.Paciente;
import negocio.Pessoa;
import negocio.Plano;
import negocio.TipoDoCargo;

public class TestPaciente {
	
	ClinicaFachada clinica;
	Paciente paciente;
	Cargo cargo;
	Endereco endereco;
	Pessoa pessoa;
	Plano plano;
	AuxiliarFuncPac aux;
	
	@Before
	public void instanciarFachada(){
		aux = new AuxiliarFuncPac();
		clinica = new ClinicaFachada();
		plano = new Plano("Simples", 25.0, "111");
		endereco = new Endereco("Rua", "bairro", Estados.PARAIBA, "55.999-999", "11");
		pessoa = new Pessoa("Fulano", "111.222.333-00", new Date(),"9999-9999", endereco);
		paciente = new Paciente(pessoa, plano);
		clinica.addPaciente(paciente);
	}
	
	@After
	public void limparCargos(){
		clinica.zerarDados();
	}
	
	@Test
	public void addPaciente(){
		Assert.assertEquals(paciente, clinica.listarPacientes().get(0));
	}
	
	@Test(expected = Excecao.class)
	public void addPacienteSemPlano(){
		Paciente pa =  new Paciente(pessoa, null);
		clinica.addPaciente(pa); 
	}
	
	@Test
	public void addPacienteExistente(){
		Paciente pa = new Paciente(pessoa, plano);
		Assert.assertFalse(clinica.addPaciente(pa));
	}
	
	@Test
	public void addPacienteQueJaEhFuncionario(){
		Pessoa p = new Pessoa("Cicrano", "111.222.333-44", new Date(),"9999-9999", endereco);
		Cargo c = new Cargo("Cargo 2", TipoDoCargo.Estagiário, 800.0, 6);
		Funcionario f = new Funcionario(p, c);
		clinica.addFuncionario(f);
		Paciente pa = new Paciente(f.getPessoa(), plano);
		Assert.assertTrue(clinica.addPaciente(pa));
	}
	
	@Test(expected = Excecao.class)
	public void addPacienteCepInvalido() throws ParseException{
		Paciente pa = new Paciente(aux.pessoaComCepInValido(), plano);
		clinica.addPaciente(pa);
	}
	
	@Test(expected = Excecao.class)
	public void addPacienteNumeroInvalido() throws ParseException{
		Paciente pa = new Paciente(aux.pessoaComNumeroInvalido(), plano);
		clinica.addPaciente(pa);
	}
	
	@Test
	public void addPacienteEnderecoSemNumero() throws ParseException{
		Paciente pa = new Paciente(aux.pessoaComEnderecoSemNumero(), plano);
		Assert.assertTrue(clinica.addPaciente(pa));
	}
	
	@Test(expected = Excecao.class)
	public void addCampoBairroInvalido() throws ParseException{
		Paciente pa = new Paciente(aux.pessoaComBairroInvalido(), plano);
		clinica.addPaciente(pa);
	}
	
	@Test(expected = Excecao.class)
	public void addCampoRuaInvalido() throws ParseException{
		Paciente pa = new Paciente(aux.pessoaComRuaInvalida(), plano);
		clinica.addPaciente(pa);
	}
	
	@Test(expected = Excecao.class)
	public void addPacienteCpfInvalido() throws ParseException{
		Paciente pa = new Paciente(aux.pessoaComCpfInvalido(), plano);
		clinica.addPaciente(pa);
	}
	
	@Test(expected = Excecao.class)
	public void addPacienteDataNascimetoInvalida() throws ParseException{
		Paciente pa = new Paciente(aux.pessoaComDataNascInvalida(), plano);
		clinica.addPaciente(pa);
	}
	
	@Test(expected = Excecao.class)
	public void addPacienteTelefoneInvalido() throws ParseException{
		Paciente pa = new Paciente(aux.pessoaComTelefoneInvalido(), plano);
		clinica.addPaciente(pa);
	}
	
	@Test
	public void addPacienteCpfExistente() throws ParseException{
		Paciente pa = new Paciente(aux.pessoaComCpfJaExistente(), plano);
		Assert.assertFalse(clinica.addPaciente(pa));
	}
	
	@Test
	public void addPacienteComCamposObrigatoriosVazios() throws ParseException{
		Paciente pa = new Paciente(aux.pessoaComCamposObrigatoriosVazios(), plano);
		Assert.assertFalse(clinica.addPaciente(pa));
	}
	
	@Test
	public void buscarPacientePeloCpf(){
		Assert.assertEquals(paciente, clinica.buscarPacientePeloCpf("111.222.333-00"));
	}

	@Test
	public void buscarPacientePeloNome(){
		Assert.assertEquals(paciente, clinica.buscarPacientePeloNome("Fulano").get(0));
	}
	
	@Test
	public void buscarPacienteInexistente(){
		Assert.assertNull(clinica.buscarPacientePeloCpf("111.222.333-90"));
	}
	
	@Test
	public void buscarPeloNomePacienteInexistente(){
		Assert.assertEquals(0, clinica.buscarPacientePeloNome("Cicrano").size());
	}
	
	@Test
	public void buscarMaisDeUmPaciente(){
		Pessoa p = new Pessoa("Fulano", "111.111.111-00", new Date(), "1111-1111", endereco);
		Paciente pa = new Paciente(p, plano);
		clinica.addPaciente(pa);
		List<Paciente> teste = new ArrayList<Paciente>();
		teste.add(paciente);
		teste.add(pa);
		Assert.assertEquals(teste, clinica.buscarPacientePeloNome("Fulano"));
	}
	
	@Test
	public void buscarPacientePeloPlano(){
		Assert.assertEquals(paciente, clinica.buscarPacientePeloPlano(plano).get(0));
	}
	
	@Test
	public void buscarPacientesComMesmoPlano(){
		Pessoa p = new Pessoa("Fulano", "111.111.111-00", new Date(), "1111-1111", endereco);
		Paciente pa = new Paciente(p, plano);
		clinica.addPaciente(pa);
		List<Paciente> teste = new ArrayList<Paciente>();
		teste.add(paciente);
		teste.add(pa);
		Assert.assertEquals(teste, clinica.buscarPacientePeloPlano(plano));
	}
	
	@Test
	public void buscarPacientePorPlanoInexistente(){
		Plano pl = new Plano("plano", 40.0, "222");
		Assert.assertEquals(0, clinica.buscarPacientePeloPlano(pl).size());
	}
	
	@Test
	public void editarPaciente(){
		Endereco e = new Endereco("Rua", "bairro", Estados.PARAIBA, "11.111-111", "11");
		Pessoa p = new Pessoa("Fulano", "111.222.333-44", new Date() ,"9999-9999", e);
		Paciente pa = new Paciente(p, plano);
		Assert.assertTrue(clinica.editarPaciente(paciente, pa));
		Assert.assertEquals(pa, clinica.listarPacientes().get(0));
	}
	
	@Test
	public void editarPlanoPaciente(){
		Plano pl = new Plano("Novo Plano", 30.0, "123");
		clinica.addPlano(pl);
		Assert.assertTrue(clinica.editarPlanoPaciente(paciente, pl));
		Assert.assertEquals(paciente.getPlano(), pl);
	}
	
	@Test(expected = Excecao.class)
	public void editarPlanoPacienteInexistente(){
		Paciente pa = new Paciente(pessoa, plano);
		Plano pl = new Plano("Novo Plano", 30.0, "123");
		clinica.addPlano(pl);
		clinica.editarPlanoPaciente(pa, pl);
	}
	
	@Test
	public void editarPacienteParaPlanoNulo(){
		Assert.assertFalse(clinica.editarPlanoPaciente(paciente, null));
	}
	
	@Test(expected = Excecao.class)
	public void editarPacienteComCpfInvalido() throws ParseException{
		Paciente pa = new Paciente(aux.pessoaComCpfInvalido(), plano);
		clinica.editarPaciente(paciente, pa);
	}
	
	@Test(expected = Excecao.class)
	public void editarPacienteComCpfExistente() throws ParseException{
		Paciente pa = new Paciente(aux.pessoaComCpfJaExistente(), plano);
		clinica.editarPaciente(paciente, pa);
	}
	
	@Test(expected = Excecao.class)
	public void editarPacienteEnderecoNumeroComLetra() throws ParseException{
		Paciente pa = new Paciente(aux.pessoaComNumeroInvalido(), plano);
		clinica.editarPaciente(paciente, pa);
	}
	
	@Test(expected = Excecao.class)
	public void editarPacienteComTelefoneInvalido() throws ParseException{
		Paciente pa = new Paciente(aux.pessoaComTelefoneInvalido(), plano);
		clinica.editarPaciente(paciente, pa);
	}
	
	@Test(expected = Excecao.class)
	public void editarPacienteComDataNascimentoMaiorQueAnoAtual() throws ParseException{
		Paciente pa = new Paciente(aux.pessoaComDataNascInvalida(), plano);
		clinica.editarPaciente(paciente, pa);
	}
	
	@Test
	public void editarPacienteComCamposObrigatoriosVazios() throws ParseException{
		Paciente pa = new Paciente(aux.pessoaComCamposObrigatoriosVazios(), plano);
		Assert.assertFalse(clinica.editarPaciente(paciente, pa));
	}
	
	@Test
	public void removerPaciente(){
		Assert.assertEquals(1, clinica.listarPacientes().size());
		Assert.assertTrue(clinica.removerPaciente(paciente));
		Assert.assertEquals(0, clinica.listarPacientes().size());
	}
	
	@Test(expected = Excecao.class)
	public void removerPacienteInexistente(){
		Paciente pa = new Paciente(pessoa, plano);
		clinica.removerPaciente(pa);
	}
}

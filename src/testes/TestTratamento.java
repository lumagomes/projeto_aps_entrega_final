package testes;

import java.util.Date;

import gerenciadores.ClinicaFachada;
import negocio.Cargo;
import negocio.Endereco;
import negocio.Estados;
import negocio.Funcionario;
import negocio.Pessoa;
import negocio.TipoDoCargo;
import negocio.Tratamento;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestTratamento {
	
	ClinicaFachada clinica;
	Tratamento tratamento;
	Funcionario funcionario;
	Cargo cargo;
	Pessoa pessoa;
	Endereco endereco;
	
	@Before
	public void instanciarFachada(){
		clinica = new ClinicaFachada();
		cargo = new Cargo("Cargo 1", TipoDoCargo.Efetivo, 1000, 8);
		endereco = new Endereco("Rua", "bairro", Estados.PARAIBA, "55.999-999", "11");
		pessoa = new Pessoa("Fulano", "111.222.333-44", new Date(),"9999-9999", endereco);
		funcionario = new Funcionario(pessoa, cargo);
		clinica.addFuncionario(funcionario);
		tratamento = new Tratamento("Restauracao");
		clinica.addTratamento(tratamento);
	}
	
	@After
	public void limparCargos(){
		clinica.zerarDados();
	}
	
	@Test
	public void adicionarTratamento(){
		Assert.assertEquals(tratamento, clinica.listarTratamentos().get(0));
	}
	
	@Test
	public void adicionarEspecialistaAoTratamento(){
		Assert.assertTrue(clinica.addEspecialista(tratamento, funcionario));
	}
	
	@Test
	public void adicionarMaisDeUmEspecialista(){
		Pessoa p = new Pessoa("Cicrano", "111.222.333-00", new Date(), "9999-9999", endereco);
		Funcionario f = new Funcionario(p, cargo);
		clinica.addFuncionario(f);
		Tratamento t = new Tratamento("Restauracao");
		Assert.assertTrue(clinica.addEspecialista(t, f));
		Assert.assertEquals(1, clinica.listarTratamentos().size());
	}
	
	@Test
	public void adicionarTratamentoJaExistente(){
		Tratamento t = new Tratamento("Restauracao");
		Assert.assertFalse(clinica.addTratamento(t));
	}		
	
	@Test
	public void addEspecialistaJaExistenteNoTratamento(){
		clinica.addEspecialista(tratamento, funcionario);
		Assert.assertFalse(clinica.addEspecialista(tratamento, funcionario));
	}
	
	@Test
	public void addTratamentoComNomeVazio(){
		Tratamento t = new Tratamento("");
		Assert.assertFalse(clinica.addTratamento(t));
	}
	
	@Test
	public void addTratamentoComNomeInvalido(){
		Tratamento t = new Tratamento("Restauração %&");
		Assert.assertFalse(clinica.addTratamento(t));
	}
	
	@Test
	public void addEspecialistaNulo(){
		Assert.assertFalse(clinica.addEspecialista(tratamento, null));
	}
	
	public void addEspecialistaInexistente(){
		Pessoa p = new Pessoa("Cicrano", "111.222.333-00", new Date(), "9999-9999", endereco);
		Funcionario f = new Funcionario(p, cargo);
		Assert.assertFalse(clinica.addEspecialista(tratamento, f));
	}

	@Test
	public void editarTratamentoExistente(){
		Assert.assertTrue(clinica.editarTratamento(tratamento, "Branqueamento"));
		Assert.assertEquals(tratamento.getNome(), "Branqueamento");
	}
	
	@Test
	public void editarTratamentoInexistente(){
		Tratamento t = new Tratamento("Raio x");
		Assert.assertFalse(clinica.editarTratamento(t, "Branqueamento"));
	}
	
	@Test
	public void editarTratamentoExistenteComNomeInvalido(){
		Assert.assertFalse(clinica.editarTratamento(tratamento, "Extração de Dente"));
	}
	
	@Test
	public void buscarTratamentoExistentePeloNome(){
		Assert.assertEquals(tratamento, clinica.buscarTratamentoPeloNome("Restauracao"));
	}
	
	@Test
	public void buscarTratamentoInexistentePeloNome(){
		Assert.assertNull(clinica.buscarTratamentoPeloNome("Branqueamento"));
	}
	
	@Test
	public void removerTratamentoExistente(){
		Assert.assertEquals(1, clinica.listarTratamentos().size());
		Assert.assertTrue(clinica.removerTratamento("Restauracao"));
		Assert.assertEquals(0, clinica.listarTratamentos().size());
	}
	
	@Test
	public void removerEspecialistaDoTratamento(){
		clinica.addEspecialista(tratamento, funcionario);
		Assert.assertEquals(1, tratamento.getEspecialistas().size());
		Assert.assertTrue(clinica.removerEspecialista(tratamento, "111.222.333-44"));
		Assert.assertEquals(0, tratamento.getEspecialistas().size());
	}
	
	@Test
	public void removerEspecialistaQueNaoExiste(){
		clinica.addEspecialista(tratamento, funcionario);
		Assert.assertEquals(1, tratamento.getEspecialistas().size());
		Assert.assertFalse(clinica.removerEspecialista(tratamento, "000.000.000-00"));
		Assert.assertEquals(1, tratamento.getEspecialistas().size());
	}
	
	@Test
	public void removerTratamentoInexistente(){
		Assert.assertEquals(1, clinica.listarTratamentos().size());
		Assert.assertFalse(clinica.removerTratamento("Branqueamento"));
		Assert.assertEquals(1, clinica.listarTratamentos().size());
	}
}

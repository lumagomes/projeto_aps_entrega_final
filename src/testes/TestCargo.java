package testes;

import java.util.ArrayList;
import java.util.List;

import excecao.Excecao;
import gerenciadores.ClinicaFachada;
import negocio.Cargo;
import negocio.TipoDoCargo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestCargo {

	ClinicaFachada c;
	Cargo cargo;

	@Before
	public void instanciarFachada(){
		c = new ClinicaFachada();
		cargo = new Cargo ("Cargo 1", TipoDoCargo.Estagiário, 800, 8);
		c.addCargo(cargo);
	}
	
	@After
	public void limparCargos(){
		c.zerarDados();
	}
	
	@Test
	public void adicionarCargo(){
		//Já foi adicionado um Cargo no Before
		Assert.assertEquals(cargo, c.listarCargos().get(0));
	}
	
	@Test
	public void addMesmoCargoTipoDiferente(){
		Cargo c2 = new Cargo ("Cargo 1", TipoDoCargo.Efetivo, 800, 8);
		Assert.assertTrue(c.addCargo(c2));
	}
	
	@Test
	public void addMesmoCargoTipoIgual(){
		Cargo c2 = new Cargo ("Cargo 1", TipoDoCargo.Estagiário, 800, 8);
		Assert.assertFalse(c.addCargo(c2));
	}
	
	@Test(expected = Excecao.class)
	public void addCampoEmBranco(){
		Cargo c2 = new Cargo ("", null , 0, 0);
		c.addCargo(c2);
	}
	
	@Test(expected = Excecao.class)
	public void addCargoFuncaoInvalida(){
		Cargo c2 = new Cargo ("Teste!", TipoDoCargo.Estagiário, 800, 8);
		c.addCargo(c2);
	}
	
	@Test(expected = Excecao.class)
	public void addCargoComAcento(){
		Cargo c2 = new Cargo ("Cargô", TipoDoCargo.Efetivo, 600, 8);
		c.addCargo(c2);
	}
	
	@Test
	public void addCargoCargaHorariaNegativa(){
		Cargo c2 = new Cargo("Cargo", TipoDoCargo.Efetivo, 600, -1);
		Assert.assertFalse(c.addCargo(c2));
	}
	
	@Test
	public void addCargoSalarioNegativo(){
		Cargo c2 = new Cargo("Cargo", TipoDoCargo.Efetivo, -1, 8);
		Assert.assertFalse(c.addCargo(c2));
	}
	
	@Test
	public void addCargoComTipoNulo(){
		Cargo c2 = new Cargo("Cargo", null, 600, 8);
		Assert.assertFalse(c.addCargo(c2));
	}
	
	@Test
	public void buscarCargoPelaFuncao(){
		Assert.assertEquals(cargo, c.buscarCargoPelaFuncao("Cargo 1").get(0));
	}
	
	@Test
	public void buscarCargoPeloTipo(){
		Assert.assertEquals(cargo,c.buscarCargoPeloTipo(TipoDoCargo.Estagiário).get(0));
	}
	
	@Test
	public void buscarMaisDeUmCargoExistente(){
		Cargo c2 = new Cargo("Cargo 1", TipoDoCargo.Efetivo, 500, 8);
		c.addCargo(c2);
		List<Cargo> teste = new ArrayList<Cargo>();
		teste.add(cargo);
		teste.add(c2);
		Assert.assertEquals(teste, c.buscarCargoPelaFuncao("Cargo 1"));
	}
	
	@Test
	public void buscarCargoEspecifico(){
		Assert.assertEquals(cargo, c.buscaAvancada("Cargo 1", TipoDoCargo.Estagiário));
	}
	
	@Test
	public void buscarPorUmCargoInexistente(){
		Assert.assertEquals(0, c.buscarCargoPelaFuncao("Teste1").size());
	}
	
	@Test
	public void removerCargoExistente(){
		Assert.assertEquals(1, c.listarCargos().size());
		c.removerCargo(c.buscaAvancada("Cargo 1", TipoDoCargo.Estagiário));
		Assert.assertEquals(0, c.listarCargos().size());
	}
	
	@Test(expected = Excecao.class)
	public void removerCargoInexistente(){
		Cargo cargoParaRemover = new Cargo("TestRemove",TipoDoCargo.Efetivo, 800, 20);
		c.removerCargo(cargoParaRemover);
	}
	
	@Test
	public void editarCargoExistente(){
		List<Cargo> antigoCargo = new ArrayList<Cargo>();
		antigoCargo = c.buscarCargoPelaFuncao("Cargo 1");
		Cargo novoCargo = new Cargo("Teste1", TipoDoCargo.Efetivo, 800, 8);
		int indiceDoCargoAntigo = c.listarCargos().indexOf(antigoCargo.get(0));
		Assert.assertTrue("Cargo foi editado", c.editarCargo(antigoCargo.get(0), novoCargo));
		Assert.assertEquals(novoCargo, c.listarCargos().get(indiceDoCargoAntigo));
	}
	
	@Test(expected = Excecao.class)
	public void editarCargoInexistente(){
		Cargo antigoCargoQueNaoExisteMais = new Cargo("CargoInexistente", TipoDoCargo.Efetivo, 800, 8);
		Cargo novoCargo = new Cargo("novoCargo", TipoDoCargo.Efetivo, 800, 8);
		c.editarCargo(antigoCargoQueNaoExisteMais, novoCargo);
	}
	
	@Test(expected = Excecao.class)
	public void editarCargoComNomeDaFuncaoInvalida(){
		List<Cargo> antigoCargo = new ArrayList<Cargo>();
		antigoCargo = c.buscarCargoPelaFuncao("Cargo 1");
		Cargo novoCargo = new Cargo("Teste!", TipoDoCargo.Efetivo, 800, 8);
		c.editarCargo(antigoCargo.get(0), novoCargo);
	}
	
	@Test(expected = Excecao.class)
	public void editarCargoPraSerIgualExistente(){
		Cargo c2 = new Cargo("Cargo 2", TipoDoCargo.Efetivo, 200, 5);
		c.addCargo(c2);
		c.editarCargo(c.buscaAvancada("Cargo 2", TipoDoCargo.Efetivo), new Cargo("Cargo 1", TipoDoCargo.Estagiário, 800, 8));
	}
	
	@Test(expected = Excecao.class)
	public void editarCargoComTipoNulo(){
		Cargo c2 = new Cargo("Cargo 2", TipoDoCargo.Efetivo, 200, 5);
		c.addCargo(c2);
		c.editarCargo(c.buscaAvancada("Cargo 2", TipoDoCargo.Efetivo), new Cargo("Cargo 4", null, 800, 8));
	}
	
	@Test(expected = Excecao.class)
	public void editarCargoComSalarioMenorQueZero(){
		Cargo c2 = new Cargo("Cargo 2", TipoDoCargo.Efetivo, 200, 5);
		c.addCargo(c2);
		c.editarCargo(c.buscaAvancada("Cargo 2", TipoDoCargo.Efetivo), new Cargo("Cargo 3", TipoDoCargo.Efetivo, -1 , 8));
	}
	
	@Test(expected = Excecao.class)
	public void editarCargoComCargaHorariaMenorQueZero(){
		Cargo c2 = new Cargo("Cargo 2", TipoDoCargo.Efetivo, 200, 5);
		c.addCargo(c2);
		c.editarCargo(c.buscaAvancada("Cargo 2", TipoDoCargo.Efetivo), new Cargo("Cargo 3", TipoDoCargo.Efetivo, 700 , -1));
	}
}
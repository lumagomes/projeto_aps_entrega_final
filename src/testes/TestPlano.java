package testes;

import excecao.Excecao;
import gerenciadores.ClinicaFachada;
import junit.framework.Assert;
import negocio.Plano;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPlano {
	
	ClinicaFachada clinica;
	Plano plano;
	
	@Before
	public void instanciarFachada(){
		clinica = new ClinicaFachada();
		plano = new Plano("SuperPlus", 30.00, "1234");
		clinica.addPlano(plano);
	}
	
	@After
	public void limparCargos(){
		clinica.zerarDados();
	}
	
	@Test
	public void addPlano(){
		Assert.assertEquals(plano, clinica.listarPlanos().get(0));
	}
	
	@Test
	public void addPlanoComCamposNulo(){
		Plano p = new Plano ("", 0, "");
		Assert.assertFalse(clinica.addPlano(p));
	}
	
	@Test
	public void addPlanoComValorNegativo(){
		Plano p = new Plano("Familia", -2, "123");
		Assert.assertFalse(clinica.addPlano(p));
	}
	
	@Test(expected = Excecao.class)
	public void addPlanoComCodigoExistente(){
		Plano p = new Plano("Familia", 100, "1234");
		clinica.addPlano(p);
	}
	
	@Test(expected = Excecao.class)
	public void addPlanoComNomeExistente(){
		Plano p = new Plano("Superplus", 100, "123");
		clinica.addPlano(p);
	}
	
	@Test(expected = Excecao.class)
	public void addPlanoComCaracteresCodigoAcimaDoLimite(){
		Plano p = new Plano("Familia", 20.0, "123456789");
		clinica.addPlano(p);
	}
	
	@Test(expected = Excecao.class)
	public void addPlanoComCodigoInvalido(){
		Plano p = new Plano("Familia", 100.0, "aa");
		clinica.addPlano(p);
	}
	
	@Test
	public void buscarPlanoPeloCodigo(){
		Assert.assertEquals(plano, clinica.buscarPlanoPeloCodigo("1234"));
	}
	
	@Test
	public void buscarPlanoPeloNome(){
		Assert.assertEquals(plano, clinica.buscarPlanoPeloNome("Superplus"));
	}
	
	@Test
	public void buscarPeloCodigoPlanoInexistente(){
		Assert.assertNull(clinica.buscarPlanoPeloCodigo("123"));
	}
	
	@Test
	public void buscarPeloNomePlanoInexistente(){
		Assert.assertNull(clinica.buscarPlanoPeloNome("Familia"));
	}
	
	@Test
	public void editarPlano(){
		Plano p = new Plano("Familia", 100.0, "123");
		Assert.assertTrue(clinica.editarPlano(plano, p));
		Assert.assertEquals(p, clinica.listarPlanos().get(0));
	}
	
	@Test(expected = Excecao.class)
	public void editarPlanoInexistente(){
		Plano p = new Plano("Familia", 100.0, "123");
		clinica.editarPlano(p, plano);
	}
	
	@Test
	public void editarPlanoParaCamposNulo(){
		Plano p = new Plano("", 0, "");
		Assert.assertFalse(clinica.editarPlano(plano, p));
	}
	
	@Test
	public void editarPlanoParaValorNegativo(){
		Plano p = new Plano("Familia", -1, "123");
		Assert.assertFalse(clinica.editarPlano(plano, p));
	}
	
	@Test(expected = Excecao.class)
	public void editarPlanoParaCodigoAcimaDoLimite(){
		Plano p = new Plano("Familia", 50.0, "123456789");
		clinica.editarPlano(plano, p);
	}
	
	@Test(expected = Excecao.class)
	public void editarPlanoParaCodigoInválido(){
		Plano p = new Plano("Familia", 40.0, "qq");
		clinica.editarPlano(plano, p);
	}
	
	@Test
	public void removerPlano(){
		Assert.assertEquals(1, clinica.listarPlanos().size());
		Assert.assertTrue(clinica.removerPlano(plano));
		Assert.assertEquals(0, clinica.listarPlanos().size()); 
	}
	
	@Test(expected = Excecao.class)
	public void removerPlanoInexistente(){
		Plano p = new Plano("Familia", 100.0, "123");
		clinica.removerPlano(p);
	}
}

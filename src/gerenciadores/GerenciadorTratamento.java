package gerenciadores;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import negocio.Funcionario;
import negocio.Tratamento;

public class GerenciadorTratamento {

	private ArrayList<Tratamento> tratamentos;

	public GerenciadorTratamento(){
		tratamentos = new ArrayList<Tratamento>();
	}

	public ArrayList<Tratamento> getTratamentos(){
		return tratamentos;
	}

	public boolean addTratamento(Tratamento t){
		boolean retorno = false;
		if(!this.verificarExistencia(t.getNome()) && this.validarCampoBranco(t.getNome()) && this.validarCaracteresEspeciais(t.getNome())){
			this.getTratamentos().add(t);
			retorno = true;			
		}
		return retorno;
	}
	
	public boolean addEspecialista(Tratamento t, Funcionario f){
		boolean retorno = false;
		if(f != null){
			if(this.verificarExistencia(t.getNome())){
				if(!this.validarExistenciaEspecialista(t, f)){
					t.getEspecialistas().add(f);
					retorno = true;
				}				
			}
		}
		return retorno;
	}
	
	public boolean editarTratamento(Tratamento t, String nome) {
		boolean retorno = false;
		if(this.verificarExistencia(t.getNome())){			
			if(!this.verificarExistencia(nome) && this.validarCampoBranco(nome) && this.validarCaracteresEspeciais(nome)){
				t.setNome(nome);
				retorno = true;
			}
		}
		return retorno;
	}	
	
	public boolean removerTratamento(String nome) {
		boolean retorno = false;
		Tratamento t = this.buscarPeloNome(nome);
		if(t != null){
			this.tratamentos.remove(t);
			retorno = true;
		}
		return retorno;
	}
	
	public boolean removerEspecialista(Tratamento t, String cpf){
		boolean retorno = false;
		ArrayList<Funcionario> lista = new ArrayList<Funcionario>(t.getEspecialistas());
		for(Funcionario esp : lista){
			if(esp.getPessoa().getCpf().equals(cpf)){
				t.getEspecialistas().remove(esp);
				retorno = true;
			}
		}
		return retorno;
	}

	public Tratamento buscarPeloNome(String nome) {
		Tratamento tr = null;
		for(Tratamento t : tratamentos){
			if(t.getNome().contains(nome))
				tr = t;
		}
		return tr;
	}

	public List<Tratamento> buscarPeloEspecialista(Funcionario f){
		List<Tratamento> lista = new ArrayList<Tratamento>();
		for (Tratamento t : tratamentos){
			if(t.getEspecialistas().contains(f))
				lista.add(t);
		}
		return lista;
	}

	public boolean verificarExistencia(String nome){
		boolean retorno = false;
		for(Tratamento t : tratamentos){
			if(t.getNome().equalsIgnoreCase(nome))
				retorno = true;
		}
		return retorno;
	}

	public boolean validarExistenciaEspecialista(Tratamento t, Funcionario f){
		boolean retorno = false;
		if (t.getEspecialistas().contains(f)){
			retorno = true;
		}
		return retorno;
	}
	
	public boolean validarCampoBranco(String nome){
		if (nome.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean validarCaracteresEspeciais(String nome) {
		boolean retorno = false;
		Pattern padrao = Pattern.compile("[a-zA-Z\\s]+");
		Matcher pesquisa = padrao.matcher(nome);
		if (pesquisa.matches()) {
			retorno = true;
		}
		return retorno;
	}	

}

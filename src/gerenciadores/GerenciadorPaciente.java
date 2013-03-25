package gerenciadores;

import java.util.ArrayList;
import java.util.List;

import excecao.Excecao;

import negocio.Paciente;
import negocio.Plano;

public class GerenciadorPaciente {

	private ArrayList<Paciente> pacientes;
	GerenciadorPlano gpl;
	GerenciadorPessoa gp; 

	public GerenciadorPaciente(){
		pacientes = new ArrayList<Paciente>();
		gpl = new GerenciadorPlano();
		gp = new GerenciadorPessoa();
	}

	public ArrayList<Paciente> getPacientes() {
		return pacientes;
	}

	public boolean addPaciente(Paciente p){
		boolean retorno = false;
		this.validatePlano(p);
		if(!gp.validarCamposBranco(p.getPessoa().getNome(), p.getPessoa().getCpf())){
			gp.validar(p.getPessoa());
			gp.validateData(p.getPessoa());
			if(!this.verificarExistencia(p.getPessoa().getCpf())){
				pacientes.add(p);
				retorno = true;
			}
		}
		return retorno;
	}

	public boolean removerFuncionario(Paciente p) {
		int numeroDeRegistrosAntes = getPacientes().size();
		getPacientes().remove(p);
		if (getPacientes().size() == numeroDeRegistrosAntes) {
			throw new Excecao(Excecao.PACIENTE_INEXISTENTE);
		} else
			return true;
	}

	public Paciente buscarPeloCpf(String cpf) {
		Paciente pac = null;
		for (Paciente p : pacientes) {
			if(p.getPessoa().getCpf().equals(cpf))
				pac = p;
		}
		return pac;
	}

	public List<Paciente> buscarPeloPlano(Plano plano){
		List<Paciente> lista = new ArrayList<>();
		for(Paciente p: pacientes){
			if(p.getPlano().equals(plano))
				lista.add(p);
		}
		return lista;
	}

	public List<Paciente> buscarPeloNome(String nome) {
		List<Paciente> lista = new ArrayList<>();
		for(Paciente p: pacientes){
			if(p.getPessoa().getNome().contains(nome))
				lista.add(p);
		}
		return lista;
	}

	public boolean verificarExistencia(String cpf){
		for (Paciente p : pacientes) {
			if(p.getPessoa().getCpf().equals(cpf)){
				pacientes.remove(p);
				return true;
			}
		}return false;
	}

	public boolean editarPlanoPaciente(Paciente pa, Plano pl) throws Excecao {
		boolean retorno = false;		
		for (Paciente p : pacientes) {
			if(pl != null){
				if(p.equals(pa)){
					p.setPlano(pl);
					retorno = true;
				}
				else throw new Excecao(Excecao.PACIENTE_INEXISTENTE);
			}			
		}
		return retorno;
	}

	public boolean editarPaciente(Paciente antigo, Paciente novo) {
		boolean retorno = false;
		int indicePacAntigo = this.getPacientes().indexOf(antigo);
		if(!gp.validarCamposBranco(novo.getPessoa().getNome(), novo.getPessoa().getCpf())){
			gp.validar(novo.getPessoa());
			gp.validateData(novo.getPessoa());
			if (indicePacAntigo == -1 || this.verificarExistencia(novo.getPessoa().getCpf())){
				throw new Excecao(Excecao.CPF_EXISTENTE);
			}else{
				this.getPacientes().set(indicePacAntigo, novo);
				retorno = true;
			}
		}
		return retorno;
	}

	public boolean removerPaciente(Paciente p) {
		int numeroDeRegistrosAntes = pacientes.size();
		pacientes.remove(p);
		if (pacientes.size() == numeroDeRegistrosAntes) {
			throw new Excecao(Excecao.PACIENTE_INEXISTENTE);
		} else
		return true;
	}
	
	public void validatePlano(Paciente p) throws Excecao{
		if(p.getPlano() == null){
			throw new Excecao(Excecao.PLANO_INEXISTENTE); 
		}
	}

}

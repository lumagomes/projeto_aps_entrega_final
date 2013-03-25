package gerenciadores;

import java.util.List;

import negocio.Cargo;
import negocio.Funcionario;
import negocio.Paciente;
import negocio.Plano;
import negocio.TipoDoCargo;
import negocio.Tratamento;
import excecao.Excecao;


public class ClinicaFachada {

	private GerenciadorCargo gc;
	private GerenciadorFuncionario gf;
	private GerenciadorPaciente gpa;
	private GerenciadorPlano gpl;
	private GerenciadorTratamento gt;

	public ClinicaFachada() {
		gc = new GerenciadorCargo();
		gf = new GerenciadorFuncionario();
		gpa = new GerenciadorPaciente();
		gpl = new GerenciadorPlano();
		gt = new GerenciadorTratamento();
	}
	
	//Metodos de Cargo
	public boolean addCargo(Cargo c){
		return gc.addCargo(c);
	}

	public boolean removerCargo(Cargo cargoParaRemover) throws Excecao {
		return gc.removerCargo(cargoParaRemover);
	}

	public boolean editarCargo(Cargo antigo, Cargo novo) throws Excecao {
		return gc.editarCargo(antigo, novo);
	}

	public List<Cargo> buscarCargoPelaFuncao(String funcao) {
		return gc.buscarCargoPelaFuncao(funcao);
	}
	
	public List<Cargo> buscarCargoPeloTipo(TipoDoCargo tipo) {
		return gc.buscarCargoPeloTipo(tipo);
	}
	
	public Cargo buscaAvancada(String funcao, TipoDoCargo tipo){
		return gc.buscaAvancada(funcao, tipo);
	}
	
	public List<Cargo> listarCargos (){
		return gc.getCargos();
	}

	//Metodos de Funcionario
	public boolean addFuncionario(Funcionario f) {
		return gf.addFuncionario(f);		
	}
	
	public List<Funcionario> listarFuncionarios(){
		return gf.getFuncionarios();
	}
	
	public Funcionario buscarFuncionarioPeloCpf(String cpf) {
		return gf.buscarPeloCpf(cpf);
	}

	public List<Funcionario> buscarFuncionarioPeloNome(String nome) {
		return gf.buscarPeloNome(nome);
	}
	
	public List<Funcionario> buscarFuncionarioPeloCargo(Cargo cargo){
		return gf.buscarPeloCargo(cargo);
	}
	
	public boolean editarCargoFuncionario(Funcionario f, Cargo c) {		
		return gf.editarCargoFuncionario(f, c);
	}
	
	public boolean editarFuncionario(Funcionario antigo, Funcionario novo) {
		return gf.editarFuncionario(antigo, novo);		
	}
	
	public boolean removerFuncionario(Funcionario f) {
		return gf.removerFuncionario(f);
	}
	
	//Metodos de Paciente
	public boolean addPaciente(Paciente p) {
		return gpa.addPaciente(p);		
	}
	
	public List<Paciente> listarPacientes(){
		return gpa.getPacientes();
	}
	
	public Paciente buscarPacientePeloCpf(String cpf){
		return gpa.buscarPeloCpf(cpf);
	}
	
	public List<Paciente> buscarPacientePeloPlano(Plano p){
		return gpa.buscarPeloPlano(p);
	}

	public List<Paciente> buscarPacientePeloNome(String nome) {
		return gpa.buscarPeloNome(nome);
	}
	
	public boolean editarPaciente(Paciente antigo, Paciente novo) {
		return gpa.editarPaciente(antigo, novo);
	}
	
	public boolean editarPlanoPaciente(Paciente paciente, Plano plano) {
		return gpa.editarPlanoPaciente(paciente, plano);
	}
	
	public boolean removerPaciente(Paciente paciente) {
		return gpa.removerPaciente(paciente);
	}

	//Metodos de Plano
	public boolean addPlano(Plano plano) {
		return gpl.addPlano(plano);
		
	}

	public List<Plano> listarPlanos() {
		return gpl.getPlanos();
	}

	public Plano buscarPlanoPeloCodigo(String cod) {
		return gpl.buscarPlanoPeloCodigo(cod);
	}

	public Plano buscarPlanoPeloNome(String nome) {
		return gpl.buscarPlanoPeloNome(nome);
	}

	public boolean editarPlano(Plano antigo, Plano novo) {
		return gpl.editarPlano(antigo, novo);
	}

	public boolean removerPlano(Plano plano) {
		return gpl.removerPlano(plano);
	}
	
	//metodos de Tratamento
	public boolean addTratamento(Tratamento t){
		return gt.addTratamento(t);
	}
	public boolean addEspecialista(Tratamento tratamento, Funcionario f) {
		return gt.addEspecialista(tratamento, f);		
	}
	
	public boolean editarTratamento(Tratamento tratamento, String nome) {
		return gt.editarTratamento(tratamento, nome);
	}
	
	public List<Tratamento> listarTratamentos(){
		return gt.getTratamentos();
	}
	
	public Tratamento buscarTratamentoPeloNome(String nome) {
		return gt.buscarPeloNome(nome);
	}
	
	public boolean removerTratamento(String nome) {
		return gt.removerTratamento(nome);
	}

	public boolean removerEspecialista(Tratamento t, String cpf) {
		return gt.removerEspecialista(t, cpf);
	}

	//apenas para os testes
	public void zerarDados() {
		gc.getCargos().clear();
		gf.getFuncionarios().clear();
		gpa.getPacientes().clear();
		gpl.getPlanos().clear(); 		
	}

}

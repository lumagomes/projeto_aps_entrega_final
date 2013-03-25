package negocio;

public class Cargo {
	
	private String funcao;
	private double salarioPorHora;
	private int cargaHoraria;
	private TipoDoCargo tipo;
	
	public Cargo(String funcao, TipoDoCargo tipo, double salarioPorHora, int cargaHoraria){
		this.funcao = funcao;
		this.tipo = tipo;
		this.salarioPorHora = salarioPorHora;
		this.cargaHoraria = cargaHoraria;
	}
	
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	public TipoDoCargo getTipo() {
		return tipo;
	}
	public void setTipo(TipoDoCargo tipo) {
		this.tipo = tipo;
	}
	public double getSalarioPorHora() {
		return salarioPorHora;
	}
	public void setSalarioPorHora(double salarioPorHora) {
		this.salarioPorHora = salarioPorHora;
	}
	public int getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	
	public String toString(){
		return "Função: " + this.funcao + "\n Salário: " + this.salarioPorHora + "\n";
	}
		
}

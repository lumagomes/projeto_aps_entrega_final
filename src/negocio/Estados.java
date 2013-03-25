package negocio;

public enum Estados {
	PARAIBA("PB", "Paraíba"), 
	PERNANBUCO("PE","Pernambuco"), 
	CEARA("CE", "Ceará"), 
	RIO_GRANDE_DO_NORTE("RN","Rio Grande do Norte");

	private final String sigla;
	private final String nome;

	private Estados (String sigla, String nome){
		this.sigla = sigla;
		this.nome = nome;
	}

	public String sigla(){
		return this.sigla;	
	}

	public String nome(){
		return this.nome;
	}
}

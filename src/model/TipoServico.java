package model;

public enum TipoServico {

	TODOS(0,"todos"),
	DUCHA_SIMPLES(1,"Ducha Simples"),
	DUCHA_CERA(2,"Ducha com Cera"),
	DUCHA_POLIMENTO_PEROLIZADO(3,"Ducha com Polimento Perolizado"),
	DUCHA_CERA_GUARIBA(4,"Ducha com Cera e Guariba"),
	ASPIRAR_INTERIOR(5,"Aspirar Interior");
	
	
	
	private int id;
	private String label;
	
	private TipoServico(int id, String label) {
		this.id=id;
		this.label=label;
	}
	public int getId() {
		return id;
	}
	public String getLabel() {
		return label;
	}
	
	//@Override
	public String toString() {
		return getLabel();
	}
	
}

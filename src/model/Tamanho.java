package model;

public enum Tamanho {

	PEQUENO(0,"Pequeno"),
	MEDIO(1,"Médio"),
	GRANDE(2,"Grande");
	
	private int id;
	private String label;
	
	private Tamanho(int id, String label) {
		this.id=id;
		this.label=label;
	}
	public int getId() {
		return id;
	}
	public String getLabel() {
		return label;
	}
	
//	@Override
//	public String toString() {
//		return string;
//	}
	
}

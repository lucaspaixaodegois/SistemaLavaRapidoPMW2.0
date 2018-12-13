package model;

public enum Status {

	ESPERA(0,"Em espera"),
	LAVANDO(1,"Lavando"),
	PRONTO(2,"Pronto");
	
	private int id;
	private String label;
	
	private Status(int id, String label) {
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

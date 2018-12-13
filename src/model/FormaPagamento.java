package model;

public enum FormaPagamento {

	DINHEIRO(0,"Dinheiro"),
	CARTAO_CREDITO(1,"Cart�o de Cr�dito"),
	CARTAO_DEBITO(2,"Cart�o de D�bito");
	
	
	
	
	private int id;
	private String label;
	
	private FormaPagamento(int id, String label) {
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

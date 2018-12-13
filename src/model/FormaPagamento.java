package model;

public enum FormaPagamento {

	DINHEIRO(0,"Dinheiro"),
	CARTAO_CREDITO(1,"Cartão de Crédito"),
	CARTAO_DEBITO(2,"Cartão de Débito");
	
	
	
	
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

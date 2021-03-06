package model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Servico extends DefaultEntity<Servico> {

	private static final long serialVersionUID = -8480871368210446283L;

	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Cliente cliente;

	private TipoServico tiposervico;
	private FormaPagamento formapagamento;
	private Status status;

	private String valorTotal;
	private String taArea;

	@Column(columnDefinition = "Date")
	private LocalDate dataServico;

	public Servico() {

	}

	public Servico(Cliente cliente, TipoServico tiposervico, FormaPagamento formapagamento, String placa,
			String valorTotal, String taArea, LocalDate dataServico, Status status) {
		super();
		this.cliente = cliente;
		this.tiposervico = tiposervico;
		this.formapagamento = formapagamento;
		this.status = status;
		this.valorTotal = valorTotal;
		this.taArea = taArea;
		this.dataServico = dataServico;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public TipoServico getTiposervico() {
		return tiposervico;
	}

	public void setTiposervico(TipoServico tiposervico) {
		this.tiposervico = tiposervico;
	}

	public FormaPagamento getFormapagamento() {
		return formapagamento;
	}

	public void setFormapagamento(FormaPagamento formapagamento) {
		this.formapagamento = formapagamento;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getTaArea() {
		return taArea;
	}

	public void setTaArea(String taArea) {
		this.taArea = taArea;
	}

	public LocalDate getDataServico() {
		return dataServico;
	}

	public void setDataServico(LocalDate dataServico) {
		this.dataServico = dataServico;
	}

	public void setPlacaCliente(Cliente value) {
		// TODO Auto-generated method stub

	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
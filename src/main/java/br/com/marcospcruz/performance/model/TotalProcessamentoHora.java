package br.com.marcospcruz.performance.model;

import java.util.Date;

public class TotalProcessamentoHora implements
		Comparable<TotalProcessamentoHora> {

	private int total;

	private Date dataHoraProcessamento;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Date getDataHoraProcessamento() {
		return dataHoraProcessamento;
	}

	public void setDataHoraProcessamento(Date dataHoraProcessamento) {
		this.dataHoraProcessamento = dataHoraProcessamento;
	}

	@Override
	public int compareTo(TotalProcessamentoHora compareTotalProcessamentoHora) {
		// TODO Auto-generated method stub

		return this.dataHoraProcessamento
				.compareTo(compareTotalProcessamentoHora
						.getDataHoraProcessamento());
	}

	// @Override
	// public String toString() {
	// return "TotalProcessamentoHora [total=" + total
	// + ", dataHoraProcessamento=" + dataHoraProcessamento + "]";
	// }

	@Override
	public String toString() {
		return dataHoraProcessamento.toString();
	}

}

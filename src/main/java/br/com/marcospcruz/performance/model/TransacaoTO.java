package br.com.marcospcruz.performance.model;

import java.util.Date;

public class TransacaoTO {

	private Date dataHoraTransacao;
	private int sequencial;
	private PistaTO pista;

	private float valorpagamento;
	private String placaveiculo;
	private PerfilCompensacaoSgmpTO perfilCompensacaoSGMP;

	public Date getDataHoraTransacao() {
		return dataHoraTransacao;
	}

	public void setDataHoraTransacao(Date dataHoraTransacao) {
		this.dataHoraTransacao = dataHoraTransacao;
	}

	public int getSequencial() {
		return sequencial;
	}

	public void setSequencial(int sequencial) {
		this.sequencial = sequencial;
	}

	public PistaTO getPista() {
		return pista;
	}

	public void setPista(PistaTO pista) {
		this.pista = pista;
	}

	public float getValorpagamento() {
		return valorpagamento;
	}

	public void setValorPagamento(float valorpagamento) {
		this.valorpagamento = valorpagamento;
	}

	public String getPlacaveiculo() {
		return placaveiculo;
	}

	public void setPlacaVeiculo(String placaveiculo) {
		this.placaveiculo = placaveiculo;
	}

	public PerfilCompensacaoSgmpTO getPerfilCompensacaoSGMP() {
		return perfilCompensacaoSGMP;
	}

	public void setPerfilCompensacaoSGMP(
			PerfilCompensacaoSgmpTO perfilCompensacaoSGMP) {
		this.perfilCompensacaoSGMP = perfilCompensacaoSGMP;
	}

	@Override
	public String toString() {
		return "TransacaoTO [dataHoraTransacao=" + dataHoraTransacao
				+ ", sequencial=" + sequencial + ", pista=" + pista
				+ ", valorpagamento=" + valorpagamento + ", placaveiculo="
				+ placaveiculo + ", perfilCompensacaoSGMP="
				+ perfilCompensacaoSGMP + "]";
	}

}

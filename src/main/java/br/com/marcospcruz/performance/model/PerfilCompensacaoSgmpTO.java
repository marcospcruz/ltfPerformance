package br.com.marcospcruz.performance.model;

import java.sql.Timestamp;
import java.util.Date;

public class PerfilCompensacaoSgmpTO {

	private Date dataCompensacao;

	private int idClasseContrato;

	private int sequencialLote;

	private int codigoCompensacao;

	public PerfilCompensacaoSgmpTO(Date dataCompensacao) {
		// TODO Auto-generated constructor stub
		this.dataCompensacao = dataCompensacao;
	}

	public Date getDataCompensacao() {
		return dataCompensacao;
	}

	public void setDataCompensacao(Date dataCompensacao) {
		this.dataCompensacao = dataCompensacao;
	}

	public int getIdClasseContrato() {
		return idClasseContrato;
	}

	public void setIdClasseContrato(int idClasseContrato) {
		this.idClasseContrato = idClasseContrato;
	}

	public int getSequencialLote() {
		return sequencialLote;
	}

	public void setSequencialLote(int sequencialLote) {
		this.sequencialLote = sequencialLote;
	}

	public int getCodigoCompensacao() {
		return codigoCompensacao;
	}

	public void setCodigoCompensacao(int codigoCompensacao) {
		this.codigoCompensacao = codigoCompensacao;
	}

}

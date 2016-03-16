package br.com.marcospcruz.performance.model;

public class PistaTO {

	private PracaTO praca;

	private String idComplementoLocal;

	private String descricao;

	public PistaTO(String idComplementoLocal) {
		// TODO Auto-generated constructor stub
		this.idComplementoLocal = idComplementoLocal;
	}

	public PracaTO getPraca() {
		return praca;
	}

	public void setPraca(PracaTO praca) {
		this.praca = praca;
	}

	public String getIdComplementoLocal() {
		return idComplementoLocal;
	}

	public void setIdComplementoLocal(String idComplementoLocal) {
		this.idComplementoLocal = idComplementoLocal;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}

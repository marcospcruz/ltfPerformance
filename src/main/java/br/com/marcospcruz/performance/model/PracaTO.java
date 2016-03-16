package br.com.marcospcruz.performance.model;

import java.util.Collection;

public class PracaTO {
	private int idlocal;
	private String descricao;
	private Collection<PistaTO> pistas;

	public PracaTO(int idLocal) {

		this.idlocal=idLocal;
		
	}

	public int getIdlocal() {
		return idlocal;
	}

	public void setIdlocal(int idlocal) {
		this.idlocal = idlocal;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Collection<PistaTO> getPistas() {
		return pistas;
	}

	public void setPistas(Collection<PistaTO> pistas) {
		this.pistas = pistas;
	}

}

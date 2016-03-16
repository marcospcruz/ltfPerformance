package br.com.marcospcruz.performance.util;

public enum ClasseContratoEnum {

	PessoaJuridica(2), PessoaFisica(1);

	private final int indice;

	private static final String[] classesContrato = { "Natural Person",
			"Legal Entity" };

	private ClasseContratoEnum(int indice) {
		// TODO Auto-generated constructor stub

		this.indice = indice;

	}

	/**
	 * 
	 * @return
	 */
	public String getValue() {

		return classesContrato[indice - 1];

	}

	/**
	 * 
	 * @return
	 */
	public int getIndice() {

		return indice;
	}
}

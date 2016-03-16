package br.com.marcospcruz.performance.util;

public class ProcessadorCSV {

	private StringBuffer linha;

	public ProcessadorCSV(StringBuffer linha) {
		// TODO Auto-generated constructor stub

		this.linha = linha;
	}

	/**
	 * 
	 * @param column
	 * @return
	 */
	public String getValueAt(int column) {
		// TODO Auto-generated method stub

		int colIndex = 0;

		String value = "";

		for (char caractere : linha.toString().toCharArray()) {

			if (caractere == ',') {

				colIndex++;

				if (colIndex == column)

					break;

				else
					value = "";

			} else if (caractere != '\"')

				value += caractere;

		}

		return value;

	}

}

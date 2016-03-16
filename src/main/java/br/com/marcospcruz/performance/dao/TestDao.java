package br.com.marcospcruz.performance.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.marcospcruz.performance.model.PerfilCompensacaoSgmpTO;
import br.com.marcospcruz.performance.model.PistaTO;
import br.com.marcospcruz.performance.model.PracaTO;
import br.com.marcospcruz.performance.model.TransacaoTO;
import br.com.marcospcruz.performance.util.ConstantesEnum;
import br.com.marcospcruz.performance.util.MyLoggerSingleton;
import br.com.marcospcruz.performance.util.ProcessadorCSV;
import br.com.marcospcruz.performance.util.Utilitario;

public class TestDao {

	private final static String FILE_NAME = "/statistisc.csv";

	public List<TransacaoTO> loadObjetos() throws IOException {
		// TODO Auto-generated method stub

		List<TransacaoTO> trns = carregaArquivo();

		return trns;
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	private List<TransacaoTO> carregaArquivo() throws IOException {
		// TODO Auto-generated method stub

		List<TransacaoTO> trns = new ArrayList<TransacaoTO>();

		BufferedReader reader = null;

		InputStream stream = null;

		stream = getClass().getResourceAsStream(FILE_NAME);

		reader = new BufferedReader(new InputStreamReader(stream));

		int cont = 0;
		log("Início");
		try {

			while (reader.ready()) {

				StringBuffer linha = new StringBuffer(reader.readLine());

				if (cont > 0 && linha.length() > 0) {

					TransacaoTO trn = populaObjetos(linha);

					String dataString = null;

					try {
						dataString = Utilitario.formatDate(
								trn.getDataHoraTransacao()).substring(0, 10);

					} catch (NullPointerException e) {

						e.printStackTrace();

					}
					if (dataString.equals("02/09/2015")
							|| dataString.equals("03/09/2015"))

						continue;

					trns.add(trn);

				}

				cont++;

				if ((cont % (Integer) ConstantesEnum.QuantLinhaProcessamentoTeste
						.getValue()) == 0)

					log("linha: " + cont);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {

			reader.close();

			log("Fim");

		}

		return trns;
	}

	private void log(String string) {
		// TODO Auto-generated method stub
		MyLoggerSingleton.getInstance()
				.writeLog(this, new StringBuffer(string));
	}

	/**
	 * xx
	 */
	public TransacaoTO populaObjetos(StringBuffer linha) {
		// TODO Auto-generated method stub

		TransacaoTO transacao = null;

		try {

			ProcessadorCSV processador = new ProcessadorCSV(linha);

			transacao = new TransacaoTO();

			String datePattern = ConstantesEnum.DateHourPattern.getValue()
					.toString();
			Date dataHoraTransacao = Utilitario.stringToDate(
					processador.getValueAt(1), datePattern);

			transacao.setDataHoraTransacao(dataHoraTransacao);

			String seqColumnValue = processador.getValueAt(2);

			transacao.setSequencial(new Integer(seqColumnValue));

			transacao.setPista(new PistaTO(processador.getValueAt(4)));

			transacao.getPista().setPraca(
					new PracaTO(new Integer(processador.getValueAt(3))));

			transacao.setValorPagamento(new Float(processador.getValueAt(5)));

			transacao.setPlacaVeiculo(processador.getValueAt(6));

			Date dataCompensacao = Utilitario.stringToDate(
					processador.getValueAt(7), datePattern);

			if (dataCompensacao != null) {

				PerfilCompensacaoSgmpTO sgmpTrn = new PerfilCompensacaoSgmpTO(
						dataCompensacao);

				sgmpTrn.setIdClasseContrato(new Integer(processador
						.getValueAt(8)));

				sgmpTrn.setSequencialLote(new Integer(processador.getValueAt(9)));

				sgmpTrn.setCodigoCompensacao(new Integer(processador
						.getValueAt(10)));

				transacao.setPerfilCompensacaoSGMP(sgmpTrn);

			}
		} catch (NumberFormatException e) {

			e.printStackTrace();

		}

		return transacao;

	}
}

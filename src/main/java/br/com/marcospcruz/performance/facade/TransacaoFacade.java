package br.com.marcospcruz.performance.facade;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.time.Hour;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import br.com.marcospcruz.performance.dao.PerfilCompensacaoSgmpDao;
import br.com.marcospcruz.performance.dao.TestDao;
import br.com.marcospcruz.performance.dao.TransacaoDao;
import br.com.marcospcruz.performance.model.PerfilCompensacaoSgmpTO;
import br.com.marcospcruz.performance.model.TotalProcessamentoHora;
import br.com.marcospcruz.performance.model.TransacaoTO;
import br.com.marcospcruz.performance.util.ClasseContratoEnum;
import br.com.marcospcruz.performance.util.ConstantesEnum;
import br.com.marcospcruz.performance.util.Database;
import br.com.marcospcruz.performance.util.MyLoggerSingleton;
import br.com.marcospcruz.performance.util.MyRunnableLogger;
import br.com.marcospcruz.performance.util.Utilitario;

public class TransacaoFacade {

	private Boolean isTestFlow;
	private String chartPerformanceTitle;
	private String chartPerformanceXaxisLabel;

	private boolean chartCreateLegend;
	private boolean chartGenerateToolTips;
	private boolean chartGenerateUrl;
	private String chartYaxisLabel;
	private String chartSyncTitle;
	private String chartSyncXaxisLabel;

	public TransacaoFacade() {

		// chartSyncYaxisLabel=ConstantesEnum.ChartPerformanceYaxisLabel

		isTestFlow = (Boolean) ConstantesEnum.IsTestFlow.getValue();

		chartPerformanceTitle = (String) ConstantesEnum.ChartPerformanceTitle
				.getValue();

		chartPerformanceXaxisLabel = ConstantesEnum.ChartPerformanceXaxisLabel
				.getValue().toString();

		chartCreateLegend = (Boolean) ConstantesEnum.ChartCreateLegend
				.getValue();

		chartGenerateToolTips = (Boolean) ConstantesEnum.ChartGenerateToolTips
				.getValue();

		chartGenerateUrl = (Boolean) ConstantesEnum.ChartGenerateUrls
				.getValue();

		chartYaxisLabel = (String) ConstantesEnum.ChartPerformanceYaxisLabel
				.getValue();

		chartSyncTitle = (String) ConstantesEnum.ChartSyncTitle.getValue();

		chartSyncXaxisLabel = (String) ConstantesEnum.ChartSyncXaxisLabel
				.getValue();

		chartYaxisLabel = (String) ConstantesEnum.ChartPerformanceYaxisLabel
				.getValue();

	}

	/**
	 * 
	 * @param dateTo
	 * @param dateFrom
	 * @param b
	 * @return
	 * @throws Exception
	 */
	public JFreeChart createSgapSgmpSyncChart(boolean isAreaChart,
			boolean[] selectLines, Date dateFrom, Date dateTo) throws Exception {
		// TODO Auto-generated method stub

		Database dao = new TransacaoDao();

		List<TransacaoTO> objetos = null;
		// fluxo de teste ou produção
		if ((Boolean) ConstantesEnum.IsTestFlow.getValue()) {

			try {

				objetos = loadObjetosForTest();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			objetos = dao.readAll(dateFrom.getTime(), dateTo.getTime());

		}

		JFreeChart chart = null;

		// if (!isAreaChart) {

		chart = createSyncChart(objetos, selectLines);

		// }

		return chart;
	}

	/**
	 * 
	 * @param printPerformanceSerie
	 * @param isAreaChart
	 * @param dateTo
	 * @param dateFrom
	 * @param objetos
	 * @return
	 * @throws Exception
	 */
	public JFreeChart createPerformanceChart(boolean[] printPerformanceSerie,
			boolean isAreaChart, Date dateFrom, Date dateTo) throws Exception {
		// TODO Auto-generated method stub

		List<TransacaoTO> objetosSgap = null;
		List<PerfilCompensacaoSgmpTO> objetosSgmp = null;

		if (isTestFlow) {

			try {
				objetosSgap = loadObjetosForTest();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			if (printPerformanceSerie[0])
				objetosSgap = extractSgapEletronicTransactions(
						dateFrom.getTime(), dateTo.getTime());
			if (printPerformanceSerie[1])
				objetosSgmp = extractSgmpEletronicTransactions(
						dateFrom.getTime(), dateTo.getTime());

		}

		// List<TransacaoTO> objetos = new ArrayList<TransacaoTO>();

		JFreeChart chart = null;

		if (!isAreaChart) {

			chart = createPerformanceLineChart(objetosSgap, objetosSgmp,
					printPerformanceSerie);

		} else {

			chart = createPerformanceAreaChart(objetosSgap);

		}

		return chart;

	}

	/**
	 * 
	 * @param time
	 * @param time2
	 * @return
	 * @throws Exception 
	 */
	private List<PerfilCompensacaoSgmpTO> extractSgmpEletronicTransactions(
			long time, long time2) throws Exception {
		// TODO Auto-generated method stub
		Database dao = new PerfilCompensacaoSgmpDao();

		List<PerfilCompensacaoSgmpTO> transacoes = dao.readAll(time, time2);

		return transacoes;
	}

	/**
	 * 
	 * @param time
	 * @param time2
	 * @return
	 * @throws Exception 
	 */
	private List<TransacaoTO> extractSgapEletronicTransactions(long time,
			long time2) throws Exception {
		// TODO Auto-generated method stub

		Database dao = new TransacaoDao();

		List<TransacaoTO> transacoes = dao.readAll(time, time2);

		return transacoes;
	}

	/**
	 * test context
	 * 
	 * @return
	 * @throws IOException
	 */
	private List<TransacaoTO> loadObjetosForTest() throws IOException {
		// TODO Auto-generated method stub

		TestDao test = new TestDao();

		return test.loadObjetos();
	}

	/**
	 * 
	 * @param objetos
	 * @return
	 * @throws Exception
	 */
	private JFreeChart createPerformanceAreaChart(List<TransacaoTO> objetos)
			throws Exception {
		// TODO Auto-generated method stub

		CategoryDataset dataset = createCategoryDataset(objetos);

		JFreeChart chart = ChartFactory.createAreaChart(chartPerformanceTitle,
				chartPerformanceXaxisLabel, chartYaxisLabel, dataset);

		return chart;
	}

	/**
	 * 
	 * @param objetos
	 * @param objetosSgmp
	 * @param printPerformanceSerie
	 * @return
	 * @throws Exception
	 */
	private JFreeChart createPerformanceLineChart(List<TransacaoTO> objetos,
			List<PerfilCompensacaoSgmpTO> objetosSgmp,
			boolean[] printPerformanceSerie) throws Exception {

		XYDataset dataset = createXYPerformanceDataset(objetos, objetosSgmp,
				printPerformanceSerie);

		JFreeChart chart = createTimeSeriesChart(dataset,
				chartPerformanceTitle, chartPerformanceXaxisLabel);

		return chart;
	}

	/**
	 * 
	 * @param dataset
	 * @param chartTitle
	 * @param chartXaxisLabel
	 * @return
	 */

	private JFreeChart createTimeSeriesChart(XYDataset dataset,
			String chartTitle, String chartXaxisLabel) {
		// TODO Auto-generated method stub

		String dataGrafico = Utilitario.formatDate(new Date());

		return ChartFactory.createTimeSeriesChart(chartTitle + " - "
				+ dataGrafico, // chart
				// title
				chartXaxisLabel, // x-axis label
				chartYaxisLabel,// y-axis label
				dataset, // data
				chartCreateLegend, // create legend?
				chartGenerateToolTips, // generate tooltips
				chartGenerateUrl // generate urls
				);

	}

	/**
	 * 
	 * @param objetos
	 * @return
	 * @throws Exception
	 */
	private JFreeChart createSyncChart(List<TransacaoTO> objetos,
			boolean[] selectLines) throws Exception {
		// TODO Auto-generated method stub
		// createPerformanceLineChart

		XYDataset dataset = createXYSyncDataset(objetos, selectLines);

		JFreeChart chart = createTimeSeriesChart(dataset, chartSyncTitle,
				chartSyncXaxisLabel);

		return chart;
	}

	/**
	 * Método responsável em gerar linhas conforme demanda de separação de
	 * linhas por classe de contrato
	 * 
	 * @param objetos
	 * @param selectLines
	 * @return
	 * @throws Exception
	 */
	private XYDataset createXYSyncDataset(List<TransacaoTO> objetos,
			boolean[] selectLines) throws Exception {
		// TODO Auto-generated method stub

		TimeSeriesCollection dataset = new TimeSeriesCollection();

		TimeSeries s1 = createTimeSeries(
				countTransactions(objetos, true, false),
				ConstantesEnum.SyncLabelSeries1.getValue().toString());

		dataset.addSeries(s1);

		int contFalse = 0;

		for (int i = 0; i < selectLines.length; i++) {

			TimeSeries series = null;

			if (selectLines[i]) {

				String classeContratoLabel = (i == 0 ? ClasseContratoEnum.PessoaFisica
						.getValue() : ClasseContratoEnum.PessoaJuridica
						.getValue());

				String seriesLabel = classeContratoLabel
						+ " debitted Transaction Total";

				series = createTimeSeries(
						countTransactionsPerClasseContrato(objetos, (i + 1)),
						seriesLabel);

			} else

				contFalse++;

			if (i == selectLines.length - 1) {

				if (i == (contFalse - 1))

					series = createTimeSeries(countTransactions(objetos, true),
							ConstantesEnum.SyncLabelSeries2.getValue()
									.toString());
			}

			if (series != null)

				dataset.addSeries(series);

		}

		// sync trns

		// dataset.addSeries(s2);

		return dataset;
	}

	/**
	 * 
	 * @param objetos
	 * @param objetosSgmp
	 * @param printPerformanceSerie
	 * @return
	 * @throws Exception
	 */
	private XYDataset createXYPerformanceDataset(List<TransacaoTO> objetos,
			List<PerfilCompensacaoSgmpTO> objetosSgmp,
			boolean[] printPerformanceSerie) throws Exception {
		// TODO Auto-generated method stub

		TimeSeriesCollection dataset = new TimeSeriesCollection();

		if (printPerformanceSerie[0]) {

			TimeSeries s1 = createTimeSeries(
					countTransactions(objetos, true, false),
					ConstantesEnum.ChartPerformanceLabelSeries1.getValue()
							.toString());

			dataset.addSeries(s1);

		}
		if (printPerformanceSerie[1]) {
			TimeSeries s2 = createTimeSeries(
					countTransactionsPerformanceSGMP(objetosSgmp, false),
					ConstantesEnum.ChartPerformanceLabelSeries2.getValue()
							.toString());

			dataset.addSeries(s2);

		}

		return dataset;
	}

	/**
	 *
	 * @param objetos
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private CategoryDataset createCategoryDataset(List<TransacaoTO> objetos)
			throws Exception {
		// TODO Auto-generated method stub

		// double data[][] = createDataMatrix(objetos);

		Comparable[] comp0 = createDataMatrix(objetos, true);

		Comparable[] comp1 = createDataMatrix(objetos, false);

		double data[][] = new double[2][0];

		data[0] = createDataMatrix(comp0);

		data[1] = createDataMatrix(comp1);

		CategoryDataset dataset = DatasetUtilities.createCategoryDataset(comp0,
				comp1, data);

		return dataset;
	}

	// /**
	// *
	// * @param objetos
	// * @return
	// * @throws Exception
	// */
	// @SuppressWarnings("unused")
	// private double[][] createDataMatrix(List<TransacaoTO> objetos)
	// throws Exception {
	// // TODO Auto-generated method stub
	//
	// Map<Long, Integer> mapa = countTransactions(objetos, true);
	//
	// TreeSet<Long> millis = new TreeSet<Long>(mapa.keySet());
	//
	// TotalProcessamentoHora[] procs = new TotalProcessamentoHora[millis
	// .size()];
	//
	// int cont = 0;
	//
	// for (Long key : millis) {
	//
	// TotalProcessamentoHora proc = new TotalProcessamentoHora();
	//
	// proc.setDataHoraProcessamento(new Date(key));
	//
	// proc.setTotal(mapa.get(key));
	//
	// procs[cont] = proc;
	//
	// cont++;
	//
	// }
	//
	// double data[][] = new double[1][procs.length];
	//
	// return data;
	// }

	private double[] createDataMatrix(Comparable[] comp0) {
		// TODO Auto-generated method stub

		double[] d = new double[comp0.length];

		for (int i = 0; i < comp0.length; i++) {

			TotalProcessamentoHora t = (TotalProcessamentoHora) comp0[i];

			d[i] = t.getTotal();

		}

		return d;
	}

	/**
	 * 
	 * @param objetos
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private Comparable[] createDataMatrix(List<TransacaoTO> objetos,
			boolean pending) throws Exception {
		// TODO Auto-generated method stub

		Map<Long, Integer> mapa = countTransactions(objetos, pending);

		TreeSet<Long> millis = new TreeSet<Long>(mapa.keySet());

		TotalProcessamentoHora[] procs = new TotalProcessamentoHora[millis
				.size()];

		int cont = 0;

		for (Long key : millis) {

			TotalProcessamentoHora proc = new TotalProcessamentoHora();

			proc.setDataHoraProcessamento(new Date(key));

			proc.setTotal(mapa.get(key));

			procs[cont] = proc;

			log(proc.toString());

			cont++;

		}

		return procs;
	}

	/**
	 * 
	 * @param valuesMap
	 * @param label
	 * @return
	 */
	private TimeSeries createTimeSeries(Map<Long, Integer> valuesMap,
			String label) {
		// TODO Auto-generated method stub

		TimeSeries series = new TimeSeries(label);

		if (valuesMap != null) {

			TreeSet<Long> keys = new TreeSet<Long>(valuesMap.keySet());

			for (Long key : keys) {

				// Millisecond hour = new Millisecond();

				RegularTimePeriod time = new Hour(new Date(key));

				int value = valuesMap.get(key);

				series.add(time, value);

			}

		}

		geraHistogram(valuesMap, label);

		return series;

	}

	/**
	 * 
	 * @param valuesMap
	 */
	private void geraHistogram(Map<Long, Integer> valuesMap, String title) {
		// TODO Auto-generated method stub

		Runnable myRunnable = new MyRunnableLogger(valuesMap, title);

		Thread thread = new Thread(myRunnable);

		thread.run();

	}

	/**
	 * 
	 * @param objetos
	 * @return
	 * @throws Exception
	 */
	private Map<Long, Integer> countTransactions(List<TransacaoTO> objetos,
			boolean sync) throws Exception {
		// TODO Auto-generated method stub

		return countTransactions(objetos, false, sync);
	}

	/**
	 * 
	 * @param objetos
	 * @param pendingTransactions
	 * @param sync
	 * @return
	 */
	private Map<Long, Integer> countTransactions(List<TransacaoTO> objetos,
			boolean pendingTransactions, boolean sync) throws Exception {
		// TODO Auto-generated method stub

		Map<Long, Integer> mapa = null;

		for (TransacaoTO trn : objetos) {
			// fluxo de contagem de transações por data/hora de ocorrencia
			if (pendingTransactions) {

				mapa = processTransaction(trn.getDataHoraTransacao(), mapa);

			} else if (!sync) {
				// fluxo de contagem de transações pela data hora processamento
				if (trn.getPerfilCompensacaoSGMP() != null) {

					mapa = processTransaction(trn.getPerfilCompensacaoSGMP()
							.getDataCompensacao(), mapa);

				}

			} else if (sync) {
				// fluxo de contagem de transações do SGAP em paralelo com o
				// perfil do SGMP
				if (trn.getPerfilCompensacaoSGMP() != null)

					mapa = processTransaction(trn.getDataHoraTransacao(), mapa);

			}

		}

		mapa = verifySequence(mapa);

		return mapa;

	}

	/**
	 * 
	 * @param mapa
	 * @return
	 */
	private Map<Long, Integer> verifySequence(Map<Long, Integer> mapa) {
		// TODO Auto-generated method stub

		TreeSet<Long> keysTreeSet = new TreeSet<Long>(mapa.keySet());

		Date dataAnterior = null;

		int cont = 0;

		for (Long key : keysTreeSet) {

			Date dataAtual = new Date(key);

			// Calendar atualCal = new GregorianCalendar();
			// Calendar anteriorCal = null;
			// atualCal.setTime(dataAtual);

			int diffHora = 0;

			if (foraDaSequencia(cont, dataAnterior, dataAtual, diffHora)) {

				// MyLoggerSingleton.getInstance().writeLog(
				// this,
				// "Filling 0 the gap between "
				// + (dataAnterior == null ? "midnight"
				// : dataAnterior) + " and " + dataAtual);

				mapa = preencherComZeroGap(dataAnterior, dataAtual, mapa);

			}

			dataAnterior = dataAtual;

			cont++;

		}

		return mapa;
	}

	/**
	 * 
	 * @param cont
	 * @param dataAnterior
	 * @param dataAtual
	 * @param diffHora
	 * @return
	 */
	private boolean foraDaSequencia(int cont, Date dataAnterior,
			Date dataAtual, int diffHora) {
		// TODO Auto-generated method stub
		// (cont == 0 && atualCal.get(Calendar.HOUR) == 1) || diffHora > 1

		Calendar atualCal = new GregorianCalendar();

		atualCal.setTime(dataAtual);

		Calendar anteriorCal = null;

		if (dataAnterior == null) {

			// int horaAtual = atualCal.get(Calendar.HOUR);

			// if (horaAtual != 0) {

			return true;

			// }

		} else {

			anteriorCal = new GregorianCalendar();

			anteriorCal.setTime(dataAnterior);

		}

		// System.out.println(dataAnterior + "," + dataAtual);

		if (Utilitario
				.validaDiferencaData(anteriorCal, atualCal, Calendar.YEAR) > 1) {

			return true;

		} else if (Utilitario.validaDiferencaData(anteriorCal, atualCal,
				Calendar.MONTH) >= 1)

			return true;

		else if (Utilitario.validaDiferencaData(anteriorCal, atualCal,
				Calendar.DAY_OF_YEAR) >= 2) {

			return true;

		} else if (Utilitario.validaDiferencaData(anteriorCal, atualCal,
				Calendar.HOUR) > 1) {

			return true;

		}

		// if ((cont == 0 && anteriorCal.get(Calendar.HOUR) == 1) ||
		// diffHora >
		// 1)
		// return true;

		return false;
	}

	/**
	 * 
	 * @param dataAnterior
	 * @param dataAtual
	 * @param mapa
	 * @return
	 */
	private Map<Long, Integer> preencherComZeroGap(Date dataAnterior,
			Date dataAtual, Map<Long, Integer> mapa) {
		// TODO Auto-generated method stub

		int horaMilis = 3600000;

		Calendar calDataAnterior = new GregorianCalendar();

		long dataAnteriorMilis = 0;

		if (dataAnterior == null) {

			int primeiraHora = 0;

			calDataAnterior.setTime(dataAtual);

			calDataAnterior.set(Calendar.HOUR, primeiraHora);

			dataAnteriorMilis = calDataAnterior.getTimeInMillis();

		} else {

			dataAnteriorMilis = dataAnterior.getTime() + horaMilis;

		}

		for (; dataAnteriorMilis < dataAtual.getTime(); dataAnteriorMilis += horaMilis) {

			mapa.put(dataAnteriorMilis, 0);

		}

		return mapa;

	}

	/**
	 * 
	 * @param objetosSgmp
	 * @param b
	 * @return
	 */
	private Map<Long, Integer> countTransactionsPerformanceSGMP(
			List<PerfilCompensacaoSgmpTO> objetosSgmp, boolean b) {
		// TODO Auto-generated method stub

		Map<Long, Integer> mapa = new HashMap<Long, Integer>();

		if (objetosSgmp != null)

			for (PerfilCompensacaoSgmpTO p : objetosSgmp) {

				mapa = processTransaction(p.getDataCompensacao(), mapa);

			}

		mapa = verifySequence(mapa);

		return mapa;
	}

	/**
	 * 
	 * @param objetos
	 * @param idClasseContrato
	 * @return
	 * @throws Exception
	 */
	private Map<Long, Integer> countTransactionsPerClasseContrato(
			List<TransacaoTO> objetos, int idClasseContrato) throws Exception {
		// TODO Auto-generated method stub

		Map<Long, Integer> mapa = null;

		log("countTransactionsPerClasseContrato" + idClasseContrato);

		for (TransacaoTO trn : objetos) {

			if (trn.getPerfilCompensacaoSGMP() != null
					&& trn.getPerfilCompensacaoSGMP().getIdClasseContrato() == idClasseContrato)

				mapa = processTransaction(trn.getDataHoraTransacao(), mapa);

			log("countTransactionsPerClasseContrato"
					+ trn.getDataHoraTransacao());

		}

		String message = "There is no data for idclassecontrato ";

		log("idclassecontrato: " + idClasseContrato
				+ " mapa de transacoes processadas: " + mapa + "-> " + message);

		// if (mapa == null)
		// throw new Exception(message + idClasseContrato);

		if (mapa != null)
			mapa = verifySequence(mapa);

		return mapa;

	}

	/**
	 * 
	 * @param string
	 */
	private void log(String string) {
		// TODO Auto-generated method stub
		MyLoggerSingleton.getInstance()
				.writeLog(this, new StringBuffer(string));
	}

	/**
	 * 
	 * @param formatedDate
	 * @param trnMap
	 * @return
	 */
	private Map<Long, Integer> processTransaction(Date trnTime,
			Map<Long, Integer> trnMap) {
		// TODO Auto-generated method stub

		if (trnMap == null)
			trnMap = new HashMap<Long, Integer>();

		try {

			SimpleDateFormat sdf = new SimpleDateFormat(
					ConstantesEnum.DateHourPattern.getValue().toString());
			String formatedDate = sdf.format(trnTime);

			Date hour = null;
			Long key = null;
			int value = 1;

			hour = sdf.parse(formatedDate);

			key = hour.getTime();

			if (trnMap.containsKey(key)) {

				value = trnMap.get(key);

				value += 1;

			}

			trnMap.put(key, value);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (NullPointerException e) {

			e.printStackTrace();

		}

		return trnMap;
	}

}

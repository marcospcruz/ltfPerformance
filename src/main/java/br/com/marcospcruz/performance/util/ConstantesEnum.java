package br.com.marcospcruz.performance.util;

public enum ConstantesEnum {
	/**
	 * Pending Transactions - Passage Date/Time
	 */
	ChartPerformanceLabelSeries1(0),
	/**
	 * Debitted into SGMP Accounts - Compensation Date/Time
	 */
	ChartPerformanceLabelSeries2(1),
	/**
	 * Eletronic Passages
	 */
	SyncLabelSeries1(2),
	/**
	 * Debited Eletronic Passages
	 */
	SyncLabelSeries2(3),
	// ChartSyncLabelSeries1(4),
	// ChartSyncLabelSeries2(5),
	/**
	 * dd/MM/yyyy HH
	 */
	DateHourPattern(4),
	/**
	 * dd/MM/yyyy HH:mm:ss
	 */
	DateTimestampPattern(5),
	/**
	 * Entry Time
	 */
	ChartPerformanceXaxisLabel(6),
	/**
	 * Total Processed
	 */
	ChartPerformanceYaxisLabel(7),
	/**
	 * Legend for the series
	 */
	ChartCreateLegend(8),
	/**
	 * tool tip text for the chart series (maybe)
	 */
	ChartGenerateToolTips(9),
	/**
	 * urls
	 */
	ChartGenerateUrls(10),

	ParamQueryQtDias(11),

	/**
	 * SGMP Entries Performance
	 */
	ChartPerformanceTitle(12),
	/**
	 * SGAP X SGMP Synchronism
	 */
	ChartSyncTitle(13),

	/**
	 * Switches the flow for prod or test. true=test(It'll pick the data from a
	 * local datasource) and false=prod(It'll pick the data from the Database)
	 */
	IsTestFlow(14),
	/**
	 * Passage Time
	 */
	ChartSyncXaxisLabel(15),
	/**
	 * Quantity of lines to log when in test flow.
	 */
	QuantLinhaProcessamentoTeste(16),

	JButtonPerformanceText(17),

	JButtonSgapSgmpSyncText(18),

	JPanelPerformanceText(19),

	JPanelSyncAppsText(20),

	DateHourWithoutSeparators(21),

	SgapQueryName(22),

	SgmpQueryName(23),

	ConfigPropertiesFile(24),

	ActionPerformedParameterDateFromKey(25), ActionPerformedParameterDateToKey(
			26), ActionPerformedParameterCheckBoxValuesToKey(27), ChartDimensionHeight(
			28), ChartDimensionWidth(29), MainGuiFrameTitle(30), ActionPerformedParameterJFrameOwnerKey(
			31);

	// private static final boolean IS_TEST_FLOW = true;

	private static final boolean IS_TEST_FLOW = false;

	private static final String DATE_FORMAT_PATTERN_DATE_HOUR_WITHOUT_SEPARATOR = "yyyyMMddHHmmss";

	// date patterns
	private static final String DATE_FORMAT_PATTERN_DATE_HOUR = "dd/MM/yyyy HH";
	private static final String DATE_FORMAT_PATTERN_DATE_TIME = "dd/MM/yyyy HH:mm:ss";
	// constantes para os gráficos
	private static final String CHART_PERFORMANCE_LABEL_SERIES_1 = "Pending Transactions - Passage Date/Time";
	private static final String CHART_PERFORMANCE_LABEL_SERIES_2 = "Debitted into SGMP Accounts - Compensation Date/Time";

	private static final String CHART_SYNC_LABEL_SERIES_1 = "Eletronic Passages";
	private static final String CHART_SYNC_LABEL_SERIES_2 = "Debited Eletronic Passages";

	private static final String CHART_PERFORMANCE_X_AXIS_LABEL = "Entry Time";
	private static final String CHART_PERFORMANCE_Y_AXIX_LABEL = "Total Processed";

	private static final String CHART_SYNC_X_AXIS_LABEL = "Passage Time";

	private static final boolean CHART_CREATE_LEGEND = true;
	private static final boolean CHART_GENERATE_TOOL_TIPS = true;
	private static final boolean CHART_GENERATE_URLS = false;

	private static final int QUERY_PARAM_DIAS = 30;
	private static final String CHART_PERFORMANCE_TITLE = "SGMP Entries Performance";
	private static final String CHART_SYNC_TITLE = "SGAP X SGMP Synchronism";

	private static final int QUANT_LINHA_CARREGAMENTO_DADOS_TESTE = 15000;

	private static final String JBUTTON1_TEXT = "Plot Performance Line";

	private static final String JBUTTON2_TEXT = "SGAP x SGMP - Sync Status";

	private static final String JPANEL_PERFORMANCE_TEXT = "SGMP Compensation Performance Chart";

	private static final String JPANEL_SYNC_APPS_TEXT = "SGAP-SGMP Synchronization Status";

	// SQL paramether
	private static final String QUERY_SGAP_NAME = "query.performance.sgap.compensation";
	private static final String QUERY_SGMP_NAME = "query.performance.sgmp.compensation";
	private static final String CONFIG_PROPERTIES_FILENAME = "/config.properties";

	// ACTION PERFORMED PARAMETHERS
	private static final String DATE_FROM = "dateFrom";
	private static final String DATE_TO = "dateTo";
	private static final String CHECK_BOXES_VALUES = "checkboxvalues";

	private static final int CHART_DIMENSION_WIDTH = 800;
	private static final int CHART_DIMENSION_HEIGHT = 600;

	private static final String MAIN_GUI_WINDOW_TITLE = "Chart SGMP Performance Creator";

	private static final String JFRAME_OWNER_KEY = "owner";

	private Object chartLabels[] = { CHART_PERFORMANCE_LABEL_SERIES_1,// 0
			CHART_PERFORMANCE_LABEL_SERIES_2,// 1
			CHART_SYNC_LABEL_SERIES_1,// 2
			CHART_SYNC_LABEL_SERIES_2,// 3
			DATE_FORMAT_PATTERN_DATE_HOUR,// 4
			DATE_FORMAT_PATTERN_DATE_TIME, // 5
			CHART_PERFORMANCE_X_AXIS_LABEL,// 6
			CHART_PERFORMANCE_Y_AXIX_LABEL,// 7
			CHART_CREATE_LEGEND,// 8
			CHART_GENERATE_TOOL_TIPS,// 9
			CHART_GENERATE_URLS, // 10
			QUERY_PARAM_DIAS,// 11
			CHART_PERFORMANCE_TITLE, // 12
			CHART_SYNC_TITLE, // 13
			IS_TEST_FLOW, // 14
			CHART_SYNC_X_AXIS_LABEL, // 15
			QUANT_LINHA_CARREGAMENTO_DADOS_TESTE, // 16
			JBUTTON1_TEXT, // 17
			JBUTTON2_TEXT, // 18
			JPANEL_PERFORMANCE_TEXT, // 19
			JPANEL_SYNC_APPS_TEXT, // 20
			DATE_FORMAT_PATTERN_DATE_HOUR_WITHOUT_SEPARATOR,// 21
			QUERY_SGAP_NAME, // 22
			QUERY_SGMP_NAME, // 23
			CONFIG_PROPERTIES_FILENAME, // 24
			DATE_FROM, // 25
			DATE_TO,// 26
			CHECK_BOXES_VALUES, // 27
			CHART_DIMENSION_HEIGHT,// 28
			CHART_DIMENSION_WIDTH, // 29
			MAIN_GUI_WINDOW_TITLE, // 30
			JFRAME_OWNER_KEY // 31

	};

	private int indice;

	private Object value;

	ConstantesEnum(int indice) {

		this.indice = indice;

		value = chartLabels[indice];

	}

	public int getIndice() {
		return indice;
	}

	public Object getValue() {
		return value;
	}

}

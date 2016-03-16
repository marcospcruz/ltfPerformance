package br.com.marcospcruz.performance.command;

import java.awt.Color;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;

import br.com.marcospcruz.performance.util.ConstantesEnum;

public abstract class BaseCommand {

	protected boolean[] booleanArray;
	protected Date dateFrom;
	protected Date dateTo;
	protected JFrame owner;

	public abstract void execute(Map parameters) throws Exception;

	/**
	 * @param owner
	 * @param jFreeChart
	 * 
	 */
	protected void drawChart(JFrame owner, JFreeChart jFreeChart) {
		// TODO Auto-generated method stub

		JFreeChart chart = jFreeChart;

		openChart(chart, owner);

		// setContentPane(chartPanel);

		// repaint();

	}

	/**
	 * xxx
	 * 
	 * @param owner
	 */
	private void openChart(JFreeChart chart, JFrame owner) {
		// TODO Auto-generated method stub

		int width = (Integer) ConstantesEnum.ChartDimensionWidth.getValue();
		int height = (Integer) ConstantesEnum.ChartDimensionHeight.getValue();
		Dimension chartDimension = new Dimension(width, height);

		// timeSeriesChart.setBackgroundPaint(Color.WHITE);

		configChartPlot(chart, Color.WHITE);

		ChartPanel chartPanel = new ChartPanel(chart);

		chartPanel.setPreferredSize(chartDimension);

		JDialog jDialog = configJPanelChart(chartPanel, true, owner);

		jDialog.setVisible(true);

	}

	/**
	 * 
	 * @param chartPanel
	 * @param b
	 * @param owner
	 * @return
	 */
	private JDialog configJPanelChart(ChartPanel chartPanel, boolean b,
			JFrame owner) {
		// TODO Auto-generated method stub

		JDialog jPanelChart = new JDialog(owner, true);

		jPanelChart.setBounds(0, 0, 800, 600);

		jPanelChart.setContentPane(chartPanel);

		jPanelChart.setDefaultCloseOperation(jPanelChart.DISPOSE_ON_CLOSE);

		return jPanelChart;
	}

	/**
	 * 
	 * @param chart
	 * @param color
	 */
	private void configChartPlot(JFreeChart chart, Color backgroundColor) {
		// TODO Auto-generated method stub

		XYPlot plot = chart.getXYPlot();

		plot.setBackgroundPaint(backgroundColor);

		plot.setDomainGridlinePaint(Color.BLACK);

		plot.setRangeGridlinePaint(Color.BLACK);

		DateAxis axis = (DateAxis) plot.getDomainAxis();

		axis.setDateFormatOverride(new SimpleDateFormat(
				ConstantesEnum.DateHourPattern.getValue().toString()));

		axis.setAxisLinePaint(Color.BLACK);

		axis.setTickMarkPaint(Color.black);

		axis.setVisible(true);

	}

	/**
	 * 
	 * @param parameters
	 */
	protected void populateAttributes(Map parameters) {
		// TODO Auto-generated method stub
		booleanArray = (boolean[]) parameters
				.get(ConstantesEnum.ActionPerformedParameterCheckBoxValuesToKey
						.getValue().toString());

		dateFrom = (Date) parameters
				.get(ConstantesEnum.ActionPerformedParameterDateFromKey
						.getValue().toString());

		dateTo = (Date) parameters
				.get(ConstantesEnum.ActionPerformedParameterDateToKey
						.getValue().toString());

		owner = (JFrame) parameters
				.get(ConstantesEnum.ActionPerformedParameterJFrameOwnerKey
						.getValue().toString());

	}
}

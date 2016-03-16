package br.com.marcospcruz.performance.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import br.com.marcospcruz.performance.command.BaseCommand;
import br.com.marcospcruz.performance.command.CommandFactory;
import br.com.marcospcruz.performance.util.ClasseContratoEnum;
import br.com.marcospcruz.performance.util.ConstantesEnum;
import br.com.marcospcruz.performance.util.Utilitario;

public class MainGui extends JFrame implements ActionListener {

	private static final JCheckBox[] JCHECKBOX_ARRAY = {
			new JCheckBox(ClasseContratoEnum.PessoaFisica.getValue()),
			new JCheckBox(ClasseContratoEnum.PessoaJuridica.getValue()),
			new JCheckBox(ConstantesEnum.ChartPerformanceLabelSeries1
					.getValue().toString()),
			new JCheckBox(ConstantesEnum.ChartPerformanceLabelSeries2
					.getValue().toString()) };

	private JButton sgmpCompensationBtn;

	private JButton sgmpSgapSyncStatusBtn;

	// private JRadioButton xyChartOption;
	//
	// private JRadioButton areaChartOption;

	private String jButtonPerformanceText;

	private String jButtonWebAppsSyncText;

	private String buttonPanelText;

	private String syncButtonPanelText;

	private JDatePickerImpl initialDatePicker;

	private JDatePickerImpl finalDatePicker;

	// private JCheckBox jNpCheckBox;
	//
	// private JCheckBox jLeCheckBox;

	public MainGui() {

		super(ConstantesEnum.MainGuiFrameTitle.getValue().toString());

		// setLayout(new FlowLayout(FlowLayout.TRAILING));

		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		jButtonPerformanceText = ConstantesEnum.JButtonPerformanceText
				.getValue().toString();

		jButtonWebAppsSyncText = ConstantesEnum.JButtonSgapSgmpSyncText
				.getValue().toString();

		buttonPanelText = ConstantesEnum.JPanelPerformanceText.getValue()
				.toString();

		syncButtonPanelText = ConstantesEnum.JPanelSyncAppsText.getValue()
				.toString();

		configJPanel();

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setBounds(0, 100, 400, 500);

		setLocationRelativeTo(null);

	}

	/**
	 * config jpanel
	 */
	private void configJPanel() {
		// TODO Auto-generated method stub

		//
		add(configJPanelPeriod());

		add(configWebAppsSyncJPanel());

		add(configPerformanceJButtonPanel());

	}

	private Component configJPanelPeriod() {
		// TODO Auto-generated method stub

		JPanel jPanel = new JPanel(new FlowLayout());

		jPanel.setBorder(new TitledBorder("Select the Period"));

		jPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

		initialDatePicker = createJDatePickerImpl(new Date(Utilitario
				.getCal(30).getTimeInMillis()));

		initialDatePicker.setBorder(new TitledBorder("From:"));

		jPanel.add(initialDatePicker);

		finalDatePicker = createJDatePickerImpl(new Date());

		finalDatePicker.setBorder(new TitledBorder("To:"));

		jPanel.add(finalDatePicker);

		return jPanel;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	private JDatePickerImpl createJDatePickerImpl(Date date) {
		// TODO Auto-generated method stub

		UtilDateModel model = new UtilDateModel(date);

		JDatePanelImpl datePanel = new JDatePanelImpl(model);

		JDatePickerImpl jd = new JDatePickerImpl(datePanel);

		jd.setSize(new Dimension(50, 100));

		return jd;
	}

	/**
	 * 
	 * @return
	 */
	private JPanel configWebAppsSyncJPanel() {
		// TODO Auto-generated method stub

		JPanel jPanel = new JPanel(new FlowLayout());

		jPanel.setBorder(new TitledBorder(syncButtonPanelText));

		jPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

		sgmpSgapSyncStatusBtn = createJButton(jButtonWebAppsSyncText);

		jPanel.add(sgmpSgapSyncStatusBtn);

		JPanel jcheckBoxPanel = new JPanel(new FlowLayout());

		jcheckBoxPanel.add(JCHECKBOX_ARRAY[0]);

		jcheckBoxPanel.add(JCHECKBOX_ARRAY[1]);

		jPanel.add(jcheckBoxPanel);

		return jPanel;
	}

	/**
	 * 
	 * @return
	 */
	private JPanel configPerformanceJButtonPanel() {
		// TODO Auto-generated method stub

		JPanel jPanel = new JPanel(new FlowLayout());

		jPanel.setBorder(new TitledBorder(buttonPanelText));

		sgmpCompensationBtn = createJButton(jButtonPerformanceText);

		jPanel.add(sgmpCompensationBtn);

		// xyChartOption = new JRadioButton("Line Chart", true);
		//
		// areaChartOption = new JRadioButton("Area Chart");

		// ButtonGroup optionsGroup = new ButtonGroup();

		// optionsGroup.add(xyChartOption);
		// optionsGroup.add(areaChartOption);
		//
		// optionsGroup.add(xyChartOption);
		//
		// optionsGroup.add(areaChartOption);
		//
		// buttonPanel.add(xyChartOption);

		// buttonPanel.add(areaChartOption);

		JPanel jCheckBoxSeriesPanel = new JPanel();

		jCheckBoxSeriesPanel.setLayout(new BoxLayout(jCheckBoxSeriesPanel,
				BoxLayout.Y_AXIS));

		for (int i = 2; i < JCHECKBOX_ARRAY.length; i++) {
			JCHECKBOX_ARRAY[i].setSelected(true);

			jCheckBoxSeriesPanel.add(JCHECKBOX_ARRAY[i]);
		}

		jPanel.add(jCheckBoxSeriesPanel);

		return jPanel;
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	private JButton createJButton(String text) {

		JButton button = new JButton(text);

		button.addActionListener(this);

		return button;
	}

	/**
	 * start the app
	 */
	public void start() {
		// TODO Auto-generated method stub

		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub

		String actionCommand = evt.getActionCommand();

		Map parameters = new HashMap();

		Date dateFrom = (Date) initialDatePicker.getModel().getValue();

		Date dateTo = Utilitario.setTime((Date) finalDatePicker.getModel()
				.getValue(), "23:59:59");

		if (validaDatas(dateFrom, dateTo)) {

			parameters.put(ConstantesEnum.ActionPerformedParameterDateFromKey
					.getValue().toString(), dateFrom);

			parameters.put(ConstantesEnum.ActionPerformedParameterDateToKey
					.getValue().toString(), dateTo);

			parameters.put(
					ConstantesEnum.ActionPerformedParameterCheckBoxValuesToKey
							.getValue().toString(),
					getCheckBoxesValues(actionCommand));

			parameters.put("owner", this);

			try {

				BaseCommand chartDrawer = CommandFactory
						.createCommand(actionCommand);

				chartDrawer.execute(parameters);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				JOptionPane.showMessageDialog(this, "FATAL!!!");

			}

		}
	}

	/**
	 * 
	 * @param actionCommand
	 * @return
	 */
	private Object getCheckBoxesValues(String actionCommand) {
		// TODO Auto-generated method stub

		boolean[] booleanArray = null;

		if (actionCommand.equals(sgmpCompensationBtn.getActionCommand()))

			booleanArray = new boolean[] { JCHECKBOX_ARRAY[2].isSelected(),
					JCHECKBOX_ARRAY[3].isSelected() };

		else

			booleanArray = new boolean[] { JCHECKBOX_ARRAY[0].isSelected(),
					JCHECKBOX_ARRAY[1].isSelected() };

		return booleanArray;
	}

	/**
	 * 
	 * @param dateFrom
	 * @param dateTo
	 * @return
	 */
	private boolean validaDatas(Date dateFrom, Date dateTo) {
		// TODO Auto-generated method stub

		// SE A DATA INICIAL ESTIVER NO FUTURO
		if (dateFrom.compareTo(dateTo) > 0) {
			JOptionPane.showMessageDialog(this, "Invalid from Date!!!");
			return false;
		}
		// SE A DATA ESTIVER NO FUTURO
		// else if (new Date().compareTo(dateTo) < 0) {
		// JOptionPane.showMessageDialog(this, "Invalid Final Date!!!");
		// return false;
		// }
		return true;
	}
}

package br.com.marcospcruz.performance.command;

import java.util.Date;
import java.util.Map;

import javax.swing.JFrame;

import br.com.marcospcruz.performance.facade.TransacaoFacade;
import br.com.marcospcruz.performance.util.ConstantesEnum;

public class PerformanceChartPlotter extends BaseCommand {

	@Override
	public void execute(Map parameters) throws Exception {
		// TODO Auto-generated method stub

		populateAttributes(parameters);

		TransacaoFacade facade = new TransacaoFacade();

		drawChart(owner,
				facade.createPerformanceChart(booleanArray, false, dateFrom, dateTo));

	}

}

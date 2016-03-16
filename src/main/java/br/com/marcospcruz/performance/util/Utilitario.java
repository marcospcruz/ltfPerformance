package br.com.marcospcruz.performance.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Utilitario {

	private static SimpleDateFormat sdf;

	private Utilitario() {
	}

	public static Date stringToDate(String substring, String datePattern) {
		// TODO Auto-generated method stub

		sdf = new SimpleDateFormat(datePattern);

		try {
			return sdf.parse(substring);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		// TODO Auto-generated method stub

		sdf = new SimpleDateFormat(
				(String) ConstantesEnum.DateTimestampPattern.getValue());

		return sdf.format(date);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateWithoutSeparator(Date date) {
		// TODO Auto-generated method stub

		sdf = new SimpleDateFormat(
				(String) ConstantesEnum.DateHourWithoutSeparators.getValue());

		return sdf.format(date);

	}

	/**
	 * 
	 * @param daysDifference
	 * @return
	 */
	public static Calendar getCal(int daysDifference) {

		Calendar cal = Calendar.getInstance();

		cal.setTime(new Date());

		cal.add(Calendar.DAY_OF_MONTH, -daysDifference);

		return cal;

	}

	public static int calculaDiferencaHoras(Date dataAnterior, Date dataAtual) {
		// TODO Auto-generated method stub

		Calendar calDataAnterior = new GregorianCalendar();

		calDataAnterior.setTime(dataAnterior);

		Calendar calDataAtual = new GregorianCalendar();

		calDataAtual.setTime(dataAtual);

		int diff = calDataAtual.get(Calendar.HOUR_OF_DAY)
				- calDataAnterior.get(Calendar.HOUR_OF_DAY);

		return diff;
	}

	/**
	 * 
	 * @param anteriorCal
	 * @param atualCal
	 * @param field
	 * @return
	 */
	public static int validaDiferencaData(Calendar anteriorCal,
			Calendar atualCal, int field) {
		// TODO Auto-generated method stub

		int retorno = atualCal.get(field) - anteriorCal.get(field);

		return retorno;
	}

	public static Date setTime(Date value, String time) {
		// TODO Auto-generated method stub

		Calendar calendar = new GregorianCalendar();

		calendar.setTime(value);
		String[] splittedTime = time.split(":");

		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

		calendar.set(year, month, dayOfMonth, new Integer(splittedTime[0]),
				new Integer(splittedTime[1]), new Integer(splittedTime[2]));

		return calendar.getTime();
	}
}

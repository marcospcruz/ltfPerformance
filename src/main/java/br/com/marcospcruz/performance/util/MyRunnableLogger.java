package br.com.marcospcruz.performance.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;
import java.util.TreeSet;

public class MyRunnableLogger implements Runnable {

	private Map<Long, Integer> valuesMap;

	private String title;

	private StringBuffer logBuffer;

	private static final String lineDelimiter = System
			.getProperty("line.separator");

	private static final String DRIVE = "";

	/**
	 * 
	 * @param valuesMap
	 */
	public MyRunnableLogger(Map<Long, Integer> valuesMap, String title) {

		logBuffer = new StringBuffer();

		this.valuesMap = valuesMap;

		this.title = title;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		log(title);
		log(lineDelimiter);

		processaMapa();

		log(lineDelimiter);
		log(lineDelimiter);
		try {
			escreveLog();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void escreveLog() throws IOException {
		// TODO Auto-generated method stub

		String formattedDate = Utilitario
				.formatDateWithoutSeparator(new Date());

		String fileName = DRIVE + title.replace(' ', '_') + "_" + formattedDate
				+ ".log";

		fileName = fileName.replace('/', '-');

		File log = new File(fileName);

		OutputStream fos = null;

		try {

			fos = new FileOutputStream(log);

			fos.write(logBuffer.toString().getBytes());

			fos.flush();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {

			fos.close();

		}

	}

	/**
	 * xxx
	 */
	private void processaMapa() {
		// TODO Auto-generated method stub

		try {
			TreeSet<Long> keys = new TreeSet<Long>(valuesMap.keySet());

			log("Data/Hora;Total");

			for (Long key : keys) {

				Date data = new Date(key);

				String formattedDate = Utilitario.formatDate(data);

				Integer value = valuesMap.get(key);

				log(formattedDate + ";" + value);

			}

		} catch (NullPointerException e) {

			e.printStackTrace();

		}

	}

	/**
	 * 
	 * @param string
	 */
	private void log(String string) {
		// TODO Auto-generated method stub

		logBuffer.append(string + lineDelimiter);

	}
}

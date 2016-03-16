package br.com.marcospcruz.performance.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public class MyLoggerSingleton {

	private static MyLoggerSingleton instance = new MyLoggerSingleton();

	/**
	 * x
	 */
	private MyLoggerSingleton() {
	}

	public static MyLoggerSingleton getInstance() {

		return instance;
	}

	/**
	 * Log information and go to a new row.
	 * 
	 * @param object
	 * @param string
	 */
	private void writeLog(Object object, String string) {
		// TODO Auto-generated method stub

		// try {
		// escreveLog(Utilitario.formatDate(new Date()) + " - ["
		// + object.getClass().getSimpleName() + "] " + string);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		System.out.println(new Date().toString() + " - ["
				+ object.getClass().getSimpleName() + "] " + string);

	}

	/**
	 * Log information at the same row.
	 * 
	 * @param string
	 */
	public void writeLog(String string) {
		// TODO Auto-generated method stub

		System.out.print(string);

	}

	/**
	 * 
	 * @param row
	 * @throws IOException
	 */
	private void escreveLog(String row) throws IOException {
		// TODO Auto-generated method stub

		String formattedDate = Utilitario
				.formatDateWithoutSeparator(new Date());

		String fileName = "whatchdog_" + formattedDate + ".log";

		fileName = fileName.replace('/', '-');

		File log = new File(fileName);

		OutputStream fos = null;

		try {

			fos = new FileOutputStream(log);

			fos.write(row.getBytes());

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

	public void writeLog(Object object, StringBuffer stringBuffer) {
		// TODO Auto-generated method stub

		writeLog(object, stringBuffer.toString());

	}

}

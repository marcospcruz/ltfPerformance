package br.com.marcospcruz.performance.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class FileUtils {

	public FileUtils() {
		// TODO Auto-generated constructor stub
	}

	private InputStream getInputStream(String fileName) {
		InputStream object = getClass().getResourceAsStream(fileName);
		// TODO Auto-generated method stub
		return object;
	}

	public Properties loadPropertiesFile(String fileName) throws IOException {
		// TODO Auto-generated method stub

		Properties properties = null;

		InputStreamReader input = new InputStreamReader(
				getInputStream(fileName));

		try {
			properties = new Properties();

			properties.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {

			if (input != null)
				input.close();

		}

		return properties;
	}
}

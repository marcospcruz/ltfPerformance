package br.com.marcospcruz.performance.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public abstract class Database {

	protected Properties queriesProperties;

	private final static String DATASOURCE_XML = "/oracle-ds.xml";

	private static final String QUERY_PROPERTIES_FILE = "/queries.properties";

	public Database() {

		// openConnection();

		loadProperties();

	}

	private void loadProperties() {
		// TODO Auto-generated method stub

		try {

			InputStream stream = getClass().getResourceAsStream(
					QUERY_PROPERTIES_FILE);

			queriesProperties = PropertiesManager
					.getPropertiesParameters(stream);

		} catch (NullPointerException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected Connection getConnection() throws SQLException {

		XMLUtils xml = null;

		Connection conn = null;

		try {

			xml = new XMLUtils(DATASOURCE_XML);

			Properties connectionProps = new Properties();

			connectionProps.put("user",
					xml.getValue(OracleDSParameterMappingClass.USER_NAME_TAG));

			connectionProps.put("password",
					xml.getValue(OracleDSParameterMappingClass.PASSWORD_TAG));

			String urlDB = (String) xml
					.getValue(OracleDSParameterMappingClass.URL_CONNECTION_TAG);

			// urlDB = "jdbc:oracle:thin:@10.0.0.47:1521:sgmp";

			log("Opening connection to: " + urlDB);

			conn = DriverManager.getConnection(urlDB, connectionProps);

			log("Connection open");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;

	}

	// /**
	// * xxx
	// */
	// protected void openConnection() {
	// // TODO Auto-generated method stub
	//
	// Connection conn = getConnection();
	//
	// try {
	// if (conn != null && !conn.isClosed())
	// conn.close();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }

	private void log(String string) {
		// TODO Auto-generated method stub

		MyLoggerSingleton.getInstance()
				.writeLog(this, new StringBuffer(string));

	}

	/**
	 * 
	 * @param connection
	 * @param ps
	 * @param rs
	 * @throws SQLException
	 */
	protected void close(Connection connection, PreparedStatement ps,
			ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub

		if (rs != null && !rs.isClosed())
			rs.close();

		if (ps != null && !ps.isClosed())
			ps.close();

		if (connection != null && !connection.isClosed()) {
			connection.close();

			log("Connection Closed.");

		}

	}

	/**
	 * 
	 * @param timeInMillis1
	 * @param timeInMillis2
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public abstract List readAll(long timeInMillis1, long timeInMillis2)
			throws SQLException, Exception;

}

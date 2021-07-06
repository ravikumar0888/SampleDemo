package utilies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.mysql.cj.jdbc.MysqlDataSource;

import config.ConfigreaderUtils;
import testbase.BasePage;

public class DSConnection extends BasePage{

	private static Logger logger = LogManager.getLogger(DSConnection.class.getName());

//	private final String user = "a757437";

	// 	private final String host = "gateway-fr";   // Comment for local run

	private static final String HOST = ConfigreaderUtils.getConfig("environment." + environment + ".dbhost");
			//"55.16.242.224";
	
	private static final String DB_USER = ConfigreaderUtils.getConfig("environment." + environment + ".dbuser");
			//"SPS_SIGNATURE";   
	private static final String DB_REMOTE_HOST =ConfigreaderUtils.getConfig("environment." + environment + ".dbremoteHost");
			//"bqsps005s";// bqsps005s.sys.meshcore.net     dbpassword
	private static final String DB_PWD = ConfigreaderUtils.getConfig("environment." + environment + ".dbpassword");
			//"5P5_51GNATUR3";
	private static final int DB_REMOTE_PORT = 3306;
	private static final int LOCALPORT = 8000;

	private MysqlDataSource mySqlDS = new MysqlDataSource();

	Session session = null;

	private void closeSSHSession() {
		if (session != null && session.isConnected()) {
			logger.info("Closing SSH Connection");
			session.disconnect();
		}

	}

	/**
	 * @param connection
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public String getOTP(String creditor, String tranId) throws SQLException {
		String otp = null;
		Connection connection = null;
		String selectSQL = "select codeotp from SPS_SIGNATURE.agreement where id=(select agreement_id from SPS_SIGNATURE.session where creditor_id= (select id from SPS_SIGNATURE.creditor where identifier= ?)  and creditortransactionid=?);";
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		try {
			openSSH();
			connection = mySqlDS.getConnection();
			prepStmt = connection.prepareStatement(selectSQL);
			prepStmt.setString(1, creditor);
			prepStmt.setString(2, tranId);
			// prepStmt.setInt(1, id);
			rs = prepStmt.executeQuery();
			while (rs.next()) {
				otp = rs.getString(1);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (prepStmt != null) {
				prepStmt.close();
			}
			if (connection != null) {
				connection.close();

			}

			closeSSHSession();

		}

		return otp;
	}

	private void openSSH() {

		final java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		config.put("ConnectionAttempts", "3");

		final JSch jsch = new JSch();
		try {

			// TCP Connection
			String username=ConfigreaderUtils.getConfig("environment." + environment + ".dbUsername");
		//	private final String user = username;
			session = jsch.getSession(username, HOST, 22);
			session.setConfig(config);
			
		String password=ConfigreaderUtils.getConfig("environment." + environment + ".dbUserPassword");
		session.setPassword(password);
			session.connect();
			if (session.isConnected()) {
				logger.info("Connected through SSH");
			}

			final int assinged_port = session.setPortForwardingL(LOCALPORT, DB_REMOTE_HOST, DB_REMOTE_PORT);
			logger.info("Port Forwarded: Assigned port {}", assinged_port);

			// mySqlDS.setDriverClassName(DRIVER_CLASS);
			String DB_CONNECTION_URL = "jdbc:mysql://127.0.0.1:" + assinged_port + "/SPS_SIGNATURE?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris";

			mySqlDS.setUrl(DB_CONNECTION_URL);
			mySqlDS.setUser(DB_USER);
			mySqlDS.setPassword(DB_PWD);

		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
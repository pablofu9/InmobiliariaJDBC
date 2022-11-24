package Conexion;



import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {
	static Connection conexion = null;

	public static void conectar() {
		// PARA CONECTAR AL ARCHIVO PROPERTIES
		Properties prop = new Properties();
		String host, port, db, user, psw;

		try {
			FileInputStream file = new FileInputStream("bd.properties");
			prop.load(file);
			host = prop.getProperty("host");
			port = prop.getProperty("port");
			db = prop.getProperty("db");
			user = prop.getProperty("user");
			psw = prop.getProperty("pass");

		} catch (IOException ex) {
			System.out.println("No existe el archivo get properties");
			System.out.println("No se ha podido crear la conexion");
			return;
		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://" + host + " : " + port + " /" + db, user, psw);
			System.out.println("Conectado a la base de datos");
			/*
			 * conexion = DriverManager.getConnection(
			 * "jdbc:mysql://localhost:3306/practica2", "root", "pabloygala96");
			 */

		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public static Connection getConexion() {
		if (conexion != null) {
			return conexion;
		} else {
			// Tratar de conectarte si no se ha podido hacer
			conectar();
		}
		return conexion;
	}

	public static void desconectar() {
		try {
			conexion.close();
			conexion = null;

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

	}
}

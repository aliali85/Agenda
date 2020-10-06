package persistencia.conexion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import com.mysql.jdbc.PreparedStatement;

public class Conexion {
	public static Conexion instancia;
	private Connection connection;
	private Logger log = Logger.getLogger(Conexion.class);	
	
	private Conexion() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // quitar si no es necesario
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo_12?zeroDateTimeBehavior=convertToNull","root","pass123");
			this.connection.setAutoCommit(false);
			log.info("Conexion exitosa");
			//runSQLScript("sql/scriptAgenda.sql"); //falla en la sintaxis sql, no encuentro el error
	}
		catch(Exception e) {
		log.error("Conexion fallida", e);
		}
	}
	
	public static Conexion getConexion() {								
		if(instancia == null) {
			instancia = new Conexion();
		}
		return instancia;
	}

	public Connection getSQLConexion() {
		return this.connection;
	}
	
	public void runSQLScript(String FilePath) throws SQLException {
		String query = obtenerContenido(FilePath);
		System.out.println(query);
		executeQuery(query);
	}
	public boolean executeQuery(String cadena) throws SQLException {
		boolean isExitoso;
		Connection con = Conexion.getConexion().getSQLConexion();
		PreparedStatement statement = (PreparedStatement) con.prepareStatement(cadena);
		if(statement.execute()) {
			System.out.println("ejecutado correctamente");
			isExitoso = true;
		}else {
			System.out.println("fallo");
			isExitoso= false;
		}
		return isExitoso;
	}
	public String obtenerContenido(String FilePath){
		String cadenaSQL = "";
		String cadenaRetorno = "";
		File archivoSQL = new File(FilePath) ;
		try {
			BufferedReader bf = new BufferedReader(new FileReader(archivoSQL));
			while((cadenaSQL = bf.readLine()) != null) {
				cadenaRetorno += cadenaSQL;
			}
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cadenaRetorno;
	}
	
	public void cerrarConexion() {
		try {
			this.connection.close();
			log.info("Conexion cerrada");
		}
		catch (SQLException e) {
			log.error("Error al cerrar la conexión!", e);
		}
		instancia = null;
	}
}

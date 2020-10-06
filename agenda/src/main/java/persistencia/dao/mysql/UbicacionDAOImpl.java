package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.PersonaDTO;
import dto.UbicacionDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.UbicacionDAO;

public class UbicacionDAOImpl implements UbicacionDAO {
	private static Connection conexion = Conexion.getConexion().getSQLConexion();
	private static final String insert = "INSERT INTO ubicacion(localidad,provincia, pais) VALUES(?,?,?)";
	private static final String update = "UPDATE ubicacion SET localidad=?, provincia=?, pais=?   WHERE idUbicacion = ?";
	private static final String delete = "DELETE FROM ubicacion WHERE idUbicacion = ?";
	private static final String readall = "SELECT * FROM ubicacion";
	private static final String getById = "SELECT * FROM ubicacion WHERE idUbicacion = ?";
	
	public boolean update(UbicacionDTO localidad_a_editar) {
		PreparedStatement statement;
		try {
			statement = conexion.prepareStatement(update);
			statement.setString(1, localidad_a_editar.getLocalidad());
			statement.setString(2, localidad_a_editar.getProvincia());
			statement.setString(3, localidad_a_editar.getPais());
			statement.setInt(4, localidad_a_editar.getIdLocalidad());
			if (statement.executeUpdate() > 0) // Si se ejecutó devuelvo true
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// Se ejecuta siempre
			//conexion.cerrarConexion();
		}
		return false;
	}
	
	public boolean insert(UbicacionDTO localidad) {
		PreparedStatement statement;
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			statement.setString(1, localidad.getLocalidad());
			statement.setString(2, localidad.getProvincia());
			statement.setString(3, localidad.getPais());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = true;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return isInsertExitoso;
	}

	public boolean delete(UbicacionDTO localidad_a_eliminar) {
		PreparedStatement statement;
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(localidad_a_eliminar.getIdLocalidad()));
			if(statement.executeUpdate() > 0 ){
				conexion.commit();
				isdeleteExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}

	public UbicacionDTO getById(UbicacionDTO localidad_a_obtener) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		UbicacionDTO localidad = null;
		try {
			statement = conexion.prepareStatement(getById);
			statement.setInt(1, localidad_a_obtener.getIdLocalidad());
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				localidad = new UbicacionDTO(resultSet.getInt("idUbicacion"), resultSet.getString("localidad"), resultSet.getString("provincia"), resultSet.getString("pais"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// Se ejecuta siempre
			//conexion.cerrarConexion();
		}
		return localidad;
	}

	public List<UbicacionDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<UbicacionDTO> localidades = new ArrayList<UbicacionDTO>();
		try {
			statement = conexion.prepareStatement(readall);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				UbicacionDTO localidad = new UbicacionDTO(resultSet.getInt("idUbicacion"),
				resultSet.getString("localidad"), resultSet.getString("provincia"), resultSet.getString("pais"));
				localidades.add(localidad);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// Se ejecuta siempre
			//conexion.cerrarConexion();
		}
		return localidades;
	}

}
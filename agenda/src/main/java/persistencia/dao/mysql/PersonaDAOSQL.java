package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PersonaDAO;
import presentacion.vista.VentanaPersona;
import dto.PersonaDTO;
import dto.TipoContactoDTO;
import dto.UbicacionDTO;

public class PersonaDAOSQL implements PersonaDAO {
	private Connection conexion;
	private static final String insert = "INSERT INTO personas(nombre, telefono,calle,altura,piso,departamento,localidad,email,fnac,Tcont) VALUES(?,?,?,?,?,?,?,?,?,?)";
	private static final String delete = "DELETE FROM personas WHERE idPersona = ?";
	private static final String edit = "UPDATE personas SET nombre= ?, telefono=?,calle=?,altura=?,piso=?,departamento=?,localidad=?,email=?,fnac=?,Tcont=? WHERE idPersona=?";
	private static final String readall = "SELECT * FROM personas";
	private static final String read = "SELECT * FROM personas where idPersona = ?";
		
	public boolean insert(PersonaDTO persona) {
		PreparedStatement statement;
		this.conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getTelefono());
			statement.setString(3, persona.getCalle());
			statement.setString(4, persona.getAltura());
			statement.setString(5, persona.getPiso());
			statement.setString(6, persona.getDepartamento());
			statement.setString(7, persona.getLocalidad());
			statement.setString(8, persona.getEmail());
			statement.setDate(9, persona.getFechaNacimiento());
			statement.setString(10, persona.getTipoContacto());
			if(statement.executeUpdate() > 0){
				conexion.commit();
				isInsertExitoso = true;
			}
			statement.close();
			
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
	
	
	
	public boolean delete(PersonaDTO persona_a_eliminar) {
		PreparedStatement statement;
		this.conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(persona_a_eliminar.getIdPersona()));
			if(statement.executeUpdate() > 0 ){
				conexion.commit();
				isdeleteExitoso = true;
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}
	
	public boolean update(PersonaDTO persona) {
		PreparedStatement statement;
		this.conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
			statement = conexion.prepareStatement(edit);
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getTelefono());
			statement.setString(3, persona.getCalle());
			statement.setString(4, persona.getAltura());
			statement.setString(5, persona.getPiso());
			statement.setString(6, persona.getDepartamento());
			statement.setString(7, persona.getLocalidad());
			statement.setString(8, persona.getEmail());
			statement.setDate(9, persona.getFechaNacimiento());
			statement.setString(10, persona.getTipoContacto());
			statement.setInt(11, persona.getIdPersona());
			
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isUpdateExitoso = true;
			}
			statement.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return isUpdateExitoso;
	}
	

	
	public List<PersonaDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				personas.add(getPersonaDTO(resultSet));
			}
			statement.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return personas;
	}
	
	private PersonaDTO getPersonaDTO(ResultSet resultSet) throws SQLException {
		//Aca se cargan los datos que se van a cargar desde la base, respetar nombres de columna
		//PersonaDTO(int idPersona, String nombre, String telefono, String email, Calendar fechaNacimiento, String calle, String altura, int piso, String departamento, UbicacionDTO localidad, TipoContactoDTO tipoContacto)
		{
		int id = resultSet.getInt("idPersona");
		String nombre = resultSet.getString("Nombre");
		String tel = resultSet.getString("Telefono");
		String calle =resultSet.getString("Calle");
		String altura = resultSet.getString("Altura");
		String piso = resultSet.getString("Piso");
		String departamento = resultSet.getString("Departamento");
		String localidad = resultSet.getString("Localidad");
		String email = resultSet.getString("email");
		Date fechaNacimiento = resultSet.getDate("fnac");
		String tipo = resultSet.getString("Tcont");
		return new PersonaDTO(id, nombre, tel,calle,altura,piso,departamento,localidad, email,fechaNacimiento,tipo);
		}
	}
	
	public PersonaDTO read(int id) {
		PersonaDTO persona = null;
		PreparedStatement statement;
		this.conexion = Conexion.getConexion().getSQLConexion();
		ResultSet result;
		try {
			statement = this.conexion.prepareStatement(read);
			statement.setInt(1, id);
			result = statement.executeQuery();
			result.next();
			persona = getPersonaDTO(result);
			statement.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return persona;
	}

}
package dto;

import java.sql.Date;
import java.util.Calendar;

public class PersonaDTO 
{
	private int idPersona;
	private String nombre;
	private String telefono;
	private String email;
	private Date fechaNacimiento;
	private String calle;
	private String altura;
	private String piso;
	private String departamento;
	private String localidad;
	private String tipoContacto;

	public PersonaDTO(int idPersona, String nombre, String telefono, String calle, String altura, String piso, String departamento, String localidad, String email, Date fechaNacimiento,String tipoContacto) 
		{
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.calle = calle;
		this.altura= altura;
		this.piso= piso;
		this.departamento = departamento;
		this.localidad= localidad;
		this.setTipoContacto(tipoContacto);
				
	}
	
	//constructor sin id, como es autoincrementable en la database
	public PersonaDTO(String nombre, String telefono, String calle, String altura, String piso, String departamento, String localidad, String email, Date fechaNacimiento, String tipoContacto) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.calle = calle;
		this.altura= altura;
		this.piso= piso;
		this.departamento = departamento;
		this.localidad= localidad;
		this.setTipoContacto(tipoContacto);		
	}
	
	public int getIdPersona() { return this.idPersona; }
	public void setIdPersona(int idPersona) { this.idPersona = idPersona; }
	public String getNombre() { return this.nombre; }
	public void setNombre(String nombre)  { this.nombre = nombre; }
	public String getTelefono() { return this.telefono; }
	public void setTelefono(String telefono) { this.telefono = telefono; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public String getCalle() { return calle; }
	public void setCalle(String calle) { this.calle = calle; }
	public Date getFechaNacimiento() { return fechaNacimiento; }
	public String getAltura() { return altura; }
	public void setAltura(String altura) { this.altura = altura; }
	public String getDepartamento() { return departamento; }
	public void setDepartamento(String departamento) { this.departamento = departamento; }
	public String getPiso() { return piso; }
	public String getLocalidad() { return localidad; }

	public String getTipoContacto() {
		return tipoContacto;
	}

	public void setTipoContacto(String tipoContacto) {
		this.tipoContacto = tipoContacto;
	}
}

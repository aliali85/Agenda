package dto;

public class UbicacionDTO {
	private int idLocalidad;
	private String localidad;
	private String provincia;
	private String pais;
	private String nombre;

	public UbicacionDTO(int idLocalidad, String localidad, String provincia, String pais) {
		this.idLocalidad = idLocalidad;
		this.localidad = localidad;
		this.provincia = provincia;
		this.pais = pais;
	}
	public int getIdLocalidad() { return idLocalidad; }
	public void setIdLocalidad(int idLocalidad) { this.idLocalidad = idLocalidad; }
	public String getLocalidad() { return localidad; }
	public void setLocalidad(String localidad) { this.localidad = localidad; }
	public String getProvincia() { return provincia; }
	public void setProvincia(String provincia) { this.provincia = provincia; }
	public String getPais() { return pais; }
	public void setPais(String pais) { this.pais = pais; }
	public String toString() { return getLocalidad(); }
}
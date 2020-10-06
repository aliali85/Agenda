package persistencia.dao.interfaz;

import java.util.List;

import dto.UbicacionDTO;

public interface UbicacionDAO {
	public boolean update(UbicacionDTO localidad_a_editar);

	public boolean insert(UbicacionDTO localidad);

	public boolean delete(UbicacionDTO localidad_a_eliminar);

	public UbicacionDTO getById(UbicacionDTO localidad_a_obtener);

	public List<UbicacionDTO> readAll();
}
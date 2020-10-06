package modelo;

import java.util.List;
import dto.PersonaDTO;
import dto.TipoContactoDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.TipoContactoDAO;

public class Agenda {
	private PersonaDAO persona;	
	private TipoContactoDAO contacto;
	
	public Agenda(DAOAbstractFactory metodo_persistencia) {
		this.persona = metodo_persistencia.createPersonaDAO();
	}
	
	public boolean agregarPersona(PersonaDTO nuevaPersona) {
		return this.persona.insert(nuevaPersona);
	}

	public void borrarPersona(PersonaDTO persona_a_eliminar) {
		this.persona.delete(persona_a_eliminar);
	}
	
	public List<PersonaDTO> obtenerPersonas() {
		return this.persona.readAll();		
	}
	
	public PersonaDTO obtenerPersona(int id) {
		return this.persona.read(id);
		
	}

	public void editarPersona(PersonaDTO persona_a_editar) {
		this.persona.update(persona_a_editar);
	}
	
	public void agregarTipo (TipoContactoDTO nuevoTipo) {
		System.out.println(nuevoTipo.getNombre().toString());
		this.contacto.insert(nuevoTipo);
	}
}


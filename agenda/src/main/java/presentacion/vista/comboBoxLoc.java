package presentacion.vista;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import dto.UbicacionDTO;
import persistencia.dao.mysql.UbicacionDAOImpl;

public class comboBoxLoc extends JComboBox<String>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public comboBoxLoc() {
		super();
		DefaultComboBoxModel<String> value = new DefaultComboBoxModel<String>();
		this.setModel(value);
		value.addElement("Seleccione la provincia");
		for (UbicacionDTO Ubicacion : new UbicacionDAOImpl().readAll()) {
			value.addElement(Ubicacion.getProvincia());
		}
		value.addElement("otra");
		
	}

	public UbicacionDTO getLocalidad() {
		return (UbicacionDTO) this.getSelectedItem();
	}
}
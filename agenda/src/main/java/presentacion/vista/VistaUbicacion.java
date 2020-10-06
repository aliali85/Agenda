package presentacion.vista;
import java.awt.Color;
//Visualizacion de las Ubicaciones
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.UbicacionDTO;
import persistencia.dao.mysql.UbicacionDAOImpl;

public class VistaUbicacion implements ActionListener {
	private JFrame frame;
	private JTable tablaUbicacion;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnBorrar;
	private DefaultTableModel modelUbicacion;   
	private List<UbicacionDTO> Ubicacion_en_tabla;
	private VentanaUbicacion ventanaUbicacion;
	private String[] nombreColumnas = { "Localidad", "Provincia", "Pais" };
	private int indiceFilaUbicacion;

	public VistaUbicacion() {
		super();
		initialize();
		Ubicacion_en_tabla = null;
		this.getBtnAgregar().addActionListener(this);
		this.getBtnBorrar().addActionListener(this);
		this.getBtnEditar().addActionListener(this);
		llenarTabla();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 340, 300);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 320, 262);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane spUbicacion = new JScrollPane();
		spUbicacion.setBounds(10, 11, 300, 182);
		panel.add(spUbicacion);

		modelUbicacion = new DefaultTableModel(null, nombreColumnas);
		tablaUbicacion = new JTable(modelUbicacion);

		tablaUbicacion.getColumnModel().getColumn(0).setPreferredWidth(130);
		tablaUbicacion.getColumnModel().getColumn(0).setResizable(false);

		spUbicacion.setViewportView(tablaUbicacion);
		
		tablaUbicacion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				indiceFilaUbicacion = tablaUbicacion.getSelectedRow();
			}
		});

		btnAgregar = new JButton("Agregar");
		btnAgregar.setBackground(Color.green);
		btnAgregar.setBounds(10, 228, 89, 23);
		panel.add(btnAgregar);

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(109, 228, 89, 23);
		panel.add(btnEditar);

		btnBorrar = new JButton("Borrar");
		btnBorrar.setBackground(Color.red);
		btnBorrar.setBounds(208, 228, 89, 23);
		panel.add(btnBorrar);

		frame.setTitle("ABM Ubicacion");
	}

	public void show() {
		this.frame.setVisible(true);
	}

	private void llenarTabla() {
		this.getModelUbicacion().setRowCount(0); // Para vaciar la tabla
		this.getModelUbicacion().setColumnCount(0);
		this.getModelUbicacion().setColumnIdentifiers(this.getNombreColumnas());

		this.Ubicacion_en_tabla = new UbicacionDAOImpl().readAll();
		for (int i = 0; i < this.Ubicacion_en_tabla.size(); i++) {
			Object[] fila = {this.Ubicacion_en_tabla.get(i).getLocalidad(),this.Ubicacion_en_tabla.get(i).getProvincia(),this.Ubicacion_en_tabla.get(i).getPais()

			};
			this.getModelUbicacion().addRow(fila);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.getBtnAgregar()) {
			this.ventanaUbicacion = new VentanaUbicacion(this, "Agregar", (UbicacionDTO)null);////

		} else if (e.getSource() == this.getBtnEditar()) {
			int[] filas_seleccionadas = this.getTablaUbicacion().getSelectedRows();
			if (filas_seleccionadas.length == 1) {
				UbicacionDTO Ubicacion_a_obtener = new UbicacionDAOImpl()
						.getById(this.Ubicacion_en_tabla.get(filas_seleccionadas[0]));
				this.ventanaUbicacion = new VentanaUbicacion(this, "Editar", (UbicacionDTO)Ubicacion_a_obtener);////
			}
		} else if (e.getSource() == this.getBtnBorrar()) {
			int[] filas_seleccionadas = this.getTablaUbicacion().getSelectedRows();
			for (int fila : filas_seleccionadas) {
				new UbicacionDAOImpl().delete(this.Ubicacion_en_tabla.get(fila));
			}

			this.llenarTabla();

		} else if (e.getSource() == this.ventanaUbicacion.getBtnAgregarLocalidad()) {
			UbicacionDTO nuevaUbicacion = this.ventanaUbicacion.getDatosLocalidad();
			new UbicacionDAOImpl().insert(nuevaUbicacion);
			this.llenarTabla();
			this.ventanaUbicacion.dispose();
		} else if (e.getSource() == this.ventanaUbicacion.getBtnEditarLocalidad()) {
			UbicacionDTO editarUbicacion = this.ventanaUbicacion.getDatosLocalidad();
			new UbicacionDAOImpl().update(editarUbicacion);
			this.llenarTabla();
			this.ventanaUbicacion.dispose();
		}
	}

	public JButton getBtnAgregar() { return btnAgregar; }
	public JButton getBtnEditar() { return btnEditar; }
	public JButton getBtnBorrar() { return btnBorrar; }
	public DefaultTableModel getModelUbicacion() { return modelUbicacion; }
	public JTable getTablaUbicacion() { return tablaUbicacion; }
	public String[] getNombreColumnas() { return nombreColumnas; }
}
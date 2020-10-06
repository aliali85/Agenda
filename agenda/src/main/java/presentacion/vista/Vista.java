package presentacion.vista;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dto.PersonaDTO;
import javax.swing.JButton;
import persistencia.conexion.Conexion;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Vista {
	private JFrame frame;
	private JPanel panel;
	private JScrollPane spPersonas;
	private JTable tablaPersonas;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnBorrar;
	private JButton btnReporte;
	private JButton btnABMLocalidades;
	private DefaultTableModel modelPersonas;
	private String[] nombreColumnas = { "Nombre y apellido", "Telefono", "Calle", "Altura", "Piso", "Departamento",
			"Localidad", "E-Mail", "Fecha de Nacimiento", "Tipo de Contacto" };
	private int indiceFilaPersona;
	private JButton btnAgregarCampo;
	private ArrayList<String> camposAgregados;

	public Vista() {
		super();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 100, 760, 318);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBackground(new Color(240, 255, 255));
		panel.setBounds(0, 0, 744, 268);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		spPersonas = new JScrollPane();
		spPersonas.setBounds(10, 11, 724, 182);
		panel.add(spPersonas);

		modelPersonas = new DefaultTableModel(null, nombreColumnas);
		tablaPersonas = new JTable(modelPersonas);

		tablaPersonas.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaPersonas.getColumnModel().getColumn(0).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaPersonas.getColumnModel().getColumn(1).setResizable(false);

		spPersonas.setViewportView(tablaPersonas);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon("C:\\Users\\Augusto\\Documents\\Iconos\\add-user-2-16.png"));
		btnAgregar.setBounds(10, 228, 100, 23);
		panel.add(btnAgregar);

		btnEditar = new JButton("Editar");
		btnEditar.setIcon(new ImageIcon("C:\\Users\\Augusto\\Documents\\Iconos\\edit-user-16.png"));
		btnEditar.setBounds(120, 228, 89, 23);
		panel.add(btnEditar);

		btnBorrar = new JButton("Borrar");
		btnBorrar.setIcon(new ImageIcon("C:\\Users\\Augusto\\Documents\\Iconos\\remove-user-16.png"));
		btnBorrar.setBounds(219, 228, 96, 23);
		panel.add(btnBorrar);

		btnABMLocalidades = new JButton("ABM Localidades");
		btnABMLocalidades.setBackground(new Color(135, 206, 250));
		btnABMLocalidades.setBounds(325, 228, 134, 23);
		panel.add(btnABMLocalidades);

		btnReporte = new JButton("Reporte");
		btnReporte.setBackground(new Color(255, 160, 122));
		btnReporte.setIcon(new ImageIcon("C:\\Users\\Augusto\\Documents\\Iconos\\database-16.png"));
		btnReporte.setBounds(476, 228, 104, 23);
		panel.add(btnReporte);

		btnAgregarCampo = new JButton("Agregar Campo");
		btnAgregarCampo.setBounds(590, 228, 132, 23);
		panel.add(btnAgregarCampo);

		this.tablaPersonas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				indiceFilaPersona = tablaPersonas.getSelectedRow();
			}
		});
		camposAgregados = new ArrayList<String>();
	}

	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "¿Estás seguro que quieres salir de la Agenda?",
						"Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					Conexion.getConexion().cerrarConexion();
					System.exit(0);
				}
			}
		});
		this.frame.setVisible(true);
	}

	public void llenarTabla(List<PersonaDTO> personasEnTabla) {
		this.getModelPersonas().setRowCount(0); // Para vaciar la tabla
		this.getModelPersonas().setColumnCount(0);
		this.getModelPersonas().setColumnIdentifiers(this.getNombreColumnas());

		for (PersonaDTO p : personasEnTabla) {
			String nombre = p.getNombre();
			String tel = p.getTelefono();
			String calle = p.getCalle();
			String altura = p.getAltura();
			String piso = p.getPiso();
			String departamento = p.getDepartamento();
			String localidad = p.getLocalidad();
			String email = p.getEmail();
			Date fechanac = (Date) p.getFechaNacimiento();
			String tipoContacto = p.getTipoContacto();
			Object[] fila = { nombre, tel, calle, altura, piso, departamento, localidad, email, fechanac,
					tipoContacto };
			this.getModelPersonas().addRow(fila);
		}

		if (camposAgregados.size() > 0) {
			String[] aux = new String[camposAgregados.size() + this.nombreColumnas.length];
			int i = 0;
			for (int z = 0; z < aux.length; z++) {
				if (z < nombreColumnas.length) {
					aux[z] = nombreColumnas[z];
				} else {
					aux[z] = camposAgregados.get(i);
					i++;
				}

			}
			this.getModelPersonas().setColumnIdentifiers(aux);

		}

	}


	public void setCamposAgregados(ArrayList<String> camposAgregados) {
		this.camposAgregados = camposAgregados;
	}

	public ArrayList<String> getCamposAgregados() {
		return camposAgregados;
	}

	public int getIndiceFilaPersona() {
		return indiceFilaPersona;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public JButton getBtnReporte() {
		return btnReporte;
	}

	public DefaultTableModel getModelPersonas() {
		return modelPersonas;
	}

	public JTable getTablaPersonas() {
		return tablaPersonas;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JButton getBtnABMLocalidades() {
		return btnABMLocalidades;
	}

	public JButton getBtnAgregarCampo() {
		return btnAgregarCampo;
	}
}

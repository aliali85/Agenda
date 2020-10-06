package presentacion.vista;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dto.UbicacionDTO;

public class VentanaUbicacion extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblNombre;
	private JLabel lblProvincia;
	private JLabel lblOtraProvincia;
	private JLabel lblPais;
	private int idLocalidad;
	private JTextField txtNombre;
	private JTextField txtProvincia;
	private JTextField txtOtraProvincia;
	private JTextField txtPais;
	private JButton btnAgregarLocalidad;
	private JButton btnEditarLocalidad;
	private ActionListener controlador;
	private comboBoxLoc cbxProvincias;
	private String ProvinciaSeleccionada;

	public VentanaUbicacion(ActionListener controlador, String accion, UbicacionDTO localidad) {
		super();
		this.controlador = controlador;
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(10, 11, 322, 201);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNombre = new JLabel("Localidad");
		lblNombre.setBounds(10, 10, 113, 14);
		panel.add(lblNombre);
		
		lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(10, 50, 113, 14);
		panel.add(lblProvincia);
		
		lblOtraProvincia = new JLabel("Otra");
		lblOtraProvincia.setBounds(10, 85, 113, 14);
		panel.add(lblOtraProvincia);
						
		lblPais = new JLabel("Pais");
		lblPais.setBounds(10, 125, 113, 14);
		panel.add(lblPais);
		
		cbxProvincias = new comboBoxLoc();
		cbxProvincias.setBounds(80, 50, 164, 20);
		cbxProvincias.getSelectedItem();
		
		idLocalidad = 0;

		txtNombre = new JTextField();
		txtNombre.setBounds(80,10, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtOtraProvincia = new JTextField();
		txtOtraProvincia.setBounds(80, 85, 164, 20);
		panel.add(txtOtraProvincia);
		
		txtPais = new JTextField();
		txtPais.setBounds(80, 125, 164, 20);
		panel.add(txtPais);
		txtPais.setColumns(10);

		if (accion == "Agregar")
			inicializarAgregar(panel, localidad);
		else
			inicializarEditar(panel, localidad);

		this.setVisible(true);
	}

	private void inicializarAgregar(JPanel panel, UbicacionDTO localidad) {
		this.setTitle("Agregar Ubicacion");
		btnAgregarLocalidad = new JButton("Agregar");
		btnAgregarLocalidad.setBackground(Color.green);
		btnAgregarLocalidad.addActionListener(this.controlador);
		btnAgregarLocalidad.setBounds(208, 180, 89, 23);
		panel.add(btnAgregarLocalidad);
		panel.add(cbxProvincias);
	}

	private void inicializarEditar(JPanel panel, UbicacionDTO localidad) {
		this.setTitle("Editar Ubicacion");
		this.llenarDatosFormulario(localidad);
		idLocalidad = localidad.getIdLocalidad();
		btnEditarLocalidad = new JButton("Actualizar");
		btnEditarLocalidad.setBackground(Color.green);
		btnEditarLocalidad.addActionListener(this.controlador);
		btnEditarLocalidad.setBounds(208, 180, 89, 23);
		panel.add(cbxProvincias);
		panel.add(btnEditarLocalidad);
	}

	public int getIdLocalidad() { return idLocalidad; }
	public JTextField getTxtLocalidad() { return txtNombre; }
	public JTextField getTxtProvincia() { return txtProvincia; }
	public JTextField getTxtPais() { return txtPais; }
	public JButton getBtnAgregarLocalidad() { return btnAgregarLocalidad; }
	public JButton getBtnEditarLocalidad() { return btnEditarLocalidad; }
	
	public UbicacionDTO getDatosLocalidad() {
		String provinciaSeleccionada;
		if(cbxProvincias.getSelectedItem().equals("otra")) {
			provinciaSeleccionada = txtOtraProvincia.getText();
		}else {
			provinciaSeleccionada = cbxProvincias.getSelectedItem().toString();
		}
		UbicacionDTO localidad = new UbicacionDTO(this.getIdLocalidad(), this.getTxtLocalidad().getText() ,provinciaSeleccionada ,this.getTxtPais().getText());
		return localidad;
	}
	
	private void llenarDatosFormulario(UbicacionDTO localidad) {
		txtNombre.setText(localidad.getLocalidad());
		//txtProvincia.setText(localidad.getProvincia());
		txtPais.setText(localidad.getPais());
	}
}
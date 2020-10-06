package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePickerImpl;

import presentacion.controlador.Controlador;
import util.ExpReg;
import dto.PersonaDTO;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import javax.swing.ImageIcon;

public class VentanaPersona extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblNombreYApellido;
	private JLabel lblTelfono;
	private JLabel lblCalle;
	private JLabel lblAltura;
	private JLabel lblPiso;
	private JLabel lblDepartamento;
	private JLabel lblLocalidad;
	private JLabel lblEmail;
	private JLabel lblFnac;
	private JLabel lblTipo;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtCalle;
	private JTextField txtAltura;
	private JTextField txtPiso;
	private JTextField txtDepartamento;
	private JTextField txtLocalidad;
	private JTextField txtEmail;
	private JTextField txtTipo;
	private JButton btnAgregarPersona;
	private JButton btnEditarPersona;
	private JDatePickerImpl datePicker;
	private JComboBox comboBoxTipoContacto;
	private JButton btnAltaTipo;
	private static VentanaPersona INSTANCE;
	private JDateChooser dateChooser;
	
	public static VentanaPersona getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new VentanaPersona(); 	
			return new VentanaPersona();
		}else
			return INSTANCE;
	}

	private VentanaPersona() {
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 694, 496);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 11, 658, 423);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(10, 11, 113, 14);
		panel.add(lblNombreYApellido);
		
		lblTelfono = new JLabel("Telefono");
		lblTelfono.setBounds(10, 52, 113, 14);
		panel.add(lblTelfono);

		lblCalle = new JLabel("Calle");
		lblCalle.setBounds(10, 93, 113, 14);
		panel.add(lblCalle);

		lblAltura = new JLabel("Altura");
		lblAltura.setBounds(329, 93, 113, 14);
		panel.add(lblAltura);
		
		lblPiso = new JLabel("Piso");
		lblPiso.setBounds(10, 175, 113, 14);
		panel.add(lblPiso);
		
		lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setBounds(329, 175, 113, 14);
		panel.add(lblDepartamento);

		lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(10, 257, 113, 14);
		panel.add(lblLocalidad);

		lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(10, 298, 113, 14);
		panel.add(lblEmail);
		
		lblFnac = new JLabel("Fecha de Nacimiento");
		lblFnac.setBounds(10, 339, 113, 14);
		panel.add(lblFnac);
		
		lblTipo = new JLabel("Tipo de Relacion");
		lblTipo.setBounds(10, 380, 113, 14);
		panel.add(lblTipo);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(133, 8, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(133, 49, 164, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		txtCalle = new JTextField();
		txtCalle.setBounds(133, 90, 164, 20);
		panel.add(txtCalle);
		txtCalle.setColumns(10);
		
		txtAltura = new JTextField();
		txtAltura.setBounds(452, 90, 164, 20);
		panel.add(txtAltura);
		txtAltura.setColumns(10);
		
		txtPiso = new JTextField();
		txtPiso.setBounds(133, 172, 164, 20);
		panel.add(txtPiso);
		txtPiso.setColumns(10);
		
		txtDepartamento = new JTextField();
		txtDepartamento.setBounds(452, 172, 164, 20);
		panel.add(txtDepartamento);
		txtDepartamento.setColumns(10);
		
		txtLocalidad = new JTextField();
		txtLocalidad.setBounds(133, 254, 164, 20);
		panel.add(txtLocalidad);
		txtLocalidad.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(133, 293, 279, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		//txtTipo = new JTextField();
		//txtTipo.setBounds(133, 375, 164, 20);
		//panel.add(txtTipo);
		//txtTipo.setColumns(10);
		
		comboBoxTipoContacto = new JComboBox();
		comboBoxTipoContacto.setBounds(135, 375, 164, 20);
		panel.add(comboBoxTipoContacto);
		
		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.setIcon(new ImageIcon("C:\\Users\\Augusto\\Documents\\Iconos\\checked-user-16.png"));
		btnAgregarPersona.setBounds(417, 400 ,113, 23);
		panel.add(btnAgregarPersona);
		
		btnEditarPersona = new JButton("Editar");
		btnEditarPersona.setBounds(417, 400 ,89, 23);
		panel.add(btnEditarPersona);
		btnEditarPersona.setVisible(false);
		
		btnAltaTipo = new JButton("Alta Tipo contacto");
		btnAltaTipo.setBounds(133, 400, 146, 23);
		panel.add(btnAltaTipo);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(138, 339, 113, 20);
		panel.add(dateChooser);
		
		
		this.setVisible(false);
	}
	
	public void mostrarVentana() {
	
//		datePicker = VentanaCalendario.getPickerToday();
//		pnlDatePicker.add(datePicker);
		
		this.setVisible(true);
		btnAgregarPersona.setVisible(true);
	}
	
	public void mostrarVentanaEditar() {
		this.setVisible(true);
		btnAgregarPersona.setVisible(false);
		btnEditarPersona.setVisible(true);
		
	}
	
	
	public JTextField getTxtNombre() { return txtNombre; }
	public JTextField getTxtTelefono() { return txtTelefono; }
	public JTextField getTxtCalle() { return txtCalle; }
	public JTextField getTxtAltura() { return txtAltura; }
	public JTextField getTxtPiso() { return txtPiso; }
	public JTextField getTxtDepartamento() { return txtDepartamento; }
	public JTextField getTxtLocalidad() { return txtLocalidad; }
	public JTextField getTxtEmail() { return txtEmail; }
//	public JTextField getTxtfnac() { return txtfnac; }
	public JTextField getTxtTipo() { return txtTipo; }
	public JButton getBtnAgregarPersona() { return btnAgregarPersona; }
	public JButton getBtnEditarPersona() { return btnEditarPersona; }
	public JButton getbtnAltaTipo () {return btnAltaTipo;}
	public JComboBox getComboboxContacto(){return comboBoxTipoContacto;	}
	
	public JDateChooser getDateChooser() {
		return dateChooser;
	}
	
	public void cerrar(){
		this.txtNombre.setText(null);
		this.txtTelefono.setText(null);
		this.txtCalle.setText(null);
		this.txtAltura.setText(null);
		this.txtPiso.setText(null);
		this.txtDepartamento.setText(null);
		this.txtLocalidad.setText(null);
		this.txtEmail.setText(null);
		this.dateChooser.setDate(null);
		//this.txtTipo.setText(null);
		this.dispose();
	}
	
	public boolean datosEstanCorrectos()
	{
		this.getTxtNombre().setText(this.getTxtNombre().getText().trim());
		this.getTxtTelefono().setText(this.getTxtTelefono().getText().trim());
		this.getTxtEmail().setText(this.getTxtEmail().getText().trim());
		this.getTxtCalle().setText(this.getTxtCalle().getText().trim());
		this.getTxtAltura().setText(this.getTxtAltura().getText().trim());
		this.getTxtPiso().setText(this.getTxtPiso().getText().trim());
		this.getTxtDepartamento().setText(this.getTxtDepartamento().getText().trim());
		
		String error = "";
	
		if(!ExpReg.contieneLetrasyEspacios(this.getTxtNombre().getText()))
			error += "-Coloque un nombre y apellido valido\n";
		
		if(this.getTxtNombre().getText().length() > 45)
			error += "-El nombre no puede ser de mas de 45 caracteres";
				
		if(!ExpReg.telefonoValido(this.getTxtTelefono().getText()))
			error += "-Coloque un telefono valido\n";
		
		if(this.getTxtTelefono().getText().length() > 20)
			error += "-El Telefono no puede contener mas de 20 caracteres";
		
		if(!ExpReg.mailValido(this.getTxtEmail().getText()))
			error += "-Coloque un mail valido\n";
		
		if(this.getTxtEmail().getText().length() > 50)
			error += "-El Mail No puede ser de mas de 50 caracteres";
			
		if(!this.getTxtCalle().getText().isEmpty() || !this.getTxtAltura().getText().isEmpty())
		{
			if (!ExpReg.contieneLetrasNumerosyEspacios(this.getTxtCalle().getText()))
				error += "-Coloque una calle valida\n";
			
			if (!ExpReg.contieneSoloNumeros(this.getTxtAltura().getText()))
				error += "-Coloque una altura valida\n";
			
			if(this.getTxtCalle().getText().length() > 45)
				error += "-La calle no puede ser de mas de 45 caracteres";
			
			if(this.getTxtAltura().getText().length() > 6)
				error += "-La Altura no puede ser de mas de 6 caracteres";
		}
		
		if (!this.getTxtPiso().getText().isEmpty())
			if(!ExpReg.contieneLetrasNumerosyEspacios(this.getTxtPiso().getText()))
				error += "-Coloque un piso valido\n";
		
		if(this.getTxtPiso().getText().length() > 3)
			error += "-El piso no puede ser de mas de 3 caracteres";
		
		if(!this.getTxtDepartamento().getText().isEmpty())
			if (!ExpReg.contieneLetrasNumerosyEspacios(this.getTxtDepartamento().getText()))
				error += "-Coloque un departamento valido\n";
		
		if(this.getTxtDepartamento().getText().length() > 10)
			error += "-El departamento no puede ser de mas de 10 caracteres";
					
		if(error != "")
		{
			JOptionPane.showMessageDialog(null, error);
			return false;
		}
		
		return true;
	}
}


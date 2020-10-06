package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VentanaTipoContacto extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnAgregarTipo;
	private static VentanaTipoContacto INSTANCE;
	
	public static VentanaTipoContacto getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new VentanaTipoContacto(); 	
			return new VentanaTipoContacto();
		}else
			return INSTANCE;
	}

	public VentanaTipoContacto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(80, 120, 100, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("NUEVO TIPO DE CONTACTO");
		lblNewLabel.setBounds(80, 56, 120, 14);
		contentPane.add(lblNewLabel);

		btnAgregarTipo = new JButton("Nuevo Tipo");
		btnAgregarTipo.setBounds(80, 180, 100, 23);
		contentPane.add(btnAgregarTipo);
		
		
		
	}

	
	public JTextField getTxtTipo() { return textField; }
	public void mostrarVentana() {

		this.setVisible(true);
	}
	
	public JButton getBtnAgregarTipo() {return btnAgregarTipo; }
}
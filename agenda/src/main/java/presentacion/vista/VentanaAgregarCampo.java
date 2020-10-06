package presentacion.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class VentanaAgregarCampo extends JFrame{

	private JPanel contentPane;
	private JFrame frame;
	private JPanel panel;
	private JLabel lblNombreCampo;
	private JLabel lblTipoDeDato;
	private JTextField txtNombreCampo;
	private JTextField txtTipoDato;
	private JButton btnAgregarCampo;
	private static VentanaAgregarCampo INSTANCE;

	public static VentanaAgregarCampo getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new VentanaAgregarCampo(); 	
			return new VentanaAgregarCampo();
		}else
			return INSTANCE;
	}
	
	private VentanaAgregarCampo() {
		super();
		setBounds(100, 100, 364, 195);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 11, 328, 143);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNombreCampo = new JLabel("Nombre del Campo");
		lblNombreCampo.setBounds(10, 8, 126, 14);
		panel.add(lblNombreCampo);
		
		txtNombreCampo = new JTextField();
		txtNombreCampo.setBounds(146, 5, 137, 20);
		panel.add(txtNombreCampo);
		txtNombreCampo.setColumns(10);
		
		
		lblTipoDeDato = new JLabel("Tipo de Dato");
		lblTipoDeDato.setBounds(10, 39, 116, 14);
		panel.add(lblTipoDeDato);
		
		txtTipoDato = new JTextField();
		txtTipoDato.setColumns(10);
		txtTipoDato.setBounds(146, 36, 137, 20);
		panel.add(txtTipoDato);
		
		btnAgregarCampo = new JButton("Agregar Campo");
		btnAgregarCampo.setBounds(181, 109, 137, 23);
		panel.add(btnAgregarCampo);
	}
	
	public void mostrarVentanaAgregarCampo() {
		this.setVisible(true);
	}
	
	public JTextField getTxtNombreCampo() { return txtNombreCampo; }
	public JTextField getTxtTipoDato() { return txtTipoDato; }
	public JButton getBtnAgregarCampo() {return btnAgregarCampo; }

}

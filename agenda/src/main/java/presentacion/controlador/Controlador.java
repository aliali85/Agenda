package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;

import com.mysql.jdbc.Connection;

import modelo.Agenda;
import persistencia.conexion.Conexion;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaAgregarCampo;
import presentacion.vista.VentanaPersona;
import presentacion.vista.VentanaTipoContacto;
import presentacion.vista.Vista;
import presentacion.vista.VistaUbicacion;
import dto.PersonaDTO;
import dto.TipoContactoDTO;

public class Controlador implements ActionListener {
		private Vista vista;
		private List<PersonaDTO> personasEnTabla;
		private ArrayList<Integer> idPersonas;
		private VentanaPersona ventanaPersona; 
		private VistaUbicacion VistaUbicacion;
		private VentanaAgregarCampo ventanaAgregarCampo;
		private VentanaTipoContacto ventanatipocontacto;
		private Agenda agenda;
		
		public Controlador(Vista vista, Agenda agenda){
			this.vista = vista;
			this.vista.getBtnAgregar().addActionListener(a->ventanaAgregarPersona(a));
			this.vista.getBtnEditar().addActionListener(e-> ventanaEditarPersona(e));
			this.vista.getBtnBorrar().addActionListener(s->borrarPersona(s));
			this.vista.getBtnReporte().addActionListener(r->mostrarReporte(r));
			this.ventanaAgregarCampo = VentanaAgregarCampo.getInstance();
			this.ventanaAgregarCampo.getBtnAgregarCampo().addActionListener(cn -> guardarCampoNuevo(cn));
			this.vista.getBtnAgregarCampo().addActionListener(ac ->ventanaAgregarCampo(ac));
			this.ventanaPersona = VentanaPersona.getInstance();
			this.ventanaPersona.getBtnAgregarPersona().addActionListener(p->guardarPersona(p));
			this.ventanaPersona.getBtnEditarPersona().addActionListener(u->updatePersona(u));
			this.ventanatipocontacto = ventanatipocontacto.getInstance();
			this.ventanatipocontacto.getBtnAgregarTipo().addActionListener(t->guardarTipo(t));
			this.ventanaPersona.getbtnAltaTipo().addActionListener(t->ventanaAgregarTipoContacto(t));
			this.vista.getBtnABMLocalidades().addActionListener(this);
			llenarContactos(this.ventanaPersona.getComboboxContacto());
			this.agenda = agenda;
		}
		
		public void guardarCampoNuevo(ActionEvent cn) {
			ArrayList<String> campoAgregados = this.vista.getCamposAgregados();
			campoAgregados.add(this.ventanaAgregarCampo.getTxtNombreCampo().getText());;
			this.vista.setCamposAgregados(campoAgregados);

			
			this.vista.llenarTabla(personasEnTabla);
			System.out.println("campo agregado");
		}
		
			private void ventanaAgregarPersona(ActionEvent a) {
			this.ventanaPersona.mostrarVentana();
		}
		
		private void ventanaAgregarTipoContacto(ActionEvent t) {
			this.ventanatipocontacto.mostrarVentana();}
		
		public void ventanaEditarPersona(ActionEvent e) {
			this.ventanaPersona.mostrarVentanaEditar();
			this.llenarDatosFormulario(this.agenda.obtenerPersona(this.idPersonas.get(this.vista.getIndiceFilaPersona()) ));
		}
		
		public void ventanaAgregarCampo(ActionEvent ac) {
			this.ventanaAgregarCampo.mostrarVentanaAgregarCampo();
		}
		
				
		private void llenarContactos(JComboBox comboboxContacto) {
			PreparedStatement statement;
			ResultSet resultSet; //Guarda el resultado de la query
			Conexion conexion = Conexion.getConexion();
			
			try {
				String sql= "Select * from tipocontacto";
				statement = conexion.getSQLConexion().prepareStatement(sql);
				resultSet = statement.executeQuery();
				while(resultSet.next()) {
					
					comboboxContacto.addItem(resultSet.getString("nombre"));
							}
			}catch(SQLException ex) {
				
			}
		}
			

		private void guardarPersona(ActionEvent p) {
			String nombre = ventanaPersona.getTxtNombre().getText();
			String tel = ventanaPersona.getTxtTelefono().getText();
			String calle = ventanaPersona.getTxtCalle().getText();
			String altura = ventanaPersona.getTxtAltura().getText();
			String piso = ventanaPersona.getTxtPiso().getText();
			String departamento = ventanaPersona.getTxtDepartamento().getText();
			String localidad = ventanaPersona.getTxtLocalidad().getText();
			String email = ventanaPersona.getTxtEmail().getText();
//			String fnacimiento = ventanaPersona.getTxtfnac().getText();
			Date date = ventanaPersona.getDateChooser().getDate();
			long d = date.getTime();
			java.sql.Date fnacimiento = new java.sql.Date(d);
			System.out.println(fnacimiento);
//			System.out.println(fnacimiento);
			String tipo = ventanaPersona.getComboboxContacto().getSelectedItem().toString();
			
			PersonaDTO nuevaPersona = new PersonaDTO(nombre, tel, calle,altura,piso,departamento,localidad,email,fnacimiento, tipo);
			if(this.ventanaPersona.datosEstanCorrectos())
			{
			this.agenda.agregarPersona(nuevaPersona);
			this.refrescarTabla();
			this.ventanaPersona.cerrar();}
			
		}
		
		private void guardarTipo(ActionEvent t) {
			
			String nombre = ventanatipocontacto.getTxtTipo().getText();
			TipoContactoDTO nuevoTipo = new TipoContactoDTO(nombre);
			
			PreparedStatement statement;
			Connection conexion = (Connection) Conexion.getConexion().getSQLConexion();
			try {
				statement = conexion.prepareStatement("INSERT INTO tipocontacto (nombre) values (?)");
				statement.setString(1, nuevoTipo.getNombre());
				if (statement.executeUpdate() > 0) {
					conexion.commit();
				}
				statement.close();

			} catch (SQLException e) {
				e.printStackTrace();
				try {
					conexion.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		private void mostrarReporte(ActionEvent r) {
			ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
			reporte.mostrar();	
		}

		public void borrarPersona(ActionEvent s) {
			int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
			for (int fila : filasSeleccionadas) {
				this.agenda.borrarPersona(this.personasEnTabla.get(fila));
			}
			
			this.refrescarTabla();
		}
		
		private void updatePersona(ActionEvent u) {
			int id = this.idPersonas.get(this.vista.getIndiceFilaPersona());
			String nombre = ventanaPersona.getTxtNombre().getText();
			String tel = ventanaPersona.getTxtTelefono().getText();
			String calle = ventanaPersona.getTxtCalle().getText();
			String altura = ventanaPersona.getTxtAltura().getText();
			String piso = ventanaPersona.getTxtPiso().getText();
			String departamento = ventanaPersona.getTxtDepartamento().getText();
			String localidad = ventanaPersona.getTxtLocalidad().getText();
			String email = ventanaPersona.getTxtEmail().getText();
			
			Date fecha = ventanaPersona.getDateChooser().getDate();
			long d = fecha.getTime();
			java.sql.Date fnacimiento = new java.sql.Date(d);//12-20-2009 
			
			String tipo = ventanaPersona.getComboboxContacto().getSelectedItem().toString();
			
			PersonaDTO personaModificada = new PersonaDTO(id,nombre, tel, calle,altura,piso,departamento,localidad,email,fnacimiento, tipo);
//			System.out.println(ventanaPersona.getComboboxContacto().getSelectedItem());
			
			if(this.ventanaPersona.datosEstanCorrectos())
			{
			this.agenda.editarPersona(personaModificada);
			this.ventanaPersona.cerrar();
			this.refrescarTabla();}
			
		}
		
		public void inicializar() {
			this.refrescarTabla();
			this.vista.show();
		}
		
		private void refrescarTabla() {
			this.personasEnTabla = agenda.obtenerPersonas();
			this.vista.llenarTabla(this.personasEnTabla);
			this.idPersonas = new ArrayList<Integer>();
			for(int i=0; i < this.personasEnTabla.size();i++) {
				this.idPersonas.add(this.personasEnTabla.get(i).getIdPersona());
			}
		}
		
		public void llenarDatosFormulario(PersonaDTO persona) {
			this.ventanaPersona.getTxtNombre().setText(persona.getNombre());
			this.ventanaPersona.getTxtTelefono().setText(persona.getTelefono());
			this.ventanaPersona.getTxtCalle().setText(persona.getCalle());
			this.ventanaPersona.getTxtAltura().setText(persona.getAltura());
			this.ventanaPersona.getTxtPiso().setText(persona.getPiso());
			this.ventanaPersona.getTxtDepartamento().setText(persona.getDepartamento());
			this.ventanaPersona.getTxtLocalidad().setText(persona.getLocalidad());
			this.ventanaPersona.getTxtEmail().setText(persona.getEmail());
			this.ventanaPersona.getDateChooser().setDate(persona.getFechaNacimiento());

			this.ventanaPersona.getComboboxContacto().getSelectedItem().toString();//no se como sacar el valor del indice
		}

		@Override
		public void actionPerformed(ActionEvent e) { 
			if (e.getSource() == this.vista.getBtnABMLocalidades()) {
				this.VistaUbicacion = new VistaUbicacion();
				this.VistaUbicacion.show();
		}
}
}
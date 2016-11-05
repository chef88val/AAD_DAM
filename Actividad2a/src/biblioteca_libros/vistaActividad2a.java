package biblioteca_libros;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;

public class vistaActividad2a extends JFrame {
	public JTextField txt_titulo;
	public JTextField txt_autor;
	public JTextField txt_editor;
	public JTextField txt_fecha;
	public JTextField txt_paginas;
	private JLabel lblNumeroPginas,lblFechaPublicacin,lblEditor,lblAutor,lblTitulo=null;
	public JButton btnGuardar,btnRecuperar,btnGuardarListas,btnModificar =null;
	private JLabel lblNombreDelFicherodefault;
	public JTextField txtNombrefichero;
	private JButton btnaddtoLista;
	
	
	public vistaActividad2a() {
		// TODO Auto-generated constructor stub
		super("Actividad 2a");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(614, 215);
		getContentPane().setLayout(null);
		
		 lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(6, 6, 61, 16);
		getContentPane().add(lblTitulo);
		
		 lblAutor = new JLabel("Autor");
		lblAutor.setBounds(6, 34, 61, 16);
		getContentPane().add(lblAutor);
		
		 lblFechaPublicacin = new JLabel("Fecha publicación");
		lblFechaPublicacin.setBounds(6, 68, 113, 16);
		getContentPane().add(lblFechaPublicacin);
		
		 lblEditor = new JLabel("Editor");
		lblEditor.setBounds(6, 96, 61, 16);
		getContentPane().add(lblEditor);
		
		 lblNumeroPginas = new JLabel("Numero páginas");
		lblNumeroPginas.setBounds(6, 124, 103, 16);
		getContentPane().add(lblNumeroPginas);
		
		txt_titulo = new JTextField();
		txt_titulo.setBounds(57, 0, 200, 28);
		getContentPane().add(txt_titulo);
		txt_titulo.setColumns(10);
		
		txt_autor = new JTextField();
		txt_autor.setBounds(57, 33, 200, 28);
		getContentPane().add(txt_autor);
		txt_autor.setColumns(10);
		
		txt_editor = new JTextField();
		txt_editor.setBounds(57, 90, 200, 28);
		getContentPane().add(txt_editor);
		txt_editor.setColumns(10);
		
		Date ddate= new Date();
		DateFormat df = new SimpleDateFormat("31/12/2016");
		
		MaskFormatter mf=null;
		try {
			 mf= new MaskFormatter("31/12/0000");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txt_fecha = new JTextField();
		txt_fecha.setText("31/12/2000");
		txt_fecha.setBounds(123, 62, 134, 28);
		getContentPane().add(txt_fecha);
		txt_fecha.setColumns(10);
		
		txt_paginas = new JTextField();
		txt_paginas.setBounds(123, 118, 134, 28);
		getContentPane().add(txt_paginas);
		txt_paginas.setColumns(10);
		
		 btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(313, 4, 117, 29);
		
		getContentPane().add(btnGuardar);
		
		 btnRecuperar = new JButton("Recuperar ultimo");
		 btnRecuperar.setActionCommand("Recuperar");
		btnRecuperar.setBounds(354, 63, 148, 29);
		
		getContentPane().add(btnRecuperar);
		
		 btnGuardarListas = new JButton("Guardar listas");
		btnGuardarListas.setBounds(431, 119, 117, 29);
		
		getContentPane().add(btnGuardarListas);
		
		 btnModificar = new JButton("Modificar");
		btnModificar.setBounds(431, 4, 117, 29);
		
		getContentPane().add(btnModificar);
		
		lblNombreDelFicherodefault = new JLabel("NOMBRE DEL FICHERO(default):");
		lblNombreDelFicherodefault.setBounds(6, 171, 233, 16);
		getContentPane().add(lblNombreDelFicherodefault);
		
		txtNombrefichero = new JTextField();
		txtNombrefichero.setToolTipText("nombre del fichero");
		txtNombrefichero.setText("biblioteca.xml");
		txtNombrefichero.setBounds(251, 165, 134, 28);
		getContentPane().add(txtNombrefichero);
		txtNombrefichero.setColumns(10);
		
		btnaddtoLista = new JButton("Añadir a lista");
		btnaddtoLista.setBounds(313, 119, 117, 29);
		getContentPane().add(btnaddtoLista);
	}
	
	public void setControlador(controladorActividad2a e){
		btnModificar.addActionListener(e);
		
		btnGuardar.addActionListener(e);
		btnGuardarListas.addActionListener(e);
		btnRecuperar.addActionListener(e);
		btnaddtoLista.addActionListener(e);
	}
	
}

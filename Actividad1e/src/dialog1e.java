import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.print.CancelablePrintJob;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//Clase del dialog para crear un nuevo fichero
public class dialog1e extends JDialog implements ActionListener {
	private JTextField textField;
	private TextArea textArea ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog1e dialog = new dialog1e();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static JButton okButton=null, cancelButton=null;
	/**
	 * Create the dialog.
	 */
	public dialog1e() {
		//Creacion de los parametros del Dialog
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 239, 450, 39);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			
			//Declaracion de elementos para el Ok y Cancel
			getContentPane().add(buttonPane);
			{
				 okButton = new JButton("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				 cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
		
		//Declaracion del resto de elementos del Dialog
		JLabel lblNombreDelFichero = new JLabel("Nombre del fichero");
		lblNombreDelFichero.setBounds(34, 17, 122, 16);
		getContentPane().add(lblNombreDelFichero);
		
		textField = new JTextField();
		textField.setBounds(168, 11, 134, 28);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblContenido = new JLabel("Contenido");
		lblContenido.setBounds(34, 46, 122, 16);
		getContentPane().add(lblContenido);
		
		textArea = new TextArea();
		textArea.setBounds(168, 47, 238, 119);
		textArea.setBackground(Color.white);
		getContentPane().add(textArea);		
		
	}
	
	//Acciones de los botones
	 public void actionPerformed(ActionEvent e) {
		 String archivo = null;
		 if(e.getSource()==okButton){//Boton OK
			 if( textArea.getText().equals("")|| textField.getText().equals("")){
				 JOptionPane.showMessageDialog(this, "Falta rellenar un campo");
			 }else{
			 File file = new File("/Users/js_cmd/"+textField.getText().toString());
			 String area = textArea.getText().toString();
			  archivo=file.getAbsolutePath();
			 FileInputStream fi=null;
			 FileOutputStream fo=null;
			 //Compruebo si existe el fichero que quiero crear
			 if (!file.exists()) {
					try {
						file.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(this, "El fichero seleccionado ya existe");
				}
			
			 //Creo el archivo y meto el texto
			 try {
				 
					fi = new FileInputStream(archivo);
					 fo = new FileOutputStream(archivo);
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				 
				 byte[] bf = new byte[1000];
				 
				 
				 try {
						
						
						fi.read(bf);
						fo.write(area.getBytes());
						 fi.close();
						 fo.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				 
					
			 dispose();
			 }
		 }else if(e.getSource()==cancelButton){
			 dispose();
		 }
		
	 }
}

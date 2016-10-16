import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.*;


public class vista1e extends JFrame implements ActionListener{
	//Declaracion de elementos del Frame
	JButton openButton, saveButton;
    JTextArea log;
    JFileChooser fc;
    public static JTextField txt_Antiguo, txt_Nuevo;
    static private final String newline = "\n";
    public static JButton botonAbrirFichero,botonBB,crearFichero;
	public vista1e() {
        super("Actividad 1e Javier Segarra");
        setSize(560, 289);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        JLabel lbl_Antiguo = new JLabel("Fichero antiguo: ");
        JLabel lbl_Nuevo = new JLabel("Fichero nuevo: ");
        txt_Antiguo = new JTextField(20);
        txt_Nuevo = new JTextField(20);
        botonBB = new JButton("Copiar B&B");
        botonAbrirFichero = new JButton("Abrir fichero");
        String currentDirectoryPath="/Users/js_cmd";
        JFileChooser fichero1 = new JFileChooser(currentDirectoryPath);
        JFileChooser fichero2 = new JFileChooser(currentDirectoryPath);
        fc = new JFileChooser();
        openButton = new JButton("Seleccionar directorio");
        saveButton = new JButton("Seleccionar directorio");
        crearFichero = new JButton("Crear Fichero");
        
        //Eventos de los botones
        openButton.addActionListener(this);
        saveButton.addActionListener(this);
        botonAbrirFichero.addActionListener(this);
        botonBB.addActionListener(this);
        crearFichero.addActionListener(this);
        cp.add(lbl_Nuevo);cp.add(txt_Nuevo);cp.add(openButton);
        cp.add(lbl_Antiguo);cp.add(txt_Antiguo);cp.add(saveButton);
        cp.add(botonAbrirFichero);cp.add(botonBB);cp.add(crearFichero);
        
	}	
	//Funcion para recoger el nombre del fichero creado en ultimo lugar
	public void insertarTexto (String texto){
		txt_Nuevo.setText(texto);
		repaint();
		System.out.print(texto);
	}
	
	//Eventos de los botones del JFrame
	 public void actionPerformed(ActionEvent e) {
		 FileInputStream fi =null;
		 FileOutputStream fo =null;
		 byte[] bf = new byte[1000];
		 int bf_used= 0;
	        //Handle open button action.
		 if(e.getSource()==botonAbrirFichero){//Boton abrir fichero
			 if(txt_Nuevo.getText().toString().equals(txt_Antiguo.getText().toString()	)	){				
				 JOptionPane.showMessageDialog(this, "Los nombres de archivos son iguales");
				 
			 }else{
				 
				 try {//Tratamiento de datos para copiar datos de un archivo en otro
					 System.out.println("Nuevo"+txt_Nuevo.getText().toString());
					 
					 System.out.println("Viejo"+txt_Antiguo.getText().toString());
					 fi = new FileInputStream(txt_Nuevo.getText().toString());
					 fo = new FileOutputStream(txt_Antiguo.getText().toString());
					 
					 
					 fi.read(bf);
					 fo.write(bf);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally{
					try {
						fi.close();
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					try {//Cierro fichero
						fo.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			 }
			 
		 }	else if(e.getSource() == botonBB){//Funcionalidad del boton Byte&Byte 
			 txt_Antiguo.setEnabled(false);
			 try {
				 System.out.println("Nuevo"+txt_Nuevo.getText().toString());
				 
				 
				 fi = new FileInputStream(txt_Nuevo.getText().toString());
				//Muestro en una ventana el numero de bytes utilizados.
				 JOptionPane.showMessageDialog(this, "Se han usado, del buffer"+bf_used+"caracteres");

				 
				 
				 
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				try {//Cierro fichero
					fi.close();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				
			}
			 		 
		 } else if(e.getSource() == crearFichero){ 
			 dialog1e d1e = new dialog1e();
			 d1e.setVisible(true);
			 
		 } else if (e.getSource() == openButton) {
	            int returnVal = fc.showOpenDialog(vista1e.this);
	 
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File file = fc.getSelectedFile();
	                txt_Nuevo.setText(file.getAbsolutePath());
	            } else {
	                log.append("Open command cancelled by user." + newline);
	            }
	        } else if (e.getSource() == saveButton) {//Boton de finalizar operacion
	            int returnVal = fc.showOpenDialog(vista1e.this);
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File file = fc.getSelectedFile();
	                txt_Antiguo.setText(file.getAbsolutePath());
	            } else {
	                log.append("Save command cancelled by user." + newline);
	            }
	           
	        }
	    }
}

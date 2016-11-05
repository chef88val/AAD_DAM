package biblioteca_libros;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class controladorActividad2a implements ActionListener{
	private vistaActividad2a vista;
	private Libro libro;
	private Almacen almacen;
	private String nombre_fichero;
	private String contTitulo,contAutor,scontFecha,contEditor,scontNPag=null;
	private Closeable acerrar;
	public ArrayList<Libro> libros = new ArrayList<Libro>();
	
	public int contFecha,contNPag=0;
	
	//Constructor por argumentos
	public controladorActividad2a(vistaActividad2a vista, Libro libro, Almacen almacen) {
		// TODO Auto-generated constructor stub
		this.vista = vista;
		this.libro = libro;
		this.almacen= almacen;
		
	}
	
	//Funcion para comprobar que todos los campos esten rellenados y con texto valido
	public boolean camposVacios(){
		 boolean estado = true;
		 
		 contTitulo = vista.txt_titulo.getText();
		 
		 contAutor =vista.txt_autor.getText();
		
		 scontFecha=vista.txt_fecha.getText();
		
		 contEditor=vista.txt_editor.getText();
		
		 scontNPag =vista.txt_paginas.getText();
		
		 nombre_fichero= vista.txtNombrefichero.getText();
		if(contTitulo.isEmpty() || contAutor.isEmpty() || scontFecha.isEmpty() || contEditor.isEmpty() ||scontNPag.isEmpty() ){
			JOptionPane.showMessageDialog(vista, "Algun campo está vacio");
			return estado;
		}else{	
			if(scontFecha.matches("\\d{1,2}/\\d{1,2}/\\d{4}")){
				
				if(scontNPag.matches("[+-]?\\d*(\\.\\d+)?")){					
					//contFecha= Integer.parseInt(scontFecha);
					 
					contNPag=Integer.parseInt(scontNPag);
					if(contNPag>=0){
						return !estado;
					}else{
						JOptionPane.showMessageDialog(vista, "Has introducido texto");
						return estado;
					}
				}else{
					JOptionPane.showMessageDialog(vista, "Has introducido texto");
					return estado;
				}
				
			}else{
				JOptionPane.showMessageDialog(vista, "Has introducido una fecha incorrecta");
				return estado;
			}
			
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	

		if(e.getActionCommand().equals("Recuperar")){
			
			nombre_fichero=vista.txtNombrefichero.getText();//Obtenenmos el nombre del fichero
			
			almacen.parseFicheroXml(nombre_fichero);//Asignamos el nombre del fichero
			
			libro=almacen.parseDocumentLibro(2);//Recuperamos los valores de las etiquetas con autor 
												//asignandolo a un objeto de tipo libro
			
			//Incluimos el texto recuperado en los JTextFields y le damos la propiedad deshabilitado
			vista.txt_autor.setText(libro.getAutor());vista.txt_autor.setEnabled(false);
			vista.txt_titulo.setText(libro.getTitulo());vista.txt_titulo.setEnabled(false);				
			vista.txt_fecha.setText(libro.getFecha());vista.txt_fecha.setEnabled(false);
			vista.txt_editor.setText(libro.getEditor());vista.txt_editor.setEnabled(false);
			vista.txt_paginas.setText(String.valueOf(libro.getnPag()));vista.txt_paginas.setEnabled(false);
			
		}else if(e.getActionCommand().equals("Guardar listas")){
			//Envia la lista de libros y el nombre del fichero para guadar la lista
			almacen.createXMLFichero(nombre_fichero, libros);
				JOptionPane.showMessageDialog(vista, "Se ha guardado en "+nombre_fichero+", "+libros.size()+" libros");
			
		}else if(e.getActionCommand().equals("Añadir a lista")){

			//Añade elementos a la lista 
			if(!camposVacios()){				
				libros.add(new Libro(contTitulo,contAutor,scontFecha,contEditor,Integer.parseInt(scontNPag)));
			}

		}else if(e.getActionCommand().equals("Modificar")){
			//Habilita los textos para que sean editables
			vista.txt_autor.setEnabled(true);	
			vista.txt_titulo.setEnabled(true);				
			vista.txt_fecha.setEnabled(true);
			vista.txt_editor.setEnabled(true);
			vista.txt_paginas.setEnabled(true);
			
		}else if(e.getActionCommand().equals("Guardar")){
		
			//Guardamos un unico elemento
			if(!camposVacios()){
				
				libros.add(new Libro(contTitulo,contAutor,scontFecha,contEditor,Integer.parseInt(scontNPag)));
				
				almacen.createXMLFichero(nombre_fichero, libros);				
			}
			
		}else {
			JOptionPane.showMessageDialog(vista, "No hay ninguna acción seleccionada");
		}
	}

}

package biblioteca.libros;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class controladorActividad1g implements ActionListener{
	private vistaActividad1g vista;
	private Libro libro;
	private Almacen almacen;
	private String nombre_fichero;
	private String contTitulo,contAutor,scontFecha,contEditor,scontNPag=null;
	private Closeable acerrar;
	public ArrayList<Libro> libros = new ArrayList<Libro>();
	
	public int contFecha,contNPag=0;
	//Constructor por argumentos
	public controladorActividad1g(vistaActividad1g vista, Libro libro, Almacen almacen) {
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
			if(scontFecha.matches("\\d{1,2}/\\d{1,2}/\\d{4}")){//Compruebo a fecha
				
				if(scontNPag.matches("[+-]?\\d*(\\.\\d+)?")){	//compruebo el valor del campo si es numero				
					//contFecha= Integer.parseInt(scontFecha);
					 System.out.println("scontFecha"+scontFecha);
					 
					contNPag=Integer.parseInt(scontNPag);
					if(contNPag>=0){//Si pagina es mayor que 0
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
		System.out.println(e.getActionCommand());

		if(e.getActionCommand().equals("Recuperar")){
			//Incluimos el texto recuperado en los JTextFields y le damos la propiedad deshabilitado
			libro=almacen.recuperar(vista.txtNombrefichero.getText());
			vista.txt_autor.setEnabled(false);vista.txt_autor.setText(libro.getAutor());
			vista.txt_titulo.setText(libro.getTitulo());vista.txt_titulo.setEnabled(false);				
			vista.txt_fecha.setText(libro.getFecha());vista.txt_fecha.setEnabled(false);
			vista.txt_editor.setText(libro.getEditor());vista.txt_editor.setEnabled(false);
			vista.txt_paginas.setText(String.valueOf(libro.getnPag()));vista.txt_paginas.setEnabled(false);
				
			JOptionPane.showMessageDialog(vista, "El libro ha sido recuperado");
			almacen.intentarCerrar(acerrar);
		}else if(e.getActionCommand().equals("Guardar listas")){
			//Envia la lista de libros y el nombre del fichero para guadar la lista
			if(!camposVacios()){
				//Recorremos la lista
				Iterator it = libros.iterator();				
				while (it.hasNext()) {
					Libro l =(Libro)it.next();
					Almacen al = new Almacen();
					if(!al.guardar(l, nombre_fichero)){//Si no se ejecuta correctamente
						JOptionPane.showMessageDialog(vista, "No se ha guardado el elemento correctamente");
					}				
				}
				JOptionPane.showMessageDialog(vista, "Se ha guardado en "+nombre_fichero+", "+libros.size()+" libros");
			}
		}else if(e.getActionCommand().equals("Añadir a lista")){
			//Añado elementos a la lista
			if(!camposVacios()){
				if(libros.add(new Libro(contTitulo,contAutor,scontFecha,contEditor,Integer.parseInt(scontNPag)))){
					JOptionPane.showMessageDialog(vista, "El libro ha sido añadido a la lista");
					System.out.println("length"+libros.size());
				}else{
					JOptionPane.showMessageDialog(vista, "El libro NO ha sido añadido a la lista");
				}
			}
		}else if(e.getActionCommand().equals("Modificar")){
			//Habilita los textos para que sean editables
			vista.txt_autor.setEnabled(true);	
			vista.txt_titulo.setEnabled(true);				
			vista.txt_fecha.setEnabled(true);
			vista.txt_editor.setEnabled(true);
			vista.txt_paginas.setEnabled(true);
			if(!camposVacios()){
				System.out.println("Modificar");
				if(almacen.guardar(
								new Libro(contTitulo,contAutor,scontFecha,contEditor,Integer.parseInt(scontNPag))
								, nombre_fichero)){
					JOptionPane.showMessageDialog(vista, "El libro está preparado para ser modificado");
					almacen.intentarCerrar(acerrar);
					}else{
						JOptionPane.showMessageDialog(vista, "El libro NO está preparado para ser modificado");
					}
			}
			

		}else if(e.getActionCommand().equals("Guardar")){
			//Guardamos un unico elemento
			if(!camposVacios()){
				System.out.println("Guardar");
				if(almacen.guardar(new Libro(contTitulo,contAutor,scontFecha,contEditor,Integer.parseInt(scontNPag)), nombre_fichero)){
					JOptionPane.showMessageDialog(vista, "El libro ha sido guardado");
					almacen.intentarCerrar(acerrar);
					}else{
						JOptionPane.showMessageDialog(vista, "El libro NO ha sido guardado");
					}
			}
		
			
		}else {
			JOptionPane.showMessageDialog(vista, "No hay ninguna acción seleccionada");
		}
	}

}

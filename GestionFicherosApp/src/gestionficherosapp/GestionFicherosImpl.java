package gestionficherosapp;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.Format;

import gestionficheros.FormatoVistas;
import gestionficheros.GestionFicheros;
import gestionficheros.GestionFicherosException;
import gestionficheros.GestionFicherosPlus;
import gestionficheros.TipoOrden;
import gestionficheros.contenido.ContenidoFicheroExcepcion;

public class GestionFicherosImpl implements GestionFicheros{
	
	//Declaracion de variables
	private File carpetaWork = null;
	private Object[][] contenido;
	private int filas=0;
	private int columnas =3;
	private TipoOrden ordenado = TipoOrden.DESORDENADO;
	private FormatoVistas formatoVistas = FormatoVistas.NOMBRES;
	
	
	
	public GestionFicherosImpl(){
		carpetaWork = File.listRoots()[0];
		actualiza();
		
	}

	//Funcion para actualizar el contenido de la variable File
	private void actualiza() {
		// TODO Auto-generated method stub
		String[] ficheros= carpetaWork.list();
		filas = ficheros.length/columnas;
		if ( filas * columnas < ficheros.length){
			filas++;
		}
		
		contenido = new String[filas][columnas];
		for (int i = 0; i < columnas; i++) {
			for (int j = 0; j < filas; j++) {
				int index = j*columnas+i;
				if(index<ficheros.length){
					contenido[j][i] = ficheros[index];
				}else{
					contenido[j][i]="";
				}
				
			}
			
		}
	}


	@Override
	public void arriba() {
		// TODO Auto-generated method stub
		
		//Compruebo que la carpeta seleccionada tenga padre
		if(carpetaWork.getParentFile()!=null){
			carpetaWork = carpetaWork.getParentFile();
			actualiza();
		}
		
	}
//&& file.canExecute() && file.canWrite()

	@Override
	public void creaCarpeta(String arg0) throws GestionFicherosException {
		//Creo un objeto file
		File file = new File(carpetaWork,arg0);
		
		//Compruebo los permisos del archivo o si no existe
		if(!file.canRead() || !file.exists()){//si el archivo no es de lectura
			if(!file.canWrite()){//si el archivo no es de escritura
				if(!file.canExecute()){//si el archivo no es de lectura
					
						//File newfile = new File(carpetaWork.getAbsolutePath()+"/"+arg0);
						System.out.println(file);
						
						//Compruebo si es posible crear el archivo
						if(!file.mkdir()){
							throw new GestionFicherosException("Error3, "+arg0+", no se puede escribir");
						}else{
							file.mkdir();//Lo creo
						}
					
						
					
				}else{
					throw new GestionFicherosException("Error2, "+arg0+", no se puede ejecutar");
				}
			}else{
				throw new GestionFicherosException("Error1, "+arg0+", no se puede escribir");
			}
		}else{
			throw new GestionFicherosException("Error0, "+arg0+", existe");
		}
	
		actualiza();
		
	}


	@Override
	public void creaFichero(String arg0) throws GestionFicherosException {
		//Creo un objeto file
				File file = new File(carpetaWork,arg0);
				
				//Compruebo los permisos del archivo o si no existe
				if(!file.canRead() || !file.exists()){//si el archivo no es de lectura
					if(!file.canWrite()){//si el archivo no es de escritura
						if(!file.canExecute()){//si el archivo no es de lectura
					
						//File newfile = new File(carpetaWork.getAbsolutePath()+"/"+arg0);
						System.out.println(file);
						
						try {//Creo un try  catch para que si hay un error me lo myestre y me salte la ejecucion
							file.createNewFile();//Creo el archivo
						} catch (IOException e) {
							// TODO Auto-generated catch block
							throw new GestionFicherosException("Error3, "+arg0+" creando el archivo");
						}
						
					
				}else{
					throw new GestionFicherosException("Error2, "+arg0+", no se puede ejecutar");
				}
			}else{
				throw new GestionFicherosException("Error1, "+arg0+", no se puede escribir");
			}
		}else{
			throw new GestionFicherosException("Error0, "+arg0+", existe");
		}
		actualiza();
	}


	@Override
	public void elimina(String arg0) throws GestionFicherosException {
		//Creo un objeto de tipo file		
		File file = new File(carpetaWork,arg0);
		
		//Compruebo si existe el archivo
		if(file.exists()){
			if(file.delete()){//Compruebo si se ejecuta la accion de eliminar
				file.delete();
			}else{
				throw new GestionFicherosException("Error"+file.getAbsolutePath()+", no se ha eliminado");
			}
		}else{
			throw new GestionFicherosException("Error"+file.getAbsolutePath()+", no existe");
		}
		actualiza();
	}


	//Funcion que entra en la carpeta clickada
	@Override
	public void entraA(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		File file = new File(carpetaWork,arg0);
		
		//Compruebo si es un directorio
		if(!file.isDirectory()){
			throw new GestionFicherosException("Error"+file.getAbsolutePath()+", no es un directorio");
		}
		
		//Comprubeo si se puede leer
		if(!file.canRead()){
			throw new GestionFicherosException("Error"+file.getAbsolutePath()+", no tiene permisos de lectura");
		}
		/*if(!file.canWrite()){
			throw new GestionFicherosException("Error"+file.getAbsolutePath()+", no tiene permisos de escritura");
		}*/
		
		//actualizar la carpeta de trabajo
		carpetaWork = file;
		
		//actualizar el contenido
		actualiza();
	}


	@Override
	public int getColumnas() {
		// TODO Auto-generated method stub
		return columnas;
	}


	@Override
	public Object[][] getContenido() {
		// TODO Auto-generated method stub
		return contenido;
	}


	@Override
	public String getDireccionCarpeta() {
		// TODO Auto-generated method stub
		return carpetaWork.getAbsolutePath();
	}


	@Override
	public String getEspacioDisponibleCarpetaTrabajo() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getEspacioTotalCarpetaTrabajo() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getFilas() {
		// TODO Auto-generated method stub
		return filas;
	}


	@Override
	public FormatoVistas getFormatoContenido() {
		// TODO Auto-generated method stub
		return formatoVistas;
	}


	//Recojo la información de la carpeta activa
	@Override
	public String getInformacion(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		File file = new File(carpetaWork,arg0);
		
		//Compruebo que el archivo exista
		if(file==null){
			throw new GestionFicherosException("Error"+file.getAbsolutePath()+", no existe");
		}
		//Titulo de la ventana
		str.append("Informacion del sistema\n\n");
		str.append("Nombre:"+arg0+"\n");
		
		//Tipo fichero o carpeta
		
		if(!file.isDirectory()){//Compruebo si es directorio
			str.append("Tipo de fichero: archivo\n");
		}else{
			str.append("Tipo de fichero: directorio\n");
		}
			
		//Ubicacion PathAboslute
		str.append("Ubicacion del archivo: "+file.getAbsolutePath()+"\n");
		
		//Fecha ultima modificacion
		DateFormat df = DateFormat.getDateInstance() ;
		
		str.append("Ultima modificación: "+df.format(file.lastModified())+"\n");
		
		//Fichero oculto
		if(!file.isHidden()){//Comoruebo si es visible
			str.append("No es oculto\n");
		}else{
			str.append("Es oculto\n");			
		}
		
		
		//Si es un directorio: espacio libre, disponible o total
		if(file.isDirectory()){//Pregunto la informacion del fihero o directorio
			str.append("Espacio libre: "+file.getFreeSpace()+"\n");
			str.append("Espacio disponible: "+file.getUsableSpace()+"\n");
			str.append("Espacio total: "+file.getTotalSpace()+"\n");
		}
		
		//Convertimos stringBuider a string
		return str.toString();
	}


	@Override
	public boolean getMostrarOcultos() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public String getNombreCarpeta() {
		// TODO Auto-generated method stub
		return carpetaWork.getName();
	}


	@Override
	public TipoOrden getOrdenado() {
		// TODO Auto-generated method stub
		return ordenado;
	}


	@Override
	public String[] getTituloColumnas() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public long getUltimaModificacion(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public String nomRaiz(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int numRaices() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void renombra(String arg0, String arg1) throws GestionFicherosException {
		//Creo el primer file
		File file = new File(carpetaWork, arg0);
		//creo el egundo file para intercambiar los nombre
		File newfile = new File(carpetaWork, arg1);
		
		if(file.exists()){//Compruebo si existe el primer archivo
			if(newfile.exists()){//Compruebo si existe el segundo archivo
				throw new GestionFicherosException("Error"+newfile.getAbsolutePath()+", ya existe");
			}else{
				file.renameTo(newfile);//Renombro
			}
		}else{
			throw new GestionFicherosException("Error"+file.getAbsolutePath()+", no existe, y no hace falta renombrar");
		}
		
		
		actualiza();
	}


	@Override
	public boolean sePuedeEjecutar(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		File file = new File(carpetaWork,arg0);
		boolean w=file.setExecutable(true);
		return w;
	}


	@Override
	public boolean sePuedeEscribir(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		File file = new File(carpetaWork,arg0);
		boolean w=file.setWritable(true);
		return w;
	}


	@Override
	public boolean sePuedeLeer(String arg0) throws GestionFicherosException {
		File file = new File(carpetaWork,arg0);
		boolean w=file.setReadable(true);
		return w;
	}


	@Override
	public void setColumnas(int arg0) {
		// TODO Auto-generated method stub
		columnas= arg0;
	}


	//Funcion que muestra la ruta completa
	@Override
	public void setDirCarpeta(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		File file = new File(arg0);
		if(!file.isDirectory()){
			throw new GestionFicherosException("Error"+file.getAbsolutePath()+", no es un directorio");
		}
		
		if(!file.canRead()){
			throw new GestionFicherosException("Error"+file.getAbsolutePath()+", no tiene permisos de lectura");
		}
		/*if(!file.canWrite()){
			throw new GestionFicherosException("Error"+file.getAbsolutePath()+", no tiene permisos de escritura");
		}*/
		
		//actualizar la carpeta de trabajo
		carpetaWork = file;
		
		//actualizar el contenido
		actualiza();
	}


	@Override
	public void setFormatoContenido(FormatoVistas arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setMostrarOcultos(boolean arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setOrdenado(TipoOrden arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setSePuedeEjecutar(String arg0, boolean arg1) throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setSePuedeEscribir(String arg0, boolean arg1) throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setSePuedeLeer(String arg0, boolean arg1) throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setUltimaModificacion(String arg0, long arg1) throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}
	
}

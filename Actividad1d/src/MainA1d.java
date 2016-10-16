import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Iterator;


public class MainA1d {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		boolean archivosiguales=false;
		File f1 = new File("/Users/js_cmd/EclipseWorkspace/x.txt");
		File f2 = new File("/Users/js_cmd/EclipseWorkspace/y.txt");
		
		if(!f1.exists() || !f2.exists()){
			System.out.println("Alguno de los archivos no existe");
		}else{
			ordenarFichero(f1, f2, 1);
		}
	

	}
		private static void ordenarFichero (File origen, File destino, int tipo_orden){
		
			try {
		           //Abre buffer para leer del fichero origen
		           BufferedReader f1= new BufferedReader(new FileReader(origen));
		           //Abre buffer para escribir del fichero destino
		           PrintWriter f2= new PrintWriter(new FileWriter(destino));  
		           
		           String valorf1="",valorf2="",cadena="";
		       	LinkedList<String> lista = new LinkedList<String>();
		          

		           //Lectura de lineas hasta final del archivo
		           while((valorf1=f1.readLine())!=null ){
		               lista.add(valorf1);
		               
		           }
		           
		           //Ordenamos la lista cargada en funcion del 3er paametro de la funcion
		           System.out.println(lista);
		           if(tipo_orden==1){
		        	   Collections.sort(lista);
					}else if(tipo_orden==2){
						Collections.sort(lista,Collections.reverseOrder());
					}else{
						System.out.println("Ese metodo de ordenacion no existe");
					}
		           
		       
				 Iterator palabras = lista.iterator();
		          
		          //Recorremos la lista
		           while(palabras.hasNext())
		           {
		        	   cadena= (String)palabras.next();
		        	   //Escribimos en el archivo de salida
		        	   f2.println(cadena);
		        	   System.out.println(cadena);
		           }
		           //Cerramos archivos
		           f1.close();f2.close();
			} catch (Exception ex) {
			      //Imprime error, si lo hay
			        System.out.println(ex.getMessage());
			   }  
		}

}

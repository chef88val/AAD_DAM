import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Ejercicio {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File dir1= new File("/Users/js_cmd/");
		File dir2= new File("/Users/js_cmd/Documents");
		if(dir1.canRead() && dir2.canRead()){
			try {
				compararContenido(dir1, dir2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}File[] fileOrigen = dir1.listFiles();
			
			/*File[] fileOrigen = dir1.listFiles();
			   File[] fileDestino = dir2.listFiles();
			   try {
				compararContenido(fileOrigen, fileDestino);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}else{System.out.println("Los directorios no se pueden leer");}
		
		
		
	}
	
	private static void compararContenido (File fichero1, File fichero2) throws IOException{
		boolean res= false;
		int cont = 0;
		ArrayList<String> lista1 = new ArrayList<String>();
		ArrayList<String> lista2 = new ArrayList<String>();
		Iterator it1;
		 Iterator it2;
		           String valorf1="",valorf2="";
		          
		          String[] ficheros1 = fichero1.list();
		           String[] ficheros2 = fichero2.list();
		           if(ficheros1.length==ficheros2.length)
		           {
		        	   System.out.print("Los directorios tienen la misma cantidad de ficheros"+
		        			   ficheros1.length+"\n");
		        	   
		           }else{
		        	   int dif=ficheros2.length-ficheros1.length;
		        	   System.out.print("Los directorios NO tienen la misma cantidad de ficheros,"
		        			   + "Fichero1:"+ficheros1.length
		        			   + ", Fichero2:"+ficheros2.length+", y la diferencia es: "+dif+"\n" );
			           
		           }
		           /*for (int x=0;x<ficheros1.length;x++){
		        	   lista1.add(ficheros1[x]);
		        	   //System.out.print("ficheros1"+ficheros1[x]+"\n");
		        	  
		        	   for (int y=0;y<ficheros2.length;y++){
		        		   lista2.add(ficheros2[y]);
		        		   //System.out.print("ficheros2"+ficheros2[y]+"\n");
		        		   //System.out.print(ficheros2[x]+"\n");
			        	  
		        		   if(ficheros1[x].equals(ficheros2[y])){
		        			   //System.out.println(fichero1.getAbsolutePath());
		        			   //System.out.println(fichero2.getAbsolutePath());
		        			   System.out.print(ficheros1[x]+" --> aparece en ambos directorios\n");
		        			   cont++;
		        		   }
		        	   }
		           }*/
		          
	
		}
}

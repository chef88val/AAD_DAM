import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class Ejercicio2 {

	public static void main(String[] args) {
		
		File f = new File("fichero_prefijos.txt");		
		
		prefijos("w",f);

	}

	private static void prefijos(String string, File f) {
		// TODO Auto-generated method stub
		int res=0;
		
		try{
			//Abre buffer del fichero1
	           BufferedReader f1= new BufferedReader(new FileReader(f));
	          
	           String valorf1="";
	          
	           //Lectura de archivos hasta final de linea
	           while(valorf1!=null ){
	        	   
	               valorf1=f1.readLine();
	          
		             
		            	   //if(valorf1.indexOf(string.toLowerCase())!= -1){//Busco solo una aparicion
	               if(valorf1.startsWith(string.toLowerCase(), 0)){//Busco solo una aparicion
		   						System.out.println("Aparece la palabra "+string+", una vez en la palabra "+valorf1);
		   						res +=1;
		   					}
		                 
		            	}
	           
	           
	        //Cerramos archivos
	           f1.close();
	          
	         
		}catch (Exception ex) {
		      //Imprime error, si lo hay
		        System.out.println(ex.getMessage());
			
		}
		System.out.println("Aparece la palabra "+string+" "+res+" veces");
		
	}

}

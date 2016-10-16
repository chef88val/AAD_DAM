import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MainA1c {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		boolean archivosiguales=false;
		File f1 = new File("/Users/js_cmd/Downloads/x.txt");
		File f2 = new File("/Users/js_cmd/Downloads/y.txt");
		
		if(!f1.exists() || !f2.exists()){
			System.out.println("Alguno de los archivos no existe");
		}else{
			archivosiguales=compararContenido(f1, f2);
			buscarPalabra(f2, "mundo", false);
		}
		if(archivosiguales){
			System.out.println("Los archivos son iguales");
		}else{
			System.out.println("Los archivos son diferentes");
		}
		
		

	}
		private static boolean compararContenido (File fichero1, File fichero2) throws IOException{
			boolean res= false;
			try {
			           //Abre buffer del fichero1
			           BufferedReader f1= new BufferedReader(new FileReader(fichero1));
			           //Abre buffer del fichero2
			           BufferedReader f2= new BufferedReader(new FileReader(fichero2));  
			           
			           String valorf1="",valorf2="";
			           
			          

			           //Lectura de archivos hasta final de linea
			           while(valorf1!=null || valorf2!=null){
			               valorf1=f1.readLine();
			               valorf2=f2.readLine();
			                
			                   //Si no son iguales las lineas
			                     if(!valorf1.trim().toUpperCase().equals(valorf2.trim().toUpperCase())){
			                    	 System.out.println("Archivos diferentes");
			                    	 return res=false;
			               }else{
			            	   System.out.println("Archivos iguales");
			            	   return res=true;
			               }
			                    
			               
			           }
			        //Cerramos archivos
			           f1.close();
			           f2.close();
			         
			   } catch (Exception ex) {
			      //Imprime error, si lo hay
			        System.out.println(ex.getMessage());
			   }
				return res;
			 }
		
		private static int buscarPalabra (File fichero1, String palabra, boolean primera_aparicion)throws IOException{
			int res=0;
			
			try{
				//Abre buffer del fichero1
		           BufferedReader f1= new BufferedReader(new FileReader(fichero1));
		          
		           String valorf1="";
		          
		           //Lectura de archivos hasta final de linea
		           while(valorf1!=null ){
		        	   
		               valorf1=f1.readLine();//.toLowerCase();
		          
			             if(primera_aparicion){//Comprubeo si es true el tercer parametro
			            	   if(valorf1.indexOf(palabra.toLowerCase())!= -1){//Busco solo una aparicion
			   						System.out.println("Aparece la palabra "+palabra+", una vez");
			   						res =1;
			   					}else{
			   						System.out.println("No aparece la palabra: "+palabra);
			   						res =0;
			   					}
			                  
			             }else{		            	 	
			               while(valorf1.indexOf(palabra)> -1){//Busco mas de una aparicion con While
			            	   valorf1= valorf1.substring(valorf1.indexOf(palabra)+palabra.length(), valorf1.length());
			            	   res++;
			            	   
			               }
			               
			             }
			             
			             //System.out.println("Aparece la palabra "+palabra+", "+res+" en la linea "+contador);
		           }
		           
		        //Cerramos archivos
		           f1.close();
		          
		         
			}catch (Exception ex) {
			      //Imprime error, si lo hay
			        System.out.println(ex.getMessage());
				
			}
			System.out.println("Aparece la palabra "+palabra+" "+res+" veces");
			return res;
		}
}

package biblioteca_libros;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Attr;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Almacen {

	private Document dom = null;
	private ArrayList<Libro> libros = null;

	private Element rootElement = null;
	private Element item;
	private Attr atributo;
	
	
	//Constructor sin argumentos
	public Almacen() {
		libros = new ArrayList<Libro>();//Inicializo el arrayList de tipo Libro
		 
	}

	//Funcion para crear el fichero XML
	public void createXMLFichero(String nombrefichero, ArrayList<Libro> lib){
        Libro l =null;
		Iterator it = lib.iterator();
		try{
			//Instrucciones para crear un xml
			 DocumentBuilderFactory dbFactory =DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder =dbFactory.newDocumentBuilder();
	         Document dom = dBuilder.newDocument();
	         // biblioteca element(root)
	         Element rootElement = dom.createElement("biblioteca");
	         dom.appendChild(rootElement);
	
		while (it.hasNext()) {//Recorro la lista para recoger el valor para cada etiqueta
			 l =(Libro)it.next();
	         //  libro element
	         Element libro = dom.createElement("libro");
	         rootElement.appendChild(libro);
	
	      // titulo element
	         Element titulo = dom.createElement("titulo");
	         Attr tituloAttr = dom.createAttribute("anyo");
	         //Atributo de fecha
	         tituloAttr.setValue(l.getFecha());
	         titulo.setAttributeNode(tituloAttr);
	         titulo.appendChild(dom.createTextNode(l.getTitulo()));
	         libro.appendChild(titulo);
	         
	         
	         //Metodo opcional para crear tantas etiquetas nombre como espacios haya en el texto
	        /* String x= l.getAutor();
	         String[] partes=x.split(" ");
	         
	         String n0=partes[0];
	         Element nombre = dom.createElement("nombre");
	         nombre.appendChild(dom.createTextNode(n0));
	         titulo.appendChild(nombre);
	         String n1=partes[1];
	         Element nombre1 = dom.createElement("nombre");
	         nombre1.appendChild(dom.createTextNode(n1));
	         titulo.appendChild(nombre1);
	         String n2=partes[2];
	         Element nombre2 = dom.createElement("nombre");
	         nombre2.appendChild(dom.createTextNode(n2));
	         titulo.appendChild(nombre2);
	         System.out.println(n0+n1+n2);*/
	         
	         
	         
	      // autor element
	         Element autor = dom.createElement("autor");
	         /*Attr autorAttr = dom.createAttribute("fecha");
	         autorAttr.setValue(l.getFecha());
	         autor.setAttributeNode(autorAttr);*/
	         autor.appendChild(dom.createTextNode(l.getAutor()));
	         libro.appendChild(autor);
	         
	      // editor element
	         Element editor = dom.createElement("editor");
	         /*Attr editorAttr = dom.createAttribute("fecha");
	         editorAttr.setValue(l.getFecha());
	         editor.setAttributeNode(editorAttr);*/
	         editor.appendChild(dom.createTextNode(l.getEditor()));
	         libro.appendChild(editor);
	         
	      // nPag element
	         Element nPag = dom.createElement("paginas");
	         /*Attr nPagAttr = dom.createAttribute("fecha");
	         nPagAttr.setValue(l.getFecha());
	         nPag.setAttributeNode(nPagAttr);*/
	         nPag.appendChild(dom.createTextNode(String.valueOf(l.getnPag())));
	         libro.appendChild(nPag);
		}
	
	         // Escribo el contenido anterior en un xml file
	         TransformerFactory transformerFactory =TransformerFactory.newInstance();
	         Transformer transformer =transformerFactory.newTransformer();
	         
	         //Formateo el xml 
	         transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	         transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	         DOMSource source = new DOMSource(dom);
	         StreamResult result =new StreamResult(new File(nombrefichero));
	         transformer.transform(source, result);
	         // Salida por consola para testear el resultado
	         StreamResult consoleResult =new StreamResult(System.out);
	         transformer.transform(source, consoleResult);
			         
		}catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	
	public void parseFicheroXml(String fichero) {
		// creamos una factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			// creamos un documentbuilder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// parseamos el XML y obtenemos una representaci�n DOM
			dom = db.parse(fichero);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	public void parseDocument(int tipo) {
		// obtenemos el elemento ra�z
		Element docEle = dom.getDocumentElement();

		// obtenemos el nodelist de elementos
		NodeList nl = docEle.getElementsByTagName("libro");
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {

				// obtenemos un elemento de la lista (libro)
				Element el = (Element) nl.item(i);

				// obtenemos un libro
				Libro p = getLibro(el,tipo);

				// lo a�adimos al array
				libros.add(p);
			}
		}
	}
	//Funcion para recoger valores de un libro e insertar en la vista
	public Libro parseDocumentLibro(int tipo) {
		// obtenemos el elemento ra�z
		Element docEle = dom.getDocumentElement();

		Libro p=null;
		// obtenemos el nodelist de elementos
		NodeList nl = docEle.getElementsByTagName("libro");
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {

				// obtenemos un elemento de la lista (libro)
				Element el = (Element) nl.item(i);

				// obtenemos un libro
				 p = getLibro(el,tipo);

				// lo a�adimos al array
				libros.add(p);			
			}
		}
		return p;		
	}
	
	public Libro getLibro(Element libroX, int tipo){
		String autor="";
		//para cada elemento persona, obtenemos su nombre y su edad
		String nombre = getTextValue(libroX,"titulo");
		String fecha= getTextAttribute(libroX, "titulo","anyo");
		if(tipo==1){//Seleccionamos el tipo de estructura de la etiqueta autor
			 autor = getMultipleTextValue(libroX,"nombre");
		}else{
			 autor = getTextValue(libroX,"autor");
		}
		String editor = getTextValue(libroX,"editor");
		int paginas = getIntValue(libroX,"paginas");
		
		//Creamos una nueva persona con los elementos le�dos del nodo
		Libro e = new Libro(nombre,autor, fecha, editor, paginas);

		return e;		
		
	}
	
	//Obtenemos el valor del atributo seleccionada
	private String getTextAttribute(Element ele, String tagName,String tagAttribute){
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		for (int i = 0; i < nl.getLength(); i++)
		{
		 Node node = nl.item(i);		
		 if (node.getNodeType() == Node.ELEMENT_NODE)
		 {		    
		    Element eElement = (Element) node;
		    textVal=eElement.getAttribute(tagAttribute);
		    }
		}
			
		return textVal;
	}
	
	//Obtenemos el valor de la etiqueta seleccionada
	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}		
		return textVal;
	}
	//Obtenemos el valor de la etiqueta seleccionada de tipo integer
	private int getIntValue(Element ele, String tagName) {				
		return Integer.parseInt(getTextValue(ele,tagName));
	}
	//Obtenemos el valor de la etiqueta seleccionada si es autor->nombre
	private String getMultipleTextValue(Element ele, String tagName){
		String textVal="";
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {
				Element el = (Element)nl.item(i);
				textVal += el.getFirstChild().getNodeValue();
				textVal +=" ";
			}
		}
		return textVal;
	}
	
	//Mostrar por pantalla
	public void print(){

		Iterator it = libros.iterator();
		while(it.hasNext()) {
			Libro p=(Libro) it.next();
			p.print();
		}
	}
	
	

}



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
	private ArrayList<Disco> Discos = null;

	private Element rootElement = null;
	private Element item;
	private Attr atributo;
	
	
	//Constructor sin argumentos
	public Almacen() {
		Discos = new ArrayList<Disco>();//Inicializo el arrayList de tipo Disco
		 
	}

	//Funcion para crear el fichero XML
	public void createXMLFichero(String nombrefichero, ArrayList<Disco> lib){
        Disco l =null;
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
			 l =(Disco)it.next();
	         //  Disco element
	         Element Disco = dom.createElement("Disco");
	         rootElement.appendChild(Disco);
	
	      // titulo element
	         Element titulo = dom.createElement("titulo");
	        
	        
	         titulo.appendChild(dom.createTextNode(l.getTitulo()));
	         Disco.appendChild(titulo);
	         
	        
	         
	         // autor element
	         Element autor = dom.createElement("grupo");
	         /*Attr autorAttr = dom.createAttribute("fecha");
	         autorAttr.setValue(l.getFecha());
	         autor.setAttributeNode(autorAttr);*/
	         autor.appendChild(dom.createTextNode(l.getGrupo()));
	         Disco.appendChild(autor);
	         
	         // nacionalidad element
	         Element nacionalidad = dom.createElement("nacionalidad");
	         /*Attr autorAttr = dom.createAttribute("fecha");
	         autorAttr.setValue(l.getFecha());
	         autor.setAttributeNode(autorAttr);*/
	         autor.appendChild(dom.createTextNode(l.getNacionalidad()));
	         Disco.appendChild(nacionalidad);
	         
	      // editor element
	         Element editor = dom.createElement("lider");
	         /*Attr editorAttr = dom.createAttribute("fecha");
	         editorAttr.setValue(l.getFecha());
	         editor.setAttributeNode(editorAttr);*/
	         editor.appendChild(dom.createTextNode(l.getLider()));
	         Disco.appendChild(editor);
	         
	      // nPag element
	         Element nPag = dom.createElement("anyo");
	         /*Attr nPagAttr = dom.createAttribute("fecha");
	         nPagAttr.setValue(l.getFecha());
	         nPag.setAttributeNode(nPagAttr);*/
	         nPag.appendChild(dom.createTextNode(String.valueOf(l.getAnyo())));
	         Disco.appendChild(nPag);
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
		NodeList nl = docEle.getElementsByTagName("disco");
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {

				// obtenemos un elemento de la lista (Disco)
				Element el = (Element) nl.item(i);

				// obtenemos un Disco
				Disco p = getDisco(el,tipo);

				// lo a�adimos al array
				Discos.add(p);
			}
		}
	}
	//Funcion para recoger valores de un Disco e insertar en la vista
	public Disco parseDocumentDisco(int tipo) {
		// obtenemos el elemento ra�z
		Element docEle = dom.getDocumentElement();

		Disco p=null;
		// obtenemos el nodelist de elementos
		NodeList nl = docEle.getElementsByTagName("Disco");
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {

				// obtenemos un elemento de la lista (Disco)
				Element el = (Element) nl.item(i);

				// obtenemos un Disco
				 p = getDisco(el,tipo);

				// lo a�adimos al array
				Discos.add(p);			
			}
		}
		return p;		
	}
	
	public Disco getDisco(Element DiscoX, int tipo){
		String autor="";
		//para cada elemento persona, obtenemos su nombre y su edad
		String titulo = getTextValue(DiscoX,"titulo");
		String nacionalidad= getTextValue(DiscoX, "nacionalidad");
		String grupo= getTextValue(DiscoX, "grupo");
		if(tipo==1){//Seleccionamos el tipo de estructura de la etiqueta autor
			 autor = getMultipleTextValue(DiscoX,"nombre");
		}else{
			 autor = getTextValue(DiscoX,"autor");
		}
		String lider = getTextValue(DiscoX,"lider");
		int anyo = getIntValue(DiscoX,"anyo");
		
		//Creamos una nueva persona con los elementos le�dos del nodo
		Disco e = new Disco(titulo,nacionalidad, lider, grupo, anyo);

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

		Iterator it = Discos.iterator();
		while(it.hasNext()) {
			Disco p=(Disco) it.next();
			p.print();
		}
	}
	
	

}

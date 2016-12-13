import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.Document;


import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Actividad2c {
static int i=1;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		org.jsoup.nodes.Document docHTML =null;
		
		try {
			
			docHTML = Jsoup.connect("https://www.pccomponentes.com").userAgent("Chrome")//.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
				     .get();
			String titulo = docHTML.title();
			String listo = docHTML.getElementsByClass("!rticulo__extras").toString();
			System.out.println(listo);
			Elements lista= docHTML.getElementById("featured-layer").select(" h3 > a[itemprop='url']");
			//String lista= docHTML.getElementsByAttributeValue("itemprop", "url").toString();
			//.getElementsByClass("tarjeta-articulo__elementos-basicos div");
			//lista=lista.select("a[itemprop='url']");
			
			for(Element item: lista){
				System.out.println(i+":"+item.attr("data-name") + " : " + item.attr("data-price")+"€");
				i++;
			}
			
			//System.out.println(lista);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

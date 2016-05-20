package JsonToXml;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlParser {
	
	protected String First=null;
	protected String Last=null;
	protected int i=0;
	
	public String getFirst(){
		return this.First;
	}
	public String getLast(){
		return this.Last;
	}


	public void Parser(String Path) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc =  builder.parse(Path);
			Element raiz=doc.getDocumentElement();
			NodeList json = raiz.getElementsByTagName("x");
			while(this.i<json.getLength()){
				Node nodo=json.item(i);
				if (i==0){
					if(nodo.getNodeType()==Node.ELEMENT_NODE){
						this.First=nodo.getTextContent();
					}
				}
				else if(i==1){
					if(nodo.getNodeType()==Node.ELEMENT_NODE){
						this.Last=nodo.getTextContent();
					}
				}
				i++;
			}
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

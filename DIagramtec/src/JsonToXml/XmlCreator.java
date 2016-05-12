package JsonToXml;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

public class XmlCreator {
	
	private String var1=null;
	private String var2=null;
	
	public void setVar1(String var){
		this.var1=var;		
	}
	
	public void setVar2(String var){
		this.var2=var;		
	}
	
	public void xml() {

		 
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                DOMImplementation implementation = builder.getDOMImplementation();
                Document document = implementation.createDocument(null, "name", null);                
                Element raiz = document.createElement("JsonContent");
                Element First = document.createElement("x");
                Text text = document.createTextNode(this.var1);
                Element Last = document.createElement("x");
                Text text2 = document.createTextNode(this.var2);
               ////////////////////////////////////////////////////
                document.setXmlVersion("1.0");
                document.getDocumentElement().appendChild(raiz);

                raiz.appendChild(First);
                First.appendChild(text);
                raiz.appendChild(Last);
                Last.appendChild(text2);
 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////          
     
                Source source = new DOMSource(document);
                Result result = new StreamResult(new java.io.File("/home/swampy-cv/Documentos/new/json.xml"));
     
                Result console = new StreamResult(System.out);
     
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
     
                transformer.transform(source, result);
                transformer.transform(source, console);
     
            

} catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }

}

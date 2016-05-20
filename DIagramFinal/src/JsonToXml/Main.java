package JsonToXml;

//import org.codehaus.jackson.map.ObjectMapper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.net.URL;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		String jsonStr ="{\"name\":{\"first\":\"Juan\",\"last\":\"Perez\"}}";//Es mejor crearlo desde java porque asi se puede setear algo***
		User user=null;
		ObjectMapper mapper= new ObjectMapper();
		user=mapper.readValue(jsonStr,User.class);
		//System.out.println(user.getName().getFirst());
		//System.out.println(user.getName().getLast());
		XmlCreator xml=new XmlCreator();
		xml.setVar1(user.getName().getFirst());
		xml.setVar2(user.getName().getLast());
		xml.xml();
		System.out.println("Success");
		XmlParser xparser= new XmlParser();
		xparser.Parser("/home/swampy-cv/Documentos/new/json.xml");
		System.out.println(xparser.getFirst());
		System.out.println(xparser.getLast());


	}

}

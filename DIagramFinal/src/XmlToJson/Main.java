package XmlToJson;

//import org.codehaus.jackson.map.ObjectMapper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.net.URL;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		XmlParser xparser= new XmlParser();
		xparser.Parser("/home/swampy-cv/Documentos/new/json.xml");
		String jsonStr ="{\"name\":{\"first\":\"null\",\"last\":\"null\"}}";//Es mejor crearlo desde java porque asi se puede setear algo***
		User user=null;
		ObjectMapper mapper= new ObjectMapper();
		user=mapper.readValue(jsonStr,User.class);
		user.getName().setFirst("Eduardo");
		user.getName().setLast("Mata");
		System.out.println(user.getName().getFirst());
		System.out.println(user.getName().getLast());
	}

}

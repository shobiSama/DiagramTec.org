package DataTrees;

import java.util.Enumeration;
import java.util.Hashtable;

public class tablehash {

	public static void main(String[] args) {
		Hashtable<String,String> contenedor=new Hashtable<String,String>();
		contenedor.put("101", "gabo");
		contenedor.put("102", "vindas");
		contenedor.put("103", "allan");
		contenedor.put("104", "ribera");
		contenedor.put("105", "locas");
		System.out.println(contenedor.get("105"));
		System.out.println(contenedor.get("101"));
		Enumeration<String> enumeration = contenedor.elements();
		
		while (enumeration.hasMoreElements()) {
		  System.out.println(""+"hashtable valores: " + enumeration.nextElement());
		}
		Enumeration<String> llaves = contenedor.keys();
		while (llaves.hasMoreElements()) {
		  System.out.println(""+"hashtable llaves: " + llaves.nextElement());
		}
		System.out.println("Claves: " +contenedor.keys());
	}
	
	
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stuff;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.io.FileReader;
import java.io.IOException;



public class JsonReader {
    
    int K = 0;
    
    
    //Constructor por si se quiere leer un json desde un archivo guardado en disco
    public JsonReader(String Archivo,String Pack){
        try{
            JsonParser parser = new JsonParser();
            FileReader fr = new FileReader("C:\\Users\\Pablo\\Documents\\U\\Semestre II\\Datos I\\Proyectos\\FreeEx\\src\\Jsons\\"+Pack+"\\"+Archivo+".json");
            JsonElement datos = parser.parse(fr);
            dumpJSONElement(datos);
            } catch (IOException e) {
                    System.out.println("Error en la lectura del archivo" + e.getMessage());
            }

    }
     public JsonReader(){};
    
    public Object[] GetArray(String Json){
        JsonParser parser = new JsonParser();
        JsonElement datos = parser.parse(Json);
        return dumpJSONElement(datos);
    }

    
    public Object[] dumpJSONElement(JsonElement elemento) {
        JsonArray array = elemento.getAsJsonArray();
        //System.out.println("Es array. Numero de elementos: " + array.size());
        
        Object[] Arreglo = new Object[array.size()];
        int ind = 0;
        
        java.util.Iterator<JsonElement> iter = array.iterator();
        while (iter.hasNext()) {
            JsonElement entrada = iter.next();
            Arreglo[ind] = getValue(entrada);
            ind++;
        }
        
        if (K!=0){
            K--;
            return Arreglo;
        }else{
            return Arreglo;
        }
    }
        
    Object getValue(JsonElement elemento) { 
        if (elemento.isJsonArray()){ 
            K++;
            return dumpJSONElement(elemento); }
        else{
        JsonPrimitive valor = elemento.getAsJsonPrimitive();
        if (valor.isBoolean()) {
            //System.out.println(  valor.getAsBoolean());
            return valor.getAsBoolean();
        } else if (valor.isNumber()) {
            //System.out.println(valor.getAsNumber());
            return valor.getAsNumber();
        } else{
            //System.out.println(  valor.getAsString());
            return valor.getAsString();
        }
        }
    }
}
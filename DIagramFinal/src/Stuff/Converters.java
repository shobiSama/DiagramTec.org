
package Stuff;

import com.google.gson.Gson;


public class Converters {
    
    public Lista MakeList(Object[] arreglo){
        Lista lista = new Lista(arreglo[0]);
        for (int i =1;i<arreglo.length;i++){
            lista.Agregar(arreglo[i]);
        }
    return lista;
    }
    
    public String MakeJson(Object Data){
        Gson gson = new Gson();
        String jsonString = gson.toJson(Data);
        return jsonString;
    }
    
   
    public Object[] MakeArray(Lista lista){
        Object[] ArregloNuevo = new Object[lista.getLenght()+1]; 
        NodoSimple Actual = lista.getInicio();
        
        for (int i = 0;Actual.getSiguiente()!=null;i++){
            ArregloNuevo[i] = Actual.getDato();
            Actual = Actual.getSiguiente();
        }
        //adfad
        ArregloNuevo[lista.getLenght()] = Actual.getDato();
        return ArregloNuevo;
    }
    
    
    
}

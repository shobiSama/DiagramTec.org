
package Stuff;


public class NodoSimple {
    
    /* Atributos:
                Un atributo Dato para guardar información en la variable.
                Atributo Siguiente que será la referencia a un nobo que se encuentre después de este.    
                Atributo Anterior para referenciar al nodo que se encuentra antes de este.
    */
    private Object Dato;
    private NodoSimple Siguiente;
    private NodoSimple Anterior;
    
    /* Constructor que recibe el Dato que se desea guardar en el Nodo
    */
    public NodoSimple(Object Dato){ 
        this.Dato = Dato;
    }
    
    /* getSiguiente: Devuelve el nodo Siguiente
    */
    public NodoSimple getSiguiente(){ 
        return this.Siguiente;} 
    
    /* setSiguiente: Recibe como paramentro un objeto tipo Nodo.
    Crea la referencia para un nodo que se encuentra después.
    */
    public void setSiguiente(NodoSimple New){ 
        this.Siguiente = New;     
    }
    
    /* getAnterior: Devuelve el nodo Anterior
    */
    NodoSimple getAnteior(){
        return this.Anterior;
    }
    
    /* setAnterior: Recibe como paramentro un objeto tipo Nodo. 
    Crea la referencia para el nodo que se encuentra antes. 
    */
    void setAnterior(NodoSimple New){
        this.Anterior = New; 
    }
    
    /* getDato: Retorna el Dato que se guardo inicialmente en el Nodo 
    */
    public Object getDato(){
        return this.Dato;
    } 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stuff;

public class Lista {
    //Atributo
    private NodoSimple Inicio;
    private int lenght;
    int[] items;
    double[] segs;
    int[] array;
        
    //Constructor
    public Lista(Object dato){
        NodoSimple New = new NodoSimple(dato);
        this.Inicio = New;
        lenght = 0;
    }
    
    public Lista(){
        this.Inicio = null;
        lenght = -1;
    }
    
    public void setInicio(NodoSimple New){ this.Inicio = New;}
    public NodoSimple getInicio(){ return this.Inicio;} //Devuelve el Nodo de la primera posición

    public int getLenght(){
        return this.lenght;
    }
    
    public void AgregarInicio(Object dato){
        NodoSimple Temp = new NodoSimple(dato);
        if (getInicio() == null){setInicio(Temp);}        
        else{
            Temp.setSiguiente(getInicio());
            setInicio(Temp);
            lenght++;
        }
    }
    
    
    public void Agregar(Object dato){
        int n=0;
        items = new int[10000];
        segs = new double[10000];
        double tiempo1 = System.nanoTime();
        double tiempo2;
        if (getInicio()==null){
            n++;
            tiempo2 = System.nanoTime();
            AgregarInicio(dato);
            items[n-1] = n;
            segs[n-1] = tiempo2 - tiempo1;
            //System.out.print(items[n-1]);
        }
        else{            
            NodoSimple Temp = new NodoSimple(dato);
            NodoSimple Actual = getInicio();
            
            while (Actual.getSiguiente()!=null){ //Mientras que el "puntero" no sea null ----> Busca la última posición
                Actual = Actual.getSiguiente();
                tiempo2 = System.nanoTime();
                n++;
                items[n-1] = n;
                segs[n-1] = tiempo2 - tiempo1;
                //System.out.print(items[n-1]);
            }
        
            Actual.setSiguiente(Temp);
            tiempo2 = System.nanoTime();
            n++;
            items[n-1] = n;
            segs[n-1] = tiempo2 - tiempo1;
            lenght++;
            //System.out.print(items[n-1]);
        }
    } 
    
    public void AgregarPos(Object dato,int Pos){ //Pos = Posición
        
       if (Pos>lenght){ 
           System.out.println("La posición indicada es inválida");
           return;
       }
       
       else if(Pos==0){
           AgregarInicio(dato);
           return;
       }
        
        NodoSimple Temp = new NodoSimple(dato);
        NodoSimple Actual = getInicio();
        
        for(int i=0; i<Pos-1; i++){ //Busca la posición que anterior a la ingresada por el usuario
            Actual = Actual.getSiguiente();
        }
        
        Temp.setSiguiente(Actual.getSiguiente());
        Actual.setSiguiente(Temp);
        lenght++;
        
        
    }
    
    public boolean Contiene(Object dato){
        int n=0;
        double tiempo1 = System.nanoTime();
        double tiempo2;
        NodoSimple Actual = getInicio();
        
        while (Actual.getDato() != dato) {
            if (Actual.getSiguiente() == null) {
                tiempo2 = System.nanoTime();
                n++;
                items[n-1] = n;
                segs[n-1] = tiempo2 - tiempo1;
                System.out.println("El dato no se encuentra en la lista");
                return false;
            }
            Actual = Actual.getSiguiente();
            tiempo2 = System.nanoTime();
            n++;
            items[n-1] = n;
            segs[n-1] = tiempo2 - tiempo1;
        }

        System.out.println("El dato sí se encuentra en la lista");
        return true;
    }
    
    
    public void Eliminar(Object dato){
        int n =0;
        double tiempo1 = System.nanoTime();
        double tiempo2;
        items = new int[10000];
        segs = new double[10000];
        NodoSimple Actual = getInicio();
        
        if (Contiene(dato)){
            if (Actual.getDato()==dato) { // Si el dato que se quiere eliminar es el inicio de la lista
            setInicio(Actual.getSiguiente());
            tiempo2 = System.nanoTime();
            n++;
            items[n-1] = n;
            segs[n-1] = tiempo2 - tiempo1;           
            lenght--;
            }else{
                while (Actual.getSiguiente().getDato() != dato) {
                    Actual = Actual.getSiguiente();
                    tiempo2 = System.nanoTime();
                    n++;
                    items[n-1] = n;
                    segs[n-1] = tiempo2 - tiempo1;
                } 
            
                Actual.setSiguiente(Actual.getSiguiente().getSiguiente());
                tiempo2 = System.nanoTime();
                n++;
                items[n-1] = n;
                segs[n-1] = tiempo2 - tiempo1;
                lenght--;
            }  
        }
        
        
    }
    
    public void Imprimir(){
        NodoSimple Actual = getInicio();
        
        while (Actual.getSiguiente()!=null){
            System.out.println(Actual.getDato());
            Actual = Actual.getSiguiente();
        }
        System.out.println(Actual.getDato());
    }
    
    
    public int[] obtenerArray(){
        array = new int[10000];
        int[] arrayfinal;
        int k = 0;
        NodoSimple Actual = getInicio();
        int p = 0;
        while(Actual.getSiguiente() != null){
            array[p] = Integer.parseInt(String.valueOf(Actual.getDato()));
            Actual = Actual.getSiguiente();
            p++;
        }
        array[p]= Integer.parseInt(String.valueOf(Actual.getDato()));
        arrayfinal = new int[p+1];
        while(k<=p){
            arrayfinal[k] = array[k];
            k++;
        }
        return arrayfinal;
        
    }
}

package gui.facade;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import gui.window.*;

import gui.Circuit.Components.guiComponente;
import gui.window.Home;
import gui.window.textfield;
import logic.Circuit.Circuit.Circuit;
import logic.Circuit.Components.*;
import logic.lista.List;
import logic.lista.Node;

public class Facade {
	public static Circuit circuito;
	ArrayList<String> array;
	int size;
	int nn;
	int mm;
	
	
	//IComponents nuevaCompuertaa;
	
	public Facade(){
	  
		
		circuito = new Circuit();
	}
	public void entradas(String a, String b){
		String entrada1=a;
		String entrada2=b;
		try
		{
		//Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
		File archivo=new File("texto11.txt");
		//Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
		FileWriter escribir=new FileWriter(archivo,true);
		//Escribimos en el archivo con el metodo write 
		escribir.write(entrada1);
		//Cerramos la conexion
		escribir.close();
		}
		//Si existe un problema al escribir cae aqui
		catch(Exception e)
		{
		System.out.println("Error al escribir");
		}
		try
		{
		//Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
		File archivo=new File("texto22.txt");
		//Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
		FileWriter escribir=new FileWriter(archivo,true);
		//Escribimos en el archivo con el metodo write 
		escribir.write(entrada2);
		
		//Cerramos la conexion
		escribir.close();
		}
		//Si existe un problema al escribir cae aqui
		catch(Exception e)
		{
		System.out.println("Error al escribir");
		}
		
		
		
	}
	
	public void crearCompuerta(String pTipo, String  pID){
		circuito.AddComponentes(pTipo, pID);
		//System.out.println(circuito.getCantidadDeComponentes());
	}
	
	public void switchValor(String pID,int pValor, int pPatilla){
		circuito.cambiarValor(pID, pValor, pPatilla);
	}
	
	
	public void eliminarCompuerta(String pCompuerta)
	{
		circuito.eliminarComponentes(pCompuerta);
		//System.out.println(circuito.getCantidadDeComponentes());
	}
	
	public void setConection(String pIDsalida,int pNumeroSalida,String pIDentrada, int pPatilla)
	{
		circuito.setConection(pIDsalida, pNumeroSalida, pIDentrada, pPatilla);
	}

	public static void setComponenteActual(String nombre) {
		if(getComponenteActual() == nombre){
			Home.strComponenteTemporal = nombre;
		}
		else{
			Home.strComponenteTemporalAnterior = Home.strComponenteTemporal;
			Home.strComponenteTemporal = nombre;
			guiComponente a = Home.findComponent(Home.strComponenteTemporalAnterior);
			a.setBorderNull();
		}
		
	}
	
	public static String getComponenteActual(){
		String tmp = Home.strComponenteTemporal;
		return tmp;
	}
	public static void rePaintCanvas(){
		Home.rePaintCanvas();
	}

	public void correr(ArrayList<String> Lista) {
		circuito.comprobarCircuito();
		String entra1;
		String entra2;
		
		try
		{
		//Creamos un archivo FileReader que obtiene lo que tenga el archivo
		FileReader lector=new FileReader("texto11.txt");
		//El contenido de lector se guarda en un BufferedReader
		BufferedReader contenido=new BufferedReader(lector);
		//Con el siguiente ciclo extraemos todo el contenido del objeto "contenido" y lo mostramos
		while((entra1=contenido.readLine())!=null)
		{
	    int aa= Integer.parseInt(entra1);
		this.nn=aa;
		}
		}
		//Si se causa un error al leer cae aqui
		catch(Exception e)
		{
		System.out.println("Error al leer");
		}
		try
		{
		//Creamos un archivo FileReader que obtiene lo que tenga el archivo
		FileReader lector2=new FileReader("texto22.txt");
		//El contenido de lector se guarda en un BufferedReader
		BufferedReader contenido2=new BufferedReader(lector2);
		//Con el siguiente ciclo extraemos todo el contenido del objeto "contenido" y lo mostramos
		while((entra2=contenido2.readLine())!=null)
		{
			int bb= Integer.parseInt(entra2);
			this.mm=bb;
	
		}
		}
		//Si se causa un error al leer cae aqui
		catch(Exception e)
		{
		System.out.println("Error al leer");
		}
		//System.out.println("entrada1"  +entrada1);
		//System.out.println(Lista.get(0));
		int Salida = 0 ;
		array = Lista;
		size = array.size();
		for(int i=0; i<size;i++){
			if(array.get(i)=="AND") {
				if(nn==0 & mm==0){
					Salida=0;
					this.nn=Salida;
					
				}
					
				if(nn==0 & mm==1){
					Salida=0;
					this.nn=Salida;
					}
				if(nn==1 & mm==0){
					Salida=0;
					this.nn=Salida;
					}
				if(nn==1 & mm==1){
					Salida=1;
					this.nn=Salida;
					}
		
			}
				
			if(array.get(i)=="OR"){
				if(nn==0 & mm==0){
						Salida=0;
						this.nn=Salida;
						}
					if(nn==0 & mm==1){
						Salida=1;
						this.nn=Salida;
						}
					if(nn==1 & mm==0){
						Salida=1;
						this.nn=Salida;
						}
					if(nn==1 & mm==1){
						Salida=1;
						this.nn=Salida;
						}
			}
			
				
			if(array.get(i)=="NOT"){
				if(nn==0 || mm==0){
					Salida=1;
					this.nn=Salida;
					}
				if(nn==1 || mm==1){
					Salida=0;
					this.nn=Salida;
					}
			}
				
			if(array.get(i)=="NAND"){
				if(nn==0 & mm==0){
					Salida=1;
					this.nn=Salida;
					}
				if(nn==0 & mm==1){
					Salida=1;
					this.nn=Salida;
					}
				if(nn==1 & mm==0){
					Salida=1;
					this.nn=Salida;
					}
				if(nn==1 & mm==1){
					Salida=0;
					this.nn=Salida;
					}
			}
				
		
				
			if(array.get(i)== "XOR"){
				if(nn==0 & mm==0){
					Salida=0;
					this.nn=Salida;
					}
				if(nn==0 & mm==1){
					Salida=1;
					this.nn=Salida;
					}
				if(nn==1 & mm==0){
					Salida=1;
					this.nn=Salida;
					}
				if(nn==1 & mm==1){
					Salida=0;
					this.nn=Salida;
					}
			}
		
				
			if(array.get(i)== "NOR"){
				if(nn==0 & mm==0){
					Salida=1;
					this.nn=Salida;
					}
				if(nn==0 & mm==1){
					Salida=0;
					this.nn=Salida;
					}
				if(nn==1 & mm==0){
					Salida=0;
					this.nn=Salida;
					}
				if(nn==1 & mm==1){
					Salida=0;
					this.nn=Salida;
					}
			}
				
				
			if(array.get(i)== "XNOR"){
			
				if(nn==0 & mm==0){
					Salida=1;
					this.nn=Salida;
					}
				if(nn==0 & mm==1){
					Salida=0;
					this.nn=Salida;
					}
				if(nn==1 & mm==0){
					Salida=0;
					this.nn=Salida;
					}
				if(nn==1 & mm==1){
					Salida=1;
					this.nn=Salida;
					}
			}
			
		
		
					
		}System.out.println("LA SALIDA DEL CIRCUITO ES:"+Salida);
			}

		
		
		
		
	

	public void changeSwitchType(String bool, String nombre){
		Componente tmp = circuito.findComponent(nombre);
		
		tmp.setTipo(bool);
		
	}


	public void changeLightImg(String pID) {
		guiComponente tmp = Home.findComponent(pID);
		tmp.changeLightImg();
	}

	public void print(String pID) {
		Componente tmp = circuito.findComponent(pID);
		System.out.println("ENTRADA 0: " + tmp.getIn(0));
		System.out.println("ENTRADA 1: " + tmp.getIn(1));
	}

	public int getOutput(String pID) {
		Componente tmp = circuito.findComponent(pID);
		tmp.logicOperation(this);
		return tmp.getOut();
	}

	public Integer getCantidadEntradas() {
		return circuito.getListaEntradas().length();
	}
	
	public Integer getCantidadSalidas() {
		return circuito.getListaSalidas().length();
	}
	
	public Integer getCantidadDeComponentes(){
		return circuito.getCantidadDeComponentes();
	}

	public ArrayList<String> getCompuertas() {
		List<Componente> listaComponentesTemporal = circuito.getListaComponentes();
		ArrayList<String> listaComponentesFinal = new ArrayList<>();
		
		Node<Componente> tmp = listaComponentesTemporal.getHead();
		for(int i = 0; i < listaComponentesTemporal.length(); i++){
			listaComponentesFinal.add("ID: " + tmp.getData().get_ID() + " " + "Tipo: " + tmp.getData().getTipo());
			tmp = tmp.getNext();
		}
		
		
		return listaComponentesFinal;
	}

	public void cargarCircuito(List<String> listaDescripcionOUTPUT,List<String> listaEntradasOUTPUT, 
			List<String> listaSalidasOUTPUT,List<String> listaDeCompuertasOUTPUT) {
		
		Home.cargarCircuito(listaDescripcionOUTPUT,listaEntradasOUTPUT,listaSalidasOUTPUT,listaDeCompuertasOUTPUT);
	}

	public void cargarInformacionDeCircuito(String nombre, List<String> descripcion, List<String> entradas, 
											List<String> salidas, List<String> compuertas) {
		
		Home.cargarInformacionDeCircuito(nombre,descripcion,entradas,salidas,compuertas);
		
	}

	
}

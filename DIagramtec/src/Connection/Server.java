package Connection;
import java.net.*;


import java.io.*;
import java.lang.reflect.Array;

/**Corresponde a la funcion principal del servidor, donde se recibe la informacion proveniente del cliente y se toman las deciciones de acuerdo a lo solicitado*/
public class Server extends Thread 
{
	final int PUERTOENTRADA = 8080;
	DataOutputStream mensaje;
	DataInputStream entrada;	
	private Socket skCliente = null;
	
	/**
	 * Instanciar el servidor Funcion que permite establecer el socket desde la funcion main
	 *  skCliente corresponde al socket que esta solicitando la coneccion*/
	public Server(Socket skCliente) {
		super("Server");
		this.skCliente = skCliente;
	}
	/**Funcion principal donde empieza a correr el server*/
	public void run()
	{
		try
		{
			System.out.println("Sirvo a un cliente");
			readC (skCliente);
			writeC(skCliente, "si");
			skCliente.close();
			
		}

		catch (Exception e)
		{
			System.out.println(e.getMessage());
			
		}  
	} 
	
	/** Se encarga de leer los String que provienen desde el cliene y redirecciona de acuerdo a las acciones solicitadas
	 * socket que se desea leer
	 */
	protected void readC (Socket socket)  
	{
		try 
		{
			InputStream entra = socket.getInputStream();
			DataInputStream flujo = new DataInputStream(entra);
			System.out.println(flujo.readUTF());	
			InputStream entra2 = socket.getInputStream();
			DataInputStream flujo2 = new DataInputStream(entra2);
			System.out.println(flujo2.readUTF());
			InputStream entra3 = socket.getInputStream();
			DataInputStream flujo3 = new DataInputStream(entra3);
			System.out.println(flujo3.readUTF());
			DataInputStream flujo4 = new DataInputStream(entra3);
			System.out.println(flujo4.readUTF());
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage()); 
			
		}
	}
	/** Esta funcion permite mandar mensajes al socket del cliente, mensajes en forma de Strings
	  socket corresponde al Socket al que se desea mandar el mensaje
	  message corresponde al string de mensaje que se desea mandar al cliente*/
	protected void writeC (Socket socket, String message)   
	{
		try 
		{
			OutputStream mensajess = socket.getOutputStream();
			DataOutputStream flujoc= new DataOutputStream(mensajess);
			//escribi siii
			flujoc.writeUTF(message);

		}
		catch (Exception e)
		{
			System.out.println(e.getMessage()); 
		}
	}

}
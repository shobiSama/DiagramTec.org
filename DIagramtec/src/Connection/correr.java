package Connection;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//va en la clase principla de la  progra

/** Clase principal donde se crea el objeto Server, es aqui donde se crean los hilos de acuerdo a las conecciones que se reciben por parte de cliente*/
public class correr {
	
	public static void main(String[] args) throws IOException {
		
		try {
			final int PUERTOENTRADA = 8080;
			ServerSocket skServidor;
			skServidor = new ServerSocket(PUERTOENTRADA);
			System.out.println("Escucho el puerto " + PUERTOENTRADA);

			while (true) {
				Socket skCliente = skServidor.accept();
				Thread t = new Server(skCliente);
				t.run();
			}

		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

	}
	
	}
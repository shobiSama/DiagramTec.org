package Connection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class Cliente extends JFrame implements ActionListener{
    JTextField passw;
    JTextField nombre;
    JTextField tipo;
    JTextField largoDeDatos;
    JLabel nom;
    JLabel tip;
    JLabel lDd;
    JLabel pass;
    JButton enviar;
    JTextArea Mensajes;
    Socket Cli;
    DataOutputStream Salida;
    DataInputStream Entrada;
    
    public Cliente(String ip,int puerto) throws UnknownHostException, IOException{
    
 	
    	tipo = new JTextField();
    	tipo.setBounds(150, 80, 200, 20);
        add(tipo);
        
        nombre = new JTextField();
        nombre.setBounds(150, 20, 200, 20);
        add(nombre);
        
        largoDeDatos = new JTextField();
        largoDeDatos.setBounds(150, 140, 200, 20);
        add(largoDeDatos);
        
        passw = new JTextField();
        passw.setBounds(150, 200, 200, 20);
        add(passw);
        
        
        enviar = new JButton();
        enviar.setText("Enviar");
        enviar.addActionListener(this);
        enviar.setBounds(110, 280, 150, 20);
        add(enviar);
               
        Mensajes = new JTextArea();
        Mensajes.setBounds(90, 310,180, 30);
        add(Mensajes);
        
        nom = new JLabel();
        nom.setText("NOMBRE:");
        nom.setBounds(60, 20, 150, 20);
        add(nom);
        
        tip = new JLabel();
        tip.setText("TIPO:");
        tip.setBounds(60, 80, 150, 20);
        add(tip);
        
        lDd = new JLabel();
        lDd.setText("LARGO DE DATOS:");
        lDd.setBounds(30, 140, 150, 20);
        add(lDd);
        
        pass = new JLabel();
        pass.setText("PASSWORD:");
        pass.setBounds(60, 200, 150, 20);
        add(pass);

                
        setLayout(null);
        setSize(400,400);
        setVisible(true);
        
    	

        
    }

//xvxb
    public static void main(String[] args) throws UnknownHostException, IOException {
      new Cliente("192.168.1.116",8080);
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==enviar){
			try{
					Cli = new Socket("192.168.1.116",8080);
				    DataInputStream Entrada = new DataInputStream(Cli.getInputStream()); 
				    DataOutputStream Salida = new DataOutputStream(Cli.getOutputStream());
				    DataOutputStream Salida1 = new DataOutputStream(Cli.getOutputStream());
				    DataOutputStream Salida2= new DataOutputStream(Cli.getOutputStream());
				    DataOutputStream Salida3 = new DataOutputStream(Cli.getOutputStream());

				    Salida.writeUTF(nombre.getText());
				    Salida1.writeUTF(tipo.getText());
				    Salida2.writeUTF(largoDeDatos.getText());
				    Salida3.writeUTF(passw.getText());
				
					String msg = Entrada.readUTF();
					Mensajes.append("\n" + msg);
					System.out.println("\n" + msg);
					System.out.println("llllll");
					Cli.close();
			}catch (Exception ex ){
				System.out.println("Error Cliente"+ex.getMessage());
			}
			//MainWindow s = new MainWindow();
			//s.init();
			this.dispose();
		}
		
	}
}

	

	

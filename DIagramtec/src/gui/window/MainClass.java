package gui.window;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;



public class MainClass extends JFrame {
	
	JFrame ventanaM;
  public static void main(String args[]) {
	@SuppressWarnings("unused")
	Home ventana = new Home("Editor de Diagramas Logicos");
    
  }
  public MainClass(String titulo){
	  ventanaM = new JFrame(titulo);
	  Dimension d = new Dimension(); //Objeto para obtener tamano de la pantalla
	  //ImageIcon im = new ImageIcon("src");
	  
	  
	  ventanaM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  //ventanaM.setIconImage(im.getImage());
	  ventanaM.setResizable(false);
	  ventanaM.setLocation((int)((d.getWidth()+2)+70),15);
	  ventanaM.setSize(1200,700);
		
	  init();
	  
	  ventanaM.setVisible(true);


  }
  
  public void init(){
	  
  }
  public void paintComponent(Graphics g) {
	  // TODO Auto-generated method stub
	
  }
}
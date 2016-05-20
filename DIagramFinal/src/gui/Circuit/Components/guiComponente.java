package gui.Circuit.Components;

import gui.facade.Facade;
import gui.window.Home;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dataAccess.ReadProperties;

public class guiComponente extends JLabel implements MouseListener, MouseMotionListener {
	double xxx ;
	double yyy;
	private String nombre = "";
	private String tipo = "";
	private Point posicion = new Point(0,0);
	private Dimension d = new Dimension(90,60);
	private Point startPoint, startDrag, offset;
	private int x = 1;
	private int y = 1;
	private int entradaX1,entradaY1,entradaX2,entradaY2,salidaX1,salidaY1;
	private int CanvasentradaX1,CanvasentradaY1,CanvasentradaX2,CanvasentradaY2,CanvassalidaX1,CanvassalidaY1;
	private boolean changeFlag = false;
	
	public guiComponente(String pNombre, String pTipo){
		super();
		nombre = pNombre;
		switch(pTipo){
		case "And":
			this.setIcon(new ImageIcon(getClass().getResource(ReadProperties.file.getSetting("img_and"))));
			tipo = pTipo;
			
			break;
		case "Nand":
			this.setIcon(new ImageIcon(getClass().getResource(ReadProperties.file.getSetting("img_nand"))));
			tipo = pTipo;
			break;
		case "Or":
			this.setIcon(new ImageIcon(getClass().getResource(ReadProperties.file.getSetting("img_or"))));
			tipo = pTipo;
			break;
		case "Nor":
			this.setIcon(new ImageIcon(getClass().getResource(ReadProperties.file.getSetting("img_nor"))));
			tipo = pTipo;
			break;
		case "Not":
			this.setIcon(new ImageIcon(getClass().getResource(ReadProperties.file.getSetting("img_not"))));
			tipo = pTipo;
			break;
		case "Xor":
			this.setIcon(new ImageIcon(getClass().getResource(ReadProperties.file.getSetting("img_xor"))));
			tipo = pTipo;
			break;
		case "Xnor":
			this.setIcon(new ImageIcon(getClass().getResource(ReadProperties.file.getSetting("img_xnor"))));
			tipo = pTipo;
			break;
		case "Wire":
			d = new Dimension(118,19);
			this.setIcon(new ImageIcon(getClass().getResource(ReadProperties.file.getSetting("img_wire"))));
			tipo = pTipo;
			break;
		case "Wire2":
			d = new Dimension(19,118);
			this.setIcon(new ImageIcon(getClass().getResource(ReadProperties.file.getSetting("img_wire2"))));
			tipo = pTipo;
			break;
		case "Wire3":
			d = new Dimension(19,118);
			this.setIcon(new ImageIcon(getClass().getResource(ReadProperties.file.getSetting("img_wire3"))));
			tipo = pTipo;
			break;
		case "Off":
			d = new Dimension(45,30);
			this.setIcon(new ImageIcon(getClass().getResource(ReadProperties.file.getSetting("img_off"))));
			tipo = pTipo;
			break;
		case "LightOFF":
			d = new Dimension(45,50);
			this.setIcon(new ImageIcon(getClass().getResource(ReadProperties.file.getSetting("img_lightoff"))));
			tipo = pTipo;
			break;
			
		}
		
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.setSize(d);
		this.setPreferredSize(d);
		this.setLocation(posicion);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
	}
	
	
	public String getNombre(){
		return nombre;
	}
	
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		Point current = this.getScreenLocation(arg0);
		offset = new Point((int)current.getX() - (int) startDrag.getX(), (int) current.getY() - (int) startDrag.getY());
		Point newLocation = new Point((int) (this.startPoint.getX() + offset.getX()), (int) (this.startPoint.getY() + offset.getY()));
		this.setLocation(newLocation);
		double xx =offset.getX();
		this.xxx=xx;
		double yy =offset.getY();
		this.yyy=yy;
		System.out.println(xx);
	}
	public double getxx(){
		return this.xxx;
	}
	public double getyy(){
		return this.yyy;
	}
	

	

	@Override
	public void mouseMoved(MouseEvent arg0) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JPanel panel = Home.getPaintPanel();
		Facade facade = Home.getFacade();
		if(changeFlag == true){
			if(tipo == "On" || tipo == "Off"){
				if(tipo == "On"){
					this.setIcon(new ImageIcon(getClass().getResource(ReadProperties.file.getSetting("img_off"))));
					tipo = "Off";
					facade.changeSwitchType("Off",nombre);
					facade.switchValor(nombre, 0, 0);
				}
				else{
					this.setIcon(new ImageIcon(getClass().getResource(ReadProperties.file.getSetting("img_on"))));
					tipo = "On";
					facade.changeSwitchType("On",nombre);
					facade.switchValor(nombre, 1, 0);
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.setBorder(BorderFactory.createLineBorder(new Color(255,0,0)));
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if(Facade.getComponenteActual() == nombre){
			this.setBorder(BorderFactory.createLineBorder(new Color(0,255,0)));
		}
		else{
			this.setBorder(null);
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		this.startDrag = getScreenLocation(arg0);
		this.startPoint = this.getLocation();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		x = this.getLocation().x;
		y = this.getLocation().y;
		setEntradasSalidas();
		this.setLocation(x, y);
		
	}
	
	
	private Point getScreenLocation(MouseEvent evt){
		Point cursor = evt.getPoint();
		Point targetLocation = this.getLocationOnScreen();
		
		return new Point((int) (targetLocation.getX() + cursor.getX()), (int)(targetLocation.getY() + cursor.getY()));
	}

	
	public void setBorderNull(){
		this.setBorder(null);
	}
	
	//SETTERS
	public void setCoordenadas(int x, int y){
		this.setLocation(x,y);
		setEntradasSalidas();
	}
	
	public void setEntradasSalidas(){
		if(tipo.equals("Wire")){
			entradaX1 = 0;
			entradaY1 =9;
			salidaX1 = 118;
			salidaY1 = 9;
			
			CanvasentradaX1 = this.getLocation().x;
			CanvasentradaY1 =this.getLocation().y-5;
			CanvassalidaX1 = this.getLocation().x+118;
			CanvassalidaY1 = this.getLocation().y-5;
		}
		else if(tipo.equals("Wire2")){
			entradaX1 = 0;
			entradaY1 =9;
			salidaX1 = 0;
			salidaY1 = 118;
			
			CanvasentradaX1 = this.getLocation().x+2;
			CanvasentradaY1 =this.getLocation().y;
			CanvassalidaX1 = this.getLocation().x;
			CanvassalidaY1 = this.getLocation().y+118;
		}
		else if(tipo.equals("Wire3")){
			entradaX1 = 0;
			entradaY1 =118;
			salidaX1 = 0;
			salidaY1 = 9;
			
			CanvasentradaX1 = this.getLocation().x+5;
			CanvasentradaY1 =this.getLocation().y+110;
			CanvassalidaX1 = this.getLocation().x;
			CanvassalidaY1 = this.getLocation().y;
		}
		else if(tipo.equals("On") || tipo.equals("Off")){
			salidaX1 = 45;
			salidaY1 = 5;
			
			CanvassalidaX1 = this.getLocation().x+45;
			CanvassalidaY1 = this.getLocation().y-2;
		}
		else{
			entradaX1 = 0;
			entradaY1 = 20;
			entradaX2 = 0;
			entradaY2 = 40;
			salidaX1 = 90;
			salidaY1 = 30;
			
			CanvasentradaX1 = this.getLocation().x;
			CanvasentradaY1 =this.getLocation().y+10;
			CanvasentradaX2 = this.getLocation().x;
			CanvasentradaY2 =this.getLocation().y+30;
			CanvassalidaX1 = this.getLocation().x+85;
			CanvassalidaY1 = this.getLocation().y+20;
		}
		
	}
	
	public void setX(int x){ this.x = x; }
	public void setY(int y){ this.y = y; }
	
	//GETTERS

	
	public double getx(){ return x = this.getLocation().x; }
	public double gety(){ return y = this.getLocation().y; }
	
	public double getEntradaX1(){ return entradaX1; }
	public double getEntradaY1(){ return entradaY1; }
	
	public double getEntradaX2(){ return entradaX2; }
	public double getEntradaY2(){ return entradaY2; }
	
	public double getSalidaX1() { return salidaX1; }
	public double getSalidaY1() { return salidaY1; }


	public boolean getChangeFlag() { return changeFlag; }
	public void setChangeFlag(boolean changeFlag) {this.changeFlag = changeFlag;}
	
	public String getTipo() { return tipo; }


	public void changeLightImg() {
		if(tipo.equals("LightOFF")){
			tipo = "LightON";
			this.setIcon(new ImageIcon(getClass().getResource("/imgs/light_ON.gif")));
		}
		else if(tipo.equals("LightON")){
			tipo = "LightOFF";
			this.setIcon(new ImageIcon(getClass().getResource("/imgs/light_OFF.gif")));
		}
		
	}
}

 
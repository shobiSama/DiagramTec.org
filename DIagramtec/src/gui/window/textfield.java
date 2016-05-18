package gui.window;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.TablaDeVerdad.SimpleTable1;

public class textfield extends JFrame{
	private JTextField textfield1,textfield2;
	private JLabel label1,label2;
	private JButton boton1;
	String entrada1;
	String entrada2;
	
	public textfield(){
		setLayout(null);
		label1=new JLabel("Entrada 1:");
        label1.setBounds(0,0,80,30);
        add(label1);
        label2=new JLabel("Entrada 2:");
        label2.setBounds(0,40,80,30);
        add(label2);
        
        textfield1=new JTextField();
        textfield1.setBounds(60,6,30,20);
        add(textfield1);
        
        textfield2=new JTextField();
        textfield2.setBounds(60,46,30,20);
        add(textfield2);
        boton1=new JButton("Aceptar");
        boton1.setBounds(120,10,100,30);
        add(boton1);
        
        boton1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				entrada1= textfield1.getText();
	            entrada2= textfield2.getText();	
	            System.out.println(entrada1);
	            
			}
        	
        });
        
        System.out.println(entrada1);;

 }
	public static void main(String ar[]) { 
		JFrame paint=new textfield();
 		 //paint.setBounds(0,0,0,0);
 	     paint.setVisible(true);
 	     paint.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 	   // paint.setBackground(Color.decode("#57ACAF"));
 	     //paint.add(new textfield());
 	     paint.setSize(500, 450);
 	     
	
}}

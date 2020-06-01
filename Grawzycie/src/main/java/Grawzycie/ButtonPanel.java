/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grawzycie;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.io.File;
import java.io.IOException;

 public class ButtonPanel extends JPanel implements ActionListener{
 
 	public static final int HEIGHT = 100;
 	public static final int WIDTH = 300;
 	private JButton startbutton;
        public Plansza plansza=new Plansza();
        private JTextField sciezka;                    
        private JTextField wyjscie;                     
        private JTextField ilegeneracji;
 
 	public ButtonPanel() {
 		startbutton = new JButton("Start");
                sciezka = new JTextField("Plik wejsciowy");
                wyjscie = new JTextField("Plik do zapisu wyniku");
                ilegeneracji = new JTextField("Ile generacji?");
 
 		startbutton.addActionListener(this);
 		setLayout(new FlowLayout());
 		setPreferredSize(new Dimension(WIDTH, HEIGHT));
                add(sciezka);
                add(wyjscie);
                add(ilegeneracji);
                add(startbutton);
 	}
 
 	@Override
 	public void actionPerformed(ActionEvent e) {
 		Object source = e.getSource();
              
 		if(source == startbutton){
                    int ile = Integer.parseInt(ilegeneracji.getText());
                    String path = sciezka.getText();
                    String path2 = wyjscie.getText();
                    
                    try{
                    plansza.wczytaj(path);
                  

                    Thread thread = new Thread(() -> {
                    for(int i=0;i<ile;i++){
                    JPanel panel = new MyPanel(plansza);
                    add(panel);
                    setVisible(false);
                    setVisible(true);
                    try
                    {
                        Thread.sleep(500);
                    }
                     catch (InterruptedException ex)
                   {
                       Thread.currentThread().interrupt();
                   }
                    remove(panel);
                    setVisible(false);
                    setVisible(true);
                    plansza.generacje(1);
                    }
                    JPanel panel = new MyPanel(plansza);
                    add(panel);
                    setVisible(false);
                    setVisible(true);
                     try
                    {
                        Thread.sleep(500);
                    }
                    catch(InterruptedException ex)
                    {
                        Thread.currentThread().interrupt();
                    }
                    remove(panel);
                    setVisible(false);
                    setVisible(true);
                    File f = new File(path2);
                    if(f.exists() && f.isFile()){
                    try{
                        plansza.zapisz(path2);
                    }catch( IOException e2 ){
                        try{
                        plansza.zapisz(path);
                    }catch( IOException e3 ){
                        System.out.println( e3.getMessage() );
                    }
                    }
                    }
                    else
                    {
                    try{
                        plansza.zapisz(path);
                    }catch( IOException e3 ){
                        System.out.println( e3.getMessage() );
                    }
                    }
                    });
                    thread.start();
}
                    catch( IOException en ){
                    System.out.println( en.getMessage() );
                    }
                        
                }
 	}
 }

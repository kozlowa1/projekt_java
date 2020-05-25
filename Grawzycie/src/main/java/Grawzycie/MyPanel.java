/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grawzycie;

import java.awt.*;
import java.awt.geom.*;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
	public Plansza plansza;
	public MyPanel(Plansza plansza) {
                this.plansza=plansza;
		setPreferredSize(new Dimension(400, 400));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
                int bok=400/plansza.y;
                if(bok>(400/plansza.x))
                    bok=400/plansza.x;
                for(int i=0;i<plansza.y;i++){
                    for(int j=0;j<plansza.x;j++){
                        if(plansza.plan[j][i]==1){
                            g2d.setColor(Color.BLACK);
                        }
                        if(plansza.plan[j][i]==2){
                            g2d.setColor(Color.BLUE);
                        }
                        if(plansza.plan[j][i]==3){
                            g2d.setColor(Color.RED);
                        }
                        if(plansza.plan[j][i]==4){
                            g2d.setColor(Color.YELLOW);
                        }
                    g2d.fillRect(j*bok, i*bok, bok, bok);
                    }
                }
	}
}
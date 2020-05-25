/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grawzycie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 *
 */
public class Plansza {
    public int x;
    public int y;
    public int [][]plan;
    public Plansza()
    {
        x=0;
        y=0;
    }
    public void wczytaj (String path) throws IOException
    {
        FileReader fr = new FileReader( path );
        BufferedReader br = new BufferedReader(fr);
        String line = null;
        line= br.readLine();
        y=1;
        x=line.length();
        while( (line= br.readLine()) != null ) {
                y++;
        }
        fr = new FileReader( path );
        br = new BufferedReader(fr);
        plan=new int [x][y];
        for(int i=0;i<y;i++){
            line=br.readLine();
            for(int j=0;j<x;j++){
                if(line.charAt(j)=='4')
                {
                    plan[j][i]=4;
                }
                else if(line.charAt(j)=='2')
                {
                    plan[j][i]=2;
                }
                else if(line.charAt(j)=='3')
                {
                    plan[j][i]=3;
                }
                else{
                    plan[j][i]=1;
                }
               
            }
        }
    }
    
    public void generacje(int ile){
        int [][]staraplan=new int [x+2][y+2];
        int [][]nowaplan=new int [x+2][y+2];
        for(int i=0;i<y+2;i++){
            for (int j=0;j<x+2;j++){
                if (i==0 || j==0 || j==x+1 || i==y+1){
                    nowaplan[j][i]=0;
                    staraplan[j][i]=0;
                }
                else{
                    staraplan[j][i]=plan[j-1][i-1];
                }
            }
        }
        int s;
        for(int licznik=0;licznik<ile;licznik++){
            for(int i=1;i<y+1;i++){
                for (int j=1;j<x+1;j++){
                    if (staraplan[j][i]==1){
                        nowaplan[j][i]=1;
                    }
                    if (staraplan[j][i]==2){
                        nowaplan[j][i]=3;
                    }
                    if (staraplan[j][i]==3){
                        nowaplan[j][i]=4;
                    }
                    if (staraplan[j][i]==4){
                        s=0;
                        for(int k=i-1;k<i+2;k++){
                            for(int l=j-1;l<j+2;l++){
                                if (staraplan[l][k]==2){
                                    s++;
                                }
                            }
                        }
                        nowaplan[j][i]=4;
                        if (s==1 || s==2){
                            nowaplan[j][i]=2;
                        }
                    }

                }
            }
            for(int k=1;k<y+1;k++){
                for (int l=1;l<x+1;l++){
                    staraplan[l][k]=nowaplan[l][k];
                }
            }
        }
        for(int i=0;i<y;i++){
                for (int j=0;j<x;j++){
                    plan[j][i]=staraplan[j+1][i+1];
                }
            }
    }
    public void zapisz (String path) throws IOException
    {
        PrintWriter zapis = new PrintWriter(path);
        String linia;
        for(int i=0;i<y;i++){
            linia="";
            for(int j=0;j<x;j++){
                linia+=plan[j][i];
            }
            zapis.println(linia);
        }
        zapis.close();
    }
    
}
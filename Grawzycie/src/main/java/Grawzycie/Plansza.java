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
    private int typdanych;
    private int symbols[][];
    public Plansza()
    {
        x=0;
        y=0;
        typdanych=-1;
    }
    public void wczytaj (String path) throws IOException
    {
        FileReader fr = new FileReader( path );
        BufferedReader br = new BufferedReader(fr);
        String line = null;
        line= br.readLine();
        char a=line.charAt(1);
        if(a=='1' || a=='2' || a=='3' || a=='4')
        {
            wczytaj_liczby(path);
        }
        else
        {
            typdanych=0;
            wczytaj_symbole(path);
        }
    }
        
    private void wczytaj_liczby(String path)throws IOException{
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
    
    private void wczytaj_symbole (String path) throws IOException{
        FileReader fr = new FileReader( path );
        BufferedReader br = new BufferedReader(fr);
        String line = null;
        int a=0;
        while( (line= br.readLine()) != null ) {
                a++;
        }
        fr = new FileReader( path );
        br = new BufferedReader(fr);
        line = null;
        x=0;
        y=0;
        symbols =new int [a][3];
        /*
        0 Diode Normal
        1 Diode Reversed
        2 Generator Normal
        3 Generator Reversed
        4 NOT Normal
        5 NOT Reversed
        6 XOR Normal
        7 XOR Reversed
        8 AND Normal
        9 AND Reversed
        10 OR Normal
        11 OR Reversed
        12 NAND Normal
        13 NAND Reversed
        14 ElectronHead
        15 ElectronTail
        16 Conductor
        */
        a=0;
        while( (line= br.readLine()) != null ) {
                String [] w = line.split( "\\s+");
                symbols[a][1]=Integer.parseInt(w[1]);
                symbols[a][2]=Integer.parseInt(w[2]);
                if(w[0].equals("ElectronHead")){
                    symbols[a][0]=14;
                }
                else if(w[0].equals("ElectronTail")){
                    symbols[a][0]=15;
                }
                else if(w[0].equals("Conductor")){
                    symbols[a][0]=16;
                }
                else{ 
                    if(w[0].equals("Diode")){
                    symbols[a][0]=0;
                    }
                    if(w[0].equals("Generator")){
                    symbols[a][0]=2;
                    }
                    if(w[0].equals("NOT")){
                    symbols[a][0]=4;
                    }
                    if(w[0].equals("XOR")){
                    symbols[a][0]=6;
                    }
                    if(w[0].equals("AND")){
                    symbols[a][0]=8;
                    }
                    if(w[0].equals("OR")){
                    symbols[a][0]=10;
                    }
                    if(w[0].equals("NAND")){
                    symbols[a][0]=12;
                    }
                    if(w[3].equals("Reversed"))
                    {
                        symbols[a][0]+=1;
                    }
                }
                a++;
        }
        y=0;
        x=0;
        for(int i=0;i<a;i++)
        {
            if(symbols[i][1]>x)
                x=symbols[i][1];
            if(symbols[i][2]>y)
                y=symbols[i][2];
        }
        y+=10;
        x+=20;
        plan=new int [x][y];
        for(int i=0;i<y;i++){
            for(int j=0; j<x;j++)
                plan[j][i]=1;
        }
        for(int i=0;i<a;i++)
        {
            int tx=symbols[i][1];
            int ty=symbols[i][2];
            if (symbols[i][0]==0){
                int tab1[]={tx, tx+1, tx+2, tx+3, tx+4, tx+6, tx+7, tx+8, tx+9};
                utworz_symbol(9, ty, tab1);
                int tab2[]={tx+4, tx+5};
                utworz_symbol(2, ty+1,tab2);
                utworz_symbol(2, ty-1, tab2);
            }
            if (symbols[i][0]==1){
                int tab1[]={tx, tx+1, tx+2, tx+3, tx+5, tx+6, tx+7, tx+8, tx+9};
                utworz_symbol(9, ty, tab1);
                int tab2[]={tx+4, tx+5};
                utworz_symbol(2, ty+1,tab2);
                utworz_symbol(2, ty-1, tab2);
            }
            if (symbols[i][0]==2){
                int tab1[]={tx, tx-1, tx-2, tx-4, tx-5};
                utworz_symbol(5, ty, tab1);
                int tab2[]={tx-3, tx-6};
                utworz_symbol(2, ty-1,tab2);
                utworz_symbol(2, ty-2, tab2);
                int tab3[]={tx-4, tx-5};
                utworz_symbol(2, ty-3, tab3);
            }
            if (symbols[i][0]==3){
                int tab1[]={tx, tx+1, tx+2, tx+4, tx+5};
                utworz_symbol(5, ty, tab1);
                int tab2[]={tx+3, tx+6};
                utworz_symbol(2, ty-1,tab2);
                utworz_symbol(2, ty-2, tab2);
                int tab3[]={tx+4, tx+5};
                utworz_symbol(2, ty-3, tab3);
            }
            if (symbols[i][0]==4){
                int tab1[]={tx, tx+1, tx+2, tx+6, tx+7, tx+8, tx+11, tx+12, tx+13};
                utworz_symbol(9, ty, tab1);
                int tab2[]={tx+3, tx+7};
                utworz_symbol(2, ty-1,tab2);
                int tab3[]={tx+4, tx+5, tx+6};
                utworz_symbol(3, ty-2, tab3);
                int tab4[]={tx+4, tx+7, tx+10 };
                utworz_symbol(3, ty+1, tab4);
                int tab5[]={tx+3, tx+5, tx+6, tx+8, tx+9 };
                utworz_symbol(5, ty+2, tab5);
                int tab6[]={tx+4};
                utworz_symbol(1, ty+3, tab6);
            }
            if (symbols[i][0]==5){
                int tab1[]={tx, tx+1, tx+2, tx+5, tx+6, tx+7, tx+11, tx+12, tx+13};
                utworz_symbol(9, ty, tab1);
                int tab2[]={tx+6, tx+10};
                utworz_symbol(2, ty-1,tab2);
                int tab3[]={tx+7, tx+8, tx+9};
                utworz_symbol(3, ty-2, tab3);
                int tab4[]={tx+3, tx+6, tx+9 };
                utworz_symbol(3, ty+1, tab4);
                int tab5[]={tx+4, tx+5, tx+7, tx+8, tx+10 };
                utworz_symbol(5, ty+2, tab5);
                int tab6[]={tx+9};
                utworz_symbol(1, ty+3, tab6);
            }
            if (symbols[i][0]==6){
                int tab1[]={tx+5, tx+7, tx+8, tx+9, tx+10, tx+11};
                utworz_symbol(6, ty, tab1);
                int tab2[]={tx, tx+1, tx+2, tx+5, tx+6, tx+7, tx+8};
                utworz_symbol(7, ty-1,tab2);
                utworz_symbol(7, ty+1,tab2);
                int tab3[]={tx+3, tx+6};
                utworz_symbol(2, ty-2, tab3);
                utworz_symbol(2, ty+2, tab3);
                int tab4[]={tx+4, tx+5};
                utworz_symbol(2, ty-3, tab4);
                utworz_symbol(2, ty+3, tab4);
            }
            if (symbols[i][0]==7){
                int tab1[]={tx, tx+1, tx+2, tx+3, tx+4, tx+6};
                utworz_symbol(6, ty, tab1);
                int tab2[]={tx+3, tx+4, tx+5, tx+6, tx+9, tx+10, tx+11};
                utworz_symbol(7, ty-1,tab2);
                utworz_symbol(7, ty+1,tab2);
                int tab3[]={tx+5, tx+8};
                utworz_symbol(2, ty-2, tab3);
                utworz_symbol(2, ty+2, tab3);
                int tab4[]={tx+6, tx+7};
                utworz_symbol(2, ty-3, tab4);
                utworz_symbol(2, ty+3, tab4);
            }
            if (symbols[i][0]==8){
                int tab1[]={tx+5, tx+7, tx+9, tx+11, tx+13, tx+16, tx+17, tx+18};
                utworz_symbol(8, ty, tab1);
                int tab2[]={tx+0, tx+1, tx+2, tx+5, tx+10, tx+11, tx+12, tx+15};
                utworz_symbol(8, ty-1,tab2);
                int tab3[]={tx+3, tx+5, tx+11, tx+13, tx+14};
                utworz_symbol(5, ty-2, tab3);
                int tab4[]={tx+3, tx+5};
                utworz_symbol(2, ty-3, tab4);
                int tab5[]={tx+4};
                utworz_symbol(1, ty-4, tab5);
                int tab6[]={tx+0, tx+1, tx+2, tx+6, tx+7, tx+8, tx+13};
                utworz_symbol(7, ty+1,tab6);
                int tab7[]={tx+3, tx+7, tx+9, tx+13};
                utworz_symbol(4, ty+2, tab7);
                int tab8[]={tx+4, tx+10, tx+11, tx+12};
                utworz_symbol(4, ty+3, tab8);
                int tab9[]={tx+5, tx+6, tx+7, tx+8, tx+9};
                utworz_symbol(5, ty+4, tab9);
            }
            if (symbols[i][0]==9){
                int tab1[]={tx+0, tx+1, tx+2, tx+5, tx+7, tx+9, tx+11, tx+13};
                utworz_symbol(8, ty, tab1);
                int tab2[]={tx+3, tx+6, tx+7, tx+8, tx+13, tx+16, tx+17, tx+18};
                utworz_symbol(8, ty-1,tab2);
                int tab3[]={tx+4, tx+5, tx+7, tx+13, tx+15};
                utworz_symbol(5, ty-2, tab3);
                int tab4[]={tx+13, tx+15};
                utworz_symbol(2, ty-3, tab4);
                int tab5[]={tx+14};
                utworz_symbol(1, ty-4, tab5);
                int tab6[]={tx+5, tx+10, tx+11, tx+12, tx+16, tx+17, tx+18};
                utworz_symbol(7, ty+1,tab6);
                int tab7[]={tx+5, tx+9, tx+11, tx+15};
                utworz_symbol(4, ty+2, tab7);
                int tab8[]={tx+6, tx+7, tx+8, tx+14};
                utworz_symbol(4, ty+3, tab8);
                int tab9[]={tx+9, tx+10, tx+11, tx+12, tx+13};
                utworz_symbol(5, ty+4, tab9);
            }
            if (symbols[i][0]==10){
                int tab1[]={tx+4, tx+5, tx+6, tx+7, tx+8};
                utworz_symbol(5, ty, tab1);
                int tab2[]={tx, tx+1, tx+2, tx+5};
                utworz_symbol(4, ty-1,tab2);
                utworz_symbol(4, ty+1,tab2);
                int tab3[]={tx+3, tx+4};
                utworz_symbol(2, ty-2, tab3);
                utworz_symbol(2, ty+2, tab3);
            }
            if (symbols[i][0]==11){
                int tab1[]={tx, tx+1, tx+2, tx+3, tx+4};
                utworz_symbol(5, ty, tab1);
                int tab2[]={tx+3, tx+6, tx+7, tx+8};
                utworz_symbol(4, ty-1,tab2);
                utworz_symbol(4, ty+1,tab2);
                int tab3[]={tx+4, tx+5};
                utworz_symbol(2, ty-2, tab3);
                utworz_symbol(2, ty+2, tab3);
            }
            if (symbols[i][0]==12){
                int tab1[]={tx+5, tx+8, tx+11, tx+12, tx+13, tx+14, tx+15};
                utworz_symbol(7, ty, tab1);
                int tab2[]={tx, tx+1, tx+2, tx+3, tx+6, tx+7, tx+9, tx+12};
                utworz_symbol(8, ty-1,tab2);
                utworz_symbol(8, ty+1,tab2);
                int tab3[]={tx+4, tx+10, tx+12};
                utworz_symbol(3, ty-2, tab3);
                utworz_symbol(3, ty+2, tab3);
                int tab4[]={tx+5, tx+11};
                utworz_symbol(2, ty-3, tab4);
                utworz_symbol(2, ty+3, tab4);
                int tab5[]={tx+6, tx+10, tx+11, tx+12};
                utworz_symbol(4, ty-4, tab5);
                utworz_symbol(4, ty+4, tab5);
                int tab6[]={tx+7, tx+11};
                utworz_symbol(2, ty-5, tab6);
                utworz_symbol(2, ty+5, tab6);
                int tab7[]={tx+8, tx+9, tx+10};
                utworz_symbol(3, ty-6, tab7);
                utworz_symbol(3, ty+6, tab7);
            }
            if (symbols[i][0]==13){
                int tab1[]={tx, tx+1, tx+2, tx+3, tx+4, tx+7, tx+10};
                utworz_symbol(7, ty, tab1);
                int tab2[]={tx+3, tx+6, tx+8, tx+9, tx+12, tx+13, tx+14, tx+15};
                utworz_symbol(8, ty-1,tab2);
                utworz_symbol(8, ty+1,tab2);
                int tab3[]={tx+3, tx+5, tx+11};
                utworz_symbol(3, ty-2, tab3);
                utworz_symbol(3, ty+2, tab3);
                int tab4[]={tx+4, tx+10};
                utworz_symbol(2, ty-3, tab4);
                utworz_symbol(2, ty+3, tab4);
                int tab5[]={tx+3, tx+4, tx+5, tx+9};
                utworz_symbol(4, ty-4, tab5);
                utworz_symbol(4, ty+4, tab5);
                int tab6[]={tx+4, tx+8};
                utworz_symbol(2, ty-5, tab6);
                utworz_symbol(2, ty+5, tab6);
                int tab7[]={tx+5, tx+6, tx+7};
                utworz_symbol(3, ty-6, tab7);
                utworz_symbol(3, ty+6, tab7);
            }
            if (symbols[i][0]==14){
                plan[tx][ty]=2;
            }
            if (symbols[i][0]==15){
                plan[tx][ty]=3;
            }
            if (symbols[i][0]==16){
                utworz_przewodnik(tx, ty);
            }
            
        }
        typdanych=a;
    }
    
    private void utworz_symbol (int ile, int y, int tab[])
    {
        for(int i=0;i<ile;i++)
        {
            utworz_przewodnik(tab[i],y);
        }
    }
    
    private void utworz_przewodnik (int x, int y)
    {
        if(plan[x][y]==1)
            plan[x][y]=4;
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
        if (typdanych==-1)
            zapisz_liczby(path);
        else
            zapisz_symbole(path);
    }
    public void zapisz_liczby (String path) throws IOException
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
    public void zapisz_symbole (String path) throws IOException
    {
        int a=typdanych;
        PrintWriter zapis = new PrintWriter(path);
        String linia;
        for( int i=0;i<a;i++ ) {
                linia="";
                if(symbols[i][0]==14 || symbols[i][0]==15 || symbols[i][0]==16){
                    linia+="Conductor ";
                    linia+=symbols[i][1];
                    linia+=" ";
                    linia+=symbols[i][2];
                }
                else{
                if(symbols[i][0]==0 || symbols[i][0]==1)
                    linia+="Diode ";
                if(symbols[i][0]==2 || symbols[i][0]==3)
                    linia+="Generator ";
                if(symbols[i][0]==4 || symbols[i][0]==5)
                    linia+="NOT ";
                if(symbols[i][0]==6 || symbols[i][0]==7)
                    linia+="XOR ";
                if(symbols[i][0]==8 || symbols[i][0]==9)
                    linia+="AND ";
                if(symbols[i][0]==10 || symbols[i][0]==11)
                    linia+="OR ";
                if(symbols[i][0]==12 || symbols[i][0]==13)
                    linia+="NAND ";
                linia+=symbols[i][1];
                linia+=" ";
                linia+=symbols[i][2];
                if (symbols[i][0]%2==1)
                    linia+=" Reversed";
                else
                    linia+=" Normal";
        }
        zapis.println(linia);
        }
        for(int i=0;i<y;i++){
            for(int j=0;j<x;j++){
                if(plan[j][i]==2)
                    zapis.println("ElectronHead "+ j + " " + i);
                if(plan[j][i]==3)
                    zapis.println("ElectronTail "+ j + " " + i);
            }
        }
        zapis.close();
    }
    
}
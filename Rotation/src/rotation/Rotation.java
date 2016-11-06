/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Tim
 */
public class Rotation {
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        /*
         *  0. Einlesen
         *  1. Check ob potentiell
         *  2. Starte mit erstem Feld
         *  3. Return Drehungen bzw. keine Drehung
         */
        FileReader FileReader = new FileReader("./src/rotation/rotation1.txt");
        BufferedReader bf = new BufferedReader(FileReader);
        String s = bf.readLine();
        int gr = Integer.parseInt(s);
        int[][] root = new int[gr][gr];
        String line = bf.readLine();
        int r = 1;
        int c = 1;
        while(r<=gr-1){
           while(c<=gr-1){
               switch (line.charAt(c)) {
                   case ' ':
                       root[r][c] = -4;
                       break;
                   case '#':
                       root[r][c] = -1;
                       break;
                   default:
                       root[r][c] = c;
                       break;
               }
               if(r==gr-1 && line.charAt(c)==' '){root[r][c] = -5;}
           c++;
           }
        r++;
        c = 0;
        line = bf.readLine();
        }
        
       
    }
}

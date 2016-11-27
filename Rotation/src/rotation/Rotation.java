/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Tim
 */
public class Rotation {
    
    static public ArrayList<int[][]> down = new ArrayList<>();
    static public ArrayList<int[][]> left = new ArrayList<>();
    static public ArrayList<int[][]> up = new ArrayList<>();
    static public ArrayList<int[][]> right = new ArrayList<>();
    
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
        int[][] source = new int[gr][gr];
        String line;
        int r = 0;
        int c;
        while(r < gr){
            c = 0;
            line = bf.readLine();
            while(c < gr){
                switch (line.charAt(c)) {
                    case ' ':
                        source[c][r] = -4;
                        break;
                    case '#':
                        source[c][r] = -1;
                        break;
                    default:
                        source[c][r] = Integer.parseInt(line.charAt(c)+"");
                        break;
                }
                if(r==gr-1 && line.charAt(c)==' '){source[c][r] = -5;}
            c++;
            }
        r++;
        }
        
        Feld links = new Feld(source, 0, true);
        Feld rechts = new Feld(source, 0, false);
        
        links.print();
    }
}

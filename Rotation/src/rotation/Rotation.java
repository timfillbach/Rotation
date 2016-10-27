/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Tim
 */
public class Rotation {
    
    /**
     * @param args the command line arguments
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
        System.out.println(gr);
        int r = gr;
        int c = gr;
        while(r>0){
           //System.out.println(bf.readLine());
           while(c>0){
           System.out.println(bf.);
           c--;
           }
           r--;
           c = gr;
       }
    }
}

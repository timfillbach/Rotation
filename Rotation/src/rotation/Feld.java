
package rotation;

public class Feld {
    
    int[][] data;
    int state;
    
    public Feld(int[][] inputData, int inputState, boolean turnLeft) {
        
        data = inputData;
        if (turnLeft) {
            if (inputState < 3) state++;
            else state = 0;
        } else {
            if (inputState > 0) state--;
            else state = 3;
        }
        
        applyGravity();
        
        // Apply Gravity
        // Testen ob erfolgreich
        // Testen Abbruchbedingung
        // Drehen Links
        // Drehen Rechts
    }
    
    private void applyGravity() {
        switch (state) {
            case 0:
                for (int n=1; n<=data.length-2; n++) {
                    for (int y=data.length-2; y>=n; y--) {
                        int current = -1;
                        boolean blocked = false;
                        for (int x=1; x<=data.length-2; x++) {
                            if (data[x][y] >= 0) {                              // Element          //!!! Wenn rechts von Element frei oder Wand?!
                                if (data[x][y] != current) {                    // neues Element
                                    if (current>=0 && blocked==false) {         // altes Element nicht geblockt -> kopieren
                                        for (int p=1; p<x; p++) {
                                            if (data[p][y+1] == -5) {
                                                // Wuhuu!
                                            } else if (data[p][y+1] == -4) {    // verschiebe Element
                                                data[p][y+1] = current;
                                                data[p][y] = -4;
                                            }
                                        }
                                    }
                                    blocked = false;
                                    current = data[x][y];
                                }
                                if (blocked==false && data[x][y+1]<=-4) {       // nicht geblockt und frei
                                    data[x][y+1] = data[x][y+1] + 2;            // erstelle temporären Wert
                                } else blocked = true;                          // blockiert Element
                            }
                        }
                        for (int p=1; p<=data.length-2; p++) {                  // temporäre Werte löschen
                            if (data[p][y+1]==-2 || data[p][y+1]==-3)
                                data[p][y+1] = data[p][y+1] - 2;
                        }
                    }
                }
                break; //Reihe von Unten, Unterste Reihe
            case 1: break; //Spalte von Links
            case 2: break; //Reihe von Oben
            case 3: break; //Spalte von Rechts
        }
    }
    
}

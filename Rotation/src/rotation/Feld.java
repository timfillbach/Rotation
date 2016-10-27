
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
                for (int n=0; n<data.length; n++) {
                    for (int y=data.length-2; y>=n; y--) {
                        int current = -1;
                        boolean blocked = false;
                        for (int x=0; x<data.length; x++) {
                            if (data[x][y] >= 0) {                              // Element
                                if (data[x][y] != current) {                    // neues Element
                                    if (blocked == false) {                     // altes Element nicht geblockt -> kopieren
                                        for (int p=0; p<data.length; p++) {
                                            if (data[p][y+1] == -5) {
                                                // Wuhuu!
                                            } else if (data[p][y+1] == -4) {    // verschiebe Element
                                                data[p][y] = -4;
                                                data[p][y+1] = current;
                                            }
                                        }
                                    }
                                    blocked = false;
                                    current = data[x][y];
                                }
                                if (blocked==false && data[x][y+1]<-3) {        // nicht geblockt und frei
                                    data[x][y+1] = data[x][y+1] + 2;            // erstelle temporären Wert
                                } else blocked = true;                          // blockiert Element
                            }
                        }
                        for (int p=0; p<data.length; p++) {                     // temporäre Werte löschen
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

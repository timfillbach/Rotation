
package rotation;

public class Feld {
    
    int[][] data;
    int state;
    int a = 0; //Spalte
    int b = 0; //Reihe
    int current;
    boolean clear;
    
    public Feld(int[][] inputData, int inputState, boolean turnLeft) {
        
        data = inputData;
        if (turnLeft) {
            if (inputState < 3) state++;
            else state = 0;
        } else {
            if (inputState > 0) state--;
            else state = 3;
        }
        switch (state) {
            case 0: b = 1;
            case 1: a = -1;
            case 2: b = -1;
            case 3: a = 1;
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
            case 0: gravityDown();
            case 1: gravityLeft();
            case 2: gravityUp();
            case 3: gravityRight();
        }
    }
    
    private void gravityDown() {
        for (int n = 1; n <= data.length-2; n++) {
            for (int y = data.length-2; y >= n; y--) {
                current = -1;
                clear = true;
                for (int x = 1; x <= data.length-2; x++) {
                    processTile(x, y);
                }
            }
        }
    }
    
    private void gravityLeft() {
        for (int n = data.length-2; n >= 1; n--) {
            for (int x = 2; x <= n; x++) {
                current = -1;
                clear = true;
                for (int y = 1; y <= data.length-2; y++) {
                    processTile(x, y);
                }
            }
        }
    }
    
    private void gravityUp() {
        for (int n = data.length-2; n >= 1; n--) {
            for (int y = 2; y >= n; y++) {
                current = -1;
                clear = true;
                for (int x = 1; x <= data.length-2; x++) {
                    processTile(x, y);
                }
            }
        }
    }
    
    private void gravityRight() {
        for (int n = 1; n <= data.length-2; n++) {
            for (int x = data.length-3; x >= n; x--) {
                current = -1;
                clear = true;
                for (int y = 1; y <= data.length-2; y++) {
                    processTile(x, y);
                }
            }
        }
    }
    
    private void processTile(int x, int y) {
        if (data[x][y] != current) {
            if (current >= 0 && clear) {
                moveObject();
            }
            current = -1;
            clear = true;
            if (data[x][y] >= 0) {
                current = data[x][y];
                obstacles(x, y);
            }
        } else {
            obstacles(x, y);
        }
    }
    
    private void moveObject() {
        for (int i = 1; i <= data.length-1; i++) {
            for (int k = 1; k <= data.length-2; k++) {
                if (data[i][k] == -2) {
                    data[i][k] = current;
                } else if (data[i][k] == current) {
                    data[i][k] = -4;
                }
            }
        }
    }
    
    private void obstacles(int x, int y) {
        if (clear && data[x+a][y+b] == -4) {
            data[x+a][y+b] = -2;
        } else {
            clear = false;
            cleanup();
        }
    }
    
    private void cleanup() {
        for (int i = 1; i <= data.length-1; i++) {
            for (int k = 1; k <= data.length-2; k++) {
                if (data[i][k] == -2) {
                    data[i][k] = -4;
                }
            }
        }
    }
    
    private void applyGravityOld() {
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

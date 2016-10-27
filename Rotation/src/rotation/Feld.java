
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
                    for (int y=data.length-1; y>=n; y--) {
                        for (int x=0; x<data.length; x++) {
                            // Einzelnes Feld bewegen
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

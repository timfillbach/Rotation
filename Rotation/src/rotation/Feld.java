
package rotation;

import static rotation.Rotation.down;
import static rotation.Rotation.left;
import static rotation.Rotation.right;
import static rotation.Rotation.up;

public class Feld {
    
    int[][] data;
    int state;
    int a = 0; //Spalte
    int b = 0; //Reihe
    int current;
    boolean clear;
    boolean success;
    boolean duplicate;
    
    public Feld(int[][] inputData, int inputState, boolean turnLeft) {
        
        data = inputData;
        state = inputState;
        if (turnLeft) { // anti-clockwise
            if (inputState < 3) state++;
            else state = 0;
        } else { // clockwise
            if (inputState > 0) state--;
            else state = 3;
        }
        
        applyGravity();
        
        testDuplicate();
        print();
        
        if (duplicate == false) {
            
            Feld links = new Feld(data, state, true);
            Feld rechts = new Feld(data, state, false);
            
        }
        
        // Drehen Links
        // Drehen Rechts
    }
    
    public void applyGravity() {
        switch (state) {
            case 0:
                b = 1;
                gravityDown();
                break;
            case 1:
                a = -1;
                gravityLeft();
                break;
            case 2:
                b = -1;
                gravityUp();
                break;
            case 3:
                a = 1;
                gravityRight();
                break;
        }
    }
    
    public void testDuplicate() {
        switch (state) {
            case 0:
                for (int n = 0; n <= down.size()-1; n++) {
                    if (this.data.equals(down.get(n))) {
                        duplicate = true;
                    }
                }
                if (duplicate == false) {
                    down.add(data);
                }
                break;
            case 1:
                for (int n = 0; n <= left.size()-1; n++) {
                    if (this.data.equals(left.get(n))) {
                        duplicate = true;
                    }
                }
                if (duplicate == false) {
                    left.add(data);
                }
                break;
            case 2:
                for (int n = 0; n <= up.size()-1; n++) {
                    if (this.data.equals(up.get(n))) {
                        duplicate = true;
                    }
                }
                if (duplicate == false) {
                    up.add(data);
                }
                break;
            case 3:
                for (int n = 0; n <= right.size()-1; n++) {
                    if (this.data.equals(right.get(n))) {
                        duplicate = true;
                    }
                }
                if (duplicate == false) {
                    right.add(data);
                }
                break;
        }
    }
    
    private void gravityDown() {
        for (int n = 0; n <= data.length-1; n++) {
            for (int y = data.length-1; y >= n; y--) {
                current = -4;
                clear = true;
                for (int x = 0; x <= data.length-1; x++) {
                    processTile(x, y);
                }
            }
        }
    }
    
    private void gravityLeft() {
        for (int n = data.length-1; n >= 0; n--) {
            for (int x = 0; x <= n; x++) {
                current = -4;
                clear = true;
                for (int y = 0; y <= data.length-1; y++) {
                    processTile(x, y);
                }
            }
        }
    }
    
    private void gravityUp() {
        for (int n = data.length-1; n >= 0; n--) {
            for (int y = 0; y <= n; y++) {
                current = -4;
                clear = true;
                for (int x = 0; x <= data.length-1; x++) {
                    processTile(x, y);
                }
            }
        }
    }
    
    private void gravityRight() {
        for (int n = 0; n <= data.length-1; n++) {
            for (int x = data.length-1; x >= n; x--) {
                current = -4;
                clear = true;
                for (int y = 0; y <= data.length-1; y++) {
                    processTile(x, y);
                }
            }
        }
    }
    
    private void processTile(int x, int y) {
        if (data[x][y] != current) {
            if (current >= 0 && clear) {
                moveObject(x, y);
            }
            current = -4;
            clear = true;
            if (data[x][y] >= 0) {
                current = data[x][y];
                obstacles(x, y);
            }
        } else {
            obstacles(x, y);
        }
    }
    
    private void moveObject(int x, int y) {
        switch (state) {
            case 0:
                for (int r = y; r <= y+1; r++) {
                    for (int c = 1; c <= data.length-2; c++) {
                        replace(c, r);
                    }
                }
                break;
            case 1:
                for (int c = x-1; c <= x; c++) {
                    for (int r = 1; r <= data.length-2; r++) {
                        replace(c, r);
                    }
                }
                break;
            case 2:
                for (int r = y-1; r <= y; r++) {
                    for (int c = 1; c <= data.length-2; c++) {
                        replace(c, r);
                    }
                }
                break;
            case 3:
                for (int c = x; c <= x+1; c++) {
                    for (int r = 1; r <= data.length-2; r++) {
                        replace(c, r);
                    }
                }
                break;
        }
    }
    
    private void replace(int c, int r) {
        if (data[c][r] == -2) {
            data[c][r] = current;
        } else if (data[c][r] == current) {
            data[c][r] = -4;
        } else if (data[c][r] == -3) {
            data[c][r] = current;
            success = true;
        }
    }
    
    private void obstacles(int x, int y) {
        if (current != -4) {
            if (clear && data[x+a][y+b] == -4) {
                data[x+a][y+b] = -2;
            } else if (clear && data[x+a][y+b] == -5) {
                data[x+a][y+b] = -3;
            } else {
                clear = false;
                cleanup();
            }
        }
    }
    
    private void cleanup() {
        for (int i = 1; i <= data.length-1; i++) {
            for (int k = 1; k <= data.length-2; k++) {
                if (data[i][k] == -2) {
                    data[i][k] = -4;
                } else if (data[i][k] == -3) {
                    data[i][k] = -5;
                }
            }
        }
    }
        
    public void print() {
        for (int y = 0; y < data.length; y++) {
            for (int x = 0; x < data.length; x++) {
                System.out.print(data[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
}

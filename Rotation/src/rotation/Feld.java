
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
        
        // More Stuff incoming
    }
    
}

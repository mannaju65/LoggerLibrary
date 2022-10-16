package policies;

import java.io.File;

public interface RotationalPolicy {
    
    boolean checkIfFileRotaionNeeded(File file);
}

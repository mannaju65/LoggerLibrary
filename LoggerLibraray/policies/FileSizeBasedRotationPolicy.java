package policies;

import java.io.File;

public class FileSizeBasedRotationPolicy implements RotationalPolicy {

    private static final double MAX_FILE_SIZE_IN_MB = 10.0;

    /** returns true if file size is greater than 10 Mb
     * 
     */
    @Override
    public boolean checkIfFileRotaionNeeded(File file) {
        if(file.exists()) {
            double bytes = file.length(); 
            double kilobytes = (bytes / 1024); 
            double megabytes = (kilobytes / 1024);
            if(megabytes > MAX_FILE_SIZE_IN_MB) {
                return true;
            } 
        }
        return false;
    }
    
}

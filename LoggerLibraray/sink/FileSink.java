package sink;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.GZIPOutputStream;

import policies.RotationalPolicy;

public class FileSink implements Sink {

    private static final String COMPRESSED_DATE_FORMAT = "yyyy-MM-dd-HH-mm-ss";

    private FileSinkInitializationContext initializationContext;

    private RotationalPolicy rotationalPolicy;

    public FileSink(FileSinkInitializationContext initializationContext, RotationalPolicy rotationalPolicy) {
        this.initializationContext = initializationContext;
        this.rotationalPolicy = rotationalPolicy;
        File logfile = new File(initializationContext.getDirectory(), initializationContext.getFileName());
        if(!logfile.exists()) {
            try {
                logfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } 
    }

    @Override
    public synchronized void logMessage(String message) {
        File logfile = new File(initializationContext.getDirectory(), initializationContext.getFileName());
        if(rotationalPolicy.checkIfFileRotaionNeeded(logfile)) { // checks if log rotation is needed or not
            compresFileAndClear();
        }
        try (FileWriter fileWritter = new FileWriter(logfile, true)) {
            BufferedWriter bw = new BufferedWriter(fileWritter);
            bw.write(message);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Compreses existing log file to a .gz file and clears content of log file
    private void compresFileAndClear() {
        byte[] buffer = new byte[1024]; 
        try {
            File logfile = new File(initializationContext.getDirectory(), initializationContext.getFileName());
            Date now = new Date();
            String compresedFilename = initializationContext.getFileName() + "-" + new SimpleDateFormat(COMPRESSED_DATE_FORMAT).format(now) + ".gz"; 
            FileInputStream fis=new FileInputStream(logfile);
            File compressFile = new File(initializationContext.getDirectory(), compresedFilename);
            FileOutputStream fos=new FileOutputStream(compressFile);
            GZIPOutputStream os =  new GZIPOutputStream(fos); 
            int data; 
            while ((data = fis.read(buffer)) > 0) 
            {
                os.write(buffer, 0, data); 
            }  
            fis.close();
            os.finish(); 
            os.close(); 
            clearFile(logfile);   
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void clearFile(File file) throws IOException {
        FileWriter fwOb = new FileWriter(file, false); 
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
    }

}
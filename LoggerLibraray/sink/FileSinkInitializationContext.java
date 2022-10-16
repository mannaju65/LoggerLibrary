package sink;

import java.nio.file.Path;
import java.nio.file.Paths;

import enums.SinkType;

public class FileSinkInitializationContext extends SinkIntializationContext {

    private String directory;

    private String fileName;

    public FileSinkInitializationContext(String filePath) {
        super(SinkType.FILE);
        Path path = Paths.get(filePath);
        this.directory = path.getParent().toString();
        this.fileName = path.getFileName().toString();
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDirectory() {
        return this.directory;
    }

    public String getFileName() {
        return this.fileName;
    }
    
}

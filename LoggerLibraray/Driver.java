import configurations.ConfigFIleLoggerConfiguration;
import configurations.LoggerConfiguration;
import enums.LogLevel;
import util.BasicDateTimeMessageFormattor;

public class Driver {
    
    public static void main(String[] args) {

        LoggerConfiguration loggerConfiguration = new ConfigFIleLoggerConfiguration();
        LoggerManager loggerManager  = new LoggerManager(loggerConfiguration);
        Logger logger = new Logger(loggerManager, new BasicDateTimeMessageFormattor());
        logger.log(LogLevel.DEBUG, "Hello World", "ota");
        logger.log(LogLevel.INFO, "Hello World2", "ota");
        logger.log(LogLevel.ERROR, "Hello World3", "ota");
        logger.log(LogLevel.WARN, "Hello World4", "ota");
    }
}



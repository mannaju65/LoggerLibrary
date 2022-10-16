import configurations.LogLevelConfig;
import entity.MessageEntity;
import enums.LogLevel;
import sink.Sink;
import util.MessageFormattor;

public class Logger {

    private static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    
    private final LoggerManager loggerManager;

    private final MessageFormattor messageFormattor;

    public Logger (LoggerManager loggerManager, MessageFormattor formattor) {
        this.loggerManager = loggerManager;
        this.messageFormattor = formattor;
    }

    public void log(LogLevel level, String message, String namespace) {
        MessageEntity messageEntity = new MessageEntity(message, level, namespace, Thread.currentThread().getName());
        LogLevelConfig configEntity = loggerManager.getLoggerConfig().getConfigEntityByLogLevel(level);
        if(configEntity == null) {
            System.out.println("config not found for log level :" + level.name());
            return;
        }
        try {
            Sink sink = loggerManager.getSinkForLog(level);
            sink.logMessage(messageFormattor.formatMessage(
                messageEntity,
                (configEntity.getDateTimeFormat() == null) ? DEFAULT_DATE_TIME_FORMAT : configEntity.getDateTimeFormat()));
        } catch(RuntimeException ex) {
            System.out.println("Logging exception : " + ex.getMessage());
        }
        
    }
}

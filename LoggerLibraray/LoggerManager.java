import java.util.HashMap;
import java.util.Map;

import configurations.LoggerConfiguration;
import enums.LogLevel;
import sink.Sink;
import sink.SinkFactory;

public class LoggerManager {

    private LoggerConfiguration loggerConfiguration;
    private Map<LogLevel, Sink> logLevelSinkMap;

    public LoggerManager(LoggerConfiguration configuration) {
        this.loggerConfiguration = configuration;
        this.logLevelSinkMap = new HashMap<>();
        for(LogLevel level : LogLevel.values()) {
            if(configuration.getConfigEntityByLogLevel(level) != null) {
                logLevelSinkMap.put(level, SinkFactory.getSinkByIntializationContext(
                    configuration.getSinkInitializationContext(configuration.getConfigEntityByLogLevel(level).getSinkType())));
            }
        }
    }

    public Sink getSinkForLog(LogLevel level) {
        return logLevelSinkMap.get(level);
    }

    public LoggerConfiguration getLoggerConfig() {
        return this.loggerConfiguration;
    }
}

package configurations;

import enums.LogLevel;
import enums.SinkType;
import enums.WriteMode;
import sink.SinkIntializationContext;

public interface LoggerConfiguration {

    /** returns configuration of a log level like Sink Type, date format
     * 
     * @param level
     * @return
     */
    LogLevelConfig getConfigEntityByLogLevel(LogLevel level);

    /** returns sink intialization context for a sink type like file path for File type sink
     * 
     * @param sinkType
     * @return
     */
    SinkIntializationContext getSinkInitializationContext(SinkType sinkType);

    WriteMode getWriteMode();
}

package configurations;

import enums.LogLevel;
import enums.SinkType;


/** Class for Log level configurations
 *  Ties a sink type to a log
 */
public class LogLevelConfig {
    
    private LogLevel logLevel;

    private String dateTimeFormat;

    private SinkType sinkType;

    public LogLevelConfig(LogLevel level, String timeFormat, SinkType sinkType) {
        this.dateTimeFormat = timeFormat;
        this.sinkType = sinkType;
        this.logLevel = level;
    }

    public LogLevel getLogLevel() {
        return this.logLevel;
    }

    public String getDateTimeFormat() {
        return this.dateTimeFormat;
    }

    public SinkType getSinkType() {
        return this.sinkType;
    }

    public void setTimeFormat(String timeFormat) {
		this.dateTimeFormat = timeFormat;
	}

    public void setLogLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
	}

    public void setSinkType(SinkType sinkType) {
		this.sinkType = sinkType;
	}
}

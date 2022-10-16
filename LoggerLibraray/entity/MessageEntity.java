package entity;

import java.util.Date;

import enums.LogLevel;

public class MessageEntity {
    
    private final String message;

    private final LogLevel logLevel;

    private final String namespace;

    private final String threadId;

    private final Date currentDate;

    public MessageEntity(String message, LogLevel level, String namespace, String threadId) {
        this.threadId = threadId;
        this.message = message;
        this.logLevel = level;
        this.namespace = namespace;
        this.currentDate = new Date();
    }

    public String getMessage() {
        return this.message;
    }

    public LogLevel getLogLevel() {
        return this.logLevel;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public String getThreadId() {
        return this.threadId;
    }

    public Date getDate() {
        return this.currentDate;
    }


}

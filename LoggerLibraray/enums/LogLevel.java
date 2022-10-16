package enums;


public enum LogLevel {
    DEBUG,
    INFO,
    WARN,
    ERROR,
    FATAL;

    /** return log level if input string matches
     * 
     * @param level
     * @return
     */
    public static LogLevel checkLogLevel(String level) {
        try {
            return LogLevel.valueOf(level);
        } catch(IllegalArgumentException ex) {
            return null;
        }
    }

}
package enums;

public enum LoggerConfig {
    file_location,
    db_url,
    thread_model,
    write_mode;

    public static LoggerConfig checkConfig(String config) {
        try {
            return LoggerConfig.valueOf(config);
        } catch(IllegalArgumentException ex) {
            return null;
        }
    }
}

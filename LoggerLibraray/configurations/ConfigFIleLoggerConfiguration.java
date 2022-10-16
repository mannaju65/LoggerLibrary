package configurations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import enums.LogLevel;
import enums.LoggerConfig;
import enums.SinkType;
import enums.WriteMode;
import sink.ConsoleSinkInitializationContext;
import sink.FileSinkInitializationContext;
import sink.SinkIntializationContext;

public class ConfigFIleLoggerConfiguration implements LoggerConfiguration {

    private static final String CONFIG_FILE_NAME = "logger.properties";

    private Map<SinkType, SinkIntializationContext> sinkContextMap;

    private Map<LogLevel, LogLevelConfig> configurationsMapByLogLevel;

    private WriteMode writeMode;

    public ConfigFIleLoggerConfiguration() {
        sinkContextMap = new HashMap<>();
        configurationsMapByLogLevel = new HashMap<>();
        loadFromConfigFile();
    }

    // Loads logger configuration from config file. 
    private void loadFromConfigFile() {
        try {
            BufferedReader  bfr = new BufferedReader(new FileReader(new File(CONFIG_FILE_NAME)));
            String line;
            while ((line = bfr.readLine()) != null) {
                if (!line.isEmpty() && !line.startsWith("#")) {
                    String[] pair = line.trim().split("=");
                    if(pair == null || pair.length != 2) continue;
                    if(LoggerConfig.checkConfig(pair[0]) != null) {
                        LoggerConfig config = LoggerConfig.valueOf(pair[0]);
                        switch (config) {
                            case file_location:
                                sinkContextMap.put(SinkType.FILE, new FileSinkInitializationContext(pair[1]));
                                break;
                            case db_url:
                                break;
                            case thread_model:
                                break;
                            case write_mode:
                                this.writeMode = WriteMode.valueOf(pair[1]); 
                                break;        
                            default:
                                break;
                        }
                    } else {
                        String[] keyPair = pair[0].split("\\.");
                        if(keyPair != null && keyPair.length == 2 && LogLevel.checkLogLevel(keyPair[0]) != null) {
                            //System.out.println(keyPair[0]);
                            if(keyPair[1].equals("ts_format")) {
                                if(!configurationsMapByLogLevel.containsKey(LogLevel.valueOf(keyPair[0]))) {
                                    configurationsMapByLogLevel.put(
                                        LogLevel.valueOf(keyPair[0]), new LogLevelConfig(LogLevel.valueOf(keyPair[0]), null, null));
                                }
                                configurationsMapByLogLevel.get(LogLevel.valueOf(keyPair[0])).setTimeFormat(pair[1]);
        
                            } else if (keyPair[1].equals("sink_type")) {
                                if(!configurationsMapByLogLevel.containsKey(LogLevel.valueOf(keyPair[0]))) {
                                    configurationsMapByLogLevel.put(
                                        LogLevel.valueOf(keyPair[0]), new LogLevelConfig(LogLevel.valueOf(keyPair[0]), null, null));
                                }
                                configurationsMapByLogLevel.get(LogLevel.valueOf(keyPair[0])).setSinkType(SinkType.valueOf(pair[1]));
                            }
                        }
                    }
                }
            }
            bfr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sinkContextMap.put(SinkType.CONSOLE, new ConsoleSinkInitializationContext());
    }

    /** returns configuration of a log level like Sink Type, date format
     * 
     * @param level
     * @return
     */
    @Override
    public LogLevelConfig getConfigEntityByLogLevel(LogLevel level) {
        return configurationsMapByLogLevel.getOrDefault(level, null);
    }

    /** returns sink intialization context for a sink type like file path for File type sink
     * 
     * @param sinkType
     * @return
     */
    @Override
    public SinkIntializationContext getSinkInitializationContext(SinkType sinkType) {
        return sinkContextMap.getOrDefault(sinkType, null);
    }

    @Override
    public WriteMode getWriteMode() {
        
        return (writeMode == null) ? WriteMode.SYNC : writeMode;
    }
    
}

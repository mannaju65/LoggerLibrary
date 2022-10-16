# LoggerLibrary

Logger can support Multiple types of Sinks. File & Console type sync is implemented.

Logger Configurations can be added as a file with name "logger.properties". Sample File : 
ERROR.ts_format=yyyy-MM-dd HH:mm
ERROR.sink_type=FILE
DEBUG.ts_format=yyyy-MM-dd HH:mm:ss
DEBUG.sink_type=CONSOLE
INFO.ts_format=yyyy-MM-dd HH:mm:ss
INFO.sink_type=FILE
WARN.ts_format=yyyy-MM-dd HH:mm:ss
WARN.sink_type=CONSOLE
FATAL.ts_format=yyyy-MM-dd HH:mm
FATAL.sink_type=FILE
file_location=/var/log/ota/test.log
thread_model=SINGLE
write_mode=SYNC




package sink;

public class ConsoleSink implements Sink {

    private static ConsoleSink sink;

    private ConsoleSink() {

    }

    public static ConsoleSink getSink() {
        ConsoleSink tmpSink = sink;
        if(tmpSink != null) {
            return tmpSink;
        }
        synchronized (ConsoleSink.class) {
            if (sink == null) {
                sink = new ConsoleSink();
            }
        }
        return sink;
    }

    @Override
    public void logMessage(String message) {
        System.out.print(message);
    }
    
}

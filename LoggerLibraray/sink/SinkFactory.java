package sink;

import enums.SinkType;
import policies.FileSizeBasedRotationPolicy;

public class SinkFactory {
    
    public static Sink getSinkByIntializationContext(SinkIntializationContext intializationContext) {
        if(intializationContext == null) return ConsoleSink.getSink();
        if(SinkType.FILE.equals(intializationContext.getSinkType())) {
            Sink sink = new FileSink((FileSinkInitializationContext)intializationContext, new FileSizeBasedRotationPolicy());
            return sink;
        } else if (SinkType.CONSOLE.equals(intializationContext.getSinkType())) {
            return ConsoleSink.getSink();
        }
        throw new RuntimeException(intializationContext.getSinkType().name() + " Sink type don't exist");
    }
}

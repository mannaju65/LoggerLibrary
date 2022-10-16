package sink;

import enums.SinkType;

public abstract class SinkIntializationContext {
    
    private final SinkType sinkType;

    public SinkIntializationContext(SinkType sinkType) {
        this.sinkType = sinkType;
    }

    public SinkType getSinkType() {
        return sinkType;
    }
}

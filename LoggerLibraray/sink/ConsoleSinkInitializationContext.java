package sink;

import enums.SinkType;

public class ConsoleSinkInitializationContext extends SinkIntializationContext {

    public ConsoleSinkInitializationContext() {
        super(SinkType.CONSOLE);
    }
    
}

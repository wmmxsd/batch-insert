package sql.impl.icssas;

import sql.AbstractScript;
import sql.Script;

public class EventScript extends AbstractScript {
    public EventScript(Integer index) {
        super(index);
    }

    @Override
    public Boolean call() {
        runScript("icssas_event-");
        return true;
    }
}

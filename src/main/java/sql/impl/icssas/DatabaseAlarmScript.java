package sql.impl.icssas;

import sql.AbstractScript;
import sql.Script;

public class DatabaseAlarmScript extends AbstractScript {
    public DatabaseAlarmScript(Integer index) {
        super(index);
    }

    @Override
    public Boolean call() {
        runScript("icssas_alarm_database");
        return true;
    }
}

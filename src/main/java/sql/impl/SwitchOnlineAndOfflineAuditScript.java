package sql.impl;

import sql.AbstractScript;
import sql.Script;

public class SwitchOnlineAndOfflineAuditScript extends AbstractScript {
    public SwitchOnlineAndOfflineAuditScript(Integer index) {
        super(index);
    }

    @Override
    public Boolean call() {
        runScript("n4a_audit_sw_done_20200001-");
        return true;
    }
}

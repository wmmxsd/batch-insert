package sql.impl;

import sql.AbstractScript;
import sql.Script;

public class DeviceOnlineAndOfflineAuditScript extends AbstractScript {
    public DeviceOnlineAndOfflineAuditScript(Integer index) {
        super(index);
    }

    @Override
    public Boolean call() {
        runScript("n4a_audit_pt_done_20200001-");
        return true;
    }
}

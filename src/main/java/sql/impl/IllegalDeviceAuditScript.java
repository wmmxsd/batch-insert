package sql.impl;

import sql.AbstractScript;
import sql.Script;

public class IllegalDeviceAuditScript extends AbstractScript {
    public IllegalDeviceAuditScript(Integer index) {
        super(index);
    }

    @Override
    public Boolean call() {
        runScript("n4a_audit_pt_violateaccess-");
        return true;
    }
}

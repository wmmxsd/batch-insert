package sql.impl;

import sql.AbstractScript;
import sql.Script;

public class UnauthorizedDeviceAuditScript extends AbstractScript {
    public UnauthorizedDeviceAuditScript(Integer index) {
        super(index);
    }

    @Override
    public Boolean call() {
        runScript("n4a_audit_pt_undecided-");
        return true;
    }
}

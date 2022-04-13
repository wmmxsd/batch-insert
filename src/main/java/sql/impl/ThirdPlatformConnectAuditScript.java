package sql.impl;

import sql.AbstractScript;
import sql.Script;

public class ThirdPlatformConnectAuditScript extends AbstractScript {
    public ThirdPlatformConnectAuditScript(Integer index) {
        super(index);
    }

    @Override
    public Boolean call() {
        runScript("n4a_audit_otherplatform_connect-");
        return true;
    }
}

package sql.impl;

import sql.AbstractScript;
import sql.Script;

public class ThirdPlatformInAuditScript extends AbstractScript {
    public ThirdPlatformInAuditScript(Integer index) {
        super(index);
    }

    @Override
    public Boolean call() {
        runScript("n4a_audit_otherplatform_done_in_20200001-");
        return true;
    }
}

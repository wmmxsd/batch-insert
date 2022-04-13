package sql.impl;

import sql.AbstractScript;
import sql.Script;

public class ThirdPlatformOutAuditScript extends AbstractScript {
    public ThirdPlatformOutAuditScript(Integer index) {
        super(index);
    }

    @Override
    public Boolean call() {
        runScript("n4a_audit_otherplatform_done_out_20200001-");
        return true;
    }
}

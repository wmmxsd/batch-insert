package sql.impl.icssas;

import sql.AbstractScript;
import sql.Script;

public class PatchScript extends AbstractScript {
    public PatchScript(Integer index) {
        super(index);
    }

    @Override
    public Boolean call() {
        runScript("icssas_patch-");
        return true;
    }
}

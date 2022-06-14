package sql.impl.icssas;

import sql.AbstractScript;
import sql.Script;

public class AssetInfoScript extends AbstractScript {
    public AssetInfoScript(Integer index) {
        super(index);
    }

    @Override
    public Boolean call() {
        runScript("icssas_asset_info-");
        return true;
    }
}

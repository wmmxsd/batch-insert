package file.factory.impl;

import file.SqlFile;
import file.factory.ScriptFactory;
import file.impl.*;
import sql.Script;
import sql.impl.*;
import utils.DuridConfig;

import javax.sql.DataSource;
import java.util.concurrent.Semaphore;

public class ScriptFactoryImpl implements ScriptFactory<SqlFile, Script> {
    @Override
    public Script build(SqlFile sqlFile) {
        if (sqlFile.getClass() == DeviceOnlineAndOfflineAudit.class) {
            return new DeviceOnlineAndOfflineAuditScript();
        } else if (sqlFile.getClass() == SwitchOnlineAndOfflineAudit.class) {
            return new SwitchOnlineAndOfflineAuditScript();
        } else if (sqlFile.getClass() == UnauthorizedDeviceAudit.class) {
            return new UnauthorizedDeviceAuditScript();
        } else if (sqlFile.getClass() == IllegalDeviceAudit.class) {
            return new IllegalDeviceAuditScript();
        } else if (sqlFile.getClass() == ThirdPlatformConnectAudit.class) {
            return new ThirdPlatformConnectAuditScript();
        } else if (sqlFile.getClass() == ThirdPlatformInAudit.class) {
            return new ThirdPlatformInAuditScript();
        } else if (sqlFile.getClass() == ThirdPlatformOutAudit.class) {
            return new ThirdPlatformOutAuditScript();
        }
        return null;
    }
}

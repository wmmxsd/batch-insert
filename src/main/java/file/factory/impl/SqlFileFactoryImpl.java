package file.factory.impl;

import file.SqlFile;
import file.factory.SqlFileFactory;
import file.impl.*;
import utils.Lists;

import java.util.List;

public class SqlFileFactoryImpl implements SqlFileFactory {
    @Override
    public List<SqlFile> buildAll() {
        System.out.println("创建实现SqlFile接口的全部对象-end");
        return Lists.newArrayList(
                new DeviceOnlineAndOfflineAudit()
                //new SwitchOnlineAndOfflineAudit(),
                //new UnauthorizedDeviceAudit(),
                //new IllegalDeviceAudit(),
                //new ThirdPlatformConnectAudit(),
                //new ThirdPlatformInAudit(),
                //new ThirdPlatformOutAudit()
        );
    }
}
package file.factory.impl;

import file.SqlFile;
import file.factory.SqlFileFactory;
import file.impl.DeviceOnlineAndOfflineAudit;
import file.impl.SwitchOnlineAndOfflineAudit;
import file.impl.ThirdPlatformOutAudit;
import file.impl.UnauthorizedDeviceAudit;
import utils.Lists;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static file.BatchConfig.CIRCLE;

public class SqlFileFactoryImpl implements SqlFileFactory {
    @Override
    public List<SqlFile> buildAll() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        System.out.println("创建实现SqlFile接口的全部对象-end");
        ArrayList<SqlFile> sqlFiles = Lists.newArrayList(
                //new DeviceOnlineAndOfflineAudit(0),
                new SwitchOnlineAndOfflineAudit(0),
                new UnauthorizedDeviceAudit(0),
                //new IllegalDeviceAudit(),
                //new ThirdPlatformConnectAudit(),
                //new ThirdPlatformInAudit(),
                new ThirdPlatformOutAudit(0)
                //new AssetInfo(),
                //new DatabaseAlarm(),
                //new Event(),
                //new Patch()
        );

        List<SqlFile> tempList = new ArrayList<>();
        for (SqlFile sqlFile : sqlFiles) {
            for (int i = 1; i < CIRCLE; i++) {
                Class<? extends SqlFile> aClass = sqlFile.getClass();
                Constructor<? extends SqlFile> constructor = aClass.getConstructor(Integer.class);
                tempList.add(constructor.newInstance(i));
            }
        }

        sqlFiles.addAll(tempList);
        return sqlFiles;
    }
}
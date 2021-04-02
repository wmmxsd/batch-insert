package file.impl;

import file.AbstractSqlFile;
import utils.UUIDUtil;

public class ThirdPlatformConnectAudit extends AbstractSqlFile {

    @Override
    public String generateSql() {
        return "INSERT INTO `n4a`.`n4a_audit_otherplatform_connect`(`id`, `name`, `cid`, `reMarks`, `ip`, `port`, `doSomething`, `doResult`, `doTime`, `isNeedWarn`, `warnLevel`) VALUES ('" + UUIDUtil.get32UUID()+ "', '集成测试平台', '010000123456ABCDEF000000000000000000000000000000000000000000000000000000000000', NULL, '10.10.20.203', 42194, 301, 0, '2021-03-13 15:31:00', NULL, NULL);";
    }

    @Override
    public Boolean call() {
        System.out.println("sql写入到文件名前缀为n4a_audit_otherplatform_connect-的文件：begin");
        writeToFile("n4a_audit_otherplatform_connect-");
        System.out.println("sql写入到文件名前缀为n4a_audit_otherplatform_connect-的文件：end");
        return true;
    }
}

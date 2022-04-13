package file.impl;

import file.AbstractSqlFile;
import utils.UUIDUtil;

public class ThirdPlatformConnectAudit extends AbstractSqlFile {

    public ThirdPlatformConnectAudit(Integer index) {
        super(index);
    }

    @Override
    public String generateSql() {
        return "INSERT INTO `n4a`.`n4a_audit_otherplatform_connect`(`id`, `name`, `cid`, `reMarks`, `ip`, `port`, `doSomething`, `doResult`, `doTime`, `isNeedWarn`, `warnLevel`) VALUES ('" + UUIDUtil.get32UUID()+ "', '集成测试平台', '010000123456ABCDEF000000000000000000000000000000000000000000000000000000000000', NULL, '10.10.20.203', 42194, 301, 0, '"+ NOW +"', NULL, NULL);";
    }

    @Override
    public Boolean call() {
        writeToFile("n4a_audit_otherplatform_connect-");
        return true;
    }
}

package file.impl;

import file.AbstractSqlFile;
import utils.UUIDUtil;

public class ThirdPlatformOutAudit extends AbstractSqlFile {

    public ThirdPlatformOutAudit(Integer index) {
        super(index);
    }

    @Override
    public String generateSql() {
        return "INSERT INTO `n4a`.`n4a_audit_otherplatform_done_out_20200001`(`id`, `name`, `cid`, `ip`, `port`, `dotime`, `datadetails`, `remarks`) VALUES ('" + UUIDUtil.get32UUID() + "', '集成测试平台', '010000123456ABCDEF000000000000000000000000000000000000000000000000000000000000', '10.10.20.203', 42194, '"+ NOW +"', '<113>Mar 13 15:39:27 N4Aserver[30525]:{\\\"data\\\":{\\\"time\\\":\\\"2021-03-13 16:39:31\\\",\\\"mac\\\":\\\"28-d2-44-fe-73-03\\\"},\\\"class\\\":\\\"3\\\",\\\"subclass\\\":\\\"2\\\"}', NULL);";
    }

    @Override
    public Boolean call() {
        writeToFile("n4a_audit_otherplatform_done_out_20200001-");
        return true;
    }
}

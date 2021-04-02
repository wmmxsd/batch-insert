package file.impl;

import file.AbstractSqlFile;
import utils.UUIDUtil;

public class ThirdPlatformInAudit extends AbstractSqlFile {

    @Override
    public String generateSql() {
        return "INSERT INTO " +
                "`n4a`.`n4a_audit_otherplatform_done_in_20200001`(`id`, `name`, `cid`, `ip`, `port`, `dotime`, `datadetails`, `remarks`) " +
                "VALUES" +
                " ('" + UUIDUtil.get32UUID() + "', '集成测试平台', '010000123456ABCDEF000000000000000000000000000000000000000000000000000000000000', '10.10.20.203', 42194, '2021-03-13 15:42:36', '{\\\"policyid\\\":\\\"123\\\",\\\"acllist\\\":[{\\\"dport\\\":\\\"8080\\\",\\\"protocol\\\":\\\"ipv4-icmp\\\",\\\"action\\\":\\\"0\\\",\\\"dip\\\":\\\"192.168.120.202\\\",\\\"sip\\\":\\\"\\\",\\\"id\\\":\\\"1\\\",\\\"sport\\\":\\\"\\\"}],\\\"remarks\\\":\\\"zwh 第三方平台策略\\\",\\\"exectype\\\":\\\"1\\\"}', NULL);";
    }

    @Override
    public Boolean call() {
        System.out.println("sql写入到文件名前缀为n4a_audit_otherplatform_done_in_20200001-的文件：begin");
        writeToFile("n4a_audit_otherplatform_done_in_20200001-");
        System.out.println("sql写入到文件名前缀为n4a_audit_otherplatform_done_in_20200001-的文件：end");
        return true;
    }
}

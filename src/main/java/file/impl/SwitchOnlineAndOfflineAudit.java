package file.impl;

import file.AbstractSqlFile;
import utils.UUIDUtil;

public class SwitchOnlineAndOfflineAudit extends AbstractSqlFile {

    @Override
    public String generateSql() {
        return "INSERT INTO `n4a_audit_sw_done_20200001`" +
                "(`id`, `cid`, `manufacturer`, `model`, `deptid`, `deptidName`, `manager`, `phone`, `mail`, `remarks`, `ip`, `dosomething`, `dotime`, `warnlevel`, `ipsection`)" +
                " VALUES " +
                "('" + UUIDUtil.get32UUID() + "', '0100000858A5EA02C7000000000000000000000000000000000000000000000000000000000000', 'vrv', '8032D', '1010', '总部\\3', '', '', '', '', '10.10.1.140', 102, '2021-03-29 10:16:00', 2, '10.10.1.');";
    }

    @Override
    public Boolean call() {
        System.out.println("sql写入到文件名前缀为n4a_audit_sw_done_20200001-的文件：begin");
        writeToFile("n4a_audit_sw_done_20200001-");
        System.out.println("sql写入到文件名前缀为n4a_audit_sw_done_20200001-的文件：end");
        return true;
    }
}

package file.impl;

import file.AbstractSqlFile;
import utils.UUIDUtil;

public class UnauthorizedDeviceAudit extends AbstractSqlFile {

    @Override
    public String generateSql() {
        return "INSERT INTO `n4a_audit_pt_undecided_20200001`" +
                "(`id`, `mac`, `deptid`, `deptidName`, `dosomething`, `dotime`, `warnlevel`, `ipsw`, `portsw`, `cidsw`)" +
                " VALUES " +
                "('" + UUIDUtil.get32UUID() + "', 'a0-36-9f-8c-b9-e5', '1010', '总部\\3', 204, '2021-03-29 10:15:12', 2, '10.10.1.50', 8, '0100000858A5001105000000000000000000000000000000000000000000000000000000000000');";
    }

    @Override
    public Boolean call() {
        System.out.println("sql写入到文件名前缀为n4a_audit_pt_undecided-的文件：begin");
        writeToFile("n4a_audit_pt_undecided-");
        System.out.println("sql写入到文件名前缀为n4a_audit_pt_undecided-的文件：end");
        return true;
    }
}

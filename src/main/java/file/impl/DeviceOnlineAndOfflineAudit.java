package file.impl;

import file.AbstractSqlFile;
import utils.UUIDUtil;

public class DeviceOnlineAndOfflineAudit extends AbstractSqlFile {

    @Override
    public String generateSql() {
        return "INSERT INTO `n4a_audit_pt_done_20200001`" +
                "(`id`, `authType`, `cid`, `mac`, `deviceTypeName`, `manuFacturer`, `model`, `deptId`, `deptidName`, `user`, `phone`, `mail`, `reMarks`, `ip`, `doSomething`, `doTime`, `warnLevel`, `ipSw`, `portSw`, `cidSw`, `ipsection`)" +
                " VALUES "
                + "('" + UUIDUtil.get32UUID() + "', 4, '02000068F7280CB650E8B1FC9CE857303633343444394339304135394541424130424543443030', '68-f7-28-0c-b6-50', '5', 'vrv', '1', '1010', '总部\\3', '张三', '', '', 'cid 10.10.150.10  68-f7-28-0c-b6-50', '10.10.150.100', 203, '2021-03-29 09:31:52', 4, '10.10.1.153', 4, '01000078B6C1045B24000000000000000000000000000000000000000000000000000000000000', '10.10.150.');";
    }

    @Override
    public Boolean call() {
        System.out.println("sql写入到文件名前缀为n4a_audit_pt_done_20200001-的文件：begin");
        writeToFile("n4a_audit_pt_done_20200001-");
        System.out.println("sql写入到文件名前缀为n4a_audit_pt_done_20200001-的文件：end");
        return true;
    }
}

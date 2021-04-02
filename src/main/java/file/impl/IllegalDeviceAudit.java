package file.impl;

import file.AbstractSqlFile;
import utils.UUIDUtil;

public class IllegalDeviceAudit extends AbstractSqlFile {

    @Override
    public String generateSql() {
        return "INSERT INTO `n4a_audit_pt_violateaccess`" +
                "(`id`, `authType`, `cid`, `mac`, `devicetypename`, `manufacturer`, `model`, `deptId`, `deptidName`, `user`, `phone`, `mail`, `remarks`, `ip`, `dotime`, `dotimebegin`, `donumber`, `externalip`, `externalport`, `warnlevel`)" +
                " VALUES " +
                "('" + UUIDUtil.get32UUID() +"', 4, '02000008D40C1F3E4368F7280DF1D2383143423437313842374446324243393146363037373931', '68-f7-28-0d-f1-d2', '1', 'vrv-ywd', '3012', '1010', '总部\\3', 'fjg-ywd', '', '', '10.10.30.12  68-f7-28-0d-f1-d2', '10.10.30.12', '2021-03-29 03:30:06', '2021-03-29 03:29:06', 2, '10.10.1.108', 55000, 2);";
    }

    @Override
    public Boolean call() {
        System.out.println("sql写入到文件名前缀为n4a_audit_pt_violateaccess-的文件：begin");
        writeToFile("n4a_audit_pt_violateaccess-");
        System.out.println("sql写入到文件名前缀为n4a_audit_pt_violateaccess-的文件：end");
        return true;
    }
}

package file.impl;

import file.AbstractSqlFile;
import utils.UUIDUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IllegalDeviceAudit extends AbstractSqlFile {
    public IllegalDeviceAudit(Integer index) {
        super(index);
    }

    @Override
    public String generateSql() {
        return "INSERT INTO `n4a_audit_pt_violateaccess`" +
                "(`id`, `authType`, `cid`, `mac`, `devicetypename`, `manufacturer`, `model`, `deptId`, `deptidName`, `user`, `phone`, `mail`, `remarks`, `ip`, `dotime`, `dotimebegin`, `donumber`, `externalip`, `externalport`, `warnlevel`)" +
                " VALUES " +
                "('" + UUIDUtil.get32UUID() +"', 4, '010000AEB53D5CB6134B203ECB80E6213AC1CC334C1D9102C2FD723C98B2C115372B1DCF89E02E', 'ae-b5-3d-5c-b6-13', '1', 'vrv-ywd', '3012', '1004', '总部\\武汉', 'fjg-ywd', '', '', '10.10.30.12  68-f7-28-0d-f1-d2', '10.10.30.12', '" + NOW + "', '"+ NOW +"', 2, '10.10.1.108', 55000, 2);";
    }

    @Override
    public Boolean call() {
        writeToFile("n4a_audit_pt_violateaccess-");
        return true;
    }
}

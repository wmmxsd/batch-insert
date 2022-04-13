package file.impl;

import file.AbstractSqlFile;
import utils.UUIDUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SwitchOnlineAndOfflineAudit extends AbstractSqlFile {
    public SwitchOnlineAndOfflineAudit(Integer index) {
        super(index);
    }

    @Override
    public String generateSql() {
        return "INSERT INTO `n4a_audit_sw_done_20200001`" +
                "(`id`, `cid`, `manufacturer`, `model`, `deptid`, `deptidName`, `manager`, `phone`, `mail`, `remarks`, `ip`, `dosomething`, `dotime`, `warnlevel`, `ipsection`)" +
                " VALUES " +
                "('" + UUIDUtil.get32UUID() + "', '010000111111000001000000000000000000000000000000000000000000000000000000000000', 'vrv', '8032D', '1004', '总部\\武汉', '', '', '', '', '10.10.1.140', 102, '" + NOW + "', 2, '10.10.1.');";
    }

    @Override
    public Boolean call() {
        writeToFile("n4a_audit_sw_done_20200001-");
        return true;
    }
}

package file.impl;

import file.AbstractSqlFile;
import utils.UUIDUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeviceOnlineAndOfflineAudit extends AbstractSqlFile {
    public DeviceOnlineAndOfflineAudit(Integer index) {
        super(index);
    }

    @Override
    public String generateSql() {
        return "INSERT INTO `n4a_audit_pt_done_20200001`" +
                "(`id`, `authType`, `cid`, `mac`, `deviceTypeName`, `manuFacturer`, `model`, `deptId`, `deptidName`, `user`, `phone`, `mail`, `reMarks`, `ip`, `doSomething`, `doTime`, `warnLevel`, `ipSw`, `portSw`, `cidSw`, `ipsection`)" +
                " VALUES "
                + "('" + UUIDUtil.get32UUID() + "', 4, '01000079C83AEB547203BC372E12803F2BA7AED43A39263D5D613F02ED23E272F5011CC7DACE34', '79-c8-3a-eb-54-72', '5', 'vrv', '1', '1004', '总部\\武汉', '张三', '', '', 'cid 10.10.150.10  79-c8-3a-eb-54-72', '" + NOW + "', 203, '2021-03-29 09:31:52', 4, '10.10.1.153', 4, '01000078b6c104d929000000000000000000000000000000000000000000000000000000000000', '10.10.150.');";
    }

    @Override
    public Boolean call() {
        writeToFile("n4a_audit_pt_done_20200001-");
        return true;
    }
}

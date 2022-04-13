package file.impl;

import file.AbstractSqlFile;
import utils.UUIDUtil;

public class UnauthorizedDeviceAudit extends AbstractSqlFile {

    public UnauthorizedDeviceAudit(Integer index) {
        super(index);
    }

    @Override
    public String generateSql() {
        return "INSERT INTO `n4a_audit_pt_undecided_20200001`" +
                "(`id`, `mac`, `deptid`, `deptidName`, `dosomething`, `dotime`, `warnlevel`, `ipsw`, `portsw`, `cidsw`)" +
                " VALUES " +
                "('" + UUIDUtil.get32UUID() + "', 'f1-91-28-3a-6d-37', '1004', '总部\\武汉', 204, '"+ NOW +"', 2, '10.10.1.50', 8, '0100000858a5ea03a0000000000000000000000000000000000000000000000000000000000000');";
    }

    @Override
    public Boolean call() {
        writeToFile("n4a_audit_pt_undecided-");
        return true;
    }
}

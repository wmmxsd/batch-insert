package file.impl;

import file.AbstractSqlFile;
import model.Switch;
import utils.UUIDUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static utils.ColumnValueUtil.RANDOM;
import static utils.ColumnValueUtil.getSwitch;

public class SwitchOnlineAndOfflineAudit extends AbstractSqlFile {
    public SwitchOnlineAndOfflineAudit(Integer index) {
        super(index);
    }

    @Override
    public String generateSql() {
        Switch aswitch = getSwitch();
        return "INSERT INTO `n4a_audit_sw_done_20200002`" +
                "(`id`, `cid`, `manufacturer`, `model`, `deptid`, `deptidName`, `manager`, `phone`, `mail`, `remarks`, `ip`, `dosomething`, `dotime`, `warnlevel`, `ipsection`)" +
                " VALUES " +
                "('" + UUIDUtil.get32UUID() + "', '" + aswitch.getCid() + "', 'vrv', '8032D', '1004', '总部\\武汉', '', '', '', '', '" + aswitch.getIp() + "', " + getDS() + ", '" + NOW + "', 2, '" + aswitch.getIp().substring(0, aswitch.getIp().lastIndexOf('.') + 1) + "');";
    }

    @Override
    public Boolean call() {
        writeToFile("n4a_audit_sw_done_20200001-");
        return true;
    }

    private int getDS() {
        List<Integer> codeList = Arrays.asList(101, 102, 103);
        return codeList.get(RANDOM.nextInt(codeList.size()));
    }
}

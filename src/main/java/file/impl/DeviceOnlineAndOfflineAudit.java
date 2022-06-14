package file.impl;

import file.AbstractSqlFile;
import model.Device;
import model.Switch;
import utils.UUIDUtil;

import java.util.Arrays;
import java.util.List;

import static utils.ColumnValueUtil.*;

public class DeviceOnlineAndOfflineAudit extends AbstractSqlFile {
    public DeviceOnlineAndOfflineAudit(Integer index) {
        super(index);
    }

    @Override
    public String generateSql() {
        Device device = getDevice();
        Switch aswitch = getSwitch();
        return "INSERT INTO `n4a_audit_pt_done_20200002`" +
                "(`id`, `authType`, `cid`, `mac`, `deviceTypeName`, `manuFacturer`, `model`, `deptId`, `deptidName`, `user`, `phone`, `mail`, `reMarks`, `ip`, `doSomething`, `doTime`, `warnLevel`, `ipSw`, `portSw`, `cidSw`, `ipsection`)" +
                " VALUES "
                + "('" + UUIDUtil.get32UUID() + "', 4, '" + device.getCid() + "', '" + device.getMac()+ "', '5', 'vrv', '1', '1004', '总部\\武汉', '张三', '', '', '" + device.getIp() + "', '" + NOW + "', " + getDS() + ", '" + NOW + "', 4, '" + aswitch.getIp() + "', 4, '" + aswitch.getCid() + "', '" + device.getIp().substring(0, device.getIp().lastIndexOf('.') + 1) + "');";
    }

    @Override
    public Boolean call() {
        writeToFile("n4a_audit_pt_done_20200001-");
        return true;
    }

    private int getDS() {
        List<Integer> codeList = Arrays.asList(201, 202, 203);
        return codeList.get(RANDOM.nextInt(codeList.size()));
    }
}

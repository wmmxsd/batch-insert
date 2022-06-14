package file.impl.icssas;

import file.AbstractSqlFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static utils.ColumnValueUtil.RANDOM;
import static utils.ColumnValueUtil.ip;

public class Patch extends AbstractSqlFile {
    private final String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    public Patch(Integer index) {
        super(index);
    }

    @Override
    public String generateSql() {
        return "INSERT INTO `icssas_patch" +
                "` (`patchName`, `patchType`, `cve`, `cwe`, `cnvd`, `cnnvd`, `riskLevel`, `patchDescription`, `discoverTime`, `assetIp`, `solution`)" +
                " VALUES " +
                "('1BSD-获得TCP/IP实现中ip_input.c拒绝服务（崩溃或者挂起）漏洞', '输入验证', 'CVE-1999-" + RANDOM.nextInt(100)+ "', 'CWE-20', 'CNVD-2017-02512', 'CNNVD-199912-105', 1, '11BSD-获得TCP/IP实现中的ip_input.c存在漏洞，远程攻击者利用该漏洞通过制作数据包导致拒绝服务（崩溃或者挂起）。', '" + now + "', '" + ip() + "', '');";
    }

    @Override
    public Boolean call() {
        writeToFile("icssas_patch-");
        return true;
    }
}

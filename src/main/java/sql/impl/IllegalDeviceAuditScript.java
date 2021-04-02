package sql.impl;

import sql.Script;

public class IllegalDeviceAuditScript implements Script {
    @Override
    public Boolean call() {
        System.out.println("执行文件名前缀为n4a_audit_pt_violateaccess-的脚本：begin");
        runScript("n4a_audit_pt_violateaccess-");
        System.out.println("执行文件名前缀为n4a_audit_pt_violateaccess-的脚本：end");
        return true;
    }
}

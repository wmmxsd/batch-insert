package sql.impl;

import sql.Script;

public class SwitchOnlineAndOfflineAuditScript implements Script {
    @Override
    public Boolean call() {
        System.out.println("执行文件名前缀为n4a_audit_sw_done_20200001-的脚本：begin");
        runScript("n4a_audit_sw_done_20200001-");
        System.out.println("执行文件名前缀为n4a_audit_sw_done_20200001-的脚本：end");
        return true;
    }
}
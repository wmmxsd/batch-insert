package file.impl.icssas;

import file.AbstractSqlFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static file.BatchConfig.ip;
import static file.BatchConfig.ipList;

public class DatabaseAlarm extends AbstractSqlFile {
    private final Random random = new Random();

    public DatabaseAlarm(Integer index) {
        super(index);
    }

    @Override
    public String generateSql() {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return "INSERT INTO `icssas_alarm_database`" +
                "(`firstTime`, `currentOccurrenceTime`, `clientIp`, `databaseIp`, `riskLevel`, `resourceName`, `dbUserName`, `actionObject`, `sql_type`, `sql_content`, `counts`, `details`)" +
                " VALUES " +
                "('" + now + "', '" + now + "', '" + ip() + "', '" + ip() + "', " + random.nextInt(5) + ", 'mysql', 'root', '1', 'USE', 'use test', 26, '终端:0.0.200.140账号:root数据库IP:192.168.19.8数据库:mysql内容:use test');";
    }

    //INSERT INTO `icssas`.`icssas_alarm_database`(`id`, `firstTime`, `currentOccurrenceTime`, `clientIp`, `databaseIp`, `riskLevel`, `resourceName`, `dbUserName`, `actionObject`, `sql_type`, `sql_content`, `counts`, `details`)
    // VALUES
    // (1, '2022-02-24 14:38:11', '2022-03-09 14:38:11', '192.168.96.8', '192.168.19.8', 1, 'mysql', 'root', '1', 'USE', 'use test', 26, '终端:0.0.200.140账号:root数据库IP:192.168.19.8数据库:mysql内容:use test');

    @Override
    public Boolean call() {
        writeToFile("icssas_alarm_database");
        return true;
    }
}

package file.impl.icssas;

import file.AbstractSqlFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static utils.ColumnValueUtil.*;

public class Event extends AbstractSqlFile {
    private final String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    private final String dateNow = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

    private AtomicInteger id = new AtomicInteger(1);

    private String sql = "INSERT INTO `icssas_event_" + dateNow +
            "` (`id`, `infoType`, `threatType`, `attackType`, `level`, `sourceIp`, `sourceMac`, `sourcePort`, `desIp`, `desMac`, `desPort`, `protocol`, `alarmTime`, `assetIp`, `content`, `remarks`, `status`)" +
            " VALUES " +
            "(";

    public Event(Integer index) {
        super(index);
    }

    @Override
    public String generateSql() {
        return getRandomSql(RANDOM.nextInt(7));
    }

    @Override
    public Boolean call() {
        writeToFile("icssas_event-");
        return true;
    }

    private String getRandomSql(int random) {
        String _sql = sql;
        String sIp = ip();
        String dIp = ip();
        int sPort = (int) port();
        int dPort = (int) port();
        switch (random) {
            case 0:
                _sql +=  id.getAndIncrement() + ", '工控卫士-操作系统基线', '篡改', '操作系统基线', 3, '" + sIp + "', 'C8-5B-76-9C-CB-EC', NULL, '', '', NULL, '', '" + now + "', '" + dIp + "', '" + now + " ip为" + sIp + "的终端执行了名为操作系统基线的基线模板，共执行0条，失败条', '', 0);";
                break;
            case 1:
                _sql +=  id.getAndIncrement() + ", '工控卫士-可信白名单', '越权或滥用', '可信白名单', 3, '" + sIp + "', '50-7B-9D-21-4B-7E', NULL, '', '', NULL, '', '" + now + "', '" + dIp + "', '" + now + " ip为" + sIp+ "的终端违规运行C:\\\\Program Files (x86)\\\\Tencent\\\\qqmusic\\\\QQMusic.exe', '', 0);";
                break;
            case 2:
                _sql +=  id.getAndIncrement() + ", '网闸-工业协议深度检测告警', '篡改', '深度检测告警', 5, '" + sIp + "', 'C8-5B-76-9C-CB-EC', " + sPort + ", '" + dIp + "', '', " + dPort + ", 'ModbusTcp', '" + now + "', '" + sIp + "', '" + now + " 对22.22.22.22:7160-22.22.22.33:502-TCP,ModbusTcp进行了检测，发现地址不能访问,序列号为279, 读保持寄存器地址0-9*', '', 0);";
                break;
            case 3:
                _sql +=  id.getAndIncrement() + ", '入侵检测系统-入侵日志', '网络攻击', 'HTTP_XSS_Attack', 1, '" + sIp + "', 'C8-5B-76-9C-CB-EC', " + sPort + ", '" + dIp + "', '', " + dPort + ", '', '" + now + "', '" + sIp + "', '" + now + " " + sIp + "对" + dIp + "进行了HTTP_XSS_Attack网络攻击', '', 0);";
                break;
            case 4:
                _sql +=  id.getAndIncrement() + ", '工控防火墙-攻击告警', '网络攻击', 'attacklog-synflood-', 3, '" + sIp + "', 'C8-5B-76-9C-CB-EC', " + sPort + ", '" + sIp + "', '', " + dPort + ", '', '" + now + "', '" + sIp + "', '" + now + " " + sIp + "对" + dIp + "进行了attacklog-synflood-网络攻击', '', 0);";
                break;
            case 5:
                _sql +=  id.getAndIncrement() + ", '公安卫士-网络访问控制', '越权或滥用', '网络访问控制', 3, '" + sIp + "', 'C8-5B-76-9C-CB-EC', " + sPort + ", '" + sIp + "', '', " + dPort + ", '', '" + now + "', '" + sIp + "', '" + now + " ip为" + sIp + "的终端违规进行了网络访问：" + sIp + ":" + sPort+ "->" + dIp + ":" +  dPort + "', '', 0);";
                break;
            case 6:
                _sql +=  id.getAndIncrement() + ", '工业隔离与单导系统-关键字告警', '越权或滥用', '关键字告警', 3, '" + sIp + "', 'C8-5B-76-9C-CB-EC', " + sPort + ", '" + sIp + "', '', " + dPort + ", 'HTTP', '" + now + "', '" + sIp + "', '" + now + " " + sIp + "对" + dIp + "进行了attacklog-synflood-网络攻击', '', 0);";
                break;
            default:
                throw new IllegalArgumentException();
        }
        return _sql;
    }
}

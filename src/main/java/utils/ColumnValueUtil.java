package utils;

import file.BatchConfig;
import model.Device;
import model.Switch;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ColumnValueUtil extends BatchConfig {
    public static final Random RANDOM = new Random();
    public static List<String> ipList = new ArrayList<>();
    public static final List<Device> DEVICE_LIST = new ArrayList<>();
    public static final List<Switch> SWITCH_LIST = new ArrayList<>();

    public static String ip() {
        if (ipList.size() == 0) {
            try {
                dataSource = DuridConfig.buildDataSource();
                Connection connection = dataSource.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet resultSet = stmt.executeQuery("select ip from icssas.icssas_asset_info");
                while (resultSet.next()) {
                    ipList.add(resultSet.getString("ip"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ipList.get(new Random().nextInt(ipList.size()));
    }

    public static String getRandomIp() {
        return 192 + "." + 168 + "." + RANDOM.nextInt(255) + "." + RANDOM.nextInt(255);
    }

    public static double port() {
        return Math.floor(Math.random() * 100 + 1);
    }

    public static Device getDevice() {
        if (DEVICE_LIST.size() == 0) {
            synchronized (DEVICE_LIST) {
                if (DEVICE_LIST.size() == 0) {
                    try {
                        dataSource = DuridConfig.buildDataSource();
                        Connection connection = dataSource.getConnection();
                        Statement stmt = connection.createStatement();
                        ResultSet resultSet = stmt.executeQuery("select a.deviceType, a.authType, b.cid, b.mac, a.deptId, b.ip from n4a_asset_pt a inner join n4a_asset_pt_extra b on (a.authType = 4 AND a.cid = b.cid) where b.ip is not null limit 500");
                        while (resultSet.next()) {
                            Device device = new Device();
                            device.setDeviceType(resultSet.getInt("deviceType"));
                            device.setAuthType(resultSet.getInt("authType"));
                            device.setCid(resultSet.getString("cid"));
                            device.setMac(resultSet.getString("mac"));
                            device.setDeptId(resultSet.getString("deptId"));
                            device.setIp(resultSet.getString("ip"));
                            DEVICE_LIST.add(device);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }

        return DEVICE_LIST.get(new Random().nextInt(DEVICE_LIST.size()));
    }

    public static Switch getSwitch() {
        if (SWITCH_LIST.size() == 0) {
            synchronized (SWITCH_LIST) {
                if (SWITCH_LIST.size() == 0) {
                    try {
                        dataSource = DuridConfig.buildDataSource();
                        Connection connection = dataSource.getConnection();
                        Statement stmt = connection.createStatement();
                        ResultSet resultSet = stmt.executeQuery("select a.cid, a.mac, a.deptId, b.ip from n4a_asset_sw a inner join n4a_asset_sw_extra b on (a.cid = b.cid) where b.ip is not null limit 500");
                        while (resultSet.next()) {
                            Switch aSwitch = new Switch();
                            aSwitch.setCid(resultSet.getString("cid"));
                            aSwitch.setMac(resultSet.getString("mac"));
                            aSwitch.setDeptId(resultSet.getString("deptId"));
                            aSwitch.setIp(resultSet.getString("ip"));
                            SWITCH_LIST.add(aSwitch);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return SWITCH_LIST.get(new Random().nextInt(SWITCH_LIST.size()));
    }
}

package file;

import utils.DuridConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class BatchConfig {
    public static int SQL_COUNTS_IN_FILE = 20000;
    public static int CIRCLE = 20;
    public static String DIRECTORY_PATH = "E:\\test\\";

    public static DataSource dataSource;
    public static Semaphore semaphore = new Semaphore(Runtime.getRuntime().availableProcessors());

    public static List<String> ipList = new ArrayList<>();

    static {
        try {
            if ("\n".equals(System.lineSeparator())) {
                DIRECTORY_PATH = "/usr/ASN/sql-insert/";
            }
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

    public static String ip() {
        return ipList.get(new Random().nextInt(ipList.size()));
    }
}

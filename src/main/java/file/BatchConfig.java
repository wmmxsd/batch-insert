package file;

import utils.DuridConfig;

import javax.sql.DataSource;
import java.util.concurrent.Semaphore;

public class BatchConfig {
    public static int SQL_COUNTS_IN_FILE = 500;
    public static int CIRCLE = 100;
    public static String DIRECTORY_PATH = "/usr/ASN/sql-insert/";
    /*public static int SQL_COUNTS_IN_FILE = 500;
    public static int CIRCLE = 1000;
    public static String DIRECTORY_PATH = "E:\\test\\";*/

    public static DataSource dataSource;
    public static Semaphore semaphore = new Semaphore(30);

    static {
        try {
            dataSource = DuridConfig.buildDataSource();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package file;

import javax.sql.DataSource;
import java.util.concurrent.Semaphore;

public class BatchConfig {
    public static int SQL_COUNTS_IN_FILE = 500;
    public static int CIRCLE = 20000;
    public static String DIRECTORY_PATH = "E:\\test\\";

    public static DataSource dataSource;
    public static Semaphore semaphore = new Semaphore(100);

    static {
        if ("\n".equals(System.lineSeparator())) {
            DIRECTORY_PATH = "/usr/sql-insert/";
        }
    }
}

package file;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;

public interface SqlFile extends Callable<Boolean> {
    String NOW = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    String generateSql();

    void writeToFile(String fileName);
}

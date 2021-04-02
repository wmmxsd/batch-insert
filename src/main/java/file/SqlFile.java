package file;

import java.util.concurrent.Callable;

public interface SqlFile extends Callable<Boolean> {
    String generateSql();

    void writeToFile(String fileName);
}

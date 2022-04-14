package file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.stream.IntStream;

import static file.BatchConfig.DIRECTORY_PATH;
import static file.BatchConfig.SQL_COUNTS_IN_FILE;

public abstract class AbstractSqlFile implements SqlFile {
    private final Integer index;

    public AbstractSqlFile(Integer index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public void writeToFile(String fileName) {
        Path path = null;
        String sqlFileName = null;
        try {
            sqlFileName = DIRECTORY_PATH + fileName + index + ".sql";
            System.out.println(Thread.currentThread().getName() + " : " + SQL_COUNTS_IN_FILE + "行sql写入" + sqlFileName + "：begin...");
            Files.deleteIfExists(Paths.get(sqlFileName));
            path = Files.createFile(Paths.get(sqlFileName));

        } catch (IOException e) {
            e.printStackTrace();
        }
        Path finalPath = path;
        IntStream.range(0, SQL_COUNTS_IN_FILE).forEach(count -> {
            try {
                assert finalPath != null;
                Files.write(finalPath, Collections.singletonList(generateSql()), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println(Thread.currentThread().getName() + " : " + SQL_COUNTS_IN_FILE + "行sql写入" + sqlFileName + "：successful");
    }
}
